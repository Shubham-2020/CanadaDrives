package salesforce.pages;

import salesforce.base.SalesforceBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

public class HomePage extends SalesforceBase {
	
	public HomePage(RemoteWebDriver driver, ExtentTest node)
	{
		this.driver = driver;
		this.node = node;
	}
	
	public HomePage clickToggleButton()
	{
		try {
			WebElement menuClk = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//div[@class='slds-icon-waffle']"));
			menuClk.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public HomePage clickViewAll()
	{
		try {
			WebElement viewALL = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']"));
			viewALL.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public HomePage searchApp(String value)
	{
		try {
			WebElement searchApp = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']"));
			searchApp.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	

	
	public SalesPage clickOnSales()
	{
		try {
			WebElement Sales = webDriverWait4ElementToBeClickable(driver.findElementByXPath("(//p/mark[text()='Sales'])[last()-3]"));
			Sales.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SalesPage(driver, node);
	}
}
