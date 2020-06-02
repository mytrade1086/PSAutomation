package ZZ_Extra;
//package Extra_Code;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import utilities.ElementUtil;
//
//public class ExtraCOde {
//	
//	
//	
	//#### Property Reading
	// public Base() throws IOException {
//	try {
//		prop = new Properties();
//		ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/Config/config.properties");
//		prop.load(ip);
//	} catch (FileNotFoundException e) {
//		e.printStackTrace();
//	}
//	finally {
//		ip.close();
//	}
// }

	
	
//	
//	public void openBrowser() {
//		WebDriverManager.chromedriver().setup();
//
//		ChromeOptions options = new ChromeOptions();
//		Map<String, Object> prefs = null;
//		prefs = new HashMap<String, Object>();
//		prefs.put("credentials_enable_service", false);
//		prefs.put("profile.password_manager_enabled", false);
//		prefs.put("profile.default_content_settings.geolocation", 2);
//		options.setExperimentalOption("prefs", prefs);
//		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//		options.addArguments("test-type");
//		options.setExperimentalOption("useAutomationExtension", false);
//		options.addArguments("start-maximized");
//		options.addArguments("--disable-extensions");
//		options.addArguments("--js-flags=--expose-gc");
//		options.addArguments("--enable-precise-memory-info");
//		options.addArguments("--disable-popup-blocking");
//		options.addArguments("--disable-default-apps");
//		// options.addArguments("--incognito");
//
//		// System.setProperty("webdriver.chrome.driver",
//		// "C:\\Users\\sunshine\\Downloads\\chromedriver_win32
//		// _83\\chromedriver_83.exe");
//		driver = new ChromeDriver(options);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// new
//		el=new ElementUtil(driver);
//
//	}
	
	
	
	

//	// Closing driver
//	public void closeDriver() throws IOException {
//		driver.quit();
//		// Runtime.getRuntime().exec("taskkill /im chrome.exe /f /t");
//		// Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
//
//	}
	
	
	//public void sendMail() {

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

	//}

	//public void launchReport() {
//		sendMail();
//		String strLaunchReport = getConfigVal("LaunchReport");
//		if (strLaunchReport.equals("Yes")) {
//			File file = new File(vFolder + "/TestSummary.html");
//			Desktop.getDesktop().open(file);
	//}
	
	
	
	

//}
