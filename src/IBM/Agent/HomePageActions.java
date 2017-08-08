package IBM.Agent;

import org.openqa.selenium.By;

import IBM.driver.ExecutionInstance;
import IBM.driver.Sessions;

public class HomePageActions {
	Sessions ses;
	
	public HomePageActions(String SessionID) {
		// TODO Auto-generated constructor stub
		ses=ExecutionInstance.getSession(SessionID);
	}
	
	public void HomeActions(int Count) throws InterruptedException{
		Thread.sleep(2000);
		ses.waitFor(10).MobileElementClickable(By.xpath("//*[@resource-id='com.fiberlink.maas360.android.control:id/fragment_container_data_item_text' and @text='Settings']"),"");
		
		ses.getAndroidDriver().findElement(By.xpath("//*[@resource-id='com.fiberlink.maas360.android.control:id/fragment_container_data_item_text' and @text='Settings']")).click();
		ses.waitFor(10).MobileElementClickable(By.xpath("//*[@content-desc='More options']"), "");
		Thread.sleep(2000);
		ses.getAndroidDriver().findElement(By.xpath("//*[@content-desc='More options']")).click();
		ses.waitFor(10).MobileElementClickable(By.xpath("//*[@text='Update Device Data' and @resource-id='android:id/title']"),"");
		Thread.sleep(2000);
		ses.getAndroidDriver().findElement(By.xpath("//*[@text='Update Device Data' and @resource-id='android:id/title']")).click();
		Thread.sleep(4000);
		ses.getAndroidDriver().findElement(By.xpath("//*[@resource-id='android:id/home']")).click();
		Thread.sleep(3000);
	}

}
