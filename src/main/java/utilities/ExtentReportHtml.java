package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;

public class ExtentReportHtml extends CommonMethod {
	private static final Status FAIL = null;
	String TestFile;
	String vScreenShotFolder;
	protected String vSSPath;
	String vSSPathNew;
	int vSSNo;
	public static ExtentReports extent;
	public static ExtentTest test = null;
	public ExtentHtmlReporter htmlReporter;
	// Creating folder
	static Date date = new Date();
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH-mm");
	static String strDate = dateFormat.format(date);
	static String vFolder = "./reports/SummaryReport_" + strDate;

	public void createReportFolder() {
		// Creating folder
		File f = new File(vFolder);
		if (!f.exists()) {
			f.mkdir();
		}
		htmlReporter = new ExtentHtmlReporter(vFolder + "/TestSummary.html");
		htmlReporter.loadXMLConfig("src/test/resources/extent-config.xml");
		htmlReporter.config().setCSS(".r-img { width: 30%; }");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", getConfigVal("ApplicationEnvironment"));
		extent.setSystemInfo("Application Name", getConfigVal("ApplicationName"));
		extent.setSystemInfo("Sprint", getConfigVal("Sprint"));

	}

	public void startTest(String vFile) {
		test = extent.createTest(vFile);
		// test.log(LogStatus.INFO, vFile+" Test has started");
		// vScreenShotFolder=System.getProperty("user.dir")+"/src/test/resources/Reports/ScreenShots/"+vFile+"_"+strDate;
		vScreenShotFolder = getConfigVal("ScreenshotPath") + "/ScreenShots/" + vFile + "_" + strDate;
	}

	public static String takeScreenshot(String desc) throws IOException {
		String destPath = null;
		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		destPath = vFolder + "\\" + desc + ".png";
		File destinationPath = new File(destPath);
		Files.copy(sourcePath, destinationPath);
		System.out.println("Screenshot Path: " + destPath);
		String screenshotPath = desc + ".png";
		return screenshotPath;
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

	public static void addFailLog(String desc) {

		try {
			// Assert.fail();
			MediaEntityModelProvider screenshot = createScreenCaptureFromPath(takeScreenshot(desc)).build();
			test.log(Status.FAIL, desc, screenshot);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addPassLog(String desc, String takeScreenshot) {
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

	private static MediaEntityBuilder createScreenCaptureFromPath(String takeScreenshot) {
		// TODO Auto-generated method stub
		return null;
	}

	public void endTest() {
		extent.flush();

	}

	public void sendMail() throws EmailException {

		System.out.println("Inside Email");
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(vFolder + "/TestSummary.html");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Automation Report");
		attachment.setName("TestSummary.html");
		System.out.println("Email");
		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("mailrelay.ra.rockwell.com");
		email.setSmtpPort(25);
		email.addTo("SShetty9@ra.rockwell.com", "Sanat Shetty");
		email.setFrom("SShetty9@ra.rockwell.com", "Sanat Shetty");
		email.setSubject("Automation Report");
		email.setMsg("PFA of the ProMS Automation execution. \n\n\n Thanks and Regards, \n Sanat ");
		// add the attachment
		email.attach(attachment);
		// send the email
		email.send();
		System.out.println("Email Sent");

	}

	@AfterSuite
	public void launchReport() throws IOException, EmailException {
		sendMail();
		String strLaunchReport = getConfigVal("LaunchReport");
		if (strLaunchReport.equals("Yes")) {
			File file = new File(vFolder + "/TestSummary.html");
			Desktop.getDesktop().open(file);
		}

	}

}
