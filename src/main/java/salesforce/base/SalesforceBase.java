package salesforce.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import salesforce.utils.*;
import io.github.bonigarcia.wdm.WebDriverManager;

// To access common methods in SalesForce Application
public class SalesforceBase {

	public RemoteWebDriver driver;
	public ChromeOptions chromeOptions;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions actions;
	public String excelFileName;
	public String excelSheetName;
	public Properties prop;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public ExtentTest test, node;
	public String testName, testDescription, testAuthor, testCategory;
	public static String browser;

	@BeforeSuite
	public void startReport() {
		reporter = new ExtentHtmlReporter("./reports/result.html");
		reporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
	}
	
	@AfterSuite(alwaysRun = true)
	public void endReport() {
		extent.flush();
	}

	@BeforeClass
	public void testDetailedReport() {
		test = extent.createTest(testName, testDescription);
		test.assignAuthor(testAuthor);
		test.assignCategory(testCategory);
		
	}

	public void reportStep(String message, String status, boolean snap) {
		MediaEntityModelProvider img = null;
		try {
			if (snap) {
				int snapNum = 6996;
				snapNum = takeSnap();
				img = MediaEntityBuilder.createScreenCaptureFromPath(".././reports/img_" + snapNum + ".jpg").build();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (status.equalsIgnoreCase("pass")) {
			node.pass(message, img);
			
		}
		if (status.equalsIgnoreCase("fail")) {
			node.fail(message, img);
			throw new RuntimeException();
		}
	}

	public void reportStep(String message, String status) {
		reportStep(message, status, true);
	}

	public int takeSnap() {
		int snapNum = (int) (Math.random() * 6996);
		File srcFile = driver.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./snaps/img_" + snapNum + ".png");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return snapNum;
	}

	@BeforeMethod
	public void launchApp() {
		// killBrowserInstances();
		// Properties File Load
		node = test.createNode(testName);
		try {
			FileInputStream fis = new FileInputStream("./src/main/resources/config.properties");
			prop = new Properties();
			prop.load(fis);
			
			WebDriverManager.chromedriver().setup();
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-notifications");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

			actions = new Actions(driver);
			js = (JavascriptExecutor) driver;

			driver.get(prop.getProperty("url"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		solidWait(2);
		driver.close();
	}

	@DataProvider(name = "getData")
	public String[][] sendData() throws IOException {
		System.out.println("Inside Base data: " + excelFileName);
		String[][] readData = ReadExcel.readData(excelFileName, excelSheetName);
		return readData;
	}

	public void scrollToVisibleElement(WebElement ele) {
		try {
			js.executeScript("arguments[0].scrollIntoView();", ele);
		} catch (JavascriptException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isDisabled(WebElement element) {
		boolean displayed = element.isDisplayed();

		if (displayed) {
			return true;
		} else {
			return displayed;
		}
	}

	public void solidWait(Integer sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public WebElement webDriverWait4VisibilityOfEle(WebElement ele) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ele;
	}

	public WebElement webDriverWait4ElementToBeClickable(WebElement ele) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(ele));
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ele;
	}

	public void clickOnTab(String value) {
		try {
			WebElement ele = driver.findElementByXPath("//a[@title='" + value + "']");
			webDriverWait4ElementToBeClickable(ele);
			js.executeScript("arguments[0].click();", ele);
		} catch (JavascriptException e) {
			e.printStackTrace();
		}
	}

}
