package IBM.Pages;

import org.openqa.selenium.By;

import IBM.driver.ExecutionInstance;
import IBM.driver.Sessions;

public class Login {
	
	Sessions ses;

	public Login(String ID) {
		// TODO Auto-generated constructor stub
		
		ses=ExecutionInstance.getSession(ID);
	}
	
	public void Login(int count){
		
		ses.getDriver().navigate().to("https://staging.maas360.com/");
		ses.waitFor(10).elementPresent(By.id("EMail"));
		
		ses.getDriver().findElement(By.id("EMail")).sendKeys(ses.getTestData("Usename"));
		ses.getDriver().findElement(By.id("submit")).click();
		ses.getDriver().findElement(By.id("Password")).sendKeys(ses.getTestData("Password"));
		ses.getDriver().findElement(By.id("submit")).click();
		
		
		
		
	}
	
	
	

}
