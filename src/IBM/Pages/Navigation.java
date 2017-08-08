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

	private String[] getTestData(String testdata, int Count) {
		System.out.println(testdata);

		String[] arraytestdata = null;
		if (testdata.equals("")) {

			String testdataValue = ";";

			arraytestdata = testdataValue.split(";");
		}

		else if (!testdata.equalsIgnoreCase("")) {
			arraytestdata = testdata.split(";");

		}
		// return testdata;

		return arraytestdata;
	}

	public void Navigate(int Count) {
		// TODO Auto-generated method stub

		String[] NaviTab = getTestData(ses.getTestData("Navigation"), Count);
		ses.getDriver().switchTo().defaultContent();
		if (NaviTab[Count].equalsIgnoreCase("Devices")) {
			ses.waitFor(10).elementClickable(By.name("NAV_EMC_SUITE_DEVICES"));

			ses.getDriver().findElement(By.name("NAV_EMC_SUITE_DEVICES")).click();
		}

		if (NaviTab[Count].equalsIgnoreCase("Security_Policy")) {
			ses.waitFor(10).elementClickable(By.name("NAV_EMC_SUITE_SECURE"));
			ses.getDriver().findElement(By.name("NAV_EMC_SUITE_SECURE")).click();
		}

	}

}
