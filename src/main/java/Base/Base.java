package Base;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import Browser_Config.BrowserOptions;
import Browser_Config.Constants;
import Utilities.ElementUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public static WebDriver driver;
static Date date = new Date();
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH-mm");
	static String strDate = dateFormat.format(date);
	public static ExtentReports extent;
	public static ExtentTest test = null;
	public ExtentHtmlReporter htmlReporter;
	public static ElementUtil el;

	@BeforeSuite
	public void setExtent() {
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\reports\\ExtentReports\\ExtentReport_" + strDate + ".html");
		htmlReporter.loadXMLConfig("src/test/resources/extent-config.xml");
		htmlReporter.config().setCSS(".r-img { width: 30%; }");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	public static void afterMethod() {
		if (extent != null) {
			extent.flush();
		}
	}

	public static void initilization() {
		String browsername = Constants.BROWSER;
		boolean webdriverManager = Constants.WEBDRIVERMANAGER;
		if ((browsername).trim().equalsIgnoreCase("chrome")) {
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
		driver.get((Constants.URL_WT));
		el = new ElementUtil(driver);
		System.out.println("Initilization Code Executed");
	}

public void closeBrowser() {
	if(driver!=null) {
	 driver.quit();
 }
}
}