package IBM.driver;

import java.util.HashMap;
import java.util.logging.Logger;

import log.LogEntry;




public class ExecutionInstance {
	
	
	
	
	
	static HashMap<String,Sessions> driverHashmap=new HashMap<String, Sessions>();
	static HashMap<String,SessionData1> data=new HashMap<String, SessionData1>();
	public static void setSession(String Testid)
	{
		Sessions ses=new Sessions(Testid);
		driverHashmap.put(Testid,ses);
		//System.out.println(a.get(Testid));
	}
	
	public static Sessions getSession(String Testid)
	{
		//System.out.println(a.get(Testid));
		return driverHashmap.get(Testid);
	}
	public static void setDataSession(String Testid)
	{
		SessionData1 datas=new SessionData1(Testid);
		data.put(Testid,datas);
		//System.out.println("dataObj->"+data.get(Testid));
	}
	public static SessionData1 getDataSession(String Testid)
	{
		//System.out.println(data.get(Testid));
		return data.get(Testid);
	}
	
//	public static void addStepEntry(Sessions ses,String Message,String status) throws Exception
//	{
//		log.addStepEntry1(ses.getVariable("QC_ID"),Message,status);
//	}

	public static void addStepEntry(Sessions ses,String Message,String Status) throws Exception {
		
		 LogEntry.loggers.get(ses.getVariable("QC_ID")).info(Message);
		 
		
	}
	public static void addStepEntry(Sessions ses,String Status) throws Exception {
		
		 LogEntry.loggers.get(ses.getVariable("QC_ID")).info(Status);
		 
		
	}
	

	
}
