package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import BrowserConfig.BrowserOptions;
import Config.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	FileInputStream ip;

//	public static ExtentSparkReporter spark ;
//	public static ExtentReports extent ;
//	public static ExtentTest test;

	static Date date = new Date();
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH-mm");
	static String strDate = dateFormat.format(date);
	public static ExtentReports extent;
	public static ExtentTest test = null;
	public ExtentHtmlReporter htmlReporter;
	// Creating folder
	// static String vFolder = "./reports/SummaryReport_" + strDate;

	@BeforeSuite
	public void setExtent() {
//		spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\target\\ExtentReports\\ExtentReport_"+strDate+".html");
//		spark.loadXMLConfig("src\\main\\java\\Reports\\ExtentReportConfig.xml");
//		extent = new ExtentReports();
//		extent.attachReporter(spark);

		htmlReporter = new ExtentHtmlReporter(
			System.getProperty("user.dir") + "\\reports\\ExtentReports\\ExtentReport_" + strDate + ".html");
		htmlReporter.loadXMLConfig("src/test/resources/extent-config.xml");
		htmlReporter.config().setCSS(".r-img { width: 30%; }");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
//		extent.setSystemInfo("Environment", getConfigVal("ApplicationEnvironment"));
//		extent.setSystemInfo("Application Name", getConfigVal("ApplicationName"));
//		extent.setSystemInfo("Sprint", getConfigVal("Sprint"));	

	}

	public static void afterMethod() {
		if (extent != null) {
			extent.flush();
		}
	}

	// @BeforeClass
	public void LaunchT2Q() throws InterruptedException {
		System.out.println("Start");
		// createReportFolder();

	}



	public static void initilization() {
		// String browsername = prop.getProperty("browser");

		String browsername = "chrome";
		boolean webdriverManager = false;
		// chromeotions

		if ((browsername).equalsIgnoreCase("chrome")) {
			if (webdriverManager) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(BrowserOptions.setChromeOptions());
			} else {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/Drivers/chromedriver_83.exe");
				System.setProperty("webdriver.chrome.silentOutput", "true");
				driver = new ChromeDriver(BrowserOptions.setChromeOptions());
			}

		} else if ((browsername).equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
		System.out.println("Initilization Code Executed");
		driver.get((Constants.URL_WT));
	}

	public void openBrowser() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = null;
		prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.default_content_settings.geolocation", 2);
		options.setExperimentalOption("prefs", prefs);
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.addArguments("test-type");
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("start-maximized");
		options.addArguments("--disable-extensions");
		options.addArguments("--js-flags=--expose-gc");
		options.addArguments("--enable-precise-memory-info");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");
		// options.addArguments("--incognito");

		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Users\\sunshine\\Downloads\\chromedriver_win32
		// _83\\chromedriver_83.exe");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// new

	}

	// Closing driver
	public void closeDriver() throws IOException {
		driver.quit();
		// Runtime.getRuntime().exec("taskkill /im chrome.exe /f /t");
		// Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");

	}

}
