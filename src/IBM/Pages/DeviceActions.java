package IBM.Pages;

import org.openqa.selenium.By;

import IBM.driver.ExecutionInstance;
import IBM.driver.Sessions;

public class DeviceActions {
	Sessions ses;

	public DeviceActions(String ID) {
		// TODO Auto-generated constructor stub
		ses = ExecutionInstance.getSession(ID);
	}

	public void actions(int Count) throws Exception {

		if (ses.getTestData("DeviceAction_Portal").equalsIgnoreCase("ChangePolicy")) {
			Thread.sleep(3000);
			ses.perform().JS_Click(By.xpath("//*[contains(text(),'More..')]"));
			Thread.sleep(2000);
			ses.perform().JS_Click(By.xpath("//a[.='Change Policy']"));
			Thread.sleep(4000);
			ses.getDriver().switchTo().defaultContent();
			ses.perform().selctValuebyText(By.id("androidPolicies"), ses.getTestData("AndroidPolicyName"));
			ses.getDriver().findElement(By.id("messageContent"))
					.sendKeys(ses.getTestData("MessageContent_PolicyChange"));
			ses.perform().JS_Click(By.id("applyPolicy"));
			Thread.sleep(2000);

			ses.getDriver().switchTo().defaultContent();
			ses.getDriver().findElement(By.xpath("//*[@type='text' and @id='password']"))
					.sendKeys(ses.getVariable("Password"));
			ses.perform().JS_Click(By.id("passwordConfirmationContinue"));

		}

	}

}
