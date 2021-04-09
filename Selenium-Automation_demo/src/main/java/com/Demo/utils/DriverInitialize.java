package com.Demo.utils;

	
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
public class DriverInitialize extends FalsePositiveConfigurations{
	public  RemoteWebDriver driver;
	public  ExtentReports extent;
	public  ExtentTest test;

	public String EnvironmentName="Orange";
	public String CurrentSuiteName;
	public String classname;
	public String BrowserType;
	String defaulttestsuitename="testng-customsuite";
	public String SqlQueryname;
	Map<String, String> EnvironmentValuesData = new LinkedHashMap<String, String>();
	// getter
	public String getQueryName() {
	    return SqlQueryname;
	  }
	// Setter
	public void setQueryName(String queryname) {
	    this.SqlQueryname = queryname;
	 }
	//get env values
	public Map<String, String> ReadCurrentQueryData(){
		try {
			EnvironmentValuesData = FalsePositiveConfigurations.ReadDbConfigData("Sql Query");
			return EnvironmentValuesData;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Map<String,JSONArray> ReadCurrentTestData(){
		Map<String,JSONArray> envdata = new LinkedHashMap<String,JSONArray>();
		try {
			System.out.println(SqlQueryname);
			envdata = FalsePositiveConfigurations.ReadCurrentData(SqlQueryname);
			return envdata;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//read the environment details 
	@DataProvider(name = "fetchData")
	public Object[][] fetchData() throws IOException {
		return DataLibrary.ReadTestData();
	}
	@BeforeClass(alwaysRun=true)
	public void Beforeclass(ITestContext SuiteName) throws InterruptedException {
		//get class name
		String[] classArray =this.getClass().getName().split("\\.");
		classname =classArray[classArray.length-1];
		BrowserType = System.getProperty("runmodeselection");
		try {
		if(BrowserType==null) {
			driver =StartBrowser("chrome");
		}else {
			driver = StartBrowser(BrowserType);
		}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"The Browser Could not be Launched. Hence Failed");
		}
		Date d =new Date();
	    String todaydatetime = new SimpleDateFormat("MMM-dd-yyyy_HH_mm_ss_SSS").format(d);
		extent = new ExtentReports("./reports/"+EnvironmentName+"_"+classname+"_"+todaydatetime+".html",true,DisplayOrder.NEWEST_FIRST);
	}
	@BeforeMethod(alwaysRun = true)
	public void report(ITestResult MethodName) throws IOException, InterruptedException {		
	//	extent.loadConfig(new File("./reports/reportconfig/ExtentReportXml.xml"));
		test = extent.startTest("<font color='#000000'><b>"+MethodName.getMethod().getMethodName()+"</b</font>",EnvironmentName);
    }
	public  RemoteWebDriver StartBrowser(String browser) {
		 ChromeOptions options = new ChromeOptions();
		 String Browser = browser.toLowerCase();
		 try {
			switch (Browser)
	        {
	            case "chrome":
	            	System.setProperty("webdriver.chrome.driver","./drivers/chromedriver_80/chromedriver");
			        driver = new ChromeDriver();
			        System.out.println("Session id: "+driver.getSessionId().toString());
	                break;
	            case "chrome(headless)":
	            	System.setProperty("webdriver.chrome.driver","./drivers/chromedriver_87/chromedriver.exe");
			        options.addArguments("--headless");
			        driver = new ChromeDriver(options);
	                break;
	            case "firefox":
	            	System.setProperty("webdriver.gecko.driver","./drivers/geckodriver.exe");
					driver = new FirefoxDriver();
	                break;
	            case "ie":
	            	System.setProperty("webdriver.ie.driver","./drivers/IEDriverServer.exe");
					driver = new InternetExplorerDriver();
	            default:
	                System.out.println("Cannot Start the Browser Driver");
	                break;
	        }
			Dimension d = new Dimension(1366,768);
			driver.manage().window().setSize(d);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return driver;
		} 
		catch (Exception e) {
			throw new RuntimeException();
		}
	}
	public String TakescreenShot(RemoteWebDriver driver) {
		Date d =new Date();
	    String todaydatetime = new SimpleDateFormat("MMM-dd-yyyy_HH_mm_ss_SSS").format(d);
		try {
			TakesScreenshot ts = (TakesScreenshot)driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        String dest = System.getProperty("user.dir")+"\\screenshots\\test_"+todaydatetime+".jpg";
	        File destination = new File(dest);
	        FileUtils.copyFile(source, destination);      
	        return dest;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void Refresh() {
		driver.get(driver.getCurrentUrl());
	}
	public void ReloginWithNewDriverSession() {
		try {
		test.log(LogStatus.WARNING, "Previous script failed due to the unhandlable error, So Launching the new driver session");	
		driver.close();
		driver.quit();
		driver = StartBrowser("chrome");
		}catch(Exception e) {
		test.log(LogStatus.ERROR, "Unable to launch new driver session");	
		}
	}
	@AfterMethod
	public void stopexecution(ITestResult result){
	    if(result.getStatus() == ITestResult.FAILURE)
	    {
	    	extent.flush();
			driver.close();
			driver.quit();
	    	throw new SkipException("Previous Script Got Failed");
	    }
	 }
	@AfterMethod(alwaysRun = true)
	public void endreport() throws IOException {
    	extent.endTest(test); 
    	if (test.getRunStatus().equals(LogStatus.UNKNOWN))
    	{
    	test.log(LogStatus.UNKNOWN, "Report result is unknown !!!");
    	}
	}
 	@AfterClass
 	public void CloseDriver() {
		extent.flush();
		driver.close();
		driver.quit();
	}
}
