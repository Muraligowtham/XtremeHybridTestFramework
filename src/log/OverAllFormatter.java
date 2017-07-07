package log;


import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import IBM.Engines.HybridExecutor;
import IBM.driver.*;


//import Syntel.Steps.WADM_POC;

public class OverAllFormatter extends Formatter{


	public OverAllFormatter()
	{
		
	}
	
	@Override
	public String format(LogRecord arg0) {
		
		String[] LoggerData=arg0.getMessage().split("@1@");
		//Sessions ses1=sesion(id);
		StringBuffer sb=new StringBuffer();
		sb.append("<tr>");
		sb.append("<td>");
		//System.out.println(testid);
		//System.out.println(ses1.getVariable("QC_ID"));
		sb.append(LoggerData[1]);
		sb.append("</td>");
		sb.append("<td>");
		sb.append(LoggerData[0]);
		sb.append("</td>");
		sb.append("</tr>");
		
		return sb.toString();
		
	}
	
	public Sessions sesion(String Testid ){
		
		
		return ExecutionInstance.getSession(Testid);
	}
	
	
	
	 public String getHead(Handler h) {
		    return ("<html>\n  <body>\n" + "<Table border>\n<tr><th>TestCase ID</th><th>Status</th></tr>\n");
		  }

		  public String getTail(Handler h) {
		    return ("</table>\n</body>\n</html>");
		  }

}


