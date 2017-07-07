package IBM.Engines;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Logger;

import log.LogEntry;
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.read.biff.BiffException;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import IBM.driver.*;

//import QcUpdate.*;
public class HybridExecutor {
	public	static String testName;
	public void mainExecutor() throws Exception{
		String FilePath = "C:\\Users\\MuraligowthamBalaji\\Documents\\NewFrameWork.xlsx";
		FileInputStream fs = new FileInputStream(FilePath);

		XSSFWorkbook wb= new XSSFWorkbook(fs); 

		XSSFSheet sh = (XSSFSheet)wb.getSheetAt(0);

		// To get the number of rows present in sheet
		int totalNoOfRows = sh.getLastRowNum();

		// To get the number of columns present in sheet
		int totalNoOfCols = sh.getRow(0).getPhysicalNumberOfCells();
		int endvalue=totalNoOfCols;

		//Creating result folder structure
		String resultsFolder=CreatingFolderForResults();

		//creating an object for overall logger
		Logger log2=LogEntry.createOverallLog(resultsFolder);


		File dir5 = new File(resultsFolder);
		if(!dir5.exists()){
			dir5.mkdir();
		}

		//Iterating  the excel rows and fetching the data + execution 
		for (int row =1 ; row <=totalNoOfRows; row++) {


			//Checking the cases to execute
			if(sh.getRow(row).getCell(3).getStringCellValue().equalsIgnoreCase("Yes")){
				Double testid1=sh.getRow(row).getCell(0).getNumericCellValue();
				String testid=String.valueOf(testid1).replace(".0", "");
				System.out.println(testid);
				testName=testid;

				//creating an session 

				Sessions se=new Sessions(testid);

				ExecutionInstance.setSession(testid);
				Sessions ses=ExecutionInstance.getSession(testid);

				//creating the flag for new Execution
				ses.setVariable("InitiateNew", "No");
				ses.setDriver();

				ses.setVariable("Status","");
				TestScenarioManager Start=new TestScenarioManager(testid);


				//setting the data for execution
				for (int col = 0; col < endvalue; col++) {


					try {
						XSSFCell cell = sh.getRow(row).getCell(col);
						if(cell != null )
						{
							switch (cell.getCellType())
							{
							case Cell.CELL_TYPE_STRING:
								ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),cell.getStringCellValue());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								if (DateUtil.isCellDateFormatted(cell)) 
								{
									DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");


									ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),df.format(cell.getDateCellValue()).toString());
								}
								else 
								{ 
									if(sh.getRow(0).getCell(col).getStringCellValue().equals("QC_ID")){
										ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),String.valueOf(cell.getNumericCellValue()).replace(".0", ""));}
									else{
										ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),String.valueOf(cell.getNumericCellValue()));

									}
								}
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),String.valueOf(cell.getBooleanCellValue()));
								break;
							case Cell.CELL_TYPE_FORMULA:
								ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),cell.getCellFormula());
								break;
							case Cell.CELL_TYPE_BLANK:
								ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),"");
								break;
							default:
								ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),cell.getStringCellValue());
							}
						}else
						{
							ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),"");
						}
					} 
					catch (Exception e) 
					{
						e.printStackTrace();

					}



				}

				ses.setVariable("Status","");
				LogEntry.createLog(testid,resultsFolder);

				ses.setVariable("resultsFolder", resultsFolder);

				Logger log=LogEntry.loggers.get(testid);


				String a =ses.getVariable("TestScenario");

				String [] steps=a.split(";");

				int i=	steps.length;

				//Executing the cases based on steps
				for(int j=0;j<i;j++){

					if(ses.getVariable("InitiateNew").equals("Yes")){

						break;

					}

					//setting the current step to execute
					ses.setVariable("currentstep",steps[j] );
					//getting the iteration count
					int count=	HybridExecutor.iteratorcount(j,ses.getVariable("TestScenario"));

					//calling the methods
					Start.WADMCode(count);
					
					
					
					
					
					
					//checking the status after executing the method
					if(ses.getVariable("Status").equalsIgnoreCase("Fail")){
						log2.info("Fail");
						ses.setVariable("Status","");
					}

					//logging the status of current step
					if(ses.getVariable("InitiateNew").equalsIgnoreCase("No")){

						ses.setVariable("Status","Pass");

						ExecutionInstance.addStepEntry( ses,"",ses.getVariable("Status"));
					}	
					//closing the browser at the last step and setting the new execution
					if(j==(i-1)){

						if(ses.getVariable("Status").equalsIgnoreCase("Pass")){

							ses.setVariable("FinalStatusOfCase", "Pass");

							ses.getDriver().close();
							ses.getDriver().quit();
							//ExecutionInstance.addOverAllStatus(ses,resultsFolder,"Pass");

							try{

								log2.info("Pass");
							}

							catch(Exception jl){
								jl.printStackTrace();
							}
							finally{
								try{
									ses.getDriver().close();
									ses.getDriver().quit();

								}
								catch(Exception dummy){}
							}
						}



					}

				}
			}


		}
	}


	public static void main(String[] args) throws Exception {
		
		
	new HybridExecutor().mainExecutor();
		
		
	}
	public static String CreatingFolderForResults()
	{
		int dates,day, month, year;
		int second, minute, hour;
		String a;
		GregorianCalendar date = new GregorianCalendar();
		dates=date.get(Calendar.DATE);
		day = date.get(Calendar.DAY_OF_MONTH);
		String daystring = Integer.toString(day);
		month = date.get(Calendar.MONTH);
		String monthstirng = Integer.toString(month+1);
		second = date.get(Calendar.SECOND);
		String secondstring = Integer.toString(second);
		minute = date.get(Calendar.MINUTE);
		String minutestring = Integer.toString(minute);
		hour = date.get(Calendar.HOUR);
		String hourstring = Integer.toString(hour);

		String ResultsFolder="D:\\Results";
		File dir0 = new File(ResultsFolder);
		if(!dir0.exists())
		{
			dir0.mkdir();
		}

		final String folderpath = ResultsFolder+"\\"+"Exec"+"_"+daystring+"_"+monthstirng+"_"+hourstring+"_"+minutestring+"_"+secondstring;

		File dir10 = new File(folderpath);
		if(!dir10.exists())
		{
			dir10.mkdir();
		}
		return folderpath;
	}
	//getting the iteration Count
	public static int iteratorcount(int j,String Scenario){


		String [] steps=Scenario.split(";");

		int i=	steps.length;

		int count = 0;

		if(j!=0){

			//checking iteration
			for(int k=j-1;k>=0;k--){


				if(steps[j].equals(steps[k])){


					count++;

				}


			}	


		}

		return count;
	}
}
