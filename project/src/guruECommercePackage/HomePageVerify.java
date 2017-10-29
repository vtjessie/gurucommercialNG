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
//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;


public class HomePageVerify {
	public int scc = 0;
 	WebDriver driver ;
	  
	
//
 	@Test( invocationCount=1)
public void verifylogin() throws IOException{
	String actualTitle = driver.getTitle();
	// On Successful login compare Actual Page Title with Expected Title
	assertEquals(actualTitle,EcomUtil.EXPECT_TITLE);
	
	 String demoSite  = driver.findElement(By.cssSelector("h2")).getText();
	    System.out.println(demoSite);
	    assertEquals(demoSite,EcomUtil.EXPECT_TITLEH);
	driver.findElement(By.linkText("MOBILE")).click();
	actualTitle = driver.getTitle();
	assertEquals(actualTitle,EcomUtil.EXPECT_TITLE1);
	
//	Select drpSortBy = new Select(driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/select")));
//	drpSortBy.selectByVisibleText("Name");
	 new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Name");
	  scc = (scc+1);
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String png = ("D:\\divine\\Selenium Guru\\Guru99 eCommerce Live Project\\Day01_TestCase1\\Mobile Products are sorted" + scc + ".png");
			FileUtils.copyFile(scrFile, new File(png));
}
// 
@Test( invocationCount=1) 
public void  costOfProduct(){
	driver.findElement(By.linkText("MOBILE")).click();
//	String cost = driver.findElement(By.id("product-price-1")).getText();
	String cost = driver.findElement(By.cssSelector("#product-price-1 > span.price")).getText();
	 System.out.print("Cost..  "+"|| "+cost); 
	 driver.findElement(By.cssSelector("a[title=\"Sony Xperia\"]")).click();
	 String detailCost = driver.findElement(By.id("product-price-1")).getText();
		assertEquals(cost,detailCost);
}

//
@Test( invocationCount=1)
public void addToCart() throws InterruptedException{
	String errorMsgExp = "The requested quantity for \"Sony Experia \" is not available ";
	driver.findElement(By.linkText("MOBILE")).click();
	
//	  driver.findElement(By.cssSelector("div.product-info div.actions   button[title=\"Add to Cart\"]")).click();
 
	// switching to new window
    for (String handle : driver.getWindowHandles()) {
    driver.switchTo().window(handle);
    }
 	 driver.findElement(By.xpath(".//*[@id='top']//*[@class='product-info']/*[@class='actions']/button")).click();
 	
// 	/html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/button
 	 Thread.sleep(1000);
 	 driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).clear();
 	 driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).sendKeys("1000");

 //	 driver.findElement(By.cssSelector("td.product-cart-actions button[title=\"Update\"]")).click();
	driver.findElement(By.xpath(" .//*[@id='shopping-cart-table']/tbody/tr/td[4]/button")).click();
	

	String errorMsg = driver.findElement(By.cssSelector(".error-msg")).getText();
	 System.out.println("Errror Msg..  "+"|| "+errorMsg); 

/*	try {
		assertEquals(errorMsg,errorMsgExp);
      } catch (Exception e) {
    	  e.printStackTrace();
      }*/
	 driver.findElement(By.cssSelector("button[title=\"Empty Cart\"]")).click();
	 String statusMsgActual  =driver.findElement(By.cssSelector("div.main h1")).getText();
	 System.out.println("statusMsgActual  "+"|| "+statusMsgActual);
	 	try {
		assertEquals(statusMsgActual,"SHOPPING CART IS EMPTY");
   } catch (Exception e) {
 	  e.printStackTrace();
   } 
}

//
@Test( invocationCount=1)
public  void compareToPhoneUsingScrren() throws IOException, InterruptedException{
	driver.findElement(By.linkText("MOBILE")).click();
 	Thread.sleep(3000);
	//SONY XPERIA	                     /html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/ul/li[2]/a
 	driver.findElement(By.xpath(".//a[contains(@href,'1')][text()='Add to Compare']")).click();

 	//	driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/ul/li[2]/a")).click();
	 System.out.println("statusMsgActual   FIRST ");

 	Thread.sleep(3000);
 	// IPHONE
	//driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/ul/li[2]/a")).click();
 	driver.findElement(By.xpath(".//a[contains(@href,'2')][text()='Add to Compare']")).click();

 	System.out.println("statusMsgActual   SECOND ");

	Thread.sleep(1000);
	driver.findElement(By.cssSelector("div.actions button[title=\"Compare\"]")).click();
 	// String MainWindow=driver.getWindowHandle();
	  Set<String> s1=driver.getWindowHandles();		
      Iterator<String> i1=s1.iterator();
    				
       String  MainWindow = i1.next();
       if (i1.hasNext()) {
    	  String ChildWindow=i1.next();
    	  driver.switchTo().window(ChildWindow) ;
    	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Code to save screenshot at desired location
			FileUtils.copyFile(scrFile, new File("D:\\divine\\Selenium Guru\\Mobile Compareison.png"));
    	   driver.switchTo().window(ChildWindow).close();
    	   driver.switchTo().window(MainWindow) ;
       }


}

//
 @Test( invocationCount=1)
public  void compareToPhoneUsingText() throws IOException, InterruptedException{
	driver.findElement(By.linkText("MOBILE")).click();
	Thread.sleep(3000);
	
 	driver.findElement(By.xpath(".//a[contains(@href,'/2/')][text()='Add to Compare']")).click();

//	driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/ul/li[2]/a")).click();
	String firstPhone = driver.findElement(By.cssSelector("div.product-info  a[title=\"IPhone\"]")).getText();
	Thread.sleep(3000);

 	driver.findElement(By.xpath(".//a[contains(@href,'/1/')][text()='Add to Compare']")).click();

//	driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/ul/li[2]/a")).click();
	String secondPhone =driver.findElement(By.cssSelector("div.product-info  a[title=\"Sony Xperia\"]")).getText();
	System.out.println("firstPhone  "+"|| "+firstPhone);
	System.out.println("secondPhone  "+"|| "+secondPhone);
	driver.findElement(By.cssSelector("div.actions button[title=\"Compare\"]")).click();
	// switching to new window
    for (String handle : driver.getWindowHandles()) {
    	driver.switchTo().window(handle);
    	}
	Thread.sleep(3000);

    String strHeadExp = "COMPARE PRODUCTS";
    String strHeadAct =driver.findElement(By.xpath(".//*[@id='top']/body/div/div[1]/h1")).getText();
	System.out.println("strHeadAct  "+"|| "+strHeadAct);


//	String firstPhonePop =driver.findElement(By.cssSelector("#product_comparison h2.product-name a[title=\"IPhone\"]")).getText();
	String firstPhonePop =driver.findElement(By.xpath(".//*[@id='product_comparison']//a[contains(@onclick,'iphone.html')][text()='IPhone']")).getText();

	System.out.println("firstPhonePop  "+"|| "+firstPhonePop);

	Thread.sleep(3000);

	String secondPhonePop =driver.findElement(By.xpath(".//*[@id='product_comparison']//a[contains(@onclick,'sony-xperia.html')][text()='Sony Xperia']")).getText();


	System.out.println("secondPhonePop  "+"|| "+secondPhonePop);
	
	try{
		assertEquals(strHeadAct,strHeadExp);
	}catch (Exception e){
		e.printStackTrace();
	}
	try{
		assertEquals(firstPhone,firstPhonePop);
	}catch (Exception e){
		e.printStackTrace();
	}
	try{
		assertEquals(secondPhone,secondPhonePop);
	}catch (Exception e){
		e.printStackTrace();
	}
	

	driver.findElement(By.cssSelector("button[title=\"Close Window\"]")).click();

	// switching to new window
    for (String handle : driver.getWindowHandles()) {
    driver.switchTo().window(handle);
    }
}

@Test( invocationCount=0) 
public void createAccount(){
	driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a/span[2]")).click();
	driver.findElement(By.cssSelector("#header-account  a[title=\"My Account\"]")).click();
	driver.findElement(By.xpath(".//*[@id='login-form']/div/div[1]/div[2]/a/span/span")).click();  
	
	// switching to new window
    for (String handle : driver.getWindowHandles()) {
    	driver.switchTo().window(handle);
    	}
	
	driver.findElement(By.cssSelector("input#firstname")).sendKeys("Ricky");
	driver.findElement(By.cssSelector("input#middlename")).sendKeys("Infant");
	driver.findElement(By.cssSelector("input#lastname")).sendKeys("Gnanasusairaj");
	driver.findElement(By.cssSelector("input#email_address")).sendKeys("vt.jessie@yahoo.com");

	driver.findElement(By.cssSelector("input#password")).sendKeys("Infant123");
	driver.findElement(By.cssSelector("input#confirmation")).sendKeys("Infant123");
	driver.findElement(By.cssSelector("input#is_subscribed")).click();
	driver.findElement(By.cssSelector("button[title=\"Register\"]")).click();

	// switching to new window
    for (String handle : driver.getWindowHandles()) {
    	driver.switchTo().window(handle);
    	}
}
@Test( invocationCount=0) 
public void createAccountNew() throws InterruptedException{
	driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a/span[2]")).click();
	
	driver.findElement(By.linkText("MY ACCOUNT")).click();
	//driver.findElement(By.cssSelector("#header-account  a[title=\"My Account\"]")).click();
	
	driver.findElement(By.linkText("CREATE AN ACCOUNT")).click();
	//driver.findElement(By.xpath(".//*[@id='login-form']/div/div[1]/div[2]/a/span/span")).click();  
	
	for (String handle : driver.getWindowHandles()) {
    	driver.switchTo().window(handle);
    	}
	
	driver.findElement(By.cssSelector("input#firstname")).sendKeys("Elfrick");
	driver.findElement(By.cssSelector("input#middlename")).sendKeys("Infant");
	driver.findElement(By.cssSelector("input#lastname")).sendKeys("Gnanasusairaj");
//	driver.findElement(By.cssSelector("input#email_address")).sendKeys("infant.elfrick@gmail.com");

	driver.findElement(By.cssSelector("input#password")).sendKeys("Infant123");
	driver.findElement(By.cssSelector("input#confirmation")).sendKeys("Infant123");
	driver.findElement(By.cssSelector("input#is_subscribed")).click();
	driver.findElement(By.cssSelector("button[title=\"Register\"]")).click();
	// switching to new window
     for (String handle : driver.getWindowHandles()) {
    	driver.switchTo().window(handle);
    	} 
	// 6. Go to TV menu	    
    driver.findElement(By.xpath(".//*[@id='nav']/ol/li[2]/a")).click();
    Thread.sleep(2000);
} 

// 
@Test( invocationCount=1, priority=1) 
public void wishList() throws Exception{
	
 	driver.findElement(By.linkText("TV")).click();
 	
 	
 	
	driver.findElement(By.xpath(" //li/a[@class='link-wishlist'] ")).click();
	
	if (driver.findElements(By.id("email")).size() != 0) {
	
		driver.findElement(By.cssSelector("#email"  )).sendKeys("vt.jessie@yahoo.com");
		driver.findElement(By.cssSelector("#pass"  )).sendKeys("Infant123");
		driver.findElement(By.cssSelector("#send2"  )).click();
	}
	else{
		driver.findElement(By.linkText("My Wishlist")).click();
	}
		//Thread.sleep(3000);
		if (driver.findElements(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[1]/form[1]/div/p")).size() !=0){
			driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[2]/p/a")).click();
			driver.findElement(By.linkText("TV")).click();
			driver.findElement(By.xpath(" //li/a[@class='link-wishlist'] ")).click();

		}
			
		driver.findElement(By.xpath(".//*[@id='wishlist-view-form']/div/div/button[1]")).click();


 	for (String handle : driver.getWindowHandles()) {
    	driver.switchTo().window(handle);
    	}
 	
 	driver.findElement(By.cssSelector("div.input-box #email_address")).sendKeys("vt.jessie@yahoo.com");
 	driver.findElement(By.xpath(".//*[@id='form-validate']/div[2]/button ")).click();

 	String msgExp = driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText();
	String msgAct = "Your Wishlist has been shared."; 
	System.out.println("msgExp  "+"|| "+msgExp);
	System.out.println("msgAct  "+"|| "+msgAct);


	try{
		assertEquals(msgExp,msgAct);
	}catch (Exception e){
		e.printStackTrace();
	} 
}


//
@Test ( invocationCount=1, priority=2)
public void purchaseProduct(){
 
	 for (String handle : driver.getWindowHandles()) {
		 driver.switchTo().window(handle);
		} 

	driver.findElement(By.linkText("MY ACCOUNT")).click();
	
	if (driver.findElements(By.id("email")).size() != 0) {	
		driver.findElement(By.cssSelector("#email"  )).sendKeys("vt.jessie@yahoo.com");
		driver.findElement(By.cssSelector("#pass"  )).sendKeys("Infant123");
		driver.findElement(By.cssSelector("#send2"  )).click();
 	}else{
 		 driver.findElement(By.linkText("MY WISHLIST")).click();
 	}

	/**************************************************
	 * No items in the LIST
	 */
	
	if ( driver.findElements(By.xpath("//*[@id='wishlist-table']/tbody/tr/td[3]/div/div/input")).size()==0){
	
		driver.findElement(By.linkText("TV")).click();
		driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[2]/ul/li[1]/a")).click();
	}

		
		
		
		/**************************************************
		 * No items in the LIST END>>>>>>>>>>>>>>>>>>>
		 */
 	
		
		
		for (String handle : driver.getWindowHandles()) {
    	driver.switchTo().window(handle);
 		}
 	


	 //Add To cart
	 driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
	 
	 for (String handle : driver.getWindowHandles()) {
	    	driver.switchTo().window(handle);
	 		}

	 //Enter Shipping Inf
	 new Select(driver.findElement(By.id("country"))).selectByVisibleText("India");
	 driver.findElement(By.id("region")).sendKeys("Karnataka");
	 driver.findElement(By.id("postcode")).sendKeys("560100");

	 //click Estimate
	 driver.findElement(By.cssSelector("#shipping-zip-form  button[title=\"Estimate\"]")).click();

	 for (String handle : driver.getWindowHandles()) { 
	    	driver.switchTo().window(handle);
	    	} 
	 
	 //verify shipping cost is generated
	 String shippingCostExp = "Flat Rate";
	 String shippingCostAct =driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dt" )).getText();
	 try{
	    	System.out.println("shippingCostExp = "+shippingCostExp);
	    	System.out.println("shippingCostAct = "+shippingCostAct);
		 assertEquals(shippingCostExp,shippingCostAct);
	 }catch (Exception e){
		 
		 e.printStackTrace( );
	 }
	 
	  String sFlatRatePrice = "Fixed - $5.00";
	  String flatRatePrice = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dd/ul/li/label")).getText();
	    try {	 
	    	System.out.println("sFlatRatePrice = "+sFlatRatePrice);
	    	System.out.println("flatRatePrice = "+flatRatePrice);
	    	assertEquals(sFlatRatePrice, flatRatePrice);
		    } catch (Exception e) {
		    	e.printStackTrace();	    	
		    }
	
	    for (String handle : driver.getWindowHandles()) { 
	    	driver.switchTo().window(handle);
	    	} 
	    
	//select shipping cost and update    
	 driver.findElement(By.id("s_method_flatrate_flatrate")).click();	 // RADIO button  -  Fixed - $5.00
	 driver.findElement(By.xpath("//button[@title='Update Total']")).click();

	// 10. Verify shipping cost is added to total                                                           
	    String vFlatRatePrice   = "$5.00";
	    String shippingCostIncd = driver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span")).getText();
	    try{
	    	System.out.println("vFlatRatePrice = "+vFlatRatePrice);
	    	System.out.println("shippingCostIncluded = "+shippingCostIncd);
	    	assertEquals(vFlatRatePrice,shippingCostIncd);
	    }catch (Exception e){
	    	e.printStackTrace();
	    }
	 
	 //Proceed T check out
	 driver.findElement(By.cssSelector("button[title=\"Proceed to Checkout\"]")).click();
	
	 //check drop down menu is there
	 try{
		 Select dropDown = new Select(driver.findElement(By.id("billing-address-select")));
		 int bilAddSize  = dropDown.getOptions().size();
		 dropDown.selectByIndex(bilAddSize-1); 
	 }catch (Exception e){
			//e.printStackTrace();
	    	System.out.println("No dropdown element present");
	 }
	 
	 
	 
	//Billing Info
	 driver.findElement(By.id("billing:street1")).sendKeys("Golahalli");
	 driver.findElement(By.id("billing:city" )).sendKeys("Bangalore");
	 driver.findElement(By.id("billing:postcode")).sendKeys("560100");
	 new Select(driver.findElement(By.cssSelector("select[title=\"Country\"]"))).selectByVisibleText("India");
	 driver.findElement(By.id("billing:telephone")).sendKeys("8762137283");
	
	 //Click same Address
	 driver.findElement(By.id("billing:use_for_shipping_yes")).click();
	 
	 //Click continue
	 driver.findElement(By.xpath(".//*[@id='billing-buttons-container']/button")).click();           
	 
	 //shipping method continue
	 driver.findElement(By.cssSelector("#shipping-method-buttons-container button.button")).click();	
	 
	 //click check/MO
	 driver.findElement(By.id("p_method_checkmo")).click();
	 
	 //click continue
	 driver.findElement(By.cssSelector("#payment-buttons-container button.button")).click();
	 
	 //Place order
	 driver.findElement(By.cssSelector("#review-buttons-container button[title=\"Place Order\"]")).click();
	 
	 //verify order is placed
	 String orderNo=driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[2]/div/div/p[1]/a")).getText();

	 System.out.println("*** Your order number for your record = " + orderNo);

}	 




 @BeforeMethod
 public void gertPage(){
  	driver.get(EcomUtil.Url);
	
} 

  @BeforeTest
  	  public  void   launcbroserFrom() {	  
				/*	File pathToBinary = new File(EcomUtil.FIREFOX_PATH);
					FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
					FirefoxProfile firefoxProfile = new FirefoxProfile();
					driver = new FirefoxDriver(ffBinary, firefoxProfile);
					driver.manage().timeouts()
					.implicitlyWait(EcomUtil.WAIT_TIME, TimeUnit.SECONDS);
				driver.get(EcomUtil.Url);*/
	 	EcomUtil.launcFirefoxbrowser();
	 	driver = EcomUtil.driver;
			    }
	
  @AfterTest
  public void afterTest() {
 	      driver.quit();
  }
  
 
  
}