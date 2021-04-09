package com.Demo.Selenium.base;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.remote.SessionId;

import com.github.javafaker.Faker;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class SeleniumBase{
	protected RemoteWebDriver driver;
	protected ExtentTest test;
	
	//
	public SeleniumBase(RemoteWebDriver driver,ExtentTest test) {
		this.driver=driver;
		this.test =test;
	}
	
	SoftAssert softAssertion= new SoftAssert();
	
	public static WebDriverWait wait;
	int i=1;
	Random rand = new Random();
	
	// report config
	public void ReportLog(String Logtype,String Logmessage) {
		Logtype= Logtype.toLowerCase();
		switch(Logtype) {
		case "info":
			test.log(LogStatus.INFO, Logmessage);
			break;
		case "pass":
			test.log(LogStatus.PASS, Logmessage);
			break;
		case "fail":
			test.log(LogStatus.FAIL, Logmessage);
			break;
		case "error":
			test.log(LogStatus.ERROR, Logmessage);
			break;
		case "warning":
			test.log(LogStatus.WARNING, Logmessage);
			break;
		case "skip":
			test.log(LogStatus.SKIP, Logmessage);
			break;
		case "unknown":
			test.log(LogStatus.UNKNOWN, Logmessage);
			break;
		case "screenshot":
			test.log(LogStatus.INFO, "Failed Script Snapshot below: <br>"+"<img src="+Logmessage+" alt=Failed Screenshot>");
			break;
		default:
			test.log(LogStatus.ERROR, "Log selection does not match with any records, Please modify the option");
		}
		
	}
	public WebElement findelement(String type,String value) {
		try {
			switch(type.toLowerCase()) {
			case "id": 
				return driver.findElementById(value);
			case "name": 
				return driver.findElementByName(value);
				
			case "class":
				return driver.findElementByClassName(value);
				
			case "partial link": 
				return driver.findElementByPartialLinkText(value);
				
			case "xpath": 
				return driver.findElementByXPath(value);
			default:
				test.log(LogStatus.ERROR, "Find element option not matches with any record, Please find correct selection");
				return null;
			}

		}catch(NoSuchElementException e) {
			System.err.println("The Element with locator:"+type+" Not Found with value: "+value);
			throw new RuntimeException();
		}
		
	}
	public void Click(WebElement ele) {
		try {
			ele.click();
		} catch (StaleElementReferenceException e) {
			test.log(LogStatus.FAIL,"The Element could not be clicked");
			throw new RuntimeException();
		} 
	}
	public void doubleclickelement(WebElement ele) {
		String text="";
		try {
			Actions actions = new Actions(driver);
			actions.click(ele);
			actions.doubleClick(ele);
			actions.perform();

		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL,"The Element "+text+"could not be clicked");
			throw new RuntimeException();
		} 
	}
	private static WebElement getShadowRoot(WebDriver driver,WebElement shadowHost) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    return (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);
	}
	 public static WebElement getShadowElement(WebDriver driver,WebElement shadowHost, String cssOfShadowElement) {
		    WebElement shardowRoot = getShadowRoot(driver, shadowHost);
		    return shardowRoot.findElement(By.cssSelector(cssOfShadowElement));
		}
	public String randomzipcode() {
		final String input = "12345";
		final Set<String> identifiers = new HashSet<String>();
		StringBuilder builder = new StringBuilder();
		while(builder.toString().length() == 0) {
			int length = 5;
			for(int i = 0; i < length; i++) {
				builder.append(input.charAt(rand.nextInt(input.length())));
			}
			if(identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		return builder.toString();
	}
	public int RandomNumber_threechar() {
		int randomNum = ThreadLocalRandom.current().nextInt(100, 500 + 1);
		return randomNum;
	}
	public int RandomNumber_twochar() {
		int randomNum = ThreadLocalRandom.current().nextInt(10, 50 + 1);
		return randomNum;
	}
	public int RandomNumber_fourchar() {
		int randomNum = ThreadLocalRandom.current().nextInt(1000, 5000 + 1);
		return randomNum;
	}
	public String randommajordob() {
		int minDay = (int) LocalDate.of(1990, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2000, 1, 1).toEpochDay();
		long randomDay = minDay + rand.nextInt(maxDay - minDay);
		LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LL/dd/yyyy");
		String RandomDob = randomBirthDate.format(formatter);
		return RandomDob;
	}
	public String randomphonenumber() {
		int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
		int num2 = rand.nextInt(743);
		int num3 = rand.nextInt(10000);
		DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
		DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros
		String phoneNumber = df3.format(num1) + "-" + df3.format(num2) + "-" + df4.format(num3);
		return phoneNumber;
	}
	public String randomssn() {
		int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
		int num2 = rand.nextInt(74);
		int num3 = rand.nextInt(100);
		DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
		//DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros
		String ssn = df3.format(num1) + "-" + df3.format(num2) + "-" + df3.format(num3);
		return ssn;
	}

	public String dateFormatter(String format_inhand, String required_format,String date_UI) throws ParseException {
		DateFormat originalFormat = new SimpleDateFormat(format_inhand, Locale.ENGLISH);
		DateFormat targetFormat = new SimpleDateFormat(required_format);
		Date date = originalFormat.parse(date_UI);
		String formattedDate = targetFormat.format(date);
		return formattedDate;
	}

	public String currentDate(String required_format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(required_format);
		String date = simpleDateFormat.format(new Date());
		return date;
	}

	public List<String> dateRanger(String str_date,String end_date,String date_format){
		List<Date> dates = new ArrayList<Date>();
		List<String> dateRange = new ArrayList<String>();
		DateFormat formatter ; 

		formatter = new SimpleDateFormat(date_format);
		try {
			Date  startDate = (Date)formatter.parse(str_date); 
			Date  endDate = (Date)formatter.parse(end_date);
			long interval = 24*1000 * 60 * 60; // 1 hour in millis
			long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
			long curTime = startDate.getTime();
			while (curTime < endTime) {
				dates.add(new Date(curTime));
				curTime += interval;
			}
			for(int i=0;i<dates.size();i++){
				Date lDate =(Date)dates.get(i);
				String ds = formatter.format(lDate); 
				dateRange.add(ds);
				//System.out.println(" Date is ..." + ds);
			}
		}catch (Exception e) {

		}
		return dateRange;
	}
	public String AgeCalculator(int month,int date,int year) {
		try {
			LocalDate today = LocalDate.now();                         
			LocalDate birthday = LocalDate.of(year, month, date);  
			Period p = Period.between(birthday, today);
//			System.out.println(p.getDays());
//			System.out.println(p.getMonths());
			return Integer.toString(p.getYears());
		}catch(Exception e) {
			ReportLog("error", "Unable to get Age");
			return null;
		}
		
	}
	public String RandomEmail(String str1,String str2) {
		try {
			return str1.toLowerCase()+str2.toLowerCase()+"@test.com";
		}catch(Exception e) {
			ReportLog("error", "Unable to get email");
			return null;
		}
	}
	public void ImplicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	public void movetoelement(WebElement ele) {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(ele).build().perform();
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL,"Unable to move element");
			throw new RuntimeException();
		} 
	}
	
	public void actionsdoubleclick(WebElement ele) {
		Actions action = new Actions(driver);
		WebElement dbclick = ele;
		action.moveToElement(dbclick).doubleClick().click().perform();
	}
	public void actionsclick(WebElement ele) {
		Actions action = new Actions(driver);
		WebElement click = ele;
		action.moveToElement(click).click().perform();
	}
	
	public void javascriptexecutor(WebElement ele,String Value) {
		WebElement element =ele;
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].value="+Value+";", element);
	}

	public void javascriptexecutorscrolltoview() {
		//		WebElement element = driver.findElementByXPath(ele);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("scroll(250, 0)");
	}
	public void javascriptexecutorscrolltoviewdown() {
		//			WebElement element = driver.findElementByXPath(ele);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("scroll(0, 250)");
	}	
	public  void javascriptscrolltoelement(WebElement ele) {
		WebElement element = ele;
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView()", element);
	}
	public boolean Verifyelementpresent(String type,String value,int timedelay) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		try {
			switch(type.toLowerCase()) {
			case "xpath":
				if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value))) != null)
					return true;
				else
					return false;
			case "id":
				if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value))) != null)
					return true;
				else
					return false;
			case "name":
				if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(value))) != null)
					return true;
				else
					return false;
			case "classname":
				if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(value))) != null)
					return true;
				else
					return false;
			case "partial link":
				if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(value))) != null)
					return true;
				else
					return false;
			default:
				test.log(LogStatus.ERROR, "Verify element option not matches with any record, Please find correct selection");
				return false;
				
			}
			
			}
		catch (Exception e) {
				System.out.println(e);
				return false;
			}
	}
	
	public boolean Verifyelementclickable(String type,String value,int timedelay) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		try {
			switch(type.toLowerCase()) {
			case "xpath":
				if(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value))) != null)
					return true;
				else
					return false;
			case "id":
				if(wait.until(ExpectedConditions.elementToBeClickable(By.id(value))) != null)
					return true;
				else
					return false;
			case "name":
				if(wait.until(ExpectedConditions.elementToBeClickable(By.name(value))) != null)
					return true;
				else
					return false;
			case "classname":
				if(wait.until(ExpectedConditions.elementToBeClickable(By.className(value))) != null)
					return true;
				else
					return false;
			case "partial link":
				if(wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(value))) != null)
					return true;
				else
					return false;
			default:
				test.log(LogStatus.ERROR, "Verify element clickable option not matches with any record, Please find correct selection");
				return false;
				
			}
			
			}
		catch (Exception e) {
				System.out.println(e);
				return false;
			}
	}
	

	public int  countOfElements(String type,String value) {

		int elementcounts = 0;
		try {
			switch(type.toLowerCase()) {
			case "id": elementcounts = driver.findElementsById(value).size();break;
			case "name": elementcounts = driver.findElementsByName(value).size();break;
			case "class": elementcounts = driver.findElementsByClassName(value).size();break;
			case "link": elementcounts = driver.findElementsByLinkText(value).size();break;
			case "xpath": elementcounts = driver.findElementsByXPath(value).size();break;
			}

		}catch(NoSuchElementException e) {
			System.err.println("The Element with locator:"+type+" Not Found with value: "+value);
			throw new RuntimeException();
		}
		return elementcounts;

	}


	public String verifyelementpresentwithcolor (String value) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {
			if(driver.findElementByXPath(value) != null){
				String ElementColor = driver.findElementByXPath(value).getCssValue("color");
				String[] hexValue = ElementColor.replace("rgba(", "").replace(")", "").split(",");
				int hexValue1=Integer.parseInt(hexValue[0]);
				hexValue[1] = hexValue[1].trim();
				int hexValue2=Integer.parseInt(hexValue[1]);
				hexValue[2] = hexValue[2].trim();
				int hexValue3=Integer.parseInt(hexValue[2]);
				String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
				return actualColor;
			}
			else{
				System.out.println("Element is Absent");
				return null;
			}}catch (Exception e) {
				System.out.println(e);
				return null;
			}
	}
	public String GetBackgroundColor (String value) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {
			if(driver.findElementByXPath(value) != null){
				String color = driver.findElement(By.xpath(value)).getCssValue("background-color");
//				System.out.println(color);
				String hex = Color.fromString(color).asHex();
//				System.out.println(hex);
				return hex;
			}
			else{
				System.out.println("Element is Absent");
				return null;
			}}catch (Exception e) {
				System.out.println(e);
				return null;
			}
	}
	public void enterkey(String ele) {

		driver.findElementByXPath(ele).sendKeys(Keys.RETURN);
	}
	public int elementlist(String value) {
		List<WebElement> xpath = driver.findElementsByXPath(value);
		int xpathCount = xpath.size();
		return xpathCount;
	}
	public void clickWithNoSnap(String ele) {
		try {
			wait = new WebDriverWait(driver, 10);
			
			driver.findElementByXPath(ele).click();
			test.log(LogStatus.PASS,"The Element "+ele+" clicked");
		} catch (StaleElementReferenceException e) {
			test.log(LogStatus.FAIL,"The Element "+ele+" could not be clicked");
			throw new RuntimeException();
		}catch(Exception e) {
			System.err.println(e);
		}

	}


	public void pageReload() {	
		try {
			driver.navigate().refresh();
		}catch(Exception e) {
			System.out.println(e);
		}

	}

	public List<String> getWebElementValues(String locatorType, String value){
		List<WebElement> webElementContent;
		List<String> webElementValues = new ArrayList<String>();
		webElementContent = locateElements(locatorType,value);
		for(WebElement webElementValue : webElementContent) {
			webElementValues.add(webElementValue.getText());
		}
		return webElementValues;
	}

	public boolean comparetwostring(String one,String two) {
		if(one.equals(two)) {
			return true;
		}else {
			return false;
		}
	}

	public void append(String ele, String data) {
		driver.findElementByXPath(ele).sendKeys(data);
	}

	public void clearelementusingkeys(WebElement ele) {
		Actions navigator = new Actions(driver);
		navigator.click(ele)
		.sendKeys(Keys.END)
		.keyDown(Keys.SHIFT)
		.sendKeys(Keys.HOME)
		.keyUp(Keys.SHIFT)
		.sendKeys(Keys.BACK_SPACE)
		.perform();
	}
	
	public void SendKeys(WebElement ele, String data) {
		try {
		ele.sendKeys(data);
		} catch (ElementNotInteractableException e) {
		test.log(LogStatus.FAIL,"The Element "+ele+" is not Interactable");
		throw new RuntimeException();
		}
	}

	public void Clear(WebElement ele) {
		try {
		ele.clear();
		} catch (ElementNotInteractableException e) {
		test.log(LogStatus.FAIL,"The Element "+ele+" is not Interactable");
		throw new RuntimeException();
		}
	}


	public String getElementText(String ele) {
		String text = driver.findElementByXPath(ele).getText();
		return text;
	}


	public String getBackgroundColor(String ele) {
		String cssValue = driver.findElementByXPath(ele).getCssValue("color");
		return cssValue;
	}


	public String getTypedText(String ele) {
		String attributeValue = driver.findElementByXPath(ele).getAttribute("value");
		return attributeValue;
	}

	public String getAttributeValue(String ele,String value) {
		String attributeValue = driver.findElementByXPath(ele).getAttribute(value);
		return attributeValue;
	}




	public void selectDropDownUsingText(String ele, String value) {

		new Select(driver.findElementByXPath(ele)).selectByVisibleText(value);
	}


	public void selectDropDownUsingIndex(String ele, int index) {
		new Select(driver.findElementByXPath(ele)).selectByIndex(index);
	}


	public void selectDropDownUsingValue(String ele, String value) {
		new Select(driver.findElementByXPath(ele)).selectByValue(value);
	}


	public boolean verifyExactText(String ele, String expectedText) {
		try {
			if(driver.findElementByXPath(ele).getText().equals(expectedText)) {

				System.out.println("The expected text contains the actual "+expectedText);
				return true;
			}else {
				System.out.println("The expected text doesn't contain the actual "+expectedText);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		} 

		return false;
	}
	public boolean verifyExactTextnotpresent(boolean ele) {
		try {
			if(ele==true) {

				System.out.println("The expected text contains the actual ");
				return true;
			}else {
				System.out.println("The expected text doesn't contain the actual ");
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		} 

		return false;
	}
	public boolean verifyExactTextString(String ele, String expectedText) {
		try {
			String ele1 = driver.findElementByXPath(ele).getText();
			if(ele1.equals(expectedText)) {

				System.out.println("The expected text contains the actual "+expectedText);
				return true;
			}else {
				System.out.println("The expected text doesn't contain the actual "+expectedText);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		} 

		return false;
	}

	public boolean verifyPartialText(String ele, String expectedText) {
		try {
			if(driver.findElementByXPath(ele).getText().contains(expectedText)) {
				System.out.println("The expected text contains the actual "+expectedText);
				return true;
			}else {
				System.out.println("The expected text doesn't contain the actual "+expectedText);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		} 

		return false;
	}


	public boolean verifyExactAttribute(String ele, String attribute, String value) {
		try {
			if(driver.findElementByXPath(ele).getAttribute(attribute).equals(value)) {
				System.out.println("The expected attribute :"+attribute+" value contains the actual "+value);
				return true;
			}else {
				System.out.println("The expected attribute :"+attribute+" value does not contains the actual "+value);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Attribute Text");
		}
		return false;
	}


	public void verifyPartialAttribute(String ele, String attribute, String value) {
		try {
			if(driver.findElementByXPath(ele).getAttribute(attribute).contains(value)) {
				System.out.println("The expected attribute :"+attribute+" value contains the actual "+value);
			}else {
				System.out.println("The expected attribute :"+attribute+" value does not contains the actual "+value);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Attribute Text");
		}

	}


	public boolean verifyDisplayed(String ele) {
		try {
			if(driver.findElementByXPath(ele).isDisplayed()) {
				System.out.println("The element "+ele+" is visible");
				return true;
			} else {
				System.out.println("The element "+ele+" is not visible");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		} 
		return false;

	}


	public boolean verifyDisappeared(WebElement ele) {
		return false;

	}


	public boolean verifyEnabled(String ele) {
		try {
			if(driver.findElementByXPath(ele).isEnabled()) {
				System.out.println("The element "+ele+" is Enabled");
				return true;
			} else {
				System.out.println("The element "+ele+" is not Enabled");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		return false;
	}


	public void verifySelected(String ele) {
		try {
			if(driver.findElementByXPath(ele).isSelected()) {
				System.out.println("The element "+ele+" is selected");
				//				return true;
			} else {
				System.out.println("The element "+ele+" is not selected");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		//		return false;

	}


	


	public WebElement locateElement(String locatorType, String value) {
		try {
			switch(locatorType.toLowerCase()) {
			case "id": return driver.findElementById(value);
			case "name": return driver.findElementByName(value);
			case "class": return driver.findElementByClassName(value);
			case "link": return driver.findElementByLinkText(value);
			case "xpath": return driver.findElementByXPath(value);
			}
		} catch (NoSuchElementException e) {
			//test.log(LogStatus.FAIL,"The Element with locator:"+locatorType+" Not Found with value: "+value);
			throw new RuntimeException();
		}catch (Exception e) {
			//test.log(LogStatus.FAIL,"The Element with locator:"+locatorType+" Not Found with value: "+value);
		}
		return null;
	}


	public WebElement locateElement(String value) {
		WebElement findElementById = driver.findElementById(value);
		return findElementById;
	}


	public List<WebElement> locateElements(String type, String value) {
		try {
			switch(type.toLowerCase()) {
			case "id": return driver.findElementsById(value);
			case "name": return driver.findElementsByName(value);
			case "class": return driver.findElementsByClassName(value);
			case "link": return driver.findElementsByLinkText(value);
			case "xpath": return driver.findElementsByXPath(value);
			}
		} catch (NoSuchElementException e) {
			System.err.println("The Element with locator:"+type+" Not Found with value: "+value);
			throw new RuntimeException();
		}
		return null;
	}


	public void switchToAlert() {
		driver.switchTo().alert();
	}


	public void acceptAlert() {
		String text = "";		
		try {
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.accept();
			test.log(LogStatus.PASS,"The alert "+text+" is accepted.");
		} catch (NoAlertPresentException e) {
			test.log(LogStatus.FAIL,"There is no alert present.");
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}  

	}


	public void dismissAlert() {
		String text = "";		
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.dismiss();
			System.out.println("The alert "+text+" is accepted.");
		} catch (NoAlertPresentException e) {
			System.out.println("There is no alert present.");
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}  


	}


	public String getAlertText() {
		String text = "";		
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
		} catch (NoAlertPresentException e) {
			System.out.println("There is no alert present.");
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		} 
		return text;
	}


	public void typeAlert(String data) {
		driver.switchTo().alert().sendKeys(data);

	}


	public void switchToWindow(int index) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			List<String> allhandles = new ArrayList<String>(allWindows);
			String exWindow = allhandles.get(index);
			driver.switchTo().window(exWindow);
			System.out.println("The Window With index: "+index+
					" switched successfully");
		} catch (NoSuchWindowException e) {
			System.err.println("The Window With index: "+index+
					" not found");	
		}
	}


	public void switchToWindow(String title) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			for (String eachWindow : allWindows) {
				driver.switchTo().window(eachWindow);
				if (driver.getTitle().equals(title)) {
					break;
				}
			}
			System.out.println("The Window With Title: "+title+
					"is switched ");
		} catch (NoSuchWindowException e) {
			System.err.println("The Window With Title: "+title+
					" not found");
		} finally {
			takeSnap();
		}
	}


	public void switchToFrame(int index) {
		driver.switchTo().frame(index);

	}


	public void switchToFrame(String ele) {
		driver.switchTo().frame(driver.findElementByXPath(ele));

	}

	public void defaultContent() {
		driver.switchTo().defaultContent();

	}


	public boolean verifyUrl(String url) {
		if (driver.getCurrentUrl().equals(url)) {
			System.out.println("The url: "+url+" matched successfully");
			return true;
		} else {
			System.out.println("The url: "+url+" not matched");
		}
		return false;
	}


	public boolean verifyTitle(String title) {
		if (driver.getTitle().equals(title)) {
			System.out.println("Page title: "+title+" matched successfully");
			return true;
		} else {
			System.out.println("Page url: "+title+" not matched");
		}
		return false;
	}


	public long takeSnap(){
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}


	public WebElement locateElementbyXpath(String value) {
		WebElement findElementByXpath = driver.findElementByXPath(value);
		return findElementByXpath;
	}
	public String RandomGander() {
		Random coin = new Random();
        int toss;
        toss = coin.nextInt(2);
        switch(toss){
            case 0 : 
             return "Male";
            case 1 : 
             return "Female";
            case 2:
             return "OTHER";
            default:
             return "Male";
        }
	}
	public Map<String,String>RandomData_Faker(){
		Map<String, String> RandomValues = new LinkedHashMap<>();
		Faker fake= new Faker();
		try {
			String Firstname,Lastname,Middlename,PrefferedName,Address1,City,State,Address2,Zip,Telephone,ssn;
			Firstname= fake.name().firstName();
			Lastname = fake.name().lastName();
			Middlename = fake.name().firstName();
			PrefferedName=fake.name().lastName();
			Address1 =fake.address().streetAddress();
			Address1 =Address1.replaceAll("[-+.^:,']","");
			Address2 =fake.address().streetAddress();
			Address2 =Address2.replaceAll("[-+.^:,']","");
			City =fake.address().cityName().replaceAll("[-+.^:,']","");
			State=fake.address().stateAbbr();
			Zip =randomzipcode();
			Telephone =randomphonenumber().replaceAll("[-+.^:,']","");
			ssn =randomssn().replaceAll("[-+.^:,']","");
			//put in map
			RandomValues.put("FirstName",Firstname);
			RandomValues.put("LastName",Lastname);
			RandomValues.put("MiddleName",Middlename);
			RandomValues.put("PrefferedName",PrefferedName);
			RandomValues.put("Address1",Address1);
			RandomValues.put("City",City);
			RandomValues.put("State",State);
			RandomValues.put("Address2",Address2);
			RandomValues.put("ZipCode",Zip);
			RandomValues.put("TelephoneNumber",Telephone);
			RandomValues.put("SSN",ssn);
			return RandomValues;
		}catch (Exception e) {
			ReportLog("Error", "Unable to get random data from faker");
			return null;
		}
	}
	public Map<String,String> RandomDataGenerration_Mockaroo(){
		Map<String, String> RandomValues = new LinkedHashMap<>();
		Faker fake= new Faker();
		try {
			URL url = new URL("https://my.api.mockaroo.com/RandomTestDatas.json?key=832d7f80&__method=POST");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			String outputString = IOUtils.toString(conn.getInputStream());
			outputString = outputString.substring(1, outputString.length()-1); 
			String[] StringArray1 = outputString.split(",");            
			for (String StringValue : StringArray1) {
				String[] StringArray2 = StringValue.split(":");
				//			System.out.println(StringArray2[1].trim().getClass().getName());
				RandomValues.put(StringArray2[0].replaceAll("\"","").trim(),StringArray2[1].replaceAll("\"","").trim());

			}
			return RandomValues;
		}catch(IOException e) {
			String Firstname,Lastname,Middlename,PrefferedName,Address1,City,State,Address2,Zip,Telephone,ssn;
			Firstname= fake.name().firstName();
			Lastname = fake.name().lastName();
			Middlename = fake.name().firstName();
			PrefferedName=fake.name().lastName();
			Address1 =fake.address().streetAddress();
			Address1 =Address1.replaceAll("[-+.^:,']","");
			Address2 =fake.address().streetAddress();
			Address2 =Address2.replaceAll("[-+.^:,']","");
			City =fake.address().cityName().replaceAll("[-+.^:,']","");
			State=fake.address().stateAbbr();
			Zip =randomzipcode();
			Telephone =randomphonenumber().replaceAll("[-+.^:,']","");
			ssn =randomssn().replaceAll("[-+.^:,']","");
			//put in map
			RandomValues.put("FirstName",Firstname);
			RandomValues.put("LastName",Lastname);
			RandomValues.put("MiddleName",Middlename);
			RandomValues.put("PrefferedName",PrefferedName);
			RandomValues.put("Address1",Address1);
			RandomValues.put("City",City);
			RandomValues.put("State",State);
			RandomValues.put("Address2",Address2);
			RandomValues.put("ZipCode",Zip);
			RandomValues.put("TelephoneNumber",Telephone);
			RandomValues.put("SSN",ssn);
			return RandomValues;
		}
	}

}

