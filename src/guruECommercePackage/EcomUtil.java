package guruECommercePackage;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class EcomUtil {

	public  static final String FIREFOX_PATH =  "D:\\Program Files\\Mozilla Firefox\\firefox.exe" ;
	public  static final String USER_NAME = "mngr83695";
	public  static final String PASSWD = "uhyjuqA";
	public  static final String Url     = "http://live.guru99.com/index.php/";
	public  static final String downloadPath ="D:\\SampleExcel";
	public static WebDriver driver;
	
	 public static final String firstName = "Jessie Rani";    // These testdata stuff will be placed    
	 public static final String lastName = "Thangiah";  // in a TestData EXCEL file at some stage
	  public static final  String vEmail = "vt.jessie@yahoo.com";
	  public static final String vPW = "Infant123";
	
	// Expected output
 	public static final String EXPECT_TITLE1 = "Mobile";
	public static final String EXPECT_ERROR = "User or Password is not valid";
	
	// Time to wait when searching for a GUI element 
	public static final int WAIT_TIME = 30; 
											

	// Valid account for login
	public static final String USER_NAME1 = "mngr1336";
	public static final String PASSWD1 = "dAnavUq";


	// Expected output
 	public static final String EXPECT_TITLE = "Home page";
	public  static final String EXPECT_TITLEH = "THIS IS DEMO SITE FOR   ";
 	public  static void   launcFirefoxbrowser() {	  
		 	File pathToBinary = new File(EcomUtil.FIREFOX_PATH);
			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		 	FirefoxProfile firefoxProfile = new FirefoxProfile(); 
			
 		 	String file_types = "application/acrobat, application/nappdf, application/x-pdf, application/vnd.pdf, text/pdf, text/x-pdf, application/vnd.sealedmedia.softseal.pdf" +
					"text/comma-separated-values,text/csv, text/pdf, application/pdf, application/x-msdos-program, application/x-unknown-application-octet-stream,"
					+ "application/vnd.ms-powerpoint, application/vnd.ms-publisher, application/x-unknown-message-rfc822, application/vnd.ms-excel,"
					+ "application/msword, application/x-mspublisher, application/x-tar, application/zip, application/x-gzip, application/x-stuffit,"
					+"application/vnd.ms-works, application/powerpoint, application/rtf, application/postscript, application/x-gtar,"
					+ "video/quicktime, video/x-msvideo, video/mpeg, audio/x-wav, audio/x-midi, audio/x-aiff, text/plain, application/vnd.ms-excel [official],"
					+ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/msexcel, application/x-msexcel,"
					+ "application/x-excel, application/excel, application/x-ms-excel, application/x-dos_ms_excel,"
					+ "text/csv, text/comma-separated-values, application/octet-stream, application/haansoftxls application/xml, application/xhtml+xml,"
					+ "application/pdf, application/x-pdf, application/acrobat, applications/vnd.pdf, text/pdf, text/x-pdf,"
					+ "application/vnd.ms-excel (official), application/msexcel, application/x-msexcel, application/x-ms-excel, application/x-excel, application/x-dos_ms_excel, application/xls, application/x-xls, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet (xlsx),"
					+ "application/softgrid-xls, x-softmaker-pm, text/tab-separated-values, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel (official)";
			 
	 		
		 	
	 	 	firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",file_types);

	 	 	/*

	  	 	firefoxProfile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);

	 	 	firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
	        	  
		  	firefoxProfile.setPreference("plugin.disable_full_page_plugin_for_types", "application/pdf");
		
		//NOT WORKING HERE
	        firefoxProfile.setPreference("browser.helperApps.neverAsk.openFile", "application/pdf;");

	    //   # disable Adobe Acrobat PDF preview plugin
	        firefoxProfile.setPreference("plugin.scan.plid.all", false);
	        firefoxProfile.setPreference("plugin.scan.Acrobat","99.0");	    
	        
	        //Accept SSL certificate errors

	         firefoxProfile.setAcceptUntrustedCertificates(true);
	         firefoxProfile.setAssumeUntrustedCertificateIssuer(false); 
	        
	       */
	       

	        firefoxProfile.setPreference( "pdfjs.disabled", true );

	        firefoxProfile.setPreference("browser.download.folderList", 2);
	        
	        
	        firefoxProfile.setPreference("browser.download.dir", downloadPath);
	        	        	        	      	 	
	        driver = new FirefoxDriver(ffBinary, firefoxProfile);
	         
			driver.manage().timeouts().implicitlyWait(EcomUtil.WAIT_TIME, TimeUnit.SECONDS);
		 
		 //	driver.get(EcomUtil.Url);
	    } 
	public  static void   launcChromebrowser() throws Exception {	
	
  	     	   	System.setProperty("webdriver.chrome.driver", "D:\\Divi\\Programs\\Google\\Chrome\\Application\\chrome.exe");
  	     	  
  	     	   	//System.setProperty("webdriver.chrome.driver","D:\\Divi\\Personal\\Java\\Workspace\\TestNGProject\\chromedriver.exe");
 	  	
  	     	   	//System.setProperty("webdriver.chrome.driver", "D:\\divine\\Selenium Guru\\GURUECommerce\\chromedriver_win32\\chromedriver.exe");
 	 
 	      
  	     	   	driver = new ChromeDriver();
	     	   	driver.manage().deleteAllCookies();
	     	   	driver.get("http://live.guru99.com/");


	}
	
	
	public static  File getLatestFilefromDir(String dirPath){
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }

	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile;
	}
	
	public static void billingAndOrderGen() throws  Exception
	{
		
		 Thread.sleep(1000);
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

}
