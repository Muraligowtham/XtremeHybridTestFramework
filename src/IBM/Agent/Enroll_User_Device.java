package IBM.Agent;

import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;

import IBM.Engines.XtremeHybridExecutor;

//import com.commonLibraries.AppiumActions;

import IBM.driver.ExecutionInstance;
import IBM.driver.Sessions;

public class Enroll_User_Device {

	Sessions ses;

	public Enroll_User_Device(String ID) {
		// TODO Auto-generated constructor stub

		ses = ExecutionInstance.getSession(ID);
	}

	public void EnrollDevice(int count) throws Exception {

		Thread.sleep(5000);

		ses.getAndroidDriver().findElement(By.id("txt_corporate_id")).clear();
		ses.getAndroidDriver().findElement(By.id("txt_corporate_id"))
				.sendKeys("##10#" + ses.getVariable("CorporateID"));
		ses.getAndroidDriver().findElement(By.id("txt_corporate_email_address")).clear();
		ses.getAndroidDriver().findElement(By.id("txt_corporate_email_address"))
				.sendKeys(ses.getVariable("CorporateEmail"));
		ses.getAndroidDriver().hideKeyboard();
		ses.getAndroidDriver()
				.findElement(By
						.xpath("//*[@resource-id='com.fiberlink.maas360.android.control:id/addDeviceLogin']|//*[@resource-id='com.fiberlink.maas360.android.control:id/btn_continue_to_enrollment']"))
				.click();
		Thread.sleep(12000);
		ses.getAndroidDriver()
				.findElement(By.xpath("//*[@content-desc='Enter Passcode' or @resource-id='webPasscode']"))
				.sendKeys(ses.getVariable("Passcode"));
		ses.getAndroidDriver()
				.findElement(By
						.xpath("//*[@resource-id='com.fiberlink.maas360.android.control:id/button_continue']|//android.widget.Button[@content-desc='Continue']"))
				.click();
		Thread.sleep(2000);
		ses.getAndroidDriver()
				.findElement(By
						.xpath("//*[@resource-id='com.fiberlink.maas360.android.control:id/button_continue']|//android.widget.Button[@content-desc='Continue']"))
				.click();
		Thread.sleep(2000);

		if (ses.waitFor(100).MobileElementClickable(By.xpath("//*[@resource-id='eulaCheckBoxLabel']"), "")) {
			ses.getAndroidDriver().findElement(By.xpath("//*[@resource-id='eulaCheckBoxLabel']")).click();

		} else {
			ses.getAndroidDriver().findElement(By.xpath("//*[@index=3 and @clickable='false']")).click();
		}

		ses.getAndroidDriver()
				.findElement(By
						.xpath("//*[@resource-id='com.fiberlink.maas360.android.control:id/button_continue']|//android.widget.Button[@content-desc='Continue']"))
				.click();
		Thread.sleep(2000);
		ses.getAndroidDriver()
				.findElement(By
						.xpath("//*[@resource-id='com.fiberlink.maas360.android.control:id/registrationContinue']|//android.widget.Button[@text='Continue']"))
				.click();
		Thread.sleep(30000);
		// ses.getAndroidDriver().hideKeyboard();
		ses.waitFor(100000).MobileElementClickable(By.xpath("//*[@resource-id='com.android.settings:id/action_button']"), "");
		ses.getAndroidDriver().findElement(By.xpath("//*[@resource-id='com.android.settings:id/action_button']"))
				.click();

		ses.perform().JS_Click(By.xpath("//*[@id='okButton']"));
		ses.waitFor(10).elementClickable(By.xpath("//*[.='View']"));
		ses.perform().JS_Click(By.xpath("//*[.='View']"));
       Thread.sleep(4000);
		ses.perform().JS_Click(By.xpath("//*[.='Enrolled']/../td/span[2]/a"));
		Thread.sleep(6000);
		ses.waitFor(5).elementClickable(By.xpath("//*[.='Device ID']/../li[2]/span"));
		XtremeHybridExecutor.devicesMapwithAndroidID.put(ses.getVariable("AndroidExecution"),
		
		ses.getDriver().findElement(By.xpath("//*[.='Device ID']/../li[2]/span")).getText());

		Iterator it = XtremeHybridExecutor.devicesMapwithAndroidID.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			// it.remove(); // avoids a ConcurrentModificationException
			String status = (String) pair.getValue();
			String DeviceID = (String) pair.getKey();

		}

	}

}
