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
				Click(findelement("xpath","//select[@id='location_country']"));
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
				ReportLog("fail", "Unable to enter the State Name");
			}
			
			
			//For city
			boolean verifycity = Verifyelementpresent("xpath","//input[@id='location_city']",10);
			if(verifycity) {
				Click(findelement("id", "location_city"));
				SendKeys(findelement("xpath","//input[@id='location_city']"), "Madurai");
				ReportLog("pass","City name have been enetered Successfully");	
			}
			else {
				ReportLog("fail","Unable to enter the City Name");
			}
			
			
		/*	WebElement ele3 = findelement("xpath", "//input[@id='location_city']");
			ele3.sendKeys("Madurai");
			ReportLog("pass", "city name have been entered Successfully"); */
			
			//For Address
			boolean verifyAddress = Verifyelementpresent("xpath","//textarea[@id='location_address']",10);
			if(verifyAddress) {
				Click(findelement("id", "location_address"));
				SendKeys(findelement("xpath","//textarea[@id='location_address']"), "New Street");
				ReportLog("pass", "Address have been entered Successfully");
			}
			else {
				ReportLog("fail", "Unable to enter the Address");
			}
			
			
		/*	Click(findelement("id", "location_address"));
			WebElement ele4 = findelement("xpath", "//textarea[@id='location_address']");
			ele4.sendKeys("New Street");
			ReportLog("pass", " Address have been  entered Successfully"); */
			
			//For Zipcode
			boolean verifyzip = Verifyelementpresent("xpath","//input[@id='location_zipCode']",10);
			if(verifyzip) {
				Click(findelement("id", "location_zipCode"));
				SendKeys(findelement("xpath","//input[@id='location_zipCode']"), "456321");
				ReportLog("pass","Zipcode have been entered Successfully");
			}
			else {
				ReportLog("fail", "Unable to enter the Zipcode");
			}
			
			
		/*	Click(findelement("id", "location_zipCode"));
			WebElement ele5 = findelement("xpath", "//input[@id='location_zipCode']");
			ele5.sendKeys("456321");
			ReportLog("pass", "zipcode have been entered Successfully"); */
			
			//For Phone
			boolean verifyphone = Verifyelementpresent("xpath","//input[@id='location_phone']",10);
			if(verifyphone) {
				Click(findelement("id", "location_phone"));
				SendKeys(findelement("xpath", "//input[@id='location_phone']"),"9556866677");
				Thread.sleep(10000);
				ReportLog("pass","Phone Number have been entered Successfully");
			}
			else {
				ReportLog("fail","Unable to enter the Phone Number");
			}
			
		/*	Click(findelement("id", "location_phone"));
			WebElement ele6 = findelement("xpath", "//input[@id='location_phone']");
			ele6.sendKeys("9556866677");
			Thread.sleep(2000);
			ReportLog("pass", "Phone number name have been entered Successfully"); */
	
			
			//For Saving the Location
	/*		boolean verifylocation = Verifyelementpresent("xpath","//input[@id='btnSave']",10);
			if(verifylocation) {
				Click(findelement("xpath","//input[@id='btnSave']"));
				ReportLog("pass","Location  have been saved Successfully");
				Thread.sleep(5000);
			}
			else {
				ReportLog("fail","Unable to Save the Location");
			} */
			
			WebElement ele7 = findelement("xpath", "//input[@id='btnSave']" );
			ele7.click();
			Thread.sleep(5000);
			ReportLog("pass", "New Location details have been entered and saved"); 
			
			
			//Verification
	/*		boolean verifylocation1 = Verifyelementpresent("xpath","//*[@id=\"frmLocation\"]/fieldset/ol/li[1]/child::span",10);
			if(verifylocation1) { */
			
				lo = "//*[@id=\"frmLocation\"]/fieldset/ol/li[1]/child::span";
				if(lo.contentEquals(lo)){
					
					WebElement we = findelement("xpath", "//input[@id='btnCancel']");
					we.click();
				
					Thread.sleep(5000); 
					ReportLog("info", "Location Already Exists");
					
				}
					else {
					WebElement ele8 = findelement("xpath", "//input[@id='btnSave']" );
					ele8.click();
					Thread.sleep(5000);
					ReportLog("fail", "Form have Been Saved Successfully");
				}
			
			/*	else {
					ReportLog("fail","Unable to Save the Location");
				} */

	 /* ---------------		----------------------------------------------- */
		
			/*	Click(findelement("xpath","//input[@id='btnSave']"));
				ReportLog("pass","Location  have been saved Successfully");
				Thread.sleep(5000);
			}
			else {
				ReportLog("fail","Unable to Save the Location");
			}
			*/
	/*
			
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
			} */
			
		/* --------------------------------------------------------------------------- */
		    
			return this;
		
			}
			
			catch(Exception e) {
			ReportLog("fail", "Unable to enter the value");	
			
			}
		return null;
		
	}
	


	public AddLocation Verify() throws InterruptedException {
		try {
		// Search by entering location  name
		
		boolean verifylocation = Verifyelementpresent("xpath","//input[@id='searchLocation_name']",10);
		if(verifylocation) {
			Click(findelement("id", "searchLocation_name"));
			SendKeys(findelement("xpath","//input[@id='searchLocation_name']"), "Madurai");
			Thread.sleep(1000);
			ReportLog("pass","Location have been entered Successfully");
		}
		else {
			ReportLog("fail","Unable to enter the Location name");
		}
		
	/*	Click(findelement("id", "searchLocation_name"));
		WebElement ele = findelement("xpath", "//input[@id='searchLocation_name']" );
		ele.sendKeys("Madurai");
		Thread.sleep(1000);
		ReportLog("pass", "Location name  have Been Entered Successfully"); */
		
		// enter the city details
		boolean verifycity = Verifyelementpresent("xpath","//input[@id='searchLocation_city']",10);
		if(verifycity) {
			Click(findelement("id", "searchLocation_city"));
			SendKeys(findelement("xpath","//input[@id='searchLocation_city']"), "Madurai");
			Thread.sleep(1000);
			ReportLog("pass","City name have been entered Successfully");
		}
		else {
			ReportLog("fail","Unable to enter the City name");
		}
		
	/*	Click(findelement("id", "searchLocation_city"));
		WebElement ele1 = findelement("xpath", "//input[@id='searchLocation_city']" );
		ele1.sendKeys("Madurai");
		Thread.sleep(1000);
		ReportLog("pass", "City name have Been Entered Successfully"); */
		
		
		// select the Country from the Dropdown
		boolean verifydrop = Verifyelementpresent("id","searchLocation_country",10);
		if(verifydrop) {
			Click(findelement("id", "searchLocation_country"));
			Select country = new Select(driver.findElement(By.xpath("//select[@id='searchLocation_country']")));
			country.selectByVisibleText("India");
			ReportLog("pass", "Value have Been Selected Successfully From the DropDown");
		}
		else {
			ReportLog("fail", "Unable to select the value from dropdown");
		}
		
		boolean check = Verifyelementpresent("xpath","//select[@id='searchLocation_country']",10);
		if(check) {
			Click(findelement("xpath", "//select[@id='searchLocation_country']"));
			Thread.sleep(2000);
			ReportLog("pass", "The value have been clicked");
		}
		else {
			ReportLog("fail", "Unable to click the Vlaue from dropdown");

		}
		
		
/*		WebElement eleB = findelement("xpath", "//select[@id='searchLocation_country']" );
		eleB.click(); */
		
		
		// Search the country 
		
		boolean verifycountry = Verifyelementpresent("xpath","//input[@id='btnSearch']",10);
		if(verifycountry) {
			Click(findelement("xpath", "//input[@id='btnSearch']"));
			Thread.sleep(5000);
			ReportLog("pass", "Search buttton have been clicked");
		}
		else {
			ReportLog("fail", "Unable to click the search button");
		}
		
	/*	WebElement ele2 = findelement("xpath", "//input[@id='btnSearch']");
		ele2.click();
		Thread.sleep(5000); */
		
		
		//moving to the checkbox and selecting
		boolean verifycheck = Verifyelementpresent("xpath","//input[@id='ohrmList_chkSelectAll']",10);
		if(verifycheck) {
			Click(findelement("xpath", "//input[@id='ohrmList_chkSelectAll']"));
			Thread.sleep(5000);
			ReportLog("pass", "Checkbox have Been Checked Successfully based on the Search results");
		}
		else {
			ReportLog("fail", "Unable to Check the box");
		}
		
		
		/*WebElement ele3 = findelement("xpath", "//input[@id='ohrmList_chkSelectAll']");
		ele3.click();
		Thread.sleep(5000);
		ReportLog("pass", "Checkbox have Been Checked Successfully based on the Search results");
		 */
		
		//Delete when the search result is occured 
		boolean verifysearch = Verifyelementpresent("xpath","//input[@id='btnDelete']",10);
		if(verifysearch) {
			Click(findelement("xpath", "//input[@id='btnDelete']"));
			Thread.sleep(3000);
			ReportLog("pass", "Delete Button have Been Clicked");		
		}
		else {
			ReportLog("fail", "Unable to Delete the Record");
		}
		
		
	/*	WebElement ele4 = findelement("xpath", "//input[@id='btnDelete']");
		ele4.click();
		Thread.sleep(3000);
		ReportLog("info", "Delete Button have Been Clicked"); */
		
		
		//Handling Popup
		boolean verifypop = Verifyelementpresent("xpath","//input[@id='dialogDeleteBtn']",10);
		if(verifypop) {
			Click(findelement("xpath", "//input[@id='dialogDeleteBtn']"));
			Thread.sleep(3000);
			ReportLog("pass", "Popup have Been handled Successfully");
		}
		else {
			ReportLog("fail", "Unable to Handle Popup Window");
		}
		
		
	/*	WebElement ele5 = findelement("xpath", "//input[@id='dialogDeleteBtn']");
		ele5.click();
		
		Thread.sleep(3000);
		ReportLog("pass", "Popup have Been handled Successfully"); */
		
		
		return this;
		}
		catch(Exception e){
			ReportLog("fail", "Unable to enter the value");	
			
		}
		return null;
		
	}
	

	
}
