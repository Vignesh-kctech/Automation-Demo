package com.Demo.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "/Users/kctechappium/eclipse-workspace/Selenium-Automation_demo/drivers/chromedriver_89/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		driver.get("https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		
		driver.findElement(By.id("identifierId")).sendKeys("vignesh_manogaran@kctechservices.com");
		
		driver.findElement(By.xpath("//*[@id=\'identifierNext\']/div/button/div[2]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("Vignesh@Kc1998");
		
		driver.findElement(By.xpath("//*[@id=\'passwordNext\']/div/button/div[2]")).click();
		
		

	}
	

}