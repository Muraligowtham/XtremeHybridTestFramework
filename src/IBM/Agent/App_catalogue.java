package IBM.Agent;

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
		ses.perform().JS_Click(By.xpath("//*[@title='Google Play App']"));
		
		ses.perform().JS_Click(ses.getElement(By.id("btnAddNew")));
		
		ses.perform().JS_Click(By.xpath("//*[@title='Google Play App']"));
		ses.waitFor(10).elementClickable(
				By.xpath("//span[contains(text(),'Enter the first')]/../../span/span/span/input"), "Waiting for popup");
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
		ses.getElement(By.id("confirm")).click();
		
		
	}
		
	}

}
