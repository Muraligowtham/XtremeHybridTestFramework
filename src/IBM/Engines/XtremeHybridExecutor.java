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
import java.util.Map;
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

public class XtremeHybridExecutor {  
	static ArrayList<Integer> testcases=new ArrayList<Integer>();
	
	static int Threadpool=2;
	public static HashMap<String,String> devicesStatus=new HashMap<String,String>();
	public static HashMap<String, String> devicesMapwithAndroidID=new HashMap<String, String>();
	public static HashMap<String, String> CaseStatus=new HashMap<String, String>();
	public void TestcaseReader() throws Exception{
		String FilePath = "C:\\Users\\MuraligowthamBalaji\\Documents\\Sanity.xlsx";
		FileInputStream fs = new FileInputStream(FilePath);

		XSSFWorkbook wb= new XSSFWorkbook(fs); 

		XSSFSheet sh = (XSSFSheet)wb.getSheetAt(0);
		
		// To get the number of rows present in sheet
				int totalNoOfRows = sh.getLastRowNum();

				// To get the number of columns present in sheet
				int totalNoOfCols = sh.getRow(0).getPhysicalNumberOfCells();
				int endvalue=totalNoOfCols;

				//Iterating  the excel rows and fetching the data + execution 
				for (int row =1 ; row <=totalNoOfRows; row++) {
					XSSFCell cell = sh.getRow(row).getCell(0);

					//Checking the cases to execute
					if(sh.getRow(row).getCell(3).getStringCellValue().equalsIgnoreCase("Yes")){
						
						
					testcases.add(Integer.parseInt(String.valueOf(cell.getNumericCellValue()).replace(".0", "")));
						
					CaseStatus.put(String.valueOf(cell.getNumericCellValue()).replace(".0", ""), "");
						
						
					}}

				
	}
	
	public static HashMap<String,String> CheckDevices(){
		HashMap<String,String> ListofDevice=new HashMap<String,String>();
		
		try {
	        Process process = Runtime.getRuntime().exec("adb devices");
	        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));  
	        String line = null;  

	        Pattern pattern = Pattern.compile("^([a-zA-Z0-9\\-]+)(\\s+)(device)");
	        Matcher matcher;

	        while ((line = in.readLine()) != null) {  
	            if (line.matches(pattern.pattern())) {
	                matcher = pattern.matcher(line);
	                if (matcher.find())
	                	ListofDevice.put(matcher.group(1),"No" );
	            }
	        }  
	    } catch (IOException e) {
	       
	    }
		
		
		
		return ListofDevice;
		
	}
	
	
     public static void main(String[] args) throws Exception {
    	 
    	 new XtremeHybridExecutor().TestcaseReader();
    	 devicesStatus=CheckDevices();
    	   	 
        ExecutorService executor = Executors.newFixedThreadPool(2);//creating a pool of n threads  
        for (Integer i:testcases) {  
        	Runnable worker = new WorkerClass(i);  
            executor.execute(worker);//calling execute method of ExecutorService  
          }  
        executor.shutdown();  
        while (!executor.isTerminated()) {   }  
  
        System.out.println("Finished all threads");  
    }  
 }  