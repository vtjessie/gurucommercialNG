package guruECommercePackage;

import guruECommercePackage.EcomUtil;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;


public class ProductReview {
	
	public WebDriver  driver;	
	
	static final  Comparator<SortingDateInTable> date_COMPARATOR = new Comparator<SortingDateInTable>() {

	//	@override
		public int compare( SortingDateInTable o1 , SortingDateInTable o2 ){
	            return o2.invoiceDate.compareTo(o1.invoiceDate );
		}
	 };
		
  @Test( invocationCount=1, priority=1)
  public void productReview() throws InterruptedException, IOException {
	//  "1. Go to http://live.guru99.com/.
	  driver.get(EcomUtil.Url);
	  System.out.println("My name is Divi");
	//  2. Go To Link - http://live.guru99.com/index.php/review/product/list/id/1/
	  driver.get("http://live.guru99.com/index.php/review/product/list/id/1/");
	//  3. Fill the required review and submit it
	  driver.findElement(By.cssSelector("#review_field")).sendKeys("");
	  driver.findElement(By.cssSelector("#review_field")).sendKeys("It is cute, nice audio quality, durable.");
	  driver.findElement(By.cssSelector("#summary_field")).sendKeys("");
	  driver.findElement(By.cssSelector("#summary_field")).sendKeys("GREAT");
	  driver.findElement(By.cssSelector("#nickname_field")).sendKeys("");
	  driver.findElement(By.cssSelector("#nickname_field")).sendKeys("RICKY");
	  driver.findElement(By.xpath(".//*[@id='review-form']/div[2]/button")).click();

	//  4.Go to http://live.guru99.com/index.php/backendlogin
	  driver.get("http://live.guru99.com/index.php/backendlogin");
	//  5.Login as with credentials provided
	  driver.findElement(By.xpath("//*[@id='username']")).clear();
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("user01");
		driver.findElement(By.xpath("//*[@id='login']")).clear();
		driver.findElement(By.xpath("//*[@id='login']")).sendKeys("guru99com");
		driver.findElement(By.xpath("//*[@class='form-button']")).click();
		// switching to new window
	    for (String handle : driver.getWindowHandles()) {
	    driver.switchTo().window(handle);
	    
	    }
	    
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/a/span")).click();
	    
	//  6. Go to Catalogue -> Reviews and Ratings -> Customer Reviews ->Pending Reviews Menu
	    driver.findElement(By.linkText("Catalog")).click();
	    driver.findElement(By.linkText("Reviews and Ratings")).click();
	    driver.findElement(By.linkText("Customer Reviews")).click();
	    driver.findElement(By.linkText("Pending Reviews")).click();
	    
	//  7.Sort table by Id and select comment then click on Edit link
	    driver.findElement(By.linkText("ID")).click();
	    driver.findElement(By.xpath(".//*[@id='reviwGrid_table']/tbody/tr[1]/td[10]/a[contains(@href,'catalog_product_review/edit/ret/pending/id')]")).click();

	    //  8.Change status to 'Approved' and click ""Save Review""
		String reviewAct = driver.findElement(By.cssSelector("#review_details #detail")).getText();
		 System.out.println("*** reviewAct = " +reviewAct);

	    new Select(driver.findElement(By.id("status_id"))).selectByVisibleText("Approved");
	    driver.findElement(By.id("save_button")).click(); 
 
	 //   9.Go to http://live.guru99.com/. Click Mobile Menu
	    driver.get(" http://live.guru99.com/");
	    driver.findElement(By.linkText("MOBILE")).click();
	    
	 //   10. Click on Sony Xperia image.
	 //   driver.findElement(By.xpath(".//*[@id='product-collection-image-1' AND @alt='Xperia']")).click();
	    driver.findElement(By.xpath(".//img[@alt='Xperia']")).click();
	    Thread.sleep(1000);

	  //  11. In detail page click on Review tab at bottom of page	
		 driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[2]/div[2]/ul/*[@class='last']/span")).click();

		 //  12. Verify the review comment is shown"
		    Thread.sleep(1000);
		/*    String reviewExp = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div[2]/div[2]/dl/dd[2]/div/div/dl/dd[106]")).getText();
	     
		 try{
				assertEquals(reviewExp,reviewAct);
			}catch (Exception e){
				e.printStackTrace();
			}

		 */
	  		 System.out.println("*** Copying..Starting " );
	  		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  		 FileUtils.copyFile(scrFile, new File("D:\\divine\\Selenium Guru\\Mobile Review.png"));
	  		 System.out.println("*** Copying..Ending " );

  }	 
	  
  @Test( invocationCount=1, priority=1)
  public void verifySort() throws InterruptedException, IOException  {
  //1.Go to http://live.guru99.com/index.php/backendlogin
	  driver.get("http://live.guru99.com/index.php/backendlogin");
	  	  
  //2.Login with credentials provided
      Thread.sleep(1000);
      if (driver.findElements(By.id("username")).size()>0){

	  	driver.findElement(By.xpath("//*[@id='username']")).clear();
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("user01");
		driver.findElement(By.xpath("//*[@id='login']")).clear();
		driver.findElement(By.xpath("//*[@id='login']")).sendKeys("guru99com");
		driver.findElement(By.xpath("//*[@class='form-button']")).click();
		// switching to new window
	    for (String handle : driver.getWindowHandles()) {
	    driver.switchTo().window(handle);
	    
	    }
	    
	    //TO CLOSE Incoming  Msg POP UP
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/a/span")).click();
      }
	    // 3.Go to Sales Invoice
	    // 3 a ) SALES
		driver.findElement(By.linkText("Sales")).click();
		//3 b) Invoice
		driver.findElement(By.xpath("//*[@id='nav']/li[1]/ul/li[2]/a/span")).click();


		// 4.Sort Invoice Date Column in ascending and descending order
		//4 a) ASC
	 	driver.findElement(By.linkText("Invoice Date")).click();
		
		
	 	// 5.Verify the sort is working as expected"

	 	// 4 a) ASCENDING
		ArrayList<String> obtainedList = new ArrayList<>(); 
		WebElement table = driver.findElement(By.xpath("//*[@id='sales_invoice_grid_table']/tbody"));

	 	List<WebElement> elementList= table.findElements(By.cssSelector("tr"));		 
		for(WebElement we:elementList){
		   obtainedList.add(we.getText());

		}
		List<SortingDateInTable> invoiceDetails = new ArrayList(obtainedList);
        Collections.sort(invoiceDetails,SortingDateInTable.date_COMPARATOR );
        System.out.println("*** ASCENDING  "  );

        System.out.println(invoiceDetails);
        Assert.assertTrue(invoiceDetails.equals(obtainedList));
 		
 		//4 b) DSC
        driver.findElement(By.xpath("//*[@id='sales_invoice_grid_table']/thead/tr[1]/th[3]/span/a/span")).click();
        Thread.sleep(1000);
 		obtainedList = new ArrayList<>();
 	   	table = driver.findElement(By.xpath("//*[@id='sales_invoice_grid_table']/tbody"));
 	 	elementList= table.findElements(By.cssSelector("tr"));
		for(WebElement we:elementList){
 		   obtainedList.add(we.getText());
 		}
 		
 		invoiceDetails = new ArrayList(obtainedList);
		Collections.sort(invoiceDetails,SortingDateInTable.date_COMPARATOR );
		Collections.sort(invoiceDetails,Collections.reverseOrder());
		Assert.assertTrue(invoiceDetails.equals(obtainedList));  
  	 	 System.out.println("*** DESCENDING  "  );
  	 	 System.out.println(invoiceDetails);		
  }
  
  @Test( invocationCount=1, priority=1)
  public void searchFunctionality() throws InterruptedException{
	
	//1. Go to http://live.guru99.com/index.php/
		  driver.get("http://live.guru99.com/index.php/");

  //	2. Click on Advance Search
		  driver.findElement(By.linkText("ADVANCED SEARCH")).click();

 //	3. In Price field enter range 0-150. Click Search
		  driver.findElement(By.id("name")).sendKeys("IPHONE");
		  driver.findElement(By.id("description")).sendKeys("Quality");
		  driver.findElement(By.id("sku")).sendKeys("sku");
		
		  driver.findElement(By.id("price")).sendKeys("0");
		  driver.findElement(By.id("price_to")).sendKeys("150");
		  driver.findElement(By.xpath("//*[@id='form-validate']/div[2]/button/span")).click();

		  //	4. Note the Price and Product Name in the result. Print on console

		  String name=driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/ul[1]/li[1]")).getText();
	 	  String description=driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/ul[1]/li[2] ")).getText();

	  	  System.out.println( name  );
	  	  System.out.println( description  );
	   	  driver.findElement(By.linkText("ADVANCED SEARCH")).click();


		  //	5. Again, In Price field enter range 151-1000. Click Search
	 	//  driver.findElement(By.linkText("ADVANCED SEARCH")).click();

	  	  driver.findElement(By.id("name")).sendKeys("IPHONE");
		  driver.findElement(By.id("description")).sendKeys("Quality");
		  driver.findElement(By.id("sku")).sendKeys("sku");
		
		  driver.findElement(By.id("price")).sendKeys("151");
		  driver.findElement(By.id("price_to")).sendKeys("1500");
	   	  driver.findElement(By.xpath ("//*[@id='form-validate']/div[2]/button")).click();


 //	6. Note the Price and Product Name in the result. Print on console 
		   name=driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/ul[1]/li[1]")).getText();
		   String cost=driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/ul[2]/li[2] ")).getText();
		  	  System.out.println( name  );
		  	  System.out.println( cost  );

 
  }
  
  @Test( invocationCount=1, priority=1)
  public void verifyFields() throws InterruptedException{
	
//	1.Go to http://live.guru99.com/index.php/backendlogin
	  driver.get("http://live.guru99.com/index.php/backendlogin");

// 	2.Login with credentials provided
	  
      Thread.sleep(1000);
      if (driver.findElements(By.id("username")).size()>0){
      driver.findElement(By.xpath("//*[@id='username']")).clear();
      driver.findElement(By.xpath("//*[@id='username']")).sendKeys("user01");
      driver.findElement(By.xpath("//*[@id='login']")).clear();
      driver.findElement(By.xpath("//*[@id='login']")).sendKeys("guru99com");
      driver.findElement(By.xpath("//*[@class='form-button']")).click();
      
      // switching to new window
      for (String handle : driver.getWindowHandles()) {
    	  driver.switchTo().window(handle);		    
      }
		    
      //TO CLOSE Incoming  Msg POP UP
      driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/a/span")).click();
      }     
      //3.Go to Customers-> Manage Customers menu
      driver.findElement(By.linkText("Customers")).click();
      driver.findElement(By.linkText("Manage Customers")).click();
		     
      //4.Open any customer's detail by clicking on view link in the grid
      driver.findElement(By.xpath("//*[@id='customerGrid_table']/tbody/tr[1]/td[3]")).click();
		     
      //	5.Click on 'Account Information' tab for the customer's detail page
      driver.findElement(By.linkText("Account Information")).click();

      //	6. Verify disabled fields
      WebElement table = driver.findElement(By.cssSelector("#customer_info_tabs_account_content"));
      List<WebElement> elementList= table.findElements(By.cssSelector("input"));	
      ArrayList<String> obtainedList = new ArrayList<>(); 

      for(WebElement we:elementList){	 				
	 				if ( !(we.isEnabled())){
					   obtainedList.add(we.getTagName()) ;
					   obtainedList.add(we.getAttribute("id"));

	 				}
	 			}
			  	  System.out.println("disabled fields" +obtainedList  );

				
			  //	7. Verify Blank fields"
			  	 table = driver.findElement(By.cssSelector("#customer_info_tabs_account_content"));
				 elementList= table.findElements(By.cssSelector("input"));	
			  	obtainedList=new ArrayList<>();
			  	String s=null;
			  	for(WebElement we:elementList){
			  		s =we.getAttribute("id");
				 // 	System.out.println("<<<<<<<fields" +s );
				  	if (we.getAttribute("value").isEmpty()){
						   obtainedList.add(we.getTagName());
						   obtainedList.add(we.getAttribute("id"));

					   }
	 			}
			   	  System.out.println( "Blank fields"+obtainedList  );
  }
  
  @BeforeMethod
  public void beforeMethod()  {
	//  driver.get(EcomUtil.Url);
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeTest
  public void lauchSetUp() throws Exception {
	  EcomUtil.launcFirefoxbrowser();
	  driver = EcomUtil.driver; 
  }

  @AfterTest
  public void afterTest() {
	      driver.quit();

  }

}
