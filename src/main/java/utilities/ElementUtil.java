package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;

import Base.Base;
import Reports.IExtentReportGenericMethods;
import Reports.myListener;

public class ElementUtil extends myListener implements IExtentReportGenericMethods {
	WebDriverWait wait;
	Actions act;
	public String flash = "yes";

	public ElementUtil(WebDriver driver) {
		wait = new WebDriverWait(driver, 20);
		act = new Actions(Base.driver);
	}

	public boolean waitForElementVisibility(WebElement element) throws InterruptedException {
		jsWaitForPageLoad();
		WebElement placeholder = null;
		// wait.until(ExpectedConditions.
		placeholder = wait.until(ExpectedConditions.visibilityOf(element));
		if (placeholder == null)
			return false;
		else
			return true;
	}

	// Inprocess
	public boolean waitForElementVisibilityByLocator(By byLocator) throws InterruptedException {
		jsWaitForPageLoad();
		WebElement placeholder = null;
		// wait.until(ExpectedConditions.

		placeholder = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		if (placeholder == null)
			return false;
		else
			return true;
	}

	public boolean waitForElementInvisibility(WebElement element) throws InterruptedException {
		jsWaitForPageLoad();
		boolean placeholder = false;
		placeholder = wait.until(ExpectedConditions.invisibilityOf(element));
		return placeholder;
	}

	public boolean waitforElement(WebElement element) throws InterruptedException {
		jsWaitForPageLoad();
		boolean placeholder = false;
		// placeholder = wait.until(ExpectedConditions.(element));
		return placeholder;
	}

	// el.IGF_clickDate(MonthYear, days, "25", "3", "2020");

	/***
	 * Sample Call: el.IGF_clickDate(MonthYear, days, "25", "3", "2020") Note that
	 * "dd","MM","yyyy" is input. Also month is one month behind in application. 3
	 * denotes April and so on
	 * 
	 * @param parentMonthYear
	 * @param days
	 * @param day
	 * @param month
	 * @param year
	 */

	public void IGF_clickDate(List<WebElement> parentMonthYear, List<WebElement> days, String day, String month,
			String year) {
		Actions act = new Actions(driver);
		for (int j = 0; j < parentMonthYear.size(); j++) {
			if (parentMonthYear.get(j).getAttribute("data-month").equals(month)) {
				for (int i = 0; i < days.size(); i++) {
					if (days.get(i).getText().equals(day)) {
						act.moveToElement(days.get(i)).click().sendKeys(Keys.ESCAPE).build().perform();
						act.sendKeys(Keys.ESCAPE).build().perform();
						break;
					}
				}
				break;
			}
		}
	}

//	public boolean waitForElementStaleReference(WebElement element) {
//		WebElement placeholder = null;
//		wait
//			.ignoring(StaleElementReferenceException.class)			
//			.pollingEvery(Duration.ofSeconds(1));
//		     placeholder = wait.until(ExpectedConditions.visibilityOf(element));
//		if (placeholder == null)
//			return false;
//		else
//			return true;
//	}
//	
	/***
	 * 
	 * Takes user to webelement by scrolling the screen
	 * 
	 * @param element
	 * @throws InterruptedException
	 */

	public void jsScrolltoElement(WebElement element) throws InterruptedException {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

		} catch (Exception e) {
			wait.ignoring(StaleElementReferenceException.class);
			Thread.sleep(2000);
			System.out.println("Error while scrolling to element: " + e.getMessage());
		}
	}

	/***
	 * 
	 * Clicks WebElement with Actions class
	 * 
	 * @param element
	 */
	public void clickWithActions(WebElement element) {
		// try {
		act.click(element);
		System.out.println("Clicked with Actions");
		// } catch (Exception e) {

		// System.out.println("Error while clicking element with Actions class:
		// "+e.getMessage());
		// }
	}

	public void jsClick(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			System.out.println("Error while scrolling to element: " + e.getMessage());
		}
	}

	public void jsscrollPixel(int pixels) {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ")");
		} catch (Exception e) {
			System.out.println("Error while scrolling to element: " + e.getMessage());
		}
	}

	public void jsWaitForPageLoad() throws InterruptedException {
		boolean status = false;
		int count = 1;
		while (status == false) {
			status = ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			Thread.sleep(1000);
			count++;
			if (count > 30)
				break;
		}
	}

	// Written by Sumit . Anika has different method
	public static String screenshot() {
		File scr = ((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.FILE);
		String strImagepath = System.getProperty("user.dir") + "\\" + "reports\\ExtentScreenshots\\" + getDatetime()
				+ ".png";
		System.out.println("Screenshotpath Generated " + strImagepath);
		try {
			FileUtils.copyFile(scr, new File(strImagepath));
		} catch (IOException e) {
			System.out.println("error while copying file :" + e.getMessage());
		}
		return strImagepath;
	}

	public static String getDatetime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");// 18-4-2020 07:01:23
		Date d = new Date();
		d.getTime();
		String data = sdf.format(d.getTime());
		return data.replace(":", "_");// 18-4-2020 07_03_41

	}

	/**
	 * Takes format and daystoadd to currentdate Ex getDate("dd/MM/yyyy",0)
	 * 
	 * @param plusminusDays
	 * @return currentdate+plusminusDays
	 */
	public static String getDate(String formatofDate, int plusminusDays) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatofDate); // M should always be Capital
		// Getting current date
		Calendar cal = Calendar.getInstance();
		// Displaying current date in the desired format
		// System.out.println("Current Date: "+sdf.format(cal.getTime()));

		// Number of Days to add
		cal.add(Calendar.DAY_OF_MONTH, plusminusDays);
		// Date after adding the days to the current date
		String datevalue = sdf.format(cal.getTime());
		// Displaying the new Date after addition of Days to current date
		// System.out.println("Date after Addition: "+newDate);

		return datevalue;

	}

	public void selectDatejs(WebElement element, String dateVal) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].setAttribute('value', '" + dateVal + "');", element);
	}

	public static void main(String[] args) {
		System.out.println(getDate("dd/MM/yyyy", 0));
		System.out.println(getDate("dd/MM/yyyy", 9));
		System.out.println(getDate("dd/MM/yyyy", 15));
//		
//		21/04/2020
//		30/04/2020
//		06/05/2020

	}

	public WebElement getElement(By locator) {
		waitForElementPresent(locator);
		WebElement element = null;
		// try {
		element = driver.findElement(locator);
//		if (flash.equalsIgnoreCase("yes")) {
//			JavaScriptUtil.flash(element, driver);
//			}
//		} catch (Exception e) {
		// System.out.println("Some exception occurred while creating webelement " +
		// locator);
		// }
		return element;
	}

	public void waitForElementPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void doSendKeys(By locator, String value) {
		// try {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
		// myListener.test.info("<b>"+ value+"</b>" + " entered in inputbox ");
		// } catch (Exception e) {
		// System.out.println("Some exception occurred while sending to webelement " +
		// locator);
		// }
	}

	public void doClick(By locator, String stepDescription) {
		// try {
		getElement(locator).click();
		// myListener.test.info(stepDescription);

		// } catch (Exception e) {
		// System.out.println("Some exception occurred while clicking on webelement " +
		// locator);
		// }
	}

	// Extent Report Method From Anika's Framework

	public void createReportFolder() {
		// Creating folder
//		File f = new File(vFolder);
//		if (!f.exists()) {
//			f.mkdir();
//		}
//		htmlReporter = new ExtentHtmlReporter(vFolder + "/TestSummary.html");
//		htmlReporter.loadXMLConfig("src/test/resources/extent-config.xml");
//		htmlReporter.config().setCSS(".r-img { width: 30%; }");
//		extent = new ExtentReports();
//		extent.attachReporter(htmlReporter);
//		extent.setSystemInfo("Environment", getConfigVal("ApplicationEnvironment"));
//		extent.setSystemInfo("Application Name", getConfigVal("ApplicationName"));
//		extent.setSystemInfo("Sprint", getConfigVal("Sprint"));

	}

	public void startTest(String vFile) {
//		test = extent.createTest(vFile);
//		// test.log(LogStatus.INFO, vFile+" Test has started");
//		// vScreenShotFolder=System.getProperty("user.dir")+"/src/test/resources/Reports/ScreenShots/"+vFile+"_"+strDate;
//		vScreenShotFolder = getConfigVal("ScreenshotPath") + "/ScreenShots/" + vFile + "_" + strDate;
	}

	public String takeScreenshot(String desc) throws IOException {
		String destPath = null;
		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// destPath = vFolder + "\\" + desc + ".png";

		destPath = System.getProperty("user.dir") + "\\" + "reports\\ExtentScreenshots\\" + desc +".png";
		File destinationPath = new File(destPath);
		Files.copy(sourcePath, destinationPath);
		System.out.println("Screenshot Path ISSSSSSS: " + destPath);
		// String screenshotPath = desc + ".png";

		return destPath; // screenshotPath;
	}

//	public static String takeScreenshot(String desc) {
//		String destPath = null;
//		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		String encodedBase64 = null;
//		FileInputStream fileInputStreamReader = null;
//		try {
//			fileInputStreamReader = new FileInputStream(sourcePath);
//			byte[] bytes = new byte[(int) sourcePath.length()];
//			fileInputStreamReader.read(bytes);
//			encodedBase64 = new String(Base64.getEncoder().encode(bytes));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "data:image/png;base64," + encodedBase64;
//
////		String destPath = null;
////		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
////		File failureImageFile = new File(vFolder + "/" + desc + ".png");
////
////		FileUtils.copyFile(sourcePath, failureImageFile);
////		InputStream is = new FileInputStream(failureImageFile);
////		byte[] imageBytes = IOUtils.toByteArray(is);
////		Thread.sleep(2000);
////		String base64 = Base64.getEncoder().encodeToString(imageBytes);
////		test.addScreenCaptureFromBase64String("data:image/png;base64," + base64, "Boom");
////		// test.addScreenCaptureFromBase64String(base64, "Boom");
////		return base64;
//
//	}

	public void addFailLog(String desc) {

		try {
			// Assert.fail();
	//	MediaEntityModelProvider screenshot = createScreenCaptureFromPath(takeScreenshot(desc)).build();
		
		
		//createScreenCaptureFromPath
			
			//test.addScreenCaptureFromPath(takeScreenshot(desc));
		//	test.log(Status.FAIL, desc, screenshot);
			test.log(Status.FAIL, desc);
			test.addScreenCaptureFromPath(takeScreenshot(desc));
			
			//test.log(status, details, MediaEntityModelProvider.)

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addPassLog(String desc, String takeScreenshot) {
		if (takeScreenshot.equals("takeScreenshot")) {

			try {
				test.log(Status.PASS, desc);
				test.addScreenCaptureFromPath(takeScreenshot(desc));
			} catch (IOException e) {
				System.out.println("Could not add Pass Log");
			}
		} else {
			test.log(Status.PASS, desc);
		}

	}

	public  void addPassFailonCondition(boolean pass_fail, String desc, String takeScreenshot) {

		// Passcode
		if (pass_fail = true) {
			addPassLog(desc, takeScreenshot);
		}

		else {
			addFailLog(desc);
		}

	}

	public MediaEntityBuilder createScreenCaptureFromPath(String takeScreenshot) {
		// TODO Auto-generated method stub
		return null;
	}

	public void endTest() {
		extent.flush();
	}

	public void sendMail() {

//		System.out.println("Inside Email");
//		EmailAttachment attachment = new EmailAttachment();
//		//attachment.setPath(vFolder + "/TestSummary.html");
//		attachment.setDisposition(EmailAttachment.ATTACHMENT);
//		attachment.setDescription("Automation Report");
//		attachment.setName("TestSummary.html");
//		System.out.println("Email");
//		// Create the email message
//		MultiPartEmail email = new MultiPartEmail();
//		email.setHostName("mailrelay.ra.rockwell.com");
//		email.setSmtpPort(25);
//		email.addTo("SShetty9@ra.rockwell.com", "Sanat Shetty");
//		email.setFrom("SShetty9@ra.rockwell.com", "Sanat Shetty");
//		email.setSubject("Automation Report");
//		email.setMsg("PFA of the ProMS Automation execution. \n\n\n Thanks and Regards, \n Sanat ");
//		// add the attachment
//		email.attach(attachment);
//		// send the email
//		email.send();
//		System.out.println("Email Sent");

	}

	public void launchReport() {
//		sendMail();
//		String strLaunchReport = getConfigVal("LaunchReport");
//		if (strLaunchReport.equals("Yes")) {
//			File file = new File(vFolder + "/TestSummary.html");
//			Desktop.getDesktop().open(file);
	}

}
