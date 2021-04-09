package com.Demo.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.collections4.map.HashedMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Databaseconfig {
	public static Map<String, String> EnvironmentValues = new LinkedHashMap<String, String>();
	public static Connection conn;
	//read data for db config
	public Object[][] ReadDbConfigData(String KeyName) throws IOException {
	EnvironmentValues =new JsonDataRead().readDatabaseconfigJsonFile(KeyName);
	System.out.println(EnvironmentValues);
	return new Object[][]{
          {EnvironmentValues}
      };
	}
	// create database connection
	@SuppressWarnings({ "finally" })
	public Connection createDatabaseConnection()throws ClassNotFoundException, SQLException {
		try  {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Test123#");
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return conn;	
		}		
	}
	//select query execution
	public TreeMap ReadSqlvalues(String Queryname) throws SQLException, JsonGenerationException, JsonMappingException, IOException{
//		Class<?> enclosingClass = getClass().getEnclosingClass();
//		String classname = enclosingClass.getName();
		ResultSet resultSet=null;
		Map<String, Map<Object,Object>> outermap = new HashMap();
		Statement stmt = conn.createStatement();
		resultSet = stmt.executeQuery(Queryname);
		ResultSetMetaData md = resultSet.getMetaData();
		  int columns = md.getColumnCount();
		  HashMap row = new HashMap();
		  while (resultSet.next()){
		     row = new HashMap(columns);
		     for(int i=1; i<=columns; ++i){  
		      row.put(md.getColumnName(i),resultSet.getObject(i));
		      outermap.put("dataset_"+i, row);
		     }
		     
		  }
		 System.out.println(outermap);
		 TreeMap FinalMap = new TreeMap();
		 int i=1;
		 for (Entry<String, Map<Object, Object>> entry : outermap.entrySet()) {
			 FinalMap.put("Databaseconfig_"+i, entry.getKey()+":"+entry.getValue());
		     System.out.println(entry.getKey() + "/" + entry.getValue());
		     i+=1;
		 }i=0;
		 System.out.println(FinalMap);
		 ObjectMapper mapper = new ObjectMapper();
		 mapper.writeValue(new File("/Users/kctechappium/eclipse-workspace/Selenium-Automation_demo/DataFiles/TestData.json"), FinalMap);
		 return FinalMap;
		
	}
	public static void main(String args[]) throws IOException, SQLException, ClassNotFoundException {
		Databaseconfig Databaseconfig =new Databaseconfig();
		Databaseconfig.ReadDbConfigData("DbCredentials");
		Databaseconfig.createDatabaseConnection();
		Databaseconfig.ReadDbConfigData("Sql Query");
		Databaseconfig.ReadSqlvalues(EnvironmentValues.get("SelectQueryName_Login"));
		
		//System.out.println(EnvironmentValues.get("SelectQueryName_Login"));
	}
}
