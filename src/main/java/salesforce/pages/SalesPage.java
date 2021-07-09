package salesforce.pages;

import salesforce.base.SalesforceBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.aventstack.extentreports.ExtentTest;

public class SalesPage extends SalesforceBase {

	public SalesPage(RemoteWebDriver driver, ExtentTest node) {
		this.driver = driver;
		this.node = node;
	}

	public SalesPage clickOnLead(String value) {
		try {
			solidWait(2);
			WebElement newLead = webDriverWait4ElementToBeClickable(
					driver.findElementByXPath("//a[text()='" + value + "']"));
			newLead.click();
			reportStep("Clicked on Lead tab", "Pass", false);
		} catch (Exception e) {
			reportStep("Not able to click on Lead tab", "Fail");
			e.printStackTrace();
		}
		return this;

	}

	public SalesPage clickOnconvertnavigation() {
		try {
			WebElement navLead = webDriverWait4ElementToBeClickable(
					driver.findElementByXPath("//button[@class='slds-button slds-button_icon-border-filled']"));
			navLead.click();
			reportStep("Navigated to dropdown button", "Pass", false);
			solidWait(1);
			WebElement clickconvert = webDriverWait4ElementToBeClickable(
					driver.findElementByXPath("//span[text()='Convert']"));
			clickconvert.click();
			reportStep("Clicked on Convert tab", "Pass", false);

		} catch (Exception e) {
			reportStep("Not able to click on convert button", "Fail");
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage verifyLeadConversion() {
		String Exptext = "Your lead has been converted";
		try {

			WebElement verifyText = webDriverWait4VisibilityOfEle(
					driver.findElementByXPath("//span[text()='Your lead has been converted']"));
			String actualTxt = verifyText.getText();
			if (Exptext.equals(actualTxt)) {
				System.out.println("Lead converted to account, conctacts and opportunity");
			} else {
				System.out.println("Lead not converted sucessfully");
			}
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage leadConvertButton() {
		try {
			
			WebElement convertLead = webDriverWait4ElementToBeClickable(
					driver.findElementByXPath("//button/span[@class=' label bBody' and text()='Convert']"));
			js.executeScript("arguments[0].click();", convertLead);
			reportStep("Clicked on Lead Convert button", "Pass", false);

		} catch (Exception e) {
			reportStep("Not able to click on Lead button", "Fail");
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage clickOnNewCase() {
		try {
			WebElement newCase = webDriverWait4ElementToBeClickable(
					driver.findElementByXPath("//a[@title='New' and @role='button']"));
			newCase.click();
			reportStep("Clicked on New Case button", "Pass", false);
			solidWait(3);
		} catch (Exception e) {
			reportStep("Not able to click on new case button", "Fail");
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage clickOnCreateNewLead() {
		try {
			WebElement ele = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//div[text()='New']"));
			ele.click();
			reportStep("Clicked on Create new lead button", "Pass", false);
			solidWait(2);
		} catch (Exception e) {
			reportStep("Not able to click on create new lead button", "Fail");
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage searchLead(String value) {
		try {
			WebElement searchLead = webDriverWait4ElementToBeClickable(
					driver.findElementByXPath("//input[contains(@placeholder,'Search this list')]"));
			searchLead.clear();
			searchLead.sendKeys(value);
			searchLead.sendKeys(Keys.ENTER);
			solidWait(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage selectLead(String value) {

		try {
			WebElement dd_lead = webDriverWait4VisibilityOfEle(
					driver.findElementByXPath("//input[@title='Search Leads']"));
			solidWait(2);
			int length = value.length();
			if (length < 2) {
				WebElement dd_leadsearch = webDriverWait4VisibilityOfEle(
						driver.findElementByXPath("//lightning-icon[contains(@class,'inputLookupIcon')]"));
				actions.moveToElement(dd_leadsearch).click().perform();
				solidWait(2);
				WebElement ele = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//span[@title='New Lead']"));
				ele.click();
				solidWait(2);
			} else {
				dd_lead.sendKeys(value);
				dd_lead.sendKeys(Keys.ENTER);
				solidWait(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public SalesPage selectSalutation(String value) {
		try {
			WebElement dd_salutation = webDriverWait4ElementToBeClickable(driver.findElementByXPath(""));
			dd_salutation.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath(
					"(//div[@class='select-options' and @role='menu']/ul//a[contains(@title,'" + value + "')])[1]");
			scrollToVisibleElement(ele);
			ele.click();
			reportStep("Selected Salutation: "+value, "Pass", false);
		} catch (Exception e) {
			reportStep("Not able to select salutation", "Fail");
			e.printStackTrace();
		}
		return this;
	}


	public SalesPage inputPhone(String value) {
		try {
			WebElement phone = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//input[@name='Phone']"));
			phone.clear();
			phone.sendKeys(value);
			reportStep("Input phone number: "+value, "Pass", false);
		} catch (Exception e) {
			reportStep("Not able to input number", "Fail");
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage inputMobile(String value) {
		try {
			WebElement mphone = webDriverWait4ElementToBeClickable(
					driver.findElementByXPath("//input[@name='MobilePhone']"));
			mphone.clear();
			mphone.sendKeys(value);
			reportStep("Input Mobile: "+value, "Pass", false);
		} catch (Exception e) {
			reportStep("Not able to input mobile", "Fail");
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage inputRevenue(String value) {
		try {
			WebElement revenue = webDriverWait4ElementToBeClickable(
					driver.findElementByXPath("//input[@name='AnnualRevenue']"));
			revenue.clear();
			revenue.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage inputEmail(String value) {
		try {
			WebElement email = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//input[@name='Email']"));
			email.clear();
			email.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage inputFax(String value) {
		try {
			WebElement fax = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//input[@name='Fax']"));
			fax.clear();
			fax.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage inputFirstName(String value) {
		try {
			WebElement firstName = webDriverWait4ElementToBeClickable(
					driver.findElementByXPath("//input[@name='firstName']"));
			firstName.clear();
			firstName.sendKeys(value);
			reportStep("Input First name: "+value, "Pass", false);
		} catch (Exception e) {
			reportStep("Not able to input First name", "Fail");
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage inputLastName(String value) {
		try {
			WebElement lastName = webDriverWait4ElementToBeClickable(
					driver.findElementByXPath("//input[@name='lastName']"));
			lastName.clear();
			lastName.sendKeys(value);
			reportStep("Input Last name: "+value, "Pass", false);
		} catch (Exception e) {
			reportStep("Not able to input Last name", "Fail");
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage inputSubject(String value) {
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(
					driver.findElementByXPath("//span[text()='Subject']/parent::label/following-sibling::input"));
			ele.clear();
			ele.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage inputCaseDescription(String value) {
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(driver
					.findElementByXPath("//span[text()='Description']/parent::label/following-sibling::textarea"));
			ele.clear();
			ele.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage inputCompanyName(String value) {
		try {
			WebElement compName = webDriverWait4ElementToBeClickable(
					driver.findElementByXPath("//input[@name='Company']"));
			compName.clear();
			compName.sendKeys(value);
			reportStep("Input Company name: "+value, "Pass", false);
		} catch (Exception e) {
			reportStep("Not able to input company name", "Fail");
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage inputCloseDate(String value) {
		try {
			WebElement closeDate = webDriverWait4ElementToBeClickable(driver.findElementByXPath(
					"//label[text()='Close Date']/following-sibling::div//input[@name ='CloseDate']"));
			closeDate.clear();
			closeDate.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage selectType(String value) {
		try {
			WebElement dd_type = driver
					.findElement(By.xpath("//label[text()='Type']/following-sibling::div//input[@type ='text']"));
			webDriverWait4ElementToBeClickable(dd_type);
			dd_type.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath(
					"(//label[text()='Type']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"
							+ value + "')])[1]");
			scrollToVisibleElement(ele);
			ele.click();
			reportStep("Selected type", "Pass", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage selectLeadSource(String value) {
		try {
			WebElement dd_leadsource = driver.findElement(
					By.xpath("//label[text()='Lead Source']/following-sibling::div//input[@type ='text']"));
			webDriverWait4ElementToBeClickable(dd_leadsource);
			dd_leadsource.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath(
					"(//label[text()='Lead Source']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"
							+ value + "')])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage selectStage(String value) {
		try {
			WebElement dd_stage = driver
					.findElement(By.xpath("//label[text()='Stage']/following-sibling::div//input[@type ='text']"));
			webDriverWait4ElementToBeClickable(dd_stage);
			dd_stage.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath(
					"(//label[text()='Stage']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"
							+ value + "')])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}


	public SalesPage inputAmount(String value) {
		try {
			WebElement oppAmt = webDriverWait4ElementToBeClickable(driver
					.findElementByXPath("//label[text()='Amount']/following-sibling::div//input[@name ='Amount']"));
			oppAmt.clear();
			oppAmt.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage clickOnAddLead() {
		try {
			webDriverWait4ElementToBeClickable(driver.findElementByXPath("//a[@title='Add Leads']")).click();
			reportStep("Clicked on add lead button", "Pass", false);
			solidWait(2);
		} catch (Exception e) {
			reportStep("Not able to click on add lead button", "Fail");
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage clickOnSubmitButton() {
		try {
			WebElement clkSubmit = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//span[text()='Submit']"));
			js.executeScript("arguments[0].click();", clkSubmit);
			reportStep("Clicked on submit button","Pass", false);
		} catch (Exception e) {
			reportStep("Not able to click on submit button", "Fail");
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage clickonSaveButton() {
		try {
			webDriverWait4ElementToBeClickable(
					driver.findElementByXPath("//button[@name='SaveEdit' and text()='Save']")).click();
			reportStep("Clicked on save button", "Pass", false);
			solidWait(1);
		} catch (Exception e) {
			reportStep("Not able to click on save button", "Pass");
			e.printStackTrace();
		}
		return this;
	}



	public SalesPage clickonNextButton() {
		try {
			WebElement clkNext = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//span[text()='Next']"));
			js.executeScript("arguments[0].click();", clkNext);
			reportStep("Click on next button", "Pass", false);
			solidWait(1);
		} catch (Exception e) {
			reportStep("Not able to click on next button", "Fail");
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage clickOndeleteLead(String fName, String lName) {
		String lead = fName + " " + lName;
		try {
			WebElement delLead = webDriverWait4VisibilityOfEle(
					driver.findElementByXPath("(//a[text()='" + lead + "']/following::td//a[@role='button'])[1]"));
			delLead.click();
			delLead = webDriverWait4VisibilityOfEle(
					driver.findElementByXPath("//div[@role='button' and @title='Delete']/.."));
			delLead.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public SalesPage createLeadValidation(String campName, String fName, String lName) {
		String Flag_Validation = null;
		String leadName = fName + " " + lName;
		try {
			WebElement output = driver.findElement(By.xpath("//div[contains(@class,'toastTitle')]"));
			webDriverWait4VisibilityOfEle(output);
			String outputValue = output.getText();

			if (outputValue.contains(campName)) {
				System.out.println(outputValue);
				Flag_Validation = "True";
			} else {
				System.out.println("Unable to update " + campName + ", Failed");
			}

			if (Flag_Validation == "True") {
				clickOnTab("Leads");
				searchLead(leadName);
				WebElement ele = webDriverWait4VisibilityOfEle(
						driver.findElementByXPath("(//a[contains(@class,'forceOutputLookup')])[1]"));
				String val = ele.getText();
				if (val.contains(leadName)) {
					System.out.println("TC-Passed");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return this;
	}

	

}
