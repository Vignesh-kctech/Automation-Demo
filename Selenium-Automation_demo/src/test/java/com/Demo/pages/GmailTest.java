package com.Demo.pages;

import com.Demo.*;
import com.Demo.pages.HomePage;



import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.Demo.pages.GmailLogin;
import com.Demo.utils.DriverInitialize;


public class GmailTest extends DriverInitialize{
	DriverInitialize DriverInitialize =  new DriverInitialize();
	Map<String, String> EnvironmentValuesDatas = new LinkedHashMap<String, String>();
	Map<String,JSONArray> Mapvalues = new LinkedHashMap<String,JSONArray>();
	String Url,Username,Password;
	//tc001
	@Test(groups = { "Regression"} , dataProvider="fetchData",alwaysRun=true)
	public void NSS_OrangeHRM(Map<String,String> map) throws Exception {
		GmailLogin lp = new GmailLogin(driver, test);
		HomePage hp = new HomePage(driver, test);
		AddLocation loc = new AddLocation(driver,test);
		//VerifyLocation vl = new VerifyLocation(driver,test);
		
		EnvironmentValuesDatas = DriverInitialize.ReadCurrentQueryData();
		
		DriverInitialize.setQueryName(EnvironmentValuesDatas.get("SelectQueryName_Login"));
		Mapvalues = DriverInitialize.ReadCurrentTestData();
		
		for (Entry<String, JSONArray> entry : Mapvalues.entrySet()) {
		 for(Object o:entry.getValue()) {
			Username =((JSONObject) o).get("username").toString();   
			Password =((JSONObject) o).get("password").toString(); 
			
			}
		}
		
			try {
			Url = "https://s2.demo.opensourcecms.com/orangehrm/symfony/web/index.php/auth/login"; 
			lp.NavigateURL(Url)
			.enterUsername(Username)
			.enterPassword(Password)
			.clickloginbutton();
			hp.movetoelement();
			//loc.EnterDetails();
			//loc.Verify();
			//vl.Verify();
		
			
			
		}
		catch (Exception e ) {
			return;
		}
	}
	
}
