package log;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogEntry {


	public static HashMap<String,Logger> loggers=new HashMap<String, Logger>();


	public static HashMap<String,Logger> OverAllloggers=new HashMap<String, Logger>();



	public static void createLog(String Testcaseid,String FolderPath) throws Exception
	{




		File dir = new File(FolderPath);

		if(!dir.exists()){
			dir.mkdir();	
		}

		String Finalfolderpath=FolderPath+"\\"+Testcaseid;

		File dir1 = new File(Finalfolderpath);

		if(!dir1.exists()){
			dir1.mkdir();	
		}




		Logger log=Logger.getLogger("MyLog"+Testcaseid);

		Handler h=new FileHandler(Finalfolderpath+"\\"+Testcaseid+".html");

		Formatter f=new MyFormatter(Testcaseid);

		h.setFormatter(f);
		log.addHandler(h);

		loggers.put(Testcaseid,log);



	}
	
	public static void	setLog(String a){}
	
	public static Logger createOverallLog(String FolderPath) throws Exception
	{


		try{

			Logger log1=Logger.getLogger("Over all Log");

			Handler h=new FileHandler(FolderPath+"\\"+"Overall"+".html");
			//System.out.println(Testcaseid+"This is the id");

			Formatter f=new OverAllFormatter();

			h.setFormatter(f);
			log1.addHandler(h);
			return log1;
			//OverAllloggers.put(Testcaseid,log1);


		}
		catch(Exception j){
			j.printStackTrace();
		}
		return null;
	}
}
