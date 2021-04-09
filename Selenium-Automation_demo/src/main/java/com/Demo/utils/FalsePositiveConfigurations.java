package com.Demo.utils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;

public class FalsePositiveConfigurations {
   public static Connection conn;
   public static Map<String, String> EnvironmentValues = new LinkedHashMap<String, String>();
   
 //read data for db config
 	public static Map<String, String> ReadDbConfigData(String KeyName) throws IOException {
 	EnvironmentValues =new JsonDataRead().readDatabaseconfigJsonFile(KeyName);
 	return EnvironmentValues;
 	}
   public static ResultSet RetrieveData(String queryname) throws Exception {
	   ReadDbConfigData("DbCredentials");
	   System.out.println("Log data");
	   System.out.println(EnvironmentValues);
	   try  {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://"+EnvironmentValues.get("HostName")+":"+EnvironmentValues.get("Port")+"/?useUnicode=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",""+EnvironmentValues.get("DbUsername")+"",""+EnvironmentValues.get("DbuserPassword")+"");
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		}
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(queryname);
      return rs;
   }
   
  @SuppressWarnings("unchecked")
  public static Map<String,JSONArray> ReadCurrentData(String queryname) throws Exception {
	 JSONObject obj1 = new JSONObject();
     JSONArray jsonArray = new JSONArray();
     ArrayList<String> data = new ArrayList<String>();
     Map<String,JSONArray> mapval = new LinkedHashMap<String,JSONArray>();
     
     String path ="./DataFiles/EnvironmentTestData.json";
     ResultSet rs = RetrieveData(queryname);
     ObjectMapper mapper = new ObjectMapper();
     System.out.println("Json read");
     
     try {
         
         ArrayList<String> columnNames = new ArrayList<String>();
         if (rs != null) {
             ResultSetMetaData columns = rs.getMetaData();
             int i = 0;
             while (i < columns.getColumnCount()) {
                 i++;
                 columnNames.add(columns.getColumnName(i));
             }
             while (rs.next()) {
                 JSONObject obj = new JSONObject();
                 
                 System.out.println("test");
                 for (i = 0; i < columnNames.size(); i++) {
                     data.add(rs.getString(columnNames.get(i)));
                     {
                         for (int j = 0; j < data.size(); j++) {
                             if (data.get(j) != null) {
                                 obj.put(columnNames.get(i), data.get(j));
                                 System.out.println(columnNames.get(i)+" ,"+data.get(j));
                             }else {
                                 obj.put(columnNames.get(i), "");
                             }
                         }
                     }
                 }
                 
                 jsonArray.add(obj);
                 obj1.put("Orange", jsonArray);
                 mapval.put("Orange", jsonArray);
                 
                 Object json = mapper.readValue(obj1.toJSONString(), Object.class);
                 String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
				 FileWriter file = new FileWriter(path);
                 file.write(indented);
                 file.flush();
                 file.close();
             }
             
         } else {
             JSONObject obj2 = new JSONObject();
             obj2.put(null, null);
             jsonArray.add(obj2);
             obj1.put("Orange", jsonArray);
         }
     return mapval;
     } catch (SQLException e) {
         e.printStackTrace();
         return mapval;
     } catch (IOException e) {
         e.printStackTrace();
         return mapval;
     } finally {
         if (conn != null) {
             try {
                 conn.close();
                 rs.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }
 }
  
  @SuppressWarnings("unchecked")
	public static Map<String, String> readDatabaseconfigJsonFile(String KeyName) throws IOException {
	Map<String, String> EnvValuesMap = new LinkedHashMap<String, String>();
	ObjectMapper mapper = new ObjectMapper();
	InputStream file = null;
	try {
		file = new FileInputStream("./DataFiles/EnvironmentTestData.json");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	Map<String, Object> jsonMaps = null;
	try {
		jsonMaps = mapper.readValue(file, HashMap.class);
	} catch (JsonParseException e) {
		e.printStackTrace();
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	System.out.println(jsonMaps.values());
	for(Object val:jsonMaps.values()) {
	}
	
	EnvValuesMap.putAll((Map<String, String>) jsonMaps.get("Orange"));
	System.out.println(EnvValuesMap);
	return EnvValuesMap;
}
//  public static void main(String args[]) throws IOException {
//	  readDatabaseconfigJsonFile("NSS");
//  }
  
  
  


}