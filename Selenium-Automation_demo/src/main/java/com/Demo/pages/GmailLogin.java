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
	
	public GmailLogin WindowNavigate() {
		try {
		Set<String> windowHandles = driver.getWindowHandles();
	    List<String> windowStrings = new ArrayList<>(windowHandles);
	    String reqWindow = windowStrings.get(1);
	    currentdriver.switchTo().window(reqWindow);	
	    Thread.sleep(2000);
	    ReportLog("info", "Navigated to New window with title of:"+currentdriver.getTitle());
	    return this;
		}
		catch(Exception e) {
		ReportLog("fail", "Unable to Navigate New Window");
		DriverInitialize.extent.flush();
		System.exit(1);
		return null;
		}}
		
		public GmailLogin enterUsername(String username) throws InterruptedException {
			try {
			//click("//input")
			//Clear(findelement("xpath", "//input[@id='txtUsername']"));
			WebElement ele = findelement("xpath", "//input[@id='txtUsername']" );
			ele.sendKeys(username);		
			//SendKeys(findelement("name", "//input[@id='txtUsername']"),username);
			ReportLog("info", "Username Entered In the Username Input Field:<font color='blue'><b>"+username+"</b></font>");
			return this;
			}
			catch(Exception e) {
			ReportLog("fail", "Unable to enter username");
			return null;
			}
			
		}
		public GmailLogin enterPassword(String password) throws InterruptedException {
			try {
			//Clear(findelement("xpath", "EnterPassword"));
			WebElement ele = findelement("xpath", "//input[@id='txtPassword']" );
			ele.sendKeys(password);
			//SendKeys(findelement("xpath", "//input[@id='txtPassword']"),password);
			ReportLog("info", "Password Entered In the Password Input Field");
			return this;
			}
			catch(Exception e) {
			ReportLog("fail", "Unable to enter password");
			return null;
			}
			
		}
		public GmailLogin clickloginbutton() throws InterruptedException {
			try {
			//Click(findelement("id", "LoginButton"));
			WebElement ele = findelement("xpath", "//input[@id='btnLogin']" );
			ele.click();
			//Click(findelement("xpath", "//input[@id='btnLogin']"));
			Thread.sleep(5000);
			return this;
			}
			catch(Exception e) {
			ReportLog("fail", "Unable to click login button");
			return null;
			}
			
		}
		public GmailLogin MainWindowNavigation() {
			try {
			String winHandleBefore = driver.getWindowHandle();
		    currentdriver.switchTo().window(winHandleBefore);
		    Thread.sleep(3000);
		    ReportLog("info", "Navigated to New window with title of:"+currentdriver.getTitle());
			return this;
			
			}catch (Exception e) {
			ReportLog("fail", "Window navigation failed !!!");
			return null;
			}
		}

	

	
	}

