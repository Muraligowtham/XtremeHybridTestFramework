package IBM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import IBM.driver.ExecutionInstance;
import IBM.driver.Sessions;

public class CreateUser {
	
	
	Sessions ses;

	public CreateUser(String ID) {
		// TODO Auto-generated constructor stub
		
		ses=ExecutionInstance.getSession(ID);
	}
	
	public void CreateUser() throws Exception{
		
		
		
		ses.waitFor().elementClickable(By.xpath("//a[@id='users']"));
		if(ses.getVariable("Controller_CreateUSer").equalsIgnoreCase("Yes")){
		WebElement frame=ses.getDriver().findElement(By.id("Content"));
		ses.getDriver().switchTo().frame(frame);
		ses.waitFor(10).elementClickable(By.xpath("//a[@id='users']"));
		ses.getDriver().findElement(By.xpath("//a[@id='users']")).click();
		Thread.sleep(5000);
		ses.waitFor(10).elementClickable(By.id("addUserBasicForm.username"));
		ses.getDriver().switchTo().defaultContent();
		ses.getDriver().findElement(By.id("addUserBasicForm.username")).sendKeys(ses.getTestData("AddUser_UserName"));
		ses.getDriver().findElement(By.id("addUserBasicForm.userPasswordHidden")).sendKeys(ses.getTestData("Password"));
		ses.getDriver().findElement(By.id("addUserBasicForm.domain")).sendKeys(ses.getTestData("Domain"));
		ses.getDriver().findElement(By.id("addUserBasicForm.notificationModel.userEmail")).sendKeys(ses.getTestData("AddUser_EmailAddress"));
		ses.getDriver().findElement(By.xpath("//input[@value='Save']")).click();
		Thread.sleep(5000);
		ses.waitFor(10).elementClickable(By.name("NAV_EMC_SUITE_USERS"));
		ses.perform().JS_Click(By.name("NAV_EMC_SUITE_USERS"));
		Thread.sleep(5000);
		ses.getDriver().switchTo().frame("Content");
		ses.waitFor(10).elementClickable(By.xpath("//div[.='Username']"));
		ses.perform().JS_Click(By.xpath("//div[.='Username']"));
		ses.waitFor(5).elementClickable(By.name("txtosusername"));
		ses.getDriver().findElement(By.name("txtosusername")).sendKeys(ses.getTestData("AddUser_UserName"));
		ses.perform().JS_Click(By.xpath("//span[@class='flTickBtn selected']"));
		Thread.sleep(5000);
		ses.perform().JS_Click(By.xpath("//a[.='Add Device']"));
		Thread.sleep(5000);
		ses.waitFor(10).elementClickable(By.xpath("//input[@value='Send Request']"));
		ses.getDriver().findElement(By.xpath("//input[@value='Send Request']")).click();
		Thread.sleep(4000);
		ses.setVariable("Passcode", ses.getDriver().findElement(By.xpath("//*[contains(text(),'Passcode')]/span")).getText());
		ses.setVariable("CorporateEmail", ses.getDriver().findElement(By.xpath("//*[contains(text(),'An enrollment')]/b")).getText());
		ses.setVariable("CorporateID", ses.getDriver().findElement(By.xpath("//*[contains(text(),'Corporate Ide')]/span")).getText());
		
		}	
		
		else if(ses.getTestData("Controller_OnlyEnroll").equalsIgnoreCase("Yes"))
		{
			
			Thread.sleep(5000);
			ses.waitFor(10).elementClickable(By.name("NAV_EMC_SUITE_USERS"));
			ses.perform().JS_Click(By.name("NAV_EMC_SUITE_USERS"));
			Thread.sleep(5000);
			ses.getDriver().switchTo().frame("Content");
			ses.waitFor(10).elementClickable(By.xpath("//div[.='Username']"));
			ses.perform().JS_Click(By.xpath("//div[.='Username']"));
			ses.waitFor(5).elementClickable(By.name("txtosusername"));
			ses.getDriver().findElement(By.name("txtosusername")).sendKeys(ses.getTestData("AddUser_UserName"));
			ses.perform().JS_Click(By.xpath("//span[@class='flTickBtn selected']"));
			Thread.sleep(5000);
			ses.perform().JS_Click(By.xpath("//a[.='Add Device']"));
			Thread.sleep(5000);
			ses.waitFor(10).elementClickable(By.xpath("//input[@value='Send Request']"));
			ses.getDriver().findElement(By.xpath("//input[@value='Send Request']")).click();
			Thread.sleep(4000);
			ses.setVariable("Passcode", ses.getDriver().findElement(By.xpath("//*[contains(text(),'Passcode')]/span")).getText());
			ses.setVariable("CorporateEmail", ses.getDriver().findElement(By.xpath("//*[contains(text(),'An enrollment')]/b")).getText());
			ses.setVariable("CorporateID", ses.getDriver().findElement(By.xpath("//*[contains(text(),'Corporate Ide')]/span")).getText());
			
			
		}
		
	}
	
	

}
