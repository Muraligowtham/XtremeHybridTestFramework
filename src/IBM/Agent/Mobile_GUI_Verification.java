package IBM.Agent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import IBM.driver.ExecutionInstance;
import IBM.driver.Sessions;

public class Mobile_GUI_Verification {
	Sessions ses;
	private static String ExpectedFields = "ExpectedFields";
	String SessionID = "";

	WebDriver driver;
	String Actual = "";
	String ObjType = "";
	String ObjName = "";
	String TestType = "";
	String ExpResult = "";
	String ObjIndex = "";
	String TargetProperty = "";

	int Count;

	public Mobile_GUI_Verification(String sessionID, int Count1) {
		super();
		SessionID = sessionID;
		ses = ExecutionInstance.getSession(sessionID);
		Count = Count1;
		ObjType = getTestData("Mobile_ObjType", Count1);
		ObjName = getTestData("Mobile_ObjName", Count1);
		TestType = getTestData("Mobile_TestType", Count1);
		ExpResult = getTestData("Mobile_ExpResult", Count1);
		ObjIndex = getTestData("Mobile_ObjIndex", Count1);
		TargetProperty = getTestData("Mobile_TargetProperty", Count1);
	}

	private String getTestData(String string, int Count) {
		System.out.println(string);
		System.out.println(ses.getVariable(string));
		String[] var = ses.getVariable(string).split(";");
		if (var.length > Count)
			return var[Count];
		else
			return "";

	}

	public void GUI_Verification(int Count) throws Exception {

		if (ses.getTestData("Mobile_TestType").equalsIgnoreCase("N")) {
			negative_validation(Count);
		} else {
			validations(Count);
		}

	}

	public void negative_validation(int Count) throws Exception {

		if (ObjType.equalsIgnoreCase("WebList") && TargetProperty.equalsIgnoreCase("html id")) {
			WebListhtmlidnegative();
		}
		if (ObjType.equalsIgnoreCase("General") && TargetProperty.equalsIgnoreCase("GetStyle")) {
			// a=false;
			genralGetStyleNegative();
		}
		if (ObjType.equalsIgnoreCase("WebList") && TargetProperty.equalsIgnoreCase("value")) {
			WebListItemIsPresentNegative();
		}
		if (ObjType.equalsIgnoreCase("WebList") && TargetProperty.equalsIgnoreCase("items count")) {
			WebListAllItemsCountVerificationnegative();
		}

		if (ObjType.equalsIgnoreCase("WebList") && TargetProperty.equalsIgnoreCase("all items")) {
			WebListAllItemsVerification();
		}

		if (ObjType.equalsIgnoreCase("WebList") && TargetProperty.equalsIgnoreCase("Default")) {
			WebListDefaultcheck();
		}
		if (ObjType.equalsIgnoreCase("WebList") && TargetProperty.equalsIgnoreCase("selected item index")) {
			WebListIndexcheck();
		}

		if (ObjType.equalsIgnoreCase("WEBCHECKBOX") && TargetProperty.equalsIgnoreCase("Checked")) {

			WebCheckBoxCheckedVerificationnegative();
		}
		if (ObjType.equalsIgnoreCase("WEBCHECKBOX") && TargetProperty.equalsIgnoreCase("html id")) {

			WebCheckBoxhtmlidVerificationnegative();
		}

		if (ObjType.equalsIgnoreCase("WEBCHECKBOX") && TargetProperty.equalsIgnoreCase("type")) {

			WebCheckBoxtypenegative();
		}

		if (ObjType.equalsIgnoreCase("WEBEDIT") && TargetProperty.equalsIgnoreCase("max length")) {
			WebEditmaxlengthnegative();

		}
		if (ObjType.equalsIgnoreCase("WEBEDIT") && TargetProperty.equalsIgnoreCase("Value")) {

			WebEditvaluenegative();
		}
		if (ObjType.equalsIgnoreCase("WEBEDIT") && TargetProperty.equalsIgnoreCase("type")) {

			WebEditTypenegative();
		}
		if (ObjType.equalsIgnoreCase("WEBEDIT") && TargetProperty.equalsIgnoreCase("Visible")) {
			Webeditnegitiveverification();
		}

		if (ObjType.equalsIgnoreCase("WebElement") && TargetProperty.equalsIgnoreCase("html id")) {
			WebElementHtmlidVerificationnegative();
		}
		if (ObjType.equalsIgnoreCase("WebElement") && TargetProperty.equalsIgnoreCase("innerText")) {
			WebElementInnerTextVerificationnegative();
		}

		if (ObjType.equalsIgnoreCase("WebElement") && TargetProperty.equalsIgnoreCase("innerhtml")) {
			WebElementInnerTextVerificationnegative();
		}

		if (ObjType.equalsIgnoreCase("WEBBUTTON") && TargetProperty.equalsIgnoreCase("Visible")) {
			WebButtonvisibleVerificationnegative();
		}
		if (ObjType.equalsIgnoreCase("WEBBUTTON") && TargetProperty.equalsIgnoreCase("html id")) {
			WebButtonhtmlidnegative();
		}
		if (ObjType.equalsIgnoreCase("image") && TargetProperty.equalsIgnoreCase("name")) {
			Webimagenamenegative();
		}

		if (ObjType.equalsIgnoreCase("WebElement") && TargetProperty.equalsIgnoreCase("Visible")) {
			WebElementnotVisible();
		}

		if (ObjType.equalsIgnoreCase("WebCheckBox") && TargetProperty.equalsIgnoreCase("Visible")) {
			WebCheckBoxnotVisible();
		}

	}

	private void Webimagenamenegative() throws Exception {

		verifynegativeEquals(getElement().getAttribute("id"), "Verifying the name of the image");

	}

	private void WebButtonhtmlidnegative() throws Exception {
		try {
			ses.getDriver().findElement(By.id(ObjName));
			logEntryFailMessage("Webbutton id is  present");
		} catch (Exception e) {
			logEntryPassMessage("WebCheckBox is not Visible ");
		}
	}

	/*
	 * private void WebElementInnerTextVerificationnegative() throws Exception {
	 * verifynegativeEquals(getElement().getText(),"verifying Webelement Text");
	 * 
	 * }
	 */ private void WebElementHtmlidVerificationnegative() throws Exception {
		try {
			ses.getDriver().findElement(By.id(ObjName));
			logEntryFailMessage("Webelement id is  present");
		} catch (Exception e) {
			logEntryPassMessage("WebCheckBox is not Visible ");
		}

	}

	private void WebEditTypenegative() throws Exception {
		verifynegativeEquals(getElement().getAttribute("type"), "verifying the type of element");

	}

	private void WebEditvaluenegative() throws Exception {
		Actual = getElement().getAttribute("value");
		verifynegativeEquals(Actual, "verify value of WebEdit");
	}

	private void WebEditmaxlengthnegative() throws Exception {

		Actual = getElement().getAttribute("maxlength");
		verifynegativeEquals(Actual, "verify maxlength of WebEdit");
	}

	private void WebCheckBoxtypenegative() throws Exception {

		verifynegativeEquals(getElement().getAttribute("type"), "Veryfying the type of webelement");
	}

	private void WebCheckBoxhtmlidVerificationnegative() throws Exception {
		try {
			ses.getDriver().findElement(By.id(ObjName));
			logEntryFailMessage("WebCheckBox id is  present");
		} catch (Exception e) {
			logEntryPassMessage("WebCheckBox is not Visible ");
		}
	}

	private void WebCheckBoxCheckedVerificationnegative() throws Exception {
		if (!getElement().isSelected()) {
			logEntryPassMessage("WebCheckBox is not Checked ");
		} else {
			logEntryFailMessage("WebCheckBox is Checked");
		}

	}

	private void WebListAllItemsCountVerificationnegative() throws Exception {

		org.openqa.selenium.support.ui.Select elementValue4 = new org.openqa.selenium.support.ui.Select(getElement());
		List<WebElement> ele = elementValue4.getOptions();
		Actual = Integer.toString(ele.size());
		verifynegativeEquals(Actual, "WebList All Items Count Verification");

	}

	private void WebListhtmlidnegative() throws Exception {
		try {
			ses.getDriver().findElement(By.id(ObjName));
			logEntryFailMessage("WebCheckBox id is  present");

		} catch (Exception e) {
			logEntryPassMessage("Webelement is not Visible ");
		}

	}

	private void WebButtonvisibleVerificationnegative() throws Exception {
		try {
			ses.getDriver().findElement(By.id(ObjName));
			logEntryFailMessage("WebCheckBox id is  present");
		} catch (Exception e) {
			logEntryPassMessage("Webelement is not Visible ");
		}
	}

	private void WebElementnotVisible() throws Exception {
		if (ses.waitFor().elementClickable(By.id("ObjectName"))) {
			if (ses.getDriver().findElement(By.id(ObjName)).isDisplayed()) {
				logEntryFailMessage("WebElement   Visible");
			} else {
				logEntryPassMessage("WebElement not Visible");
			}
		} else {
			logEntryPassMessage("WebElement not Visible");

		}
	}

	private void WebCheckBoxnotVisible() throws Exception {
		try {
			ses.getDriver().findElement(By.id(ObjName));
			logEntryFailMessage("WebCheckBox id is  present");
		} catch (Exception e) {
			logEntryPassMessage("WebCheckBox is not Visible ");
		}
	}

	private void Webeditnegitiveverification() throws Exception {
		try {
			if (ses.getDriver().findElement(By.id(ObjName)).isDisplayed())
				logEntryFailMessage("WebCheckBox id is  present");
			else
				logEntryPassMessage("WebCheckBox is not Visible ");
		} catch (Exception e) {
			logEntryPassMessage("WebCheckBox is not Visible ");
		}
	}

	private void WebElementInnerTextVerificationnegative() throws Exception {
		try {
			// ses.getDriver().findElement(By.id(ObjName));
			ses.getDriver().findElement(By.xpath("//*[contains(text(),'" + ObjName + "')]"));
			logEntryFailMessage("webElement text is  Visible ");
		} catch (Exception e) {
			logEntryPassMessage("webElement text is not Visible ");
		}
	}

	private void WebListItemIsPresentNegative() throws Exception {
		Select sel = new Select(getElement());
		int j = sel.getOptions().size();
		for (int i = 0; i < j; i++) {
			if (!ExpResult.equals(sel.getOptions().get(i).getText())) {
				logEntryPassMessage("WebListItem is not Present ");
				return;
			}

		}
		logEntryFailMessage("WebListItem is  present");

	}

	public void validations(int Count) throws Exception
	 {
		 
		 
	if(getTestData("TestType",Count).equalsIgnoreCase("N")){
				negative_validation(Count);
			}
			else
			{
		
Boolean a=true;
		

			 if(ObjType.equalsIgnoreCase("WebList")&&TargetProperty.equalsIgnoreCase("html id"))
			 {
				 WebListhtmlid();
				 a=false;
				 }
			 if(ObjType.equalsIgnoreCase("WebList")&&TargetProperty.equalsIgnoreCase("visible"))
			 {
				 a=false;
				 WebListVisible();}
			 if(ObjType.equalsIgnoreCase("WebList")&&TargetProperty.equalsIgnoreCase("value"))
			 { a=false;
				 WebListItemIsPresent();}
			 if(ObjType.equalsIgnoreCase("WebList")&&TargetProperty.equalsIgnoreCase("items count"))
			 { a=false;
				 WebListAllItemsCountVerification();}

			 if(ObjType.equalsIgnoreCase("WebList")&&TargetProperty.equalsIgnoreCase("all items"))
			 { a=false;
				 WebListAllItemsVerification();}


			 if(ObjType.equalsIgnoreCase("WebList")&&TargetProperty.equalsIgnoreCase("Default"))
			 { a=false;
				 WebListDefaultcheck();}
			 if(ObjType.equalsIgnoreCase("WebList")&&TargetProperty.equalsIgnoreCase("selected item index"))
			 { a=false;
				 WebListIndexcheck();}


			 if(ObjType.equalsIgnoreCase("WEBCHECKBOX")&&TargetProperty.equalsIgnoreCase("Checked"))
			 {
				 a=false;
				 WebCheckBoxCheckedVerification();			}
			 if(ObjType.equalsIgnoreCase("WEBCHECKBOX")&&TargetProperty.equalsIgnoreCase("disabled"))
			 {
				 a=false;
				 WebCheckBoxCheckedVerification();			}
			 if(ObjType.equalsIgnoreCase("WEBCHECKBOX")&&TargetProperty.equalsIgnoreCase("html id"))
			 {
				 a=false;
				 WebCheckBoxhtmlidVerification();			}
			 if(ObjType.equalsIgnoreCase("WEBCHECKBOX")&&TargetProperty.equalsIgnoreCase("Visible"))
			 {
				 a=false;
				 WebCheckBoxVisible();			
			 }
			 if(ObjType.equalsIgnoreCase("WEBCHECKBOX")&&TargetProperty.equalsIgnoreCase("type"))
			 {
				 a=false;
				 WebCheckBoxtype();			
			 }

			 if(ObjType.equalsIgnoreCase("WEBEDIT")&&TargetProperty.equalsIgnoreCase("max length")){
				 a=false;
				 WebEditmaxlength();


			 }
			 if(ObjType.equalsIgnoreCase("WEBEDIT")&&TargetProperty.equalsIgnoreCase("Value")){
				 a=false;
				 WebEditValue();	
			 }
			 
			 if(ObjType.equalsIgnoreCase("WEBEDIT")&&TargetProperty.equalsIgnoreCase("NotBlank")){
				 a=false;
				 WebEditNotBlank();	
			 }
			 if(ObjType.equalsIgnoreCase("WEBEDIT")&&TargetProperty.equalsIgnoreCase("placeholder")){
				 a=false;
				 WebEditPlaceHolder();	
			 }
			 if(ObjType.equalsIgnoreCase("WEBEDIT")&&TargetProperty.equalsIgnoreCase("type")){
				 a=false;
				 WebEditType();	
			 }
			 
			 if(ObjType.equalsIgnoreCase("WEBEDIT")&&TargetProperty.equalsIgnoreCase("Disabled")){
				 a=false;
				 WebEditDisabled();	
			 }
			 

			 if(ObjType.equalsIgnoreCase("WebElement")&&TargetProperty.equalsIgnoreCase("html id"))
			 { a=false;
				 WebElementHtmlidVerification();
			 }
			 
			 if(ObjType.equalsIgnoreCase("WebElement")&&TargetProperty.equalsIgnoreCase("innerText"))
			 { a=false;
				 WebElementInnerTextVerification();
			 }

			 if(ObjType.equalsIgnoreCase("WebElement")&&TargetProperty.equalsIgnoreCase("containsText"))
			 { a=false;
				 WebElementContainsTextVerification();
			 }
			 if(ObjType.equalsIgnoreCase("WebElement")&&TargetProperty.equalsIgnoreCase("innerhtml"))
			 { a=false;
				 WebElementInnerTextVerification();
			 }
			 

			 if(ObjType.equalsIgnoreCase("WebElement")&&TargetProperty.equalsIgnoreCase("Visible"))
			 { a=false;
				 WebElementVisible();
			}
			 
			 if(a){
				 throw new Exception("Test data is wrong");
			 }
			 
			 
		}}

	
	private void Webimagename() throws Exception {

		verifyEquals(getElement().getAttribute("id"), "Verifying the name of the image");

	}

	private void WebListhtmlid() throws Exception {

		verifyEquals(getElement().getAttribute("id"), "Verifying the id of the Webelement");

	}

	// Tested

	// Tested
	private void WebListItemIsPresent() throws Exception {
		Select sel = new Select(getElement());
		int j = sel.getOptions().size();
		for (int i = 0; i < j; i++) {
			if (ExpResult.equals(sel.getOptions().get(i).getText())) {
				logEntryPassMessage("WebListItem is Present ");
				return;
			}

		}
		logEntryFailMessage("WebListItem is not present");

	}

	private void WebListIndexcheck() throws Exception {

		// not completed
		org.openqa.selenium.support.ui.Select elementValue4 = new org.openqa.selenium.support.ui.Select(getElement());
		elementValue4.selectByIndex(0);

	}

	private void WebListVerifyOptions() throws Exception {

		Select sel = new Select(getElement());

		String actual = "";
		for (WebElement options : sel.getOptions()) {
			actual += ";" + options.getText();
		}
		if (actual.contains(";"))
			actual = actual.substring(1);

		verifyEquals(actual, "Verify Web List Verify All Options to be Available");

	}

	// Tested
	private void WebListAllItemsVerification() throws Exception {
		Select sel = new Select(getElement());

		for (WebElement options : sel.getOptions()) {
			Actual += "@" + options.getText();
		}
		if (Actual.startsWith("@"))
			Actual = Actual.substring(1);
		if (Actual.endsWith("@"))
			Actual = Actual.substring(0, Actual.length() - 1);

		verifyEquals(Actual, "WebList All Items Verification");

	}

	// Tested
	private void WebListAllItemsCountVerification() throws Exception {

		org.openqa.selenium.support.ui.Select elementValue4 = new org.openqa.selenium.support.ui.Select(getElement());
		List<WebElement> ele = elementValue4.getOptions();
		Actual = Integer.toString(ele.size());
		verifyEquals(Actual, "WebList All Items Count Verification");

	}

	private void WebListDefaultcheck() throws Exception {

		WebElement element = ses.getDriver().findElement(By.id(ObjName));

		Select sel = new Select(element);

		String expectedValue = ses.getTestData("ExpResult");
		String actual = "";
		for (WebElement options : sel.getOptions()) {
			actual += ";" + options.getText();
		}
		if (actual.contains(";"))
			actual = actual.substring(1);
		verifyEquals(expectedValue, actual);

	}

	// tested
	private void WebEditmaxlength() throws Exception {

		Actual = getElement().getAttribute("maxlength");
		verifyEquals(Actual, "verify maxlength of WebEdit");
	}

	// tested
	private void WebEditPlaceHolder() throws Exception {
		Actual = getElement().getAttribute("placeholder");
		verifyEquals(Actual, "verify value of WebEdit");
	}

	// tested
	private void WebEditValue() throws Exception {
		Actual = getElement().getAttribute("value");
		verifyEquals(Actual, "verify value of WebEdit");
	}

	// tested
	private void WebEditNotBlank() throws Exception {
		Actual = getElement().getAttribute("value");
		if (!Actual.equalsIgnoreCase(""))
			logEntryPassMessage("Filed is Mandatory");
		else
			logEntryFailMessage("Field is Mandatory");

	}

	private void WebEditDisabled() throws Exception {
		Actual = getElement().getAttribute("disabled");
		if (Actual.equalsIgnoreCase("disabled"))
			logEntryPassMessage("Webedit is not user editable");
		else
			logEntryFailMessage("Webedit is  user editable");

	}

	private void WebEditType() throws Exception {

		verifyEquals(getElement().getAttribute("type"), "verifying the type of element");

	}

	private void WebButtonhtmlid() throws Exception {
		verifyEquals(getElement().getAttribute("id"), "Verifying the id of the Webelement");
	}

	// tested
	private void WebButtonvisibleVerification() throws Exception {

		if (getElement().isDisplayed()) {
			logEntryPassMessage("Webelement is Visible ");
		} else {
			logEntryFailMessage("Webelement is  not Visible");
		}

	}

	private void WebCheckBoxhtmlidVerification() throws Exception {
		verifyEquals(getElement().getAttribute("id"), "Verifying the id of the Webelement");

	}

	private void WebCheckBoxtype() throws Exception {

		verifyEquals(getElement().getAttribute("type"), "Veryfying the type of webelement");
	}

	// tested
	private void WebCheckBoxVisible() throws Exception {

		if (getElement().isDisplayed()) {
			logEntryPassMessage(" WebCheckBox is Visible");
		} else {
			logEntryFailMessage(" WebCheckBox is not Visible");
		}
	}

	private void WebListVisible() throws Exception {

		if (getElement().getTagName().contains("option") || getElement().getTagName().contains("select")) {
			logEntryPassMessage(" WebListVisible is Visible");
		} else {
			logEntryFailMessage(" WebListVisible is not Visible");
		}
	}

	// tested
	private void WebCheckBoxCheckedVerification() throws Exception {
		if (ExpResult.equals(1)) {
			if (getElement().isSelected()) {
				logEntryPassMessage("WebCheckBox is Checked ");
			} else {
				logEntryFailMessage("WebCheckBox is  not Checked");
			}
		} else if (ExpResult.equals("0")) {
			System.out.println(ExpResult);
			if (!getElement().isSelected()) {
				logEntryPassMessage("WebCheckBox is not Checked ");
			} else {
				logEntryFailMessage("WebCheckBox is   Checked");
			}
		}

	}

	// tested
	private void WebElementInnerTextVerification() throws Exception {
		String text = getElement().getText();
		if (text != "") {
			verifyEquals(text, "verifying Webelement Text");
		} else if (text.contains(":") || text.contains(";")) {
			verifyEquals(getElement().getAttribute("value"), "verifying Webelement Text");
		} else {
			verifyEquals(getElement().getAttribute("value"), "verifying Webelement Text");
		}

	}

	private void WebElementContainsTextVerification() throws Exception {
		String text = getElement().getText();
		if (text != "") {
			verifyContains(text, "verifying Webelement Text");
		} else if (text.contains(":") || text.contains(";")) {
			verifyEquals(getElement().getAttribute("value"), "verifying Webelement Text");
		} else {
			verifyEquals(getElement().getAttribute("value"), "verifying Webelement Text");
		}

	}

	private void WebButtonInnerTextVerification() throws Exception {
		String text = getElement().getText();
		if (text != "") {
			verifyEquals(text, "verifying WebButton Text");
		}

	}

	private void genralGetStyleNegative() throws Exception {
		if (getElement().getAttribute("style").contains(ExpResult))

			logEntryFailMessage("Style is as expected");
		else
			logEntryPassMessage("Style Mismatch, Element enabled--Passed");
	}

	private void genralGetStyle() throws Exception {
		if (getElement().getAttribute("style").contains(ExpResult))

			logEntryPassMessage("Style is as expected");
		else
			logEntryFailMessage("Style Mismatch");
	}

	private void genralEnabled() throws Exception {
		if (getElement().isEnabled())

			logEntryPassMessage("Enabled");
		else
			logEntryFailMessage("Disabled");
	}

	private void genralDisabled() throws Exception {
		if (!getElement().isEnabled()) {
			logEntryPassMessage("Disabled");
		} else
			logEntryFailMessage("Enabled");

	}

	// tested
	private void WebElementVisible() throws Exception {

		if (getElement().isDisplayed()) {
			logEntryPassMessage("Webelement is Visible");
		} else {
			logEntryFailMessage("Webelement is  not Visible");
		}

	}

	private void WebElementHtmlidVerification() throws Exception {

		verifyEquals(getElement().getAttribute("id"), "Veyrfying the id of webelement");

	}

	private void verifyEquals(String Actual, String Message) throws Exception {
		// System.out.println("Print" + ExpResult );
		// System.out.println("Print" + Actual );

		if (ExpResult.trim().equals(Actual.trim()))
			logEntryPassMessage(Message + ">>  Actual : " + Actual + "; Expected : " + ExpResult);
		else
			logEntryFailMessage(Message + ">>  Actual : " + Actual + "; Expected : " + ExpResult);

	}

	private void verifyContains(String Actual, String Message) throws Exception {
		// System.out.println("Print" + ExpResult );
		// System.out.println("Print" + Actual );

		if (Actual.trim().contains(ExpResult.trim()))
			logEntryPassMessage(Message + ">>  Actual : " + Actual + "; Expected : " + ExpResult);
		else
			logEntryFailMessage(Message + ">>  Actual : " + Actual + "; Expected : " + ExpResult);

	}

	private void verifynegativeEquals(String Actual, String Message) throws Exception {
		// System.out.println("Print" + ExpResult );
		// System.out.println("Print" + Actual );
		if (ExpResult.equals(Actual))
			logEntryPassMessage(Message + ">>  Actual : " + Actual + "; Expected : " + ExpResult);
		else
			logEntryFailMessage(Message + ">>  Actual : " + Actual + "; Expected : " + ExpResult);

	}

	private WebElement getElement() throws Exception {
		if (ses.waitFor(20).elementPresent(By.id(ObjName), ""))
			return ses.getAndroidDriver().findElement(By.id(ObjName));

		else if (ses.waitFor(5).elementPresent(By.name(ObjName), ""))
			return ses.getAndroidDriver().findElement(By.name(ObjName));

		else if (ses.waitFor(5).elementPresent(By.xpath(ObjName), ""))
			return ses.getAndroidDriver().findElement(By.xpath(ObjName));

		else if (ses.waitFor(5).elementPresent(By.xpath("//*[contains(text(),'" + ObjName + "')]"), ""))
			return ses.getAndroidDriver().findElement(By.xpath("//*[contains(text(),'" + ObjName + "')]"));

		else if (ses.waitFor(5).elementPresent(By.xpath("//*[.='" + ObjName + "']"), ""))
			return ses.getAndroidDriver().findElement(By.xpath("//*[.='" + ObjName + "']"));

		else if (ses.waitFor(5).elementPresent(By.xpath("//*[@class='" + ObjName + "']"), ""))
			return ses.getAndroidDriver().findElement(By.xpath("//*[@class='" + ObjName + "']"));

		else
			throw new Exception("Element identification Errror with the GUI Verfification");
	}

	private void logEntryPassMessage(String Message) throws Exception {
		ExecutionInstance.addStepEntry(ses, Message, "Pass");
	}

	private void logEntryFailMessage(String Message) throws Exception {
		ExecutionInstance.addStepEntry(ses, Message, "Fail");
		throw new Exception(Message + " : GUI Verfication Failed");
	}

	

}
