package com.Demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.Demo.Selenium.base.SeleniumBase;
import com.Demo.utils.DriverInitialize;
import com.relevantcodes.extentreports.ExtentTest;

public class VerifyLocation extends SeleniumBase {
	DriverInitialize DriverInitialize = new DriverInitialize();
	protected RemoteWebDriver currentdriver;
	protected ExtentTest test;
	
	

	public VerifyLocation(RemoteWebDriver driver, ExtentTest test) {
		super(driver, test);
		this.currentdriver =driver;
		this.test=test;
		
	}
	public VerifyLocation Verify() throws InterruptedException {
		
		// Search by entering location  name
		Click(findelement("id", "searchLocation_name"));
		WebElement ele = findelement("xpath", "//input[@id='searchLocation_name']" );
		ele.sendKeys("Madurai");
		Thread.sleep(1000);
		
		// enter the city details
		Click(findelement("id", "searchLocation_city"));
		WebElement ele1 = findelement("xpath", "//input[@id='searchLocation_city']" );
		ele1.sendKeys("Madurai");
		Thread.sleep(1000);
		
		// select the COuntry from the dropdown
		Click(findelement("id", "searchLocation_country"));
		Select country = new Select(driver.findElement(By.xpath("//select[@id='searchLocation_country']")));
		country.selectByVisibleText("India");
		
		WebElement eleB = findelement("xpath", "//select[@id='searchLocation_country']" );
		eleB.click();
		Thread.sleep(2000);
		
		
		// Search the country 
		
		WebElement ele2 = findelement("xpath", "//input[@id='btnSearch']");
		ele2.click();
		Thread.sleep(3000);
		
		//moving to the checkbox and selecting
		WebElement ele3 = findelement("xpath", "//input[@id='ohrmList_chkSelectAll']");
		ele3.click();
		Thread.sleep(5000);
		
		//Delete when the search result is occured 
		
		WebElement ele4 = findelement("xpath", "//input[@id='btnDelete']");
		ele4.click();
		Thread.sleep(3000);
		
		
		//Handling Popup
		
		//WebElement ele5 = findelement("xpath", "//input[@id='dialogDeleteBtn']");
		//ele5.click();
		
		//Thread.sleep(3000);
		
		
		return this;
		
	}
	

}
