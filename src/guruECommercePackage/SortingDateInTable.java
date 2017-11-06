package guruECommercePackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;


class SortingDateInTable  implements Comparable<SortingDateInTable>{

	
 	public static Comparator date_COMPARATOR;
	private String[]  invoiceDetail  =null;
	Date invoiceDate;  
	private String invoiceDateString ;

	public SortingDateInTable(String invoiceDetail) throws ParseException{		
		this.invoiceDetail = invoiceDetail.split(" ");
		this.invoiceDateString  =  this.invoiceDetail[1];

		for(int i=2;i<6;i++){
			 System.out.println("***  Elements  "+this.invoiceDetail[i] );
			 this.invoiceDateString  = this.invoiceDateString+" " + this.invoiceDetail[i];

		}
		 System.out.println("***  Elements  "+this.invoiceDateString );
		 
		 SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");  
		 try{         
			 this.invoiceDate = format.parse(this.invoiceDateString);
		 }
		 catch (ParseException e){
			 e.printStackTrace();
		 }
		 System.out.println("***  DATE  "+this.invoiceDate );
	 
	}
	
	
	 
		 
	@Override
	public int compareTo(SortingDateInTable o1) {
		// TODO Auto-generated method stub
		
		 int lastCmp = invoiceDate.compareTo(o1.invoiceDate);
	//       return (lastCmp != 0 ? lastCmp : firstName.compareTo(n.firstName));
		
		return lastCmp;
	}
}