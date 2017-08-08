package IBM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import IBM.driver.ExecutionInstance;
import IBM.driver.Sessions;

public class Security_Policy {
	Sessions ses;

	public Security_Policy(String TestID) {
		// TODO Auto-generated constructor stub
		ses = ExecutionInstance.getSession(TestID);
	}

	public void CreatePolicy(int count) throws Exception {
		Thread.sleep(3000);
		ses.getDriver().switchTo().frame("Content");
		ses.perform().JS_Click(By.id("create_new_pol"));
		Thread.sleep(5000);
		ses.waitFor(10).elementClickable(By.id("configurationName"));
		ses.getDriver().findElement(By.id("configurationName")).sendKeys(ses.getTestData("PolicyName"));
		ses.perform().selctValuebyText(By.id("orgPackages"), ses.getTestData("PolicyType"));
		ses.perform().selctValuebyText(By.id("copyConfigs"), ses.getTestData("PolicyStartFrom"));
		ses.perform().JS_Click(By.id("save"));
		Thread.sleep(15000);
		ses.perform().JS_Click(By.id("sel_cat_actions_menu_title"));
		ses.perform().JS_Click(By.xpath("//div[@id='sel_cat_actions_menu_child']/a/span[.='Publish Policy']"));
		Thread.sleep(5000);
		ses.getDriver().switchTo().frame("confirmPubIframeId");
		ses.getDriver().findElement(By.name("password")).sendKeys(ses.getTestData("Password"));
		ses.getDriver().findElement(By.xpath("//input[@value='Continue']")).click();
		Thread.sleep(15000);
	}

}
