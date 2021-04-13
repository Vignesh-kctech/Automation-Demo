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
			/*boolean verifyname = Verifyelementpresent("id","location_name", 10);
			if(verifyname) {
				Click(findelement("id", "location_name"));
				WebElement ele = findelement("xpath", "//input[@id='location_name']" );
				ele.sendKeys("Madurai");
				Thread.sleep(8000);
				ReportLog("pass", "Location name have been entered Successfully");
			}
			else {
				ReportLog("fail", "Unable to enter the name");
			}
				*/
			
			//For Location Name
			Click(findelement("id", "location_name"));
			boolean location  = Verifyelementpresent("xpath", "//input[@id='location_name']", 10);
			if(location) {
				SendKeys(findelement("id", "location_name"), "Madurai");
				ReportLog("pass", "Location name have been entered Successfully");
				Thread.sleep(1000);	
			}
			else {
				ReportLog("fail", "Unable to enter the location name");
			}
			
			//For Country
			boolean verifycountry = Verifyelementpresent("id","location_country",10);
			if(verifycountry) {
				Click(findelement("id", "location_country"));
				Select country = new Select(driver.findElement(By.xpath("//select[@id='location_country']")));
				country.selectByVisibleText("India");
				ReportLog("pass", "Dropdown is working Fine");	
			}
			else{
				ReportLog("fail","Dropdown box is not Working");

			}
			/*Click(findelement("id", "location_country"));
			Select country = new Select(driver.findElement(By.xpath("//select[@id='location_country']")));
			country.selectByVisibleText("India");
			ReportLog("pass", "Dropdown is working Fine");*/
			
			boolean selectcountry = Verifyelementpresent("xpath", "//select[@id='location_country']",10);
			if(selectcountry){
				Click("xpath","//select[@id='location_country']");
				ReportLog("pass", "From dropdown,India is selected");
			}
			else{
				ReportLog("fail", "Unable to select The values");
			}
			
				
			/*WebElement eleB = findelement("xpath", "//select[@id='location_country']" );
			eleB.click(); */
			
			
			// For State
			boolean verifystate = Verifyelementpresent("xpath","//input[@id='location_province']",10);
			if(verifystate) {
				Click(findelement("id", "location_province"));
				SendKeys(findelement("xpath", "//input[@id='location_province']"), "Tamil nadu");
				ReportLog("pass", "State name have been entered Successfully");
			}
			else{
				ReportLog("fail", "Unable to enter the value");
			}
			
			
			//For city
			Click(findelement("id", "location_city"));
			WebElement ele3 = findelement("xpath", "//input[@id='location_city']");
			ele3.sendKeys("Madurai");
			ReportLog("pass", "city name have been entered Successfully");
			
			//For Address
			Click(findelement("id", "location_address"));
			WebElement ele4 = findelement("xpath", "//textarea[@id='location_address']");
			ele4.sendKeys("New Street");
			ReportLog("pass", " Address have been  entered Successfully");
			
			//For Zipcode
			Click(findelement("id", "location_zipCode"));
			WebElement ele5 = findelement("xpath", "//input[@id='location_zipCode']");
			ele5.sendKeys("456321");
			ReportLog("pass", "zipcode have been entered Successfully");
			
			//For Phone
			Click(findelement("id", "location_phone"));
			WebElement ele6 = findelement("xpath", "//input[@id='location_phone']");
			ele6.sendKeys("9556866677");
			Thread.sleep(2000);
			ReportLog("pass", "Phone number name have been entered Successfully");
	
			
			//For Saving the Location
			
			WebElement ele7 = findelement("xpath", "//input[@id='btnSave']" );
			ele7.click();
			Thread.sleep(5000);
			ReportLog("pass", "New Location details have been entered and saved");
			
			
			//Verification
			lo = "//span[contains(text(),'Already exists')]";
			
			if(lo.contentEquals(lo)){
			
				WebElement we = findelement("xpath", "//input[@id='btnCancel']");
				we.click();
			
				Thread.sleep(2000); 
				ReportLog("info", "Location Already Exists");
				
			}
			else {
				WebElement ele8 = findelement("xpath", "//input[@id='btnSave']" );
				ele8.click();
				Thread.sleep(5000);
				ReportLog("fail", "Form have Been Saved Successfully");
			}

			return this;
		
			}
			
			catch(Exception e) {
			ReportLog("fail", "Unable to enter the value");	
			
			}
		return null;
		
	}
	
	private void Click(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	public AddLocation Verify() throws InterruptedException {
		try {
		// Search by entering location  name
		Click(findelement("id", "searchLocation_name"));
		WebElement ele = findelement("xpath", "//input[@id='searchLocation_name']" );
		ele.sendKeys("Madurai");
		Thread.sleep(1000);
		ReportLog("pass", "Location name  have Been Entered Successfully");
		
		// enter the city details
		Click(findelement("id", "searchLocation_city"));
		WebElement ele1 = findelement("xpath", "//input[@id='searchLocation_city']" );
		ele1.sendKeys("Madurai");
		Thread.sleep(1000);
		ReportLog("pass", "City name have Been Entered Successfully");
		
		// select the COuntry from the dropdown
		Click(findelement("id", "searchLocation_country"));
		Select country = new Select(driver.findElement(By.xpath("//select[@id='searchLocation_country']")));
		country.selectByVisibleText("India");
		ReportLog("pass", "Value have Been Selected Successfully From the DropDown");
		
		WebElement eleB = findelement("xpath", "//select[@id='searchLocation_country']" );
		eleB.click();
		Thread.sleep(2000);
		ReportLog("info", "The value have been clicked");
		
		// Search the country 
		
		WebElement ele2 = findelement("xpath", "//input[@id='btnSearch']");
		ele2.click();
		Thread.sleep(3000);
		ReportLog("info", "Search buttton have been clicked");
		
		//moving to the checkbox and selecting
		WebElement ele3 = findelement("xpath", "//input[@id='ohrmList_chkSelectAll']");
		ele3.click();
		Thread.sleep(5000);
		ReportLog("pass", "Checkbox have Been Checked Successfully based on the Search results");
		
		//Delete when the search result is occured 
		
		WebElement ele4 = findelement("xpath", "//input[@id='btnDelete']");
		ele4.click();
		Thread.sleep(3000);
		ReportLog("info", "Delete Button have Been Clicked");
		
		
		//Handling Popup
		
		WebElement ele5 = findelement("xpath", "//input[@id='dialogDeleteBtn']");
		ele5.click();
		
		Thread.sleep(3000);
		ReportLog("pass", "Popup have Been handled Successfully");
		
		
		return this;
		}
		catch(Exception e){
			ReportLog("fail", "Unable to enter the value");	
			
		}
		return null;
		
	}
	

	
}
