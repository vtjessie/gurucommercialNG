package TestNGPackage;

import TestNGPackage.Util;
import guruECommercePackage.EcomUtil;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
@Test
public class NewTest{
/*	public  static final String FIREFOX_PATH =  "D:\\Program Files\\Mozilla Firefox\\firefox.exe" ;
	public  static final String USER_NAME = "mngr83695";
	public  static final String PASSWD = "uhyjuqA";*/
	public  static final String baseUrl     = "http://www.demo.guru99.com/V4/";
	WebDriver driver ;
	@Test(dataProvider = "data-provider")
	public void verifylogin(String username, String password)  throws  Exception{
		  System.out.print(username +"|| ");

		  System.out.println(password+"|| ");
	    // Enter username
	    driver.findElement(By.name("uid")).sendKeys(username);

	    // Enter Password
	    driver.findElement(By.name("password")).sendKeys(password);
 
	    // Click Login
	    driver.findElement(By.name("btnLogin")).click();
	    
	    try{ 
		    
	       	Alert alt = driver.switchTo().alert();
			String actualBoxMsg = alt.getText(); // get content of the Alter Message
			alt.accept();
			 // Compare Error Text with Expected Error Value					
			assertEquals(actualBoxMsg,Util.EXPECT_ERROR);
			
		}    
	    catch (NoAlertPresentException Ex){ 
	    	String actualTitle = driver.getTitle();
			// On Successful login compare Actual Page Title with Expected Title
	    	assertEquals(actualTitle,Util.EXPECT_TITLE);
        } 


	    }
  @BeforeTest
	public  void   launcbroser() throws Exception{	  
		
		File pathToBinary = new File( Util.FIREFOX_PATH);
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);
		driver.manage().timeouts()
		.implicitlyWait( Util.WAIT_TIME, TimeUnit.SECONDS);	
		 driver.get( Util.Url);


	}
	
  
  @AfterTest 
  public void terminateBrowser() throws Exception{
      driver.quit();
  }
  
  @DataProvider(name = "data-provider")
  public static Object[][] dataProviderMethod() throws Exception 
  {
 
	  return ((Object[][])Util.getDataFromExcel(Util.filePathXl, Util.SHEET_NAME));
  }

}