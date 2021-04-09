package com.Demo.utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
public class JsonDataRead  {
	@SuppressWarnings("unchecked")
	public Map<String, LinkedHashMap<String, String>> EnvironmentValues() throws IOException {
	Map<String, LinkedHashMap<String, String>> EnvValuesMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
	ObjectMapper mapper = new ObjectMapper();
	InputStream file = null;
	try {
		file = new FileInputStream("./DataFiles/EnvironmentTestData.json");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	Map<String, Object> jsonMaps = null;
	try {
		jsonMaps = mapper.readValue(file, new TypeReference<Map<String, Object>>() {});
	} catch (JsonParseException e) {
		e.printStackTrace();
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	ArrayList<LinkedHashMap<String, String>> value = new ArrayList<LinkedHashMap<String, String>>();
	value =(ArrayList<LinkedHashMap<String, String>>) jsonMaps.get("Orange");
	
	for(int i=0;i<=value.size()-1;i++) {
		EnvValuesMap.put("dataset_"+i, value.get(i));
	}

	System.out.println(EnvValuesMap);
	return EnvValuesMap;
}
	
	//read database config json
	@SuppressWarnings("unchecked")
	public Map<String, String> readDatabaseconfigJsonFile(String KeyName) throws IOException {
	Map<String, String> EnvValuesMap = new LinkedHashMap<String, String>();
	ObjectMapper mapper = new ObjectMapper();
	InputStream file = null;
	try {
		file = new FileInputStream("./DataFiles/DataBaseConfig.json");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	Map<String, Object> jsonMaps = null;
	try {
		jsonMaps = mapper.readValue(file, new TypeReference<Map<String, Object>>() {});
	} catch (JsonParseException e) {
		e.printStackTrace();
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	EnvValuesMap.putAll((Map<String,String>) jsonMaps.get(KeyName));
	return EnvValuesMap;
}
}
