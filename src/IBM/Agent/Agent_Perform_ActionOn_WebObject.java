package IBM.Agent;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import log.LogEntry;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.InvalidBrowserExecutableException;

import IBM.driver.ExecutionInstance;
import IBM.driver.Sessions;

//import GUI.Validator.GuiValidation;

public class Agent_Perform_ActionOn_WebObject {
	String SessionID = "";
	private Sessions ses;
	String[] value;
	String[] value10;

	public Agent_Perform_ActionOn_WebObject(String sessionID, int Count) {
		super();
		SessionID = sessionID;
		ses = ExecutionInstance.getSession(SessionID);
		value = getTestData("Mobile_PERFORM_ObjType", Count);
		value10 = getTestData("Mobile_PERFORM_ObjName", Count);
	}

	private String[] getTestData(String testdata, int Count) {
		System.out.println(testdata);

		String[] arraytestdata = null;
		if (ses.getVariable(testdata).equals("")) {

			String testdataValue = ";";

			arraytestdata = testdataValue.split(";");
		}

		else if (!ses.getVariable(testdata).equalsIgnoreCase("")) {
			arraytestdata = ses.getVariable(testdata).split(";");

		}
		// return testdata;

		return arraytestdata;
	}

	public void Perform_ActionOn_WebObject(int Count) throws Exception {

		String[] PERFORM_SelectListItem = getTestData("Mobile_PERFORM_Data", Count);

		String[] ObjName = getTestData("Mobile_PERFORM_ObjName", Count);

		if (value[Count].equalsIgnoreCase("SwitchFrame")) {

			ses.getDriver().switchTo().frame("Content");

		}

		if (value[Count].equalsIgnoreCase("WebEdit")) {

			getElement(ObjName[Count]).sendKeys(PERFORM_SelectListItem[Count]);

		} else if (value[Count].equalsIgnoreCase("MOUSEOVER"))

		{

			WebElement mouseover = ses.getDriver().findElement(By.id(ObjName[Count]));

			if (ses.getDriver().findElement(By.id(ObjName[Count])).isDisplayed()) {
				ses.perform().mouseOverElement(mouseover);

			}

		} else if (value[Count].equalsIgnoreCase("WebList")) {

			String[] Element = getTestData("Mobile_PERFORM_Data", Count);
			try {
				WebElement selectedElement = ses.getDriver().findElement(By.name(ObjName[Count]));
				org.openqa.selenium.support.ui.Select elementValue = new org.openqa.selenium.support.ui.Select(
						selectedElement);
				elementValue.selectByVisibleText(Element[Count]);
			} catch (Exception k) {

				WebElement selectedElement = ses.getDriver().findElement(By.id(ObjName[Count]));
				org.openqa.selenium.support.ui.Select elementValue = new org.openqa.selenium.support.ui.Select(
						selectedElement);
				elementValue.selectByVisibleText(Element[Count]);
			}

		}

		else if (value[Count].equalsIgnoreCase("WEBRADIOGROUPON")) {

			if (ses.getDriver().findElement(By.id(ObjName[Count])).isDisplayed()) {

				WebElement element = ses.getDriver().findElement(By.id(ObjName[Count]));
				if (element.isSelected()) {

				} else {
					ses.perform().JS_Click(element);

				}
			}
		}

		else if (value[Count].equalsIgnoreCase("WEBRADIOGROUPOFF")) {

			if (ses.getDriver().findElement(By.id(ObjName[Count])).isDisplayed()) {

				WebElement element = ses.getDriver().findElement(By.id(ObjName[Count]));
				if (!element.isSelected()) {

				} else {
					ses.perform().JS_Click(element);
				}
			}

		} else if (value[Count].equalsIgnoreCase("WEBBUTTON")) {
			try {
				if (ses.waitFor(5).MobileElementClickable(By.id(ObjName[Count]), ""))
					ses.perform().JS_Click(By.id(ObjName[Count]));

				else if (ses.waitFor(2).MobileElementClickable(By.name(ObjName[Count]), ""))
					ses.perform().JS_Click(By.name(ObjName[Count]));

				else if (ses.waitFor().MobileElementClickable(By.xpath(ObjName[Count]), ""))
					ses.getAndroidDriver().findElement(By.xpath(ObjName[Count])).click();

				else
					ses.perform().JS_Click(By.xpath("//*[.='" + ObjName[Count] + "']"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (value[Count].equalsIgnoreCase("LINK")) {

			if (ses.waitFor(10).MobileElementClickable(By.id(ObjName[Count]), ""))

				ses.perform().JS_Click(By.id(ObjName[Count]));

			else if (ses.waitFor(5).MobileElementClickable(By.name(ObjName[Count]), ""))
				ses.perform().JS_Click(By.name(ObjName[Count]));

			else if (ses.waitFor(5).MobileElementClickable(By.xpath(ObjName[Count]), ""))
				ses.perform().JS_Click(By.xpath(ObjName[Count]));

		} else if (value[Count].equalsIgnoreCase("Click")) {
			if (ses.waitFor(5).MobileElementClickable(By.xpath(ObjName[Count]), "")) {
				ses.perform().JS_Click(By.xpath(ObjName[Count]));
				Thread.sleep(5000);
			}

			else if (ses.waitFor(5).MobileElementClickable(By.name(ObjName[Count]), ""))
				ses.perform().JS_Click(By.name(ObjName[Count]));

			else if (ses.waitFor(20).MobileElementClickable(By.id(ObjName[Count]), ""))
				ses.perform().JS_Click(By.id(ObjName[Count]));

			else
				ses.perform().JS_Click(By.xpath("//*[.='" + ObjName[Count] + "']"));

		} else if (value[Count].equalsIgnoreCase("WebCheckBox")) {

			if (ses.getDriver().findElement(By.id(ObjName[Count])).isDisplayed()) {

				WebElement element = ses.getDriver().findElement(By.id(ObjName[Count]));
				if (element.isSelected()) {

				} else {
					ses.perform().JS_Click(element);
				}
			}

		} else if (value[Count].equalsIgnoreCase("WEBCHECKBOXON")) {

			if (ses.getDriver().findElement(By.id(ObjName[Count])).isDisplayed()) {

				WebElement element = ses.getDriver().findElement(By.id(ObjName[Count]));
				if (element.isSelected()) {

				} else {
					ses.perform().JS_Click(element);
				}
			}

		} else if (value[Count].equalsIgnoreCase("WEBCHECKBOXOFF")) {
			if (ses.getDriver().findElement(By.id(ObjName[Count])).isDisplayed()) {

				WebElement element = ses.getDriver().findElement(By.id(ObjName[Count]));
				if (!element.isSelected()) {

				} else {
					ses.perform().JS_Click(element);
				}
			}

		}

	}

	private WebElement getElement(String ObjName) throws Exception {
		try {

			ses.getDriver().switchTo().defaultContent();
			if (ses.waitFor(5).MobileElementClickable(By.id(ObjName), ""))
				return ses.getAndroidDriver().findElement(By.id(ObjName));

			else if (ses.waitFor(1).MobileElementClickable(By.name(ObjName), ""))
				return ses.getAndroidDriver().findElement(By.name(ObjName));

			else if (ses.waitFor(1).MobileElementClickable(By.xpath(ObjName), ""))
				return ses.getAndroidDriver().findElement(By.xpath(ObjName));

			else if (ses.waitFor().MobileElementClickable(By.xpath("//*[contains(text(),'" + ObjName + "')]"), ""))
				return ses.getAndroidDriver().findElement(By.xpath("//*[contains(text(),'" + ObjName + "')]"));

			else if (ses.waitFor().MobileElementClickable(By.xpath("//p[contains(text(),'" + ObjName + "')]"), ""))
				return ses.getAndroidDriver().findElement(By.xpath("//p[contains(text(),'" + ObjName + "')]"));

			else if (ses.waitFor().MobileElementClickable(By.xpath("//*[.='" + ObjName + "']"), ""))
				return ses.getAndroidDriver().findElement(By.xpath("//*[.='" + ObjName + "']"));

			else if (ses.waitFor().MobileElementClickable(By.xpath("//*[@class='" + ObjName + "']"), ""))
				return ses.getAndroidDriver().findElement(By.xpath("//*[@class='" + ObjName + "']"));

			else
				throw new Exception("Element identification Error at Perform Action -->" + ObjName + "Switching Frame");
		}

		catch (Exception E) {

			ses.getDriver().switchTo().frame("Content");
			if (ses.waitFor(5).MobileElementClickable(By.id(ObjName), ""))
				return ses.getAndroidDriver().findElement(By.id(ObjName));

			else if (ses.waitFor(1).MobileElementClickable(By.name(ObjName), ""))
				return ses.getAndroidDriver().findElement(By.name(ObjName));

			else if (ses.waitFor(1).MobileElementClickable(By.xpath(ObjName), ""))
				return ses.getAndroidDriver().findElement(By.xpath(ObjName));

			else if (ses.waitFor().MobileElementClickable(By.xpath("//*[contains(text(),'" + ObjName + "')]"), ""))
				return ses.getAndroidDriver().findElement(By.xpath("//*[contains(text(),'" + ObjName + "')]"));

			else if (ses.waitFor().MobileElementClickable(By.xpath("//p[contains(text(),'" + ObjName + "')]"), ""))
				return ses.getAndroidDriver().findElement(By.xpath("//p[contains(text(),'" + ObjName + "')]"));

			else if (ses.waitFor().MobileElementClickable(By.xpath("//*[.='" + ObjName + "']"), ""))
				return ses.getAndroidDriver().findElement(By.xpath("//*[.='" + ObjName + "']"));

			else if (ses.waitFor().MobileElementClickable(By.xpath("//*[@class='" + ObjName + "']"), ""))
				return ses.getAndroidDriver().findElement(By.xpath("//*[@class='" + ObjName + "']"));

			else
				throw new Exception("Element identification Error at Perform Action -->" + ObjName);
		}
	}

}
