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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Demo.Selenium.base.SeleniumBase;
import com.Demo.utils.DriverInitialize;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends SeleniumBase {
	DriverInitialize DriverInitialize = new DriverInitialize();
	protected RemoteWebDriver currentdriver;
	protected ExtentTest test;
	public HomePage(RemoteWebDriver driver, ExtentTest test) {
		super(driver, test);
		this.currentdriver =driver;
		this.test=test;
	}
	
	

public  HomePage movetoelement() throws InterruptedException {
	try {
		boolean isEnabled = Verifyelementpresent("xpath","//b[contains(text(),'Admin')]",10);
		if(isEnabled) {
			WebElement ele = findelement("xpath", "//b[contains(text(),'Admin')]" );
			Actions act = new Actions(driver);
			act.moveToElement(ele).perform();
		}
		else {
			ReportLog("info", "Unable to move to the Expected tab");
		}
		Thread.sleep(2000);
		
		// organization tab
		boolean isEnabled1 = Verifyelementpresent("xpath","//div[@class='menu']//a[@id='menu_admin_Organization']",10);
		if(isEnabled1) {
			WebElement ele1 = findelement("xpath", "//div[@class='menu']//a[@id='menu_admin_Organization']");
			Actions act = new Actions(driver);
			act.moveToElement(ele1).perform();
			Thread.sleep(2000);
			ReportLog("pass", "Successfully Moved on to Admin tab");
		}
		else {
			ReportLog("fail", "Unable to move to the specified tab");
		}
		
		
		boolean isEnabled3 = Verifyelementpresent("xpath","//div[@class='menu']//a[@id='menu_admin_viewLocations']",10);
		if(isEnabled3) {
			WebElement ele2 = driver.findElement(By.xpath("//div[@class='menu']//a[@id='menu_admin_viewLocations']"));
			Actions act = new Actions(driver);
			act.moveToElement(ele2).perform();
			ReportLog("pass", "Successfully Moved on to the Organizations tab");
		}
		else {
			ReportLog("fail", "Unable to move to the Organizations Tab");
		}
				
		//organization subtab	
		
		
		//Click action
		boolean isEnabled4 = Verifyelementpresent("xpath","//div[@class='menu']//a[@id='menu_admin_viewLocations']", 10);
		if(isEnabled4) {
			Click(findelement("xpath", "//div[@class='menu']//a[@id='menu_admin_viewLocations']"));
			ReportLog("pass", "Successfully Moved on to the Location tab");
			Thread.sleep(3000);
		}
		else {
			ReportLog("fail", "Unbale to move to the Location tab");
		}
		
		
		boolean isClicked = Verifyelementpresent("xpath","//input[@id='btnAdd']", 5);
		if(isClicked) {
			Click(findelement("xpath", "//input[@id='btnAdd']"));
			ReportLog("pass", "Add button is clicked to add the locations");
			Thread.sleep(3000);	
		}
		else {
			ReportLog("fail", "Unable to click the AddButton");
		}
		return this;
	}
	
	catch(Exception e){
		return null;
		
	}	
	
}
}
