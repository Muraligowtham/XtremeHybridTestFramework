package IBM.driver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

//import com.InitializeEnvironment.AppiumConnection;
//import com.InitializeEnvironment.InitializeSetup;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
//import org.testng.annotations.*;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class MobileDriver {
	 AndroidDriver driver;
	public  AppiumDriverLocalService service;

	public  AndroidDriver setMobileSession() throws MalformedURLException, InterruptedException {
		service = AppiumDriverLocalService.buildDefaultService();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("VERSION", "7.0");
		capabilities.setCapability("deviceName", "Muraligowtham");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
		capabilities.setCapability(MobileCapabilityType.UDID, "ZY223C7Q2R");
		capabilities.setCapability("appPackage", "com.fiberlink.maas360.android.control");
		capabilities.setCapability("appActivity", ".ui.SplashActivity");
		service.start();
		driver = new AndroidDriver<MobileElement>(service, capabilities);
		//Thread.sleep(10000);
//		driver.findElement(
//				By.xpath("//*[@resource-id='com.fiberlink.maas360.android.control:id/txt_corporate_email_address']"))
//				.sendKeys("amc");
		return driver;
		

	}

}
