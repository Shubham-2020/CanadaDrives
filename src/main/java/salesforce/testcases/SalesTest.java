package salesforce.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;

public class SalesTest extends SalesforceBase {

	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Sales";
		excelSheetName = "Sales";
		browser = "chrome";
		testName = "LeadConvert";
		testAuthor = "Shubham";
	}

	@Test(dataProvider = "getData", groups = { "Sales" })
	public void ConvertLeadToAccountContactOpportunity(String FstName, String lstName, String Cmpny)
			throws InterruptedException {
		
		new LoginPage(driver, prop, node).enterUsername().enterPassword().clickLogin().clickToggleButton()
		.clickViewAll().searchApp("Sales").clickOnSales();

		clickOnTab("Leads");

		new SalesPage(driver, node).clickOnCreateNewLead().inputFirstName(FstName).inputLastName(lstName)
		.inputCompanyName(Cmpny).clickonSaveButton().clickOnLead(lstName).clickOnconvertnavigation()
		.leadConvertButton();

	}
}
