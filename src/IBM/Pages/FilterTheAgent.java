package IBM.Pages;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

	public void filter(int Count) throws Exception {

		ses.getDriver().findElement(By.name("NAV_EMC_SUITE_DEVICES")).click();
		Thread.sleep(5000);
		ses.getDriver().switchTo().frame("content");
		ses.waitFor(30000).elementClickable(By.xpath("//*[.='Device ID']"));

		if (ses.getVariable("isAllocated").equalsIgnoreCase("Yes")) {
			System.out.println(ses.getTestData("AndroidExecution"));
			ses.getDriver().findElement(By.xpath("//*[.='Device ID']")).click();
			ses.getDriver().findElement(By.name("txtcsn"))
					.sendKeys(XtremeHybridExecutor.devicesMapwithAndroidID.get(ses.getTestData("AndroidExecution")));
			;
		} else {
			
			if(ses.getVariable("ExecuteSingleTestcase").equalsIgnoreCase("Yes")){
			
		if(XtremeHybridExecutor.singleExecutionMetaData){
			List<WebElement> allRows	=ses.getDriver().findElements(By.xpath("//table[@id='table_deviceViewDiv']/tbody/tr"));
			for(int i=1;i<=allRows.size();i++){
			    ses.perform().JS_Click(By.xpath("//table[@id='table_deviceViewDiv']/tbody/tr["+i+"]/td/span[2]/span"));
				Thread.sleep(6000);
				String AndroidDeviceId=ses.getDriver().findElement(By.xpath("//*[.='Device ID']/../li[2]/span")).getText();
				ses.perform().JS_Click(By.xpath("//*[.='Summary']"));
				Thread.sleep(1000);
				Actions action= new Actions(ses.getDriver());
				action.moveToElement(ses.getDriver().findElement(By.id("hardwareTab")));
				action.click();
				action.build().perform();
				Thread.sleep(3000);
				ses.perform().JS_Click(By.xpath("//*[contains(text(),'Hardware & OS')]"));
				String RealDeviceid=ses.getDriver().findElement(By.xpath("//*[.='Platform Serial Number']/../li[2]/span")).getText();
				XtremeHybridExecutor.devicesMapwithAndroidID.put(RealDeviceid,AndroidDeviceId);
				ses.getDriver().findElement(By.xpath("//div[@id='backButton']")).click();Thread.sleep(5000);;
			}
			XtremeHybridExecutor.singleExecutionMetaData=false;
			
				
			}}
	
		}
		
		ses.allocateDevice();

			Thread.sleep(5000);
			ses.waitFor(30000).elementClickable(By.xpath("//*[.='Device ID']"));
			ses.getDriver().findElement(By.xpath("//*[.='Device ID']")).click();
			System.out.println(ses.getTestData("AndroidExecution"));
			ses.getDriver().findElement(By.name("txtcsn"))
					.sendKeys(XtremeHybridExecutor.devicesMapwithAndroidID.get(ses.getTestData("AndroidExecution")));
		ses.getDriver().findElement(By.xpath("//*[@class='flTickBtn selected']")).click();	

		}
		
	
}
