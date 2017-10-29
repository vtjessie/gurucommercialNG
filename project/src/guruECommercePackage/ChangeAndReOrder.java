package guruECommercePackage;

import guruECommercePackage.EcomUtil;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper; 
import org.apache.pdfbox.text.PDFTextStripperByArea;
import au.com.bytecode.opencsv.CSVReader;

import java.util.Iterator;
import java.util.List;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class ChangeAndReOrder {
	
	public WebDriver  driver;
	
  @Test( invocationCount=1, priority=1) 
  public void changeAndReOrder() throws Exception {
		  driver.findElement(By.linkText("MY ACCOUNT")).click();
			driver.findElement(By.cssSelector("#email"  )).sendKeys("vt.jessie@yahoo.com");
			driver.findElement(By.cssSelector("#pass"  )).sendKeys("Infant123");
			driver.findElement(By.cssSelector("#send2"  )).click();
			String beforeReOrder =driver.findElement(By.xpath(".//*[@id='my-orders-table']/tbody/tr[1]/td[4]/span")).getText();
			driver.findElement(By.xpath(".//*[@id='my-orders-table']//span/a[@class='link-reorder']")).click();

			System.out.println("beforeReOrder Amount  "+"|| "+beforeReOrder);
			
			driver.findElement(By.linkText("Edit")).click();
			
			driver.findElement(By.id("qty")).clear();
			driver.findElement(By.id("qty")).sendKeys("10");
			driver.findElement(By.cssSelector("button[title='Update Cart']")).click();
			String afterReOrder = driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[5]/span/span")).getText();

			System.out.println("afterReOrder Amount  "+"|| "+afterReOrder);
			
		
				
			    	System.out.println("beforeReOrder = "+beforeReOrder);
			    	System.out.println("afterReOrder = "+afterReOrder);
				 if (afterReOrder!=beforeReOrder) {
				    	System.out.println("ReOrder is Updated ");}
				    	else{
					    	System.out.println("ReOrder is not Updated ");

				    	}

				 //Proceed T check out
				 driver.findElement(By.xpath("//*[@id='top']//li[@class='method-checkout-cart-methods-onepage-bottom']/button")).click();
				
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
	@Test( invocationCount=1, priority=2) 
	public void  discountCoupon() throws Exception{
		driver.findElement(By.linkText("MOBILE")).click();
		
		// switching to new window
	    for (String handle : driver.getWindowHandles()) {
	    driver.switchTo().window(handle);
	    }
	    Thread.sleep(3000);
	 	 driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/button")).click();
		String actAmount = driver.findElement(By.xpath("//*[@id='shopping-cart-totals-table']/tbody/tr[1]/td[2]/span")).getText();
		float actAmt = Float.parseFloat(actAmount.substring(1));
		System.out.println("actAmount INTAmount  "+"|| "+actAmount +"|| "+actAmt);
		float discount =  (float) (actAmt * 0.05) ;
		float afterdiscountAmount = actAmt - discount;
		System.out.println("actAmount afterdiscountAmount  "+"|| "+actAmount +"|| "+afterdiscountAmount);

		
	 	 driver.findElement(By.xpath(".//*[@id='discount-coupon-form']//*[@id='coupon_code']")).clear();

		driver.findElement(By.xpath(".//*[@id='discount-coupon-form']//*[@id='coupon_code']")).sendKeys("GURU50");
		driver.findElement(By.xpath(".//*[@id='discount-coupon-form']/div/div/div/div/button")).click();

		String couponAcc = driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div/div/ul/li/ul/li/span")).getText();
		System.out.println("couponAcc Amount  "+"|| "+couponAcc);
		String grandTotal =driver.findElement(By.xpath("//*[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span")).getText();
		float grandAmt = Float.parseFloat(grandTotal.substring(1));
		
		System.out.println("grandAmt Amount  "+"|| "+grandAmt);


		if (afterdiscountAmount == grandAmt){
			System.out.println("couponAcc Amount is not  included ");
		}
		else{
			System.out.println("couponAcc Amount is not  included ");
		}
		
			

			
	}
	

	
	@Test( invocationCount=1, priority=3)  
	public void orderInfoEmail() throws IOException, InterruptedException{
	 	driver.get("live.guru99.com/index.php/backendlogin");
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
	    
		driver.findElement(By.linkText("Sales")).click();
		
		driver.findElement(By.xpath("//*[@id='nav']/li[1]/ul/li[1]/a/span")).click();
		new Select(driver.findElement(By.id("sales_order_grid_export"))).selectByVisibleText("CSV");
		driver.findElement(By.xpath("//*[@id='sales_order_grid']/table/tbody/tr/td[2]/button")).click();
		int cnt=0;
		 for (String handle : driver.getWindowHandles()) {
			    driver.switchTo().window(handle);
			    String titlePopup = driver.getCurrentUrl();
			    cnt++;
				System.out.println("POPUP  windows    titlePopup     "+"|| "+ titlePopup + cnt);

			    }
	
		 String csvFileName = EcomUtil.downloadPath+"\\orders.csv" ;
		 
		 // This will load csv file 
		// CSVReader reader = new CSVReader(new FileReader("D:\\SampleExcel\\orders.csv"));
		
			CSVReader reader = new CSVReader(new FileReader(csvFileName));

		 // this will load content into list
		  List<String[]> li=reader.readAll();
		  System.out.println("Total rows which we have is "+li.size());
		            
		 // create Iterator reference
		  Iterator<String[]>i1= li.iterator();
		    
		 // Iterate all values 
		 while(i1.hasNext()){
		     
		 String[] str=i1.next();
		   
		 System.out.print(" Values are ");
		 
		 for(int i=0;i<str.length;i++) 
		{
		 
		   System.out.print(" "+str[i]);
		 
		}
		   System.out.println("   ");
		     
		    
		}
		    Thread.sleep(3000);

	}
	 
	 
	 
	@Test( invocationCount=1, priority=4)  
		public void invoicePrint() throws IOException, InterruptedException{
			
			driver.get("live.guru99.com/index.php/backendlogin");
			if (driver.findElements(By.id("username")).size()>0)
			{
			driver.findElement(By.xpath("//*[@id='username']")).clear();
			driver.findElement(By.xpath("//*[@id='username']")).sendKeys("user01");
			driver.findElement(By.xpath("//*[@id='login']")).clear();
			driver.findElement(By.xpath("//*[@id='login']")).sendKeys("guru99com");
			driver.findElement(By.xpath("//*[@class='form-button']")).click();
			
			}
			// switching to new window
		    for (String handle : driver.getWindowHandles()) {
		    driver.switchTo().window(handle);
		    
		    }
		
		 //   driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/a/span")).click();
			driver.findElement(By.linkText("Sales")).click();
			
			driver.findElement(By.xpath("//*[@id='nav']/li[1]/ul/li[1]/a/span")).click(); 
			
			//Select CANCELLED status
			new Select(driver.findElement(By.id("sales_order_grid_filter_status"))).selectByVisibleText("Canceled");
			driver.findElement(By.xpath(".//*[@id='sales_order_grid']/table/tbody/tr/td[3]/button[2]")).click();
			
			Thread.sleep(3000);
			
			//Click First Order
			driver.findElement(By.xpath(".//*[@id='sales_order_grid_table']/tbody/tr[1]/td[1]/input")).click();
			
			Thread.sleep(3000);
			
			//Select PrintInvoices
			new Select(driver.findElement(By.id("sales_order_grid_massaction-select"))).selectByVisibleText("Print Invoices");
			driver.findElement(By.xpath(".//*[@id='sales_order_grid_massaction-form']/fieldset/span[4]/button")).click();
			
			//Error msg
			String printErExpr="There are no printable documents related to selected orders.";
			String printErAct=driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[1]/ul/li/ul/li/span")).getText();
			try{
				assertEquals(printErExpr,printErAct);
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
			
			
		    
			//Select COMPLETE status
			//new Select(driver.findElement(By.id("sales_order_grid_filter_status"))).
			new Select(driver.findElement(By.id("sales_order_grid_filter_status"))).selectByVisibleText("Complete");
			System.out.println("Selected COMPLETE status     " );
			
			// switching to new window
		    for (String handle : driver.getWindowHandles()) {
		    driver.switchTo().window(handle);
		    }
		
		    // PAGEUP
		   
	 	    JavascriptExecutor js = ((JavascriptExecutor) driver);
		    js.executeScript("window.scrollBy(0,-250)", "");		    
	
		    /*  	Robot robot = new Robot();
		    robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		    robot.keyRelease(KeyEvent.VK_PAGE_DOWN);*/
		
		    //Select SEARCH
			Thread.sleep(3000);	
		 	driver.findElement(By.xpath(".//*[@id='sales_order_grid']/table/tbody/tr/td[3]/button[2]")).click();
			System.out.println("Selected SEARCH button     " );
			Thread.sleep(2000);

			//Select First ORDER
			driver.findElement(By.xpath(".//*[@id='sales_order_grid_table']/tbody/tr[1]/td[1]/input")).click();
		
			//Select PrintInvoices
			new Select(driver.findElement(By.id("sales_order_grid_massaction-select"))).selectByVisibleText("Print Invoices");
			driver.findElement(By.xpath(".//*[@id='sales_order_grid_massaction-form']/fieldset/span[4]/button")).click();
			
		    Thread.sleep(7000);
		    
	//	    Path child = dir.resolve(filename);
		    File getLatestFile = EcomUtil.getLatestFilefromDir(EcomUtil.downloadPath);
		    String fileName = getLatestFile.getName();
		    
		    
	        File f=new File(EcomUtil.downloadPath+"\\"+fileName);

	        OutputStream oos = new FileOutputStream("test.pdf");

	        byte[] buf = new byte[8192];

	        InputStream is = new FileInputStream(f);

	        int c = 0;

	        while ((c = is.read(buf, 0, buf.length)) > 0) {
	            oos.write(buf, 0, c);
	            oos.flush();
	        }

	        oos.close();
	        System.out.println("stop");
	        is.close();
	        
	        try {
	            PDDocument document = null;
	            document = PDDocument.load(new File("test.pdf"));
	            document.getClass();
	            if (!document.isEncrypted()) {
	                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
	                stripper.setSortByPosition(true);
	                PDFTextStripper Tstripper = new PDFTextStripper();
	                String st = Tstripper.getText(document);
	                System.out.println("Text:" + st);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	     
			

	 } 
	 
  @BeforeMethod
  public void beforeMethod() {
	  String baseUrl = "http://live.guru99.com/index.php/backendlogin";
	  driver.get(EcomUtil.Url);
  }

  @AfterMethod
  public void afterMethod() {
	//  driver.get(EcomUtil.Url);
  }

  @BeforeTest
  public void lauchSetUp() throws Exception {
	  EcomUtil.launcFirefoxbrowser();
//	  EcomUtil.firefoxSavePDF();
	  driver = EcomUtil.driver; 
 	//	 driver.get("http://live.guru99.com/");
  }

  @AfterTest
  public void afterTest() {
  	     driver.quit();
  }

}
