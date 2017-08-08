package IBM.Engines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import IBM.driver.ExecutionInstance;
import IBM.driver.Sessions;
import log.LogEntry;

public class WorkerClass implements Runnable {
	public Integer Testcase;
	// WADM start;
	String resultsFolder;
	

	public WorkerClass(Integer Testcase) {
		this.Testcase = Testcase;
		resultsFolder = CreatingFolderForResults();

	}
	

	public static String CreatingFolderForResults() {
		int dates, day, month, year;
		int second, minute, hour;
		String a;
		GregorianCalendar date = new GregorianCalendar();
		dates = date.get(Calendar.DATE);
		day = date.get(Calendar.DAY_OF_MONTH);
		String daystring = Integer.toString(day);
		month = date.get(Calendar.MONTH);
		String monthstirng = Integer.toString(month + 1);
		second = date.get(Calendar.SECOND);
		String secondstring = Integer.toString(second);
		minute = date.get(Calendar.MINUTE);
		String minutestring = Integer.toString(minute);
		hour = date.get(Calendar.HOUR);
		String hourstring = Integer.toString(hour);

		String ResultsFolder = "C:\\Results";
		File dir0 = new File(ResultsFolder);
		if (!dir0.exists()) {
			dir0.mkdir();
		}

		final String folderpath = ResultsFolder + "\\" + "Exec" + "_" + daystring + "_" + monthstirng + "_" + hourstring
				+ "_" + minutestring + "_" + secondstring;

		File dir10 = new File(folderpath);
		if (!dir10.exists()) {
			dir10.mkdir();
		}
		return folderpath;
	}

	public void run() {
		
		processmessage();
		try {
			processData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		
		
		try {

			String DependentCases=ses.getVariable("DependentCases");
			if(!DependentCases.equals("")){
			
			String[] cases=DependentCases.split(";");
			for(int i=0;i<cases.length;i++)
			{
				if(ses.getTestData("ExecuteSingleTestcase").equalsIgnoreCase("yes")){
					
					break;
					
					
				}
				if(XtremeHybridExecutor.CaseStatus.get(cases[i]).equals(""))
				
				{
					while(true){
						Thread.sleep(30000);
						if(XtremeHybridExecutor.CaseStatus.get(cases[i]).equalsIgnoreCase("pass"))
						{
							break;
						}else if(XtremeHybridExecutor.CaseStatus.get(cases[i]).equalsIgnoreCase("Fail")){
							
							throw new Exception("Dependent test cases["+cases[i]+"] failed");
						}
						
					}
					
				}
				else if(XtremeHybridExecutor.CaseStatus.get(cases[i]).equals("Fail"))
				{
					throw new Exception("Dependent test cases["+cases[i]+"] failed");
				}
				
				
				
				
				
			}
			
				
			
			}
			
			
			Execution();
			checkingReferecne();
		} catch (Exception j) {
			j.printStackTrace();
		}

	}

	Sessions ses;

	private void processData() throws IOException {
		String FilePath = "C:\\Users\\MuraligowthamBalaji\\Documents\\Sanity.xlsx";
		FileInputStream fs = new FileInputStream(FilePath);
		ExecutionInstance.setSession(Testcase.toString());
		ses = ExecutionInstance.getSession(Testcase.toString());
		XSSFWorkbook wb = new XSSFWorkbook(fs);

		XSSFSheet sh = (XSSFSheet) wb.getSheetAt(0);

		// To get the number of rows present in sheet
		int totalNoOfRows = sh.getLastRowNum();

		// To get the number of columns present in sheet
		int totalNoOfCols = sh.getRow(0).getPhysicalNumberOfCells();
		int endvalue = totalNoOfCols;
		// ses.setVariableReference();
		// Iterating the excel rows and fetching the data + execution

		for (int row = 1; row <= totalNoOfRows; row++) {

			// Checking the cases to execute

			Double testid1 = sh.getRow(row).getCell(0).getNumericCellValue();

			if (testid1 == (int) Testcase) {
				String testid = String.valueOf(testid1).replace(".0", "");

				ses.setVariable("Status", "");
				// WADM Start=new WADM(testid);

				// setting the data for execution
				for (int col = 0; col < endvalue; col++) {

					try {
						XSSFCell cell = sh.getRow(row).getCell(col);
						if (cell != null) {
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),
										cell.getStringCellValue());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								if (DateUtil.isCellDateFormatted(cell)) {
									DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

									ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),
											df.format(cell.getDateCellValue()).toString());
								} else {
									if (sh.getRow(0).getCell(col).getStringCellValue().equals("QC_ID")) {
										ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),
												String.valueOf(cell.getNumericCellValue()).replace(".0", ""));
									} else {
										ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),
												String.valueOf(cell.getNumericCellValue()));

									}
								}
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),
										String.valueOf(cell.getBooleanCellValue()));
								break;
							case Cell.CELL_TYPE_FORMULA:
								ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(), cell.getCellFormula());
								break;
							case Cell.CELL_TYPE_BLANK:
								ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(), "");
								break;
							default:
								ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(),
										cell.getStringCellValue());
							}
						} else {
							ses.setVariable(sh.getRow(0).getCell(col).getStringCellValue(), "");
						}
					} catch (Exception e) {
						e.printStackTrace();

					}

				}

			}

		}

	}

	private void Execution() throws Exception {
		
		
				
				
		

		// Creating result folder structure
		
		LogEntry.createLog(Testcase.toString(), resultsFolder);
		Logger log = LogEntry.loggers.get(Testcase.toString());
		Logger log2 = LogEntry.createOverallLog(resultsFolder);
		File dir5 = new File(resultsFolder);
		if (!dir5.exists()) {
			dir5.mkdir();
		}
		ses = ExecutionInstance.getSession(Testcase.toString());
		System.out.println(ses + "session");
		ses.setVariable("Status", "");
		ses.setVariable("InitiateNew", "No");
		ses.setDriver();
		ses.setVariable("isAllocated", "");
		
		
		String a = ses.getVariable("TestScenario");
		String[] steps = a.split(";");
		int i = steps.length;

		// Executing the cases based on steps
		for (int j = 0; j < i; j++) {

			if (ses.getVariable("InitiateNew").equalsIgnoreCase("Yes")) {

				break;

			}
			// setting the current step to execute
			ses.setVariable("currentstep", steps[j]);
			// getting the iteration count6
			int count = HybridExecutor.iteratorcount(j, ses.getVariable("TestScenario"));
			TestScenarioManager start = new TestScenarioManager(Testcase.toString());
			// calling the methods
			start.WADMCode(count);

			if (ses.getVariable("InitiateNew").equalsIgnoreCase("No")) {

				ses.setVariable("Status", "Pass");

				ExecutionInstance.addStepEntry(ses, "", ses.getVariable("Status"));
				
			}
		
			if (ses.getVariable("InitiateNew").equalsIgnoreCase("yes")) {
				log2.info("Fail@1@"+ses.getScenario_ID());
				ses.setTcStatus(ses.getScenario_ID(), "Fail");
				String device=ses.getVariable("AndroidExecution");
				ses.getDriver().close();
				ses.getDriver().quit();
				ses.deallocateDevice();
				ses.getDriver().quit();
				try{
				ses.getAndroidDriver().quit();}
				catch(Exception android){}
						
							}
			// closing the browser at the last step and setting the new
			// execution
			if (j == (i - 1)) {
				

				if (ses.getVariable("Status").equalsIgnoreCase("Pass")) {
					ses.setTcStatus(ses.getScenario_ID(), "Pass");
					ses.setVariable("FinalStatusOfCase", "Pass");	
					//String device=ses.getVariable("AndroidExecution");
					ses.deallocateDevice();
					ses.getDriver().close();
					ses.getDriver().quit();
					
					try{
					ses.getAndroidDriver().quit();
					}
					catch(Exception android1){}

					try {

						log2.info("Pass@1@"+ses.getScenario_ID());
					}

					catch (Exception jl) {
						jl.printStackTrace();
					} finally {
						try {
							ses.getDriver().close();
							ses.getDriver().quit();

						} catch (Exception dummy) {
						}
					}
				}
			}
		}

	}

	private void processmessage() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	
	

	private void checkingReferecne() throws InterruptedException {

		ses = ExecutionInstance.getSession(Testcase.toString());
		System.out.println("Completed test case---------->" + ses.getVariable("QC_ID"));

	}

}
