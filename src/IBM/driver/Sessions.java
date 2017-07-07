package IBM.driver;

import java.net.MalformedURLException;
import IBM.*;
import IBM.Engines.WorkerClass;
import IBM.Engines.XtremeHybridExecutor;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import InbuitMethods.Perform;
import InbuitMethods.WaitForAdvanced;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import net.sourceforge.htmlunit.corejs.javascript.Synchronizer;

public class Sessions {
	String deviceId = null;
	HashMap<String,String> a=new HashMap<String, String>();
	DataVariable data;
			private WebDriver driver;
			 private AndroidDriver<MobileElement> AndroidDriver;
			 private static  AppiumDriverLocalService service;
	String testid;
	
	SessionData1 data1;
	 public WaitForAdvanced wait;
	 
	public Sessions(String testid)
	{
		 data =new DataVariable(testid);
		this.testid=testid;
		  data1=new SessionData1(testid);
		  ExecutionInstance.setDataSession(testid);		
	}
	
	
	public void setDriver() throws MalformedURLException
	{

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\MuraligowthamBalaji\\Downloads\\chromedriver_win32\\chromedriver.exe");
		  driver = new ChromeDriver();
	
	
	}

	public  void setAndroidDriver(String Device) throws MalformedURLException
	{   
		allocateDevice();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("VERSION", "6.0.1");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,deviceId);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
		capabilities.setCapability(MobileCapabilityType.UDID, deviceId);
		capabilities.setCapability("appPackage", "com.fiberlink.maas360.android.control");
		capabilities.setCapability("appActivity", ".ui.SplashActivity");
		
	
		AndroidDriver = new AndroidDriver<MobileElement>(new URL("http://localhost:4444/wd/hub/"), capabilities);
		

		//AndroidDriver.quit();
		

	
	
	}
	
	
	
	public String getScenario_ID(){
		return testid;
	}
	public WebDriver getDriver()
	{
		return driver;
	}
	public AndroidDriver<MobileElement> getAndroidDriver()
	{
		return AndroidDriver;
	}
	public String getTestData(String Key)
	{
		return  getVariable(Key);
	}
	public  void setVariable(String key,String Value)
	{
		key=key+testid;
		data.SessionVariable.put(key, Value);

	}
	
	public  void setVariableReference()
	{
		
		
		data1.setVariableReference();
	}
	
	public String getVariable(String key)
		
	{
		key=key+testid;
		return data.SessionVariable.get(key);
	}
	
    public WaitForAdvanced waitFor(int milli)
    {
    	return new WaitForAdvanced(driver,AndroidDriver,milli);
    }
    public WaitForAdvanced waitFor()
    {
    	return new WaitForAdvanced(driver,AndroidDriver);
    }
	
    public Perform perform()
    {
    	return new Perform(driver);
    }
	
	
	public WebElement getElement(By by){
		
		return driver.findElement(by);
		
	
		
	}
	
	
	public void allocateDevice(){
		
		
		Iterator it = XtremeHybridExecutor.devicesStatus.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        //it.remove(); // avoids a ConcurrentModificationException
	        String status=(String) pair.getValue();
	        String DeviceID=(String) pair.getKey();
	        if(status.equalsIgnoreCase("No"))
	        {
	        	 deviceId=DeviceID;	        	
	        	XtremeHybridExecutor.devicesStatus.put(deviceId, "Yes");
	        	setVariable("AndroidExecution", deviceId);
	        	setVariable("isAllocated", "Yes");
	        	break;
	        
	        } 
	    }
		System.out.println("Executing on device ID"+deviceId);
		
	}
	
	
	public void setTcStatus(String Testcase,String Status){
		
		XtremeHybridExecutor.CaseStatus.put(Testcase, Status);
		
		
	}
	
	public void deallocateDevice(){
		setVariable("isAllocated", "No");
		XtremeHybridExecutor.devicesStatus.put(deviceId,"No");
		Iterator it = XtremeHybridExecutor.devicesStatus.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        //it.remove(); // avoids a ConcurrentModificationException
	      
	    }
		
	}

	
}
