package guruECommercePackage;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//import reporter.JyperionListener;

//Add listener for pdf report generation



import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.AfterSuite;


public class ExcelReportMail {
	
    WebDriver driver;
 
  @AfterSuite
//@Test
    public void tearDown(){
		
        sendPDFReportByGMail("vt.jessie@gmail.com", "mashere123", "infant.derrick.gnanasusairaj@gmail.com", "PDF Report", "");
        System.out.println("Test Successful");
	}
    

    /**
     * Send email using java
     * @param from
     * @param pass
     * @param to
     * @param subject
     * @param body
     */

    private static void sendPDFReportByGMail(String from, String pass, String to, String subject, String body) {

    	 //1) get the session object
    		
    		
		String host =  "smtp.gmail.com";
//		int port = 465;//465
		Properties props = new Properties(); 
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.starttls.enable", "true");
	/*	props.put("mail.smtp.port", port);
		props.put("mail.smtp.socketFactory.port", port);    
		props.put("mail.smtp.socketFactory.class",    
                        "javax.net.ssl.SSLSocketFactory"); 
		props.put("mail.smtp.socketFactory.fallback", "false");

		props.put("mail.transport.protocol", "smtp");
		*/
    		
    	// 	Session session = Session.getDefaultInstance(props);
		Session session = Session.getDefaultInstance(props,  
    				   new  javax.mail.Authenticator() {  
    				   protected PasswordAuthentication getPasswordAuthentication() {  
    				   return new PasswordAuthentication(from,pass);  
    				   }  
    				  });  
    	 	
    	 	//2) Set subject    compose message
    		MimeMessage message = new MimeMessage(session);
    		
    		try {

    		
    			message.setFrom(new InternetAddress(from));
    			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
    		
    			
    			message.setSubject(subject);
    			message.setText(body);
    			

    		    //3) create MimeBodyPart object and set your message text 
    			BodyPart objMessageBodyPart1 = new MimeBodyPart();
    			objMessageBodyPart1.setText("Please Find The Attached Report File!");
    			
    			Multipart multipart = new MimeMultipart();
    			multipart.addBodyPart(objMessageBodyPart1);
    			
    			
    			//4) create new MimeBodyPart object and set DataHandler object to this object      
    			BodyPart objMessageBodyPart2 = new MimeBodyPart();

    			//Set path to the pdf report file

    			//String filename = System.getProperty("user.dir")+"\\SampleExcel\\orders.csv";
    			String filename = "\\SampleExcel\\orders.csv";

    			//Create data source to attach the file in mail

    			DataSource source = new FileDataSource(filename);
    			
    			objMessageBodyPart2.setDataHandler(new DataHandler(source));
    			objMessageBodyPart2.setFileName(filename);
    			
    			multipart.addBodyPart(objMessageBodyPart1);
    			multipart.addBodyPart(objMessageBodyPart2);


    		    //6) set the multiplart object to the message object 
    			message.setContent(multipart);
    		/*	
    			Transport transport = session.getTransport("smtp");
    			transport.connect(host,port ,from, pass);
    			transport.sendMessage(message, message.getAllRecipients());
    			transport.close();  
    		*/	
    			   //7) send message  
    		    Transport.send(message );

    			System.out.println("=====Email Sent=====");
    		}

    	

    		catch (MessagingException me) {
    			me.printStackTrace();
    		}

    }
    
 

	    

	  

	    

 

}
