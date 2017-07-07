package IBM.driver;

import java.util.HashMap;

public class SessionData1 {

	HashMap<String,HashMap<String,String>> SessionVariableReference=new HashMap<String, HashMap<String,String>>();

	HashMap<String,String> SessionVariable=new HashMap<String, String>();
	String TestcaseID;
	public SessionData1(String TestcaseID)
	{
		//this.settingVariable=settingVariable;
		this.TestcaseID=TestcaseID;
	}
	
//	public void setVariable(HashMap<String,String> settingVariable)
//	{
//		
//		this.settingVariable=settingVariable;
//		
//	}
//	
	
	
	public void setVariableReference()
	{
		HashMap<String,String> settingVariable=new HashMap<String, String>();
		
		SessionVariableReference.put(TestcaseID,settingVariable );
		
		
		//System.out.println("Referenced-> "+TestcaseID+SessionVariableReference.get(TestcaseID));
	
	}
	
	
	public HashMap<String,String> getVariable()
	{
		
		//System.out.println("QC_ID---"+SessionVariableReference.get(TestcaseID).get("QC_ID")+ "   OBJ"+SessionVariableReference.get(TestcaseID));
		
		return SessionVariable;
	}
	
}
