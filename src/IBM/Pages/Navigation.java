package IBM.Pages;

import org.openqa.selenium.By;

import IBM.driver.ExecutionInstance;
import IBM.driver.Sessions;

public class Navigation {
	Sessions ses;

	public Navigation(String ID) {
		// TODO Auto-generated constructor stub
		ses = ExecutionInstance.getSession(ID);
	}

	public void Navigate(int Count) {
		// TODO Auto-generated method stub

		if (ses.getTestData("Navigation").equalsIgnoreCase("Devices")) {
			ses.waitFor(10).elementClickable(By.name("NAV_EMC_SUITE_DEVICES"));
			ses.getDriver().findElement(By.name("NAV_EMC_SUITE_DEVICES")).click();
		}
		
		
		
		
		

	}

}
