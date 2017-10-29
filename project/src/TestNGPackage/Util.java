package TestNGPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class Util {
	public  static final String FIREFOX_PATH =  "D:\\Program Files\\Mozilla Firefox\\firefox.exe" ;
	public  static final String USER_NAME = "mngr83695";
	public  static final String PASSWD = "uhyjuqA";
	public  static final String Url     = "http://www.demo.guru99.com/V4/";
	public  static final String BASE_URL = "http://www.demo.guru99.com";
	
	// Expected output
//	public static final String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
	public static final String EXPECT_ERROR = "User or Password is not valid";
	
	// Time to wait when searching for a GUI element 
	public static final int WAIT_TIME = 30; 
											

	// Valid account for login
	public static final String USER_NAME1 = "mngr1336";
	public static final String PASSWD1 = "dAnavUq";


	// Expected output
	public static final String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
	

	// You can change the information of your data file here
 	public static String  xlFilePath  =  "D:\\divine\\Selenium Guru";
 	public static String  fileName   =  "GuruCredential.xlsx";
 	public static String filePathXl  =xlFilePath+"\\"+fileName;
 	
	public static final String SHEET_NAME = "Sheet1"; // Sheet name
	public static final String TABLE_NAME = "testData"; // Name of data table
	public static   Object[][] tabArray = null;
	








	public static String[][] getDataFromExcel(String filepath,String sheetName )throws Exception, IOException {
		// Declare a 2 dimensions array to store all the test data read from
		// excel
		String[][] tabArray = null;
		  File file =    new File(filepath); 
         FileInputStream inputStream = new FileInputStream(file);
		// get the workbook file. Provide the path of excel file
		 Workbook logincredential =new XSSFWorkbook(inputStream);
		// get the sheet name
		Sheet sheet = logincredential.getSheet(sheetName);

		 int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		 System.out.println("rowCount  "+"|| "+rowCount);

		 
		 tabArray = new String[rowCount+1][2];
		 
		  for (int i=0; i<=rowCount;i++){		

			  Row row = sheet.getRow(i);  
			  tabArray[i][0] =row.getCell(0).getStringCellValue();
			  tabArray[i][1]= row.getCell(1).getStringCellValue();
		  }
			return (tabArray);
	}
}
 


 
