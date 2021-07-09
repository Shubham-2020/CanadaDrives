package salesforce.pages;

import salesforce.base.SalesforceBase;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogoutPage extends SalesforceBase {
	
	public LogoutPage(ChromeDriver driver)
	{
		this.driver = driver;
	}
	
	public LoginPage clickLogout()
	{
		driver.findElementByXPath("//img[@alt='User' and @title='User']/ancestor::button").click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByLinkText("Log Out"))).click();
		return new LoginPage(driver, prop, node);
	}
}
