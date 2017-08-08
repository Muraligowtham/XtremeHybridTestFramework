package IBM.Agent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import IBM.driver.ExecutionInstance;
import IBM.driver.Sessions;

public class App_catalogue {

	Sessions ses;

	public App_catalogue(String TestcaseID) {
		// TODO Auto-generated constructor stub
		ses = ExecutionInstance.getSession(TestcaseID);
	}

	public void add_App_to_catalogue(int Count) throws Exception {
	if(ses.getVariable("Controller_Add_ApptoCatalogue").equalsIgnoreCase("Yes"))
	{
		
		
		
		ses.waitFor().elementClickable(By.name("NAV_EMC_SUITE_APPS"));
		
		ses.perform().JS_Click(ses.getElement(By.name("NAV_EMC_SUITE_APPS")));
		Thread.sleep(5000);
		ses.getDriver().switchTo().frame("Content");
		
		ses.perform().JS_Click(By.id("filter_name"));
		ses.getDriver().findElement(By.name("txtname")).sendKeys(ses.getTestData("AppName"));
		ses.perform().JS_Click(By.xpath("//*[@name='txtname']/../span"));
		Thread.sleep(10000);
		List<WebElement> list= ses.getDriver().findElements(By.xpath("//*[@id='table_manageUsersDiv']/tbody/tr"));
		int rowsize=list.size();
		if(rowsize>0){
			try{
			ses.perform().JS_Click(By.xpath("//a[.='Delete']"));
			Thread.sleep(3000);
			ses.waitFor(3000).elementClickable(By.xpath("//*[.='OK']"));
			ses.perform().JS_Click(By.xpath("//*[.='OK']"));
			Thread.sleep(3000);
			ses.getDriver().findElement(By.id("cloneId")).sendKeys(ses.getVariable("Password"));
			ses.perform().JS_Click(By.id("confirm"));
			
		
		
			Thread.sleep(7000);}
			catch(Exception d){}
		}
		
		//ses.perform().JS_Click(By.xpath("//*[@title='Google Play App']"));
		
		ses.perform().JS_Click(ses.getElement(By.id("btnAddNew")));
		
		ses.perform().JS_Click(By.xpath("//*[@title='Google Play App']"));
		ses.waitFor(10).elementClickable(
				By.xpath("//span[contains(text(),'Enter the first')]/../../span/span/span/input"), "Waiting for popup");
		Thread.sleep(5000);
		Actions actions = new Actions(ses.getDriver());
		actions.moveToElement(
				ses.getElement(By.xpath("//span[contains(text(),'Enter the first')]/../../span/span/span/input")));
		actions.click();
		actions.sendKeys(ses.getTestData("AppName"));
		actions.build().perform();
		Thread.sleep(5000);
		System.out.println("//a[.='" + ses.getVariable("AppName") + "']/div/span/img");
		ses.getElement(By.xpath("//a[.='" + ses.getVariable("AppName") + "']/div/span/img")).click();
		actions.moveToElement(ses.getElement(By.xpath("//a[.='" + ses.getVariable("AppName") + "']/div/span/img")));
		actions.click();
		actions.build().perform();
		ses.perform().JS_Click(ses.getElement(By.id("addPublicApp")));
		ses.getElement(By.id("cloneId")).sendKeys(ses.getVariable("Password"));
		Thread.sleep(3000);
		ses.getElement(By.id("confirm")).click();
		Thread.sleep(10000);
		
		if(ses.getVariable("DistibuteApp").equalsIgnoreCase("Yes")){
		ses.waitFor(50000).elementClickable(By.xpath("//a[.='Distribute']"));
			ses.perform().JS_Click(By.xpath("//a[.='Distribute']"));
			Thread.sleep(5000);
			ses.perform().selctValuebyText(By.name("deviceType1"), "All Devices");
			ses.perform().JS_Click(By.xpath("//input[@value='Distribute']"));
		ses.getDriver().findElement(By.id("cloneId")).sendKeys(ses.getTestData("Password"));
		ses.perform().JS_Click(By.id("confirm"));
		Thread.sleep(5000);
		}
		
	}
		
	}

}
