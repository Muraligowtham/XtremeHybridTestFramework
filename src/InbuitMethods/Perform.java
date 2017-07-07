package InbuitMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import IBM.driver.Sessions;

public class Perform {
private WebDriver driver;
	private int milli;
	
	
	public Perform(WebDriver driver)
	{
		
		this.milli=milli;
		this.driver=driver;
		
	}
	
	
	public  void JS_Click(Sessions ses,By locator) throws Exception
	{
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(locator));
         //   logStepEntry(new Detail("Java Script Click On the Web Element", details, "PASS", ""));
            }catch (Exception e) {
                  try{
                	  ((JavascriptExecutor)driver).executeScript("var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null); arguments[0].dispatchEvent(evt);", driver.findElement(locator));
                        // logStepEntry(new Detail("Java Script Click On the Web Element", details, "PASS", ""));
                         
                   }catch (Exception ex) {
                        // ExecutionInstance.addStepEntry(Scenario_ID,CurrentStepUnderExecution,"Java Script  Click On the Web Element",e);
                   }
            }

		
	}
	public  void JS_Click(By locator) throws Exception
	{
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(locator));
         //   logStepEntry(new Detail("Java Script Click On the Web Element", details, "PASS", ""));
            }catch (Exception e) {
                  try{
                	  ((JavascriptExecutor)driver).executeScript("var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null); arguments[0].dispatchEvent(evt);", driver.findElement(locator));
                        // logStepEntry(new Detail("Java Script Click On the Web Element", details, "PASS", ""));
                         
                   }catch (Exception ex) {
                        // ExecutionInstance.addStepEntry(Scenario_ID,CurrentStepUnderExecution,"Java Script  Click On the Web Element",e);
                   }
            }

		
	}
	public  void mouseOverElement(WebElement a) throws Exception
	{
		
		Actions builder=new Actions(driver);
		builder.moveToElement(a);
	}
	
	
	
	
	public  void JS_Click(WebElement a) throws Exception
	{
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",a);
         //   logStepEntry(new Detail("Java Script Click On the Web Element", details, "PASS", ""));
            }catch (Exception e) {
                  try{
                	  ((JavascriptExecutor)driver).executeScript("var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null); arguments[0].dispatchEvent(evt);",a);
                        // logStepEntry(new Detail("Java Script Click On the Web Element", details, "PASS", ""));
                         
                   }catch (Exception ex) {
                        // ExecutionInstance.addStepEntry(Scenario_ID,CurrentStepUnderExecution,"Java Script  Click On the Web Element",e);
                   }
            }

		
	}
	
	
	
	
	
	public  void JS_Click(Sessions ses,WebElement Element) throws Exception
	{
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",Element);
         //   logStepEntry(new Detail("Java Script Click On the Web Element", details, "PASS", ""));
            }catch (Exception e) {
                  try{
                	  ((JavascriptExecutor)driver).executeScript("var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null); arguments[0].dispatchEvent(evt);", Element);
                        // logStepEntry(new Detail("Java Script Click On the Web Element", details, "PASS", ""));
                         
                   }catch (Exception ex) {
                        // ExecutionInstance.addStepEntry(Scenario_ID,CurrentStepUnderExecution,"Java Script  Click On the Web Element",e);
                   }
            }

		
	}
	
}
