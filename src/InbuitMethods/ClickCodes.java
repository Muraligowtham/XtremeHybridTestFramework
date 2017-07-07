package InbuitMethods;

//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
//import com.thoughtworks.selenium.webdriven.commands.KeyEvent;

import IBM.driver.Sessions;

/*import syntel.Objects.Detail;
import syntel.hwdriver.ExecutionInstance;
import syntel.hwdriver.Sessions;
import syntel.log.LogEntry;
import syntel.log.LogEntry.loggerLevel;*/

import java.awt.List;
import java.awt.Robot;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ClickCodes {

	String SessionID="";
	private Sessions ses;


	public static void JS_Click(Sessions ses,By locator) throws Exception
	{
		try{
			((JavascriptExecutor)ses.getDriver()).executeScript("arguments[0].click();",ses.getDriver().findElement(locator));
         //   logStepEntry(new Detail("Java Script Click On the Web Element", details, "PASS", ""));
            }catch (Exception e) {
                  try{
                	  ((JavascriptExecutor)ses.getDriver()).executeScript("var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null); arguments[0].dispatchEvent(evt);", ses.getDriver().findElement(locator));
                        // logStepEntry(new Detail("Java Script Click On the Web Element", details, "PASS", ""));
                         
                   }catch (Exception ex) {
                        // ExecutionInstance.addStepEntry(Scenario_ID,CurrentStepUnderExecution,"Java Script  Click On the Web Element",e);
                   }
            }

		
	}
	public static void JS_Click(Sessions ses,WebElement Element) throws Exception
	{
		try{
			((JavascriptExecutor)ses.getDriver()).executeScript("arguments[0].click();",Element);
         //   logStepEntry(new Detail("Java Script Click On the Web Element", details, "PASS", ""));
            }catch (Exception e) {
                  try{
                	  ((JavascriptExecutor)ses.getDriver()).executeScript("var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null); arguments[0].dispatchEvent(evt);", Element);
                        // logStepEntry(new Detail("Java Script Click On the Web Element", details, "PASS", ""));
                         
                   }catch (Exception ex) {
                        // ExecutionInstance.addStepEntry(Scenario_ID,CurrentStepUnderExecution,"Java Script  Click On the Web Element",e);
                   }
            }

		
	}
	


	}

