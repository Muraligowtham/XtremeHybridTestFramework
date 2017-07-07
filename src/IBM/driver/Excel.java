package IBM.driver;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Excel {
	Sessions ses;
	public  Excel(String Testcaseid){
		ses=ExecutionInstance.getSession(Testcaseid);
		
	}
	
	 public synchronized  void captureScreenLable(WebDriver driver, String TestCaseID) throws Exception 
	    { 
		 try{
	    	int dates,day, month, year;
			int second, minute, hour;
			String a;
			GregorianCalendar date = new GregorianCalendar();
			dates=date.get(Calendar.DATE);
			day = date.get(Calendar.DAY_OF_MONTH);
			String daystring = Integer.toString(day);
			month = date.get(Calendar.MONTH);
			String monthstirng = Integer.toString(month);
			second = date.get(Calendar.SECOND);
			String secondstring = Integer.toString(second);
			minute = date.get(Calendar.MINUTE);
			String minutestring = Integer.toString(minute);
			hour = date.get(Calendar.HOUR);
			String hourstring = Integer.toString(hour);
		

			String append = TestCaseID+"_"+daystring+monthstirng+hourstring+minutestring+secondstring;

		
	    	
	    	String folderpath,Finalpath,screenshotfolder;
	    	screenshotfolder="D:\\Screenshot";
	    	File dir3 = new File(screenshotfolder);
	    	
	    	if(dir3.exists()){
	    		System.out.println("date folder present");
	    	}
	    	else{
	        dir3.mkdir();
	        }
	    	
	    	
	    	folderpath="D:\\Screenshot\\"+dates+"_"+monthstirng;
	    	
	    	Finalpath=folderpath+"\\"+TestCaseID;
	    	File dir = new File(folderpath);
	    	
	    	if(dir.exists()){
	    		System.out.println("date folder present");
	    	}
	    	else{
	        dir.mkdir();
	        }
	    	File dir1 = new File(Finalpath);
	    	
	    	if(dir1.exists()){
	    		System.out.println("date folder present");
	    	}
	    	else{
	    		dir1.mkdir();
	        }
	    	
	    	
	          String fileName=Finalpath+"\\"+append+".png";
	          
	          ses.setVariable("Imagepath", fileName);
	          
	          File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	          // Now you can do whatever you need to do with it, for example copy somewhere
	          FileUtils.copyFile(scrFile, new File(fileName));
		 }
		 catch(Exception j){
			 System.out.println("Browser is manually closed");
		 }
	    }


	public void excelsheet(WebDriver driver,String Exception,String Scenario_id) throws Exception{
		captureScreenLable( driver,  Scenario_id);
		
		File dir = new File("D:\\Result.xls");
		
if(!dir.exists()){
	WritableWorkbook workbook = Workbook.createWorkbook(new File("D:\\Result.xls"));
	
	WritableSheet sheet = workbook.createSheet("First Sheet",0); 
	String amm="Results";
	Label l=new Label(0,0,"ID");
	sheet.addCell(l);
	Label l1=new Label(1,0,"TIME");
	sheet.addCell(l1);
	
	Label l2=new Label(2,0,"Exception");
	sheet.addCell(l2);
	Label l3=new Label(3,0,"Screen Shots");
	sheet.addCell(l3);
	workbook.write();
	  workbook.close();
	  write(Exception,Scenario_id);
	 }

else
{
	write(Exception,Scenario_id);
	}
	
}
	public void write(String Exception,String Scenario_id) throws JXLException, IOException{
		try{
		int dates,day, month, year;
		int second, minute, hour;
		String folderpath;
		URL path;
		GregorianCalendar datesss = new GregorianCalendar();
		dates=datesss.get(Calendar.DATE);
		day = datesss.get(Calendar.DAY_OF_MONTH);
		String daystring = Integer.toString(day);
		month = datesss.get(Calendar.MONTH);
		String monthstirng = Integer.toString(month);
		folderpath="D:\\Screenshot\\"+dates+"_"+monthstirng+"\\"+Scenario_id+"\\";
		File dir = new File("D:\\Result.xls");
		Workbook w = Workbook.getWorkbook(dir);
		Sheet sheet=w.getSheet(0);
		int a=sheet.getRows();
		Workbook existingWorkbook = Workbook.getWorkbook(new File("D:\\Result.xls"));
		WritableWorkbook workbookCopy = Workbook.createWorkbook(new File("D:\\Result.xls"), existingWorkbook);
		WritableSheet sheetToEdit = workbookCopy.getSheet("First Sheet");
		WritableCell cell;
		File screen=new File(folderpath);
		WritableHyperlink wh = new WritableHyperlink(3, a+1,screen);
		sheetToEdit.addHyperlink(wh);


		Calendar cal = Calendar.getInstance();
		String date = cal.getTime().toString();
		

			Label l=new Label(0,a+1,Scenario_id);
			Label l2=new Label(1,a+1,date);
			Label l1=new Label(2,a+1,Exception);

			 
			 
			sheetToEdit.addCell(l);
			sheetToEdit.addCell(l2);
			sheetToEdit.addCell(l1);
;
			workbookCopy.write();
			workbookCopy.close();
	}
	
	
	catch(Exception j){}

}}
