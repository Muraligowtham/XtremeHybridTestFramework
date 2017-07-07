package IBM.Pages;

import org.openqa.selenium.By;

import IBM.Engines.XtremeHybridExecutor;
import IBM.driver.ExecutionInstance;
import IBM.driver.Sessions;

public class FilterTheAgent {
	Sessions ses;
	String SessionID;
	public FilterTheAgent(String sessionID) {
		// TODO Auto-generated constructor stub
		super();
		SessionID = sessionID;
		ses = ExecutionInstance.getSession(sessionID);
			}
	
	
	
	public void filter(int Count) throws InterruptedException{
				
	ses.getDriver().findElement(By.name("NAV_EMC_SUITE_DEVICES")).click();
	Thread.sleep(5000);
	ses.getDriver().switchTo().frame("content");
	ses.getDriver().findElement(By.xpath("//*[.='Device ID']")).click();
	
	ses.allocateDevice();
	if(ses.getVariable("isAllocated").equalsIgnoreCase("Yes"))
	{
		System.out.println(ses.getTestData("AndroidExecution"));
	ses.getDriver().findElement(By.name("txtcsn")).sendKeys(XtremeHybridExecutor.devicesMapwithAndroidID.get(ses.getTestData("AndroidExecution")));;
	}
	
	
	
	
	
	ses.getDriver().findElement(By.xpath("//*[@class='flTickBtn selected']")).click();
	
	
	}
	
	

}
