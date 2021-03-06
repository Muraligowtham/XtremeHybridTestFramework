package log;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import IBM.driver.*;
public class MyFormatter extends Formatter{
	
	Sessions ses;
	public MyFormatter(String Testid)
	{
		ses=ExecutionInstance.getSession(Testid);
	}

	@Override
	public String format(LogRecord arg0) {
		
		//String[] CurrentSenario=ses.getVariable("Senario").split(";");
		
		StringBuffer sb=new StringBuffer();
		sb.append("<tr>");
		sb.append("<td>");
		sb.append(ses.getVariable("currentstep"));
		sb.append("</td>");
		sb.append("<td>");
		sb.append(arg0.getMessage());
		sb.append("</td>");
		sb.append("<td>");
		sb.append(ses.getVariable("Status"));
		sb.append("</td>");
		sb.append("</tr>");
		
		return sb.toString();
		
	}
	
	
	 public String getHead(Handler h) {
		    return ("<html>\n  <body>\n" + "<Table border>\n<tr><th>Modules</th><th>Error Message</th><th>Status</th></tr>\n");
		  }

		  public String getTail(Handler h) {
		    return ("</table>\n</body>\n</html>");
		  }

}
