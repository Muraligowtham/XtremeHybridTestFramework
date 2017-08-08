package InbuitMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import IBM.driver.Sessions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Perform {
	private WebDriver driver;
	private int milli;
	AndroidDriver<MobileElement> AndroidDriver;

	public Perform(WebDriver driver, AndroidDriver<MobileElement> AndroidDriver) {

		this.milli = milli;
		this.driver = driver;
		this.AndroidDriver = AndroidDriver;

	}

	public void JS_Click(Sessions ses, By locator) throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
			// logStepEntry(new Detail("Java Script Click On the Web Element",
			// details, "PASS", ""));
		} catch (Exception e) {
			try {
				((JavascriptExecutor) driver).executeScript(
						"var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null); arguments[0].dispatchEvent(evt);",
						driver.findElement(locator));
				// logStepEntry(new Detail("Java Script Click On the Web
				// Element", details, "PASS", ""));

			} catch (Exception ex) {
				// ExecutionInstance.addStepEntry(Scenario_ID,CurrentStepUnderExecution,"Java
				// Script Click On the Web Element",e);
			}
		}

	}

	public void selctValuebyText(By locator, String text) throws Exception {
		Select dropdown = new Select(driver.findElement(locator));
		dropdown.selectByVisibleText(text);
	}

	public void JS_Click(By locator) throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
			// logStepEntry(new Detail("Java Script Click On the Web Element",
			// details, "PASS", ""));
		} catch (Exception e) {
			try {
				((JavascriptExecutor) driver).executeScript(
						"var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null); arguments[0].dispatchEvent(evt);",
						driver.findElement(locator));
				// logStepEntry(new Detail("Java Script Click On the Web
				// Element", details, "PASS", ""));

			} catch (Exception ex) {
				// ExecutionInstance.addStepEntry(Scenario_ID,CurrentStepUnderExecution,"Java
				// Script Click On the Web Element",e);
			}
		}

	}

	public void mouseOverElement(WebElement a) throws Exception {

		Actions builder = new Actions(driver);
		builder.moveToElement(a);
	}

	public void JS_Click(WebElement a) throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", a);
			// logStepEntry(new Detail("Java Script Click On the Web Element",
			// details, "PASS", ""));
		} catch (Exception e) {
			try {
				((JavascriptExecutor) driver).executeScript(
						"var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null); arguments[0].dispatchEvent(evt);",
						a);
				// logStepEntry(new Detail("Java Script Click On the Web
				// Element", details, "PASS", ""));

			} catch (Exception ex) {
				// ExecutionInstance.addStepEntry(Scenario_ID,CurrentStepUnderExecution,"Java
				// Script Click On the Web Element",e);
			}
		}

	}

	public void JS_Click(Sessions ses, WebElement Element) throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Element);
			// logStepEntry(new Detail("Java Script Click On the Web Element",
			// details, "PASS", ""));
		} catch (Exception e) {
			try {
				((JavascriptExecutor) driver).executeScript(
						"var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null); arguments[0].dispatchEvent(evt);",
						Element);
				// logStepEntry(new Detail("Java Script Click On the Web
				// Element", details, "PASS", ""));

			} catch (Exception ex) {
				// ExecutionInstance.addStepEntry(Scenario_ID,CurrentStepUnderExecution,"Java
				// Script Click On the Web Element",e);
			}
		}

	}

	public boolean swipeUp(int speed) {

		Dimension dm = AndroidDriver.manage().window().getSize();
		int HEIGHT = dm.getHeight();
		int WIDTH = dm.getWidth();
		int CENTER_X = WIDTH / 2;
		int CENTER_Y = HEIGHT / 2;
		// setScreenSize();
		boolean status = false;
		try {
			AndroidDriver.swipe(CENTER_X, CENTER_Y, CENTER_X, 200, speed);
			status = true;
		} catch (Exception e) {

		}
		return status;
	}

	public boolean swipe(int startX, int startY, int endX, int endY, int duration) {
		boolean status = false;
		try {
			AndroidDriver.swipe(startX, startY, endX, endY, duration);
			status = true;
		} catch (Exception e) {

		}
		return status;
	}

}
