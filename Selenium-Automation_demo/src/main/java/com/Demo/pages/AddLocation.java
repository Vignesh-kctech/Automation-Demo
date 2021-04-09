package com.Demo.pages;

import com.Demo.Selenium.base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import org.openqa.selenium.support.ui.Select;
import com.Demo.utils.DriverInitialize;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class AddLocation extends SeleniumBase {
	DriverInitialize DriverInitialize = new DriverInitialize();
	protected RemoteWebDriver currentdriver;
	protected ExtentTest test;
	private String lo;
	
	public AddLocation(RemoteWebDriver driver, ExtentTest test) {
		super(driver, test);
		this.currentdriver =driver;
		this.test=test;
}
	
	public AddLocation EnterDetails() throws InterruptedException { 
		try {
			//For Location Name
			Click(findelement("id", "location_name"));
			WebElement ele = findelement("xpath", "//input[@id='location_name']" );
			ele.sendKeys("Madurai");
			//Thread.sleep(1000);
			
			
			//For Country
			Click(findelement("id", "location_country"));
			Select country = new Select(driver.findElement(By.xpath("//select[@id='location_country']")));
			country.selectByVisibleText("India");
			
			WebElement eleB = findelement("xpath", "//select[@id='location_country']" );
			eleB.click();
			
			// For State
			Click(findelement("id", "location_province"));
			WebElement ele2 = findelement("xpath", "//input[@id='location_province']");
			ele2.sendKeys("Tamil Nadu");
			
			//For city
			Click(findelement("id", "location_city"));
			WebElement ele3 = findelement("xpath", "//input[@id='location_city']");
			ele3.sendKeys("Madurai");
			
			//For Address
			Click(findelement("id", "location_address"));
			WebElement ele4 = findelement("xpath", "//textarea[@id='location_address']");
			ele4.sendKeys("New Street");
			
			//For Zipcode
			Click(findelement("id", "location_zipCode"));
			WebElement ele5 = findelement("xpath", "//input[@id='location_zipCode']");
			ele5.sendKeys("456321");
			
			//For Phone
			Click(findelement("id", "location_phone"));
			WebElement ele6 = findelement("xpath", "//input[@id='location_phone']");
			ele6.sendKeys("9556866677");
			Thread.sleep(2000);
	
			
			//For Saving the Location
			
			WebElement ele7 = findelement("xpath", "//input[@id='btnSave']" );
			ele7.click();
			Thread.sleep(5000);
			
			
			//Verification
			lo = "//span[contains(text(),'Already exists')]";
			
			if(lo.contentEquals(lo)){
			
				WebElement we = findelement("xpath", "//input[@id='btnCancel']");
				we.click();
			
				Thread.sleep(2000);
				
			}
			else {
				ReportLog("fail", "unable to save the form");
			
			}
			
			
			return this;
		
			}
			
			catch(Exception e) {
			ReportLog("fail", "Unable to enter the value");	
			
			}
		
			
			
			
			
			
		
		return null;
		
		
		
	}
	

	
}
