package com.Demo.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.Demo.Selenium.base.SeleniumBase;
import com.Demo.utils.DriverInitialize;
import com.nss.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//Constructor
public class GmailLogin extends SeleniumBase {
	DriverInitialize DriverInitialize = new DriverInitialize();
	protected RemoteWebDriver currentdriver;
	protected ExtentTest test;
	public GmailLogin(RemoteWebDriver driver, ExtentTest test) {
		super(driver, test);
		this.currentdriver =driver;
		this.test=test;
	}

	
	
	// Element list

	String mainframe = "mainFrame";
	String Enterusername = "//input[@id='txtUsername']";
	String EnterPassword = "//input[@id='txtPassword']";
	String LoginButton = "//input[@id='btnLogin']";
	String url = "https://s2.demo.opensourcecms.com/orangehrm/symfony/web/index.php/auth/login";
	String Firstname,Lastname;
	

	public GmailLogin NavigateURL(String Url) {
		try {
		currentdriver.navigate().to(Url);
	    ReportLog("info", "Successfully navigated to the Ornage HRM page"+Url+"</b></font>");
		Thread.sleep(5000);
		}
		catch(Exception e) {
		ReportLog("error", "website  is Down");
		DriverInitialize.extent.flush();
		System.exit(1);
		}
		return this;	
	}
/*		
		public GmailLogin enterUsername(String username) throws InterruptedException {
			try {
			WebElement ele = findelement("xpath", "//input[@id='txtUsername']" );
			ele.sendKeys(username);
			ReportLog("info", "Username Entered In the Username Input Field:<font color='blue'><b>"+username+"</b></font>");
			return this;
			}
			catch(Exception e) {
			ReportLog("fail", "Unable to enter username");
			return null;
			}
			
		}
*/
	// Enter user name in username field
		public GmailLogin enterUsername(String username) throws InterruptedException {
			try {
			//click("//input")
				boolean verifyname = Verifyelementpresent("name", "txtUsername", 10);
				if(verifyname) {
					Clear(findelement("name", "txtUsername"));
					SendKeys(findelement("name", "txtUsername"),username);
					ReportLog("info", "Username Entered In the Username Input Field:<font color='blue'><b>"+username+"</b></font>");	
				}
				return this;
			}
			catch(Exception e) {
			ReportLog("fail", "Unable to enter username");
			return null;
			}
			
		}
	
/*	
		public GmailLogin enterPassword(String password) throws InterruptedException {
			try {
			WebElement ele = findelement("xpath", "//input[@id='txtPassword']" );
			ele.sendKeys(password);
			ReportLog("info", "Password Entered In the Password Input Field");
			return this;
			}
			catch(Exception e) {
			ReportLog("fail", "Unable to enter password");
			return null;
			}
			
		}
*/
		
		public GmailLogin enterPassword(String password) throws InterruptedException {
			try {
				boolean verifypass = Verifyelementpresent("name", "txtPassword", 10);
				if(verifypass) {
					Clear(findelement("name", "txtPassword"));
					SendKeys(findelement("name", "txtPassword"),password);
					ReportLog("info", "Password Entered In the Password Input Field");	
				}
				else {
					ReportLog("fail", "unable to enter the password");
					
				}
				return this;
			}
			catch(Exception e) {
			ReportLog("fail", "Unable to enter password");
			return null;
			}
			
		}
		
/*
		public GmailLogin clickloginbutton() throws InterruptedException {
			try {
			//Click(findelement("id", "LoginButton"));
			WebElement ele = findelement("xpath", "//input[@id='btnLogin']" );
			ele.click();
			//Click(findelement("xpath", "//input[@id='btnLogin']"));
			Thread.sleep(5000);
			ReportLog("info", "Successfully logged into the website");
			return this;
			}
			catch(Exception e) {
			ReportLog("fail", "Unable to click login button");
			return null;
			}
			
		}
	*/
		
		public GmailLogin clickloginbutton() throws InterruptedException {
			try {
				boolean verifyloginbutton = Verifyelementpresent("id", "btnLogin", 10);
				if(verifyloginbutton) {
					Click(findelement("id", "btnLogin"));
					Thread.sleep(2000);
					ReportLog("pass", "Successfully logged into the website");
				}
				else {
					ReportLog("fail", "Unable to Login");
				}
				//Click(findelement("xpath", "//input[@id='btnLogin']"));
				return this;
				}
				catch(Exception e) {
				ReportLog("fail", "Unable to click login button");
				return null;
				}
			
			}
		}

