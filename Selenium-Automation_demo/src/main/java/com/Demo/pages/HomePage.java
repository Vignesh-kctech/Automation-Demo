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
		WebElement ele = findelement("xpath", "//b[contains(text(),'Admin')]" );
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
		Thread.sleep(2000);
		
		// organization tab
		WebElement ele1 = findelement("xpath", "//div[@class='menu']//a[@id='menu_admin_Organization']");
		act.moveToElement(ele1).perform();
		Thread.sleep(2000);
				
		//organization subtab	
		WebElement ele2 = driver.findElement(By.xpath("//div[@class='menu']//a[@id='menu_admin_viewLocations']"));
		act.moveToElement(ele2).perform();
		
		//Click action
		WebElement ele3 = driver.findElement(By.xpath("//div[@class='menu']//a[@id='menu_admin_viewLocations']"));
		ele3.click();
		Thread.sleep(5000);
		
		
		WebElement ele4 = findelement("xpath", "//input[@id='btnAdd']" );
		ele4.click();
		Thread.sleep(3000);
		
		return this;
	}
	
	catch(Exception e){
		return null;
		
	}
	
	
	
	
	
}
}
