package guruECommercePackage;

import org.testng.annotations.Test;


import guruECommercePackage.EcomUtil;


import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;



public class VerifyOrderAndPdf {
	public WebDriver  driver;
  @Test
  public void verifyOrderPdf() {
	  driver.findElement(By.linkText("MY ACCOUNT")).click();
		driver.findElement(By.cssSelector("#email"  )).sendKeys("vt.jessie@yahoo.com");
		driver.findElement(By.cssSelector("#pass"  )).sendKeys("Infant123");
		driver.findElement(By.cssSelector("#send2"  )).click();
  
		//My Order
		  driver.findElement(By.linkText("MY ORDERS")).click();
		  
		//view order
		  driver.findElement(By.linkText("VIEW ORDER")).click();

		  //Pending msg
		  String ordeNoAndMsg = driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[2]/div/div[2]/div/div[1]/h1")).getText();
		  System.out.println("ordeNoAndMsg  "+"|| "+ordeNoAndMsg);
		  
		  //print order
		  driver.findElement(By.linkText("Print Order")).click();


  }
  @BeforeMethod
  public void getBrowser() {
	 String baseUrl = "http://live.guru99.com/";
	 driver.get(baseUrl);
	 
 	//    driver.get(EcomUtil.Url);

 
  }

  @BeforeTest
  public void lauchSetUp() throws Exception {
	  EcomUtil.launcFirefoxbrowser();
	  driver = EcomUtil.driver; 
	
 		 driver.get("http://live.guru99.com/");

  }

  @AfterTest
  public void afterTest() {
 	  driver.quit();
  }

}
