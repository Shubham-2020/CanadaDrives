package salesforce.pages;

import salesforce.base.SalesforceBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

public class AccountsPage extends SalesforceBase {
	
	public AccountsPage(RemoteWebDriver driver, ExtentTest node)
	{
		this.driver = driver;
		this.node = node;
	}
	
	
}
