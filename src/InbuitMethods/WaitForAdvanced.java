package InbuitMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class WaitForAdvanced {
private WebDriver driver;
	private int milli;
	 AndroidDriver<MobileElement> androidDriver;
	
	public WaitForAdvanced(WebDriver driver, AndroidDriver<MobileElement> androidDriver, int milli2)
	{
		
		this.milli=milli;
		this.driver=driver;
		this.androidDriver=androidDriver;
	}
	public WaitForAdvanced(WebDriver driver,AndroidDriver<MobileElement> androidDriver)
	{
		
		this.milli=milli;
		this.driver=driver;
		this.androidDriver=androidDriver;
		
	}
	
	
	public Boolean elementPresent(By locator)
	{
		try{
			WebElement element = null;
			WebDriverWait wait = new WebDriverWait(driver, 10);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			System.out.println(element.getSize());
			if(element.isDisplayed()){
				return true;
			}else{
				return false;
			}}
		catch(Exception h){

			//			captureScreenLable(driver);
			//			driver.close();
			//			driver.quit();
			return false;
		}
		
	}
	public Boolean elementPresent(By locator,String SomeInfo)
	{
		try{
			WebElement element = null;
			WebDriverWait wait = new WebDriverWait(driver, milli);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			System.out.println(element.getSize());
			if(element.isDisplayed()){
				return true;
			}else{
				return false;
			}}
		catch(Exception h){

			//			captureScreenLable(driver);
			//			driver.close();
			//			driver.quit();
			return false;
		}
		
	}
	public Boolean mobileElementPresent(By locator,String SomeInfo)
	{
		try{
			WebElement element = null;
			WebDriverWait wait = new WebDriverWait(androidDriver, milli);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			System.out.println(element.getSize());
			if(element.isDisplayed()){
				return true;
			}else{
				return false;
			}}
		catch(Exception h){

			//			captureScreenLable(driver);
			//			driver.close();
			//			driver.quit();
			return false;
		}
		
	}
	public Boolean elementClickable(By locator,String a)
	{
		try{
			WebElement element = null;
			WebDriverWait wait = new WebDriverWait(driver, milli);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			System.out.println(element.getSize());
			if(element.isDisplayed()){
				return true;
			}else{
				return false;
			}}
		catch(Exception h){

			//			captureScreenLable(driver);
			//			driver.close();
			//			driver.quit();
			return false;
		}
		
	}
	
	public Boolean MobileElementClickable(By locator,String a)
	{
		try{
			WebElement element = null;
			WebDriverWait wait = new WebDriverWait(androidDriver, milli);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			System.out.println(element.getSize());
			if(element.isDisplayed()){
				return true;
			}else{
				return false;
			}}
		catch(Exception h){

			//			captureScreenLable(driver);
			//			driver.close();
			//			driver.quit();
			return false;
		}
		
	}
	
	
	
	public Boolean elementClickable(By locator)
	{
		try{
			WebElement element = null;
			WebDriverWait wait = new WebDriverWait(driver, milli);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			System.out.println(element.getSize());
			if(element.isDisplayed()){
				return true;
			}else{
				return false;
			}}
		catch(Exception h){

			//			captureScreenLable(driver);
			//			driver.close();
			//			driver.quit();
			return false;
		}
		
	}

}
