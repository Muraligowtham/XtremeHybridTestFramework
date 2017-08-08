package IBM.Engines;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import IBM.Pages.CreateUser;
import IBM.Pages.DeviceActions;
import IBM.Pages.FilterTheAgent;
import IBM.Pages.GUI_Verification;
import IBM.Agent.Agent_Perform_ActionOn_WebObject;
import IBM.Agent.App_catalogue;
import IBM.Agent.Enroll_User_Device;
import IBM.Agent.HomePageActions;
import IBM.Agent.Mobile_GUI_Verification;
import IBM.Pages.Login;
import IBM.Pages.Navigation;
import IBM.Pages.Perform_ActionOn_WebObject;
import IBM.Pages.Security_Policy;
import IBM.driver.*;
import InbuitMethods.ClickCodes;

public class TestScenarioManager {

	String SessionID = "";
	private Sessions ses;

	public TestScenarioManager(String sessionID) {

		SessionID = sessionID;

		ses = ExecutionInstance.getSession(sessionID);
	}

	public void WADMCode(int Count) throws Exception {
		String ID = ses.getVariable("QC_ID").replace(".0", "");

		try {
			if (ses.getVariable("currentstep").equalsIgnoreCase("Login")) {

				Login login = new Login(ID);
				login.Login(Count);

			}

			else if (ses.getVariable("currentstep").equalsIgnoreCase("Navigation")) {

				Navigation navigate = new Navigation(ID);
				navigate.Navigate(Count);

			} else if (ses.getVariable("currentstep").equalsIgnoreCase("Adduser")) {

				CreateUser user = new CreateUser(ID);
				user.CreateUser();

			} else if (ses.getVariable("currentstep").equalsIgnoreCase("AppCatalogue")) {

				App_catalogue apps = new App_catalogue(ID);
				apps.add_App_to_catalogue(Count);

			} else if (ses.getVariable("currentstep").equalsIgnoreCase("EnrollaDevice")) {

				Enroll_User_Device enroll = new Enroll_User_Device(ID);
				enroll.EnrollDevice(Count);

			} else if (ses.getVariable("currentstep").equalsIgnoreCase("LaunchAndroidApp")) {
				ses.setAndroidDriver(ses.getVariable("DeviceName"));

			} else if (ses.getVariable("currentstep").equalsIgnoreCase("GUIVerification")) {
				GUI_Verification verify = new GUI_Verification(ID, Count);
				verify.GUI_Verification(Count);

			} else if (ses.getVariable("currentstep").equalsIgnoreCase("MobileGUIVerification")) {
				Mobile_GUI_Verification verify = new Mobile_GUI_Verification(ID, Count);
				verify.GUI_Verification(Count);

			} else if (ses.getVariable("currentstep").equalsIgnoreCase("PerformActionsOnWebelements")) {
				Perform_ActionOn_WebObject perform = new Perform_ActionOn_WebObject(ID, Count);
				perform.Perform_ActionOn_WebObject(Count);
			} else if (ses.getVariable("currentstep").equalsIgnoreCase("PerformActionOnMobileElement")) {

				Agent_Perform_ActionOn_WebObject perform = new Agent_Perform_ActionOn_WebObject(ID, Count);
				perform.Perform_ActionOn_WebObject(Count);
			} else if (ses.getVariable("currentstep").equalsIgnoreCase("DeviceActions")) {
				DeviceActions actions = new DeviceActions(ID);
				actions.actions(Count);

			}

			else if (ses.getVariable("currentstep").equalsIgnoreCase("WAIT")) {

				String numberAsString = ses.getTestData("DependentWait");
				DecimalFormat decimalFormat = new DecimalFormat("#");
				try {
					long sleep = decimalFormat.parse(numberAsString).longValue();
					Thread.sleep(sleep);
				} catch (ParseException e) {
					System.out.println(numberAsString + " is not a valid number.");
				}

			}

			else if (ses.getVariable("currentstep").equalsIgnoreCase("MobileHomePage")) {
				HomePageActions action = new HomePageActions(ID);
				action.HomeActions(Count);

			} else if (ses.getVariable("currentstep").equalsIgnoreCase("CreatePolicy")) {
				Security_Policy policy = new Security_Policy(ID);
				policy.CreatePolicy(Count);

			}

			else if (ses.getVariable("currentstep").equalsIgnoreCase("FilterTheAgent")) {

				FilterTheAgent filter = new FilterTheAgent(ID);
				filter.filter(Count);

			}

		} catch (Exception e) {

			ses.setVariable("InitiateNew", "Yes");

			ses.setVariable("Status", "Fail");
			ExecutionInstance.addStepEntry(ses, e.getMessage(), ses.getVariable("Status"));
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}