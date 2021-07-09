package salesforce.pages;

import salesforce.base.SalesforceBase;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

public class LoginPage extends SalesforceBase {
	
	public LoginPage(RemoteWebDriver driver, Properties prop, ExtentTest node)
	{
		this.driver = driver;
		this.prop = prop;
		this.node = node;
	}
	
	public LoginPage enterUsername()
	{
		try {
			webDriverWait4VisibilityOfEle(driver.findElement(By.id("username"))).sendKeys(prop.getProperty("username"));
			reportStep("Username entered as: "+prop.getProperty("username"), "Pass", false);
		} catch (Exception e) {
			reportStep("Username not entered in Login Page", "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public LoginPage enterPassword()
	{
		try {
			webDriverWait4VisibilityOfEle(driver.findElement(By.id("password"))).sendKeys(prop.getProperty("password"));
			reportStep("Password entered as: "+prop.getProperty("password"), "Pass", false);
		} catch (Exception e) {
			reportStep("Password not entered in Login Page", "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public HomePage clickLogin()
	{
		try {
			webDriverWait4ElementToBeClickable(driver.findElement(By.id("Login"))).click();
			reportStep("Clicked on login button", "Pass", false);
			solidWait(2);
		} catch (NoSuchElementException e) {
			reportStep("Not able to find login button", "Fail");
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			reportStep("Error on click of login button", "Fail");
			System.out.println(e.getMessage());
		}
		
		return new HomePage(driver, node);
	}
}
