package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.yaml.snakeyaml.Yaml;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonMethod extends ExcelReader {
	public static WebDriver driver;

	protected void openBrowser() {
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
		
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\sunshine\\Downloads\\chromedriver_win32 _83\\chromedriver_83.exe");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);// new

	}

	// Closing driver
	public void closeDriver() throws IOException {
		driver.quit();
		// Runtime.getRuntime().exec("taskkill /im chrome.exe /f /t");
		// Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");

	}

	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String[] getInputsInAList(String Input) {

		String[] InputList = null;
		InputList = Input.split(",");
		return InputList;

	}

	public static boolean isPresentAndDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not present" + element.toString());
			return false;

		}
	}

	public WebElement find(By locator) {
		WebElement element = null;

		int attempts = 0;
		while (attempts < 2) {
			try {
				element = driver.findElement(locator);
				return element;
			} catch (NoSuchElementException e) {

				System.out.println("Element not found");
				e.printStackTrace();
			} catch (StaleElementReferenceException e) {
				System.out.println("Stale element, finding again" + element.toString());
			}
		}
		return element;
	}

	public static String getCurrentDate(String format) {
		DateFormat df = new SimpleDateFormat(format);
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
		return df.format(dateobj);
	}

	public void waitForElementPresent(By element) {
		int attempts = 0;
		while (attempts < 2) {

			try {
				WebDriverWait wait = new WebDriverWait(driver, 70);
				wait.until(ExpectedConditions.presenceOfElementLocated(element));
				break;
			}

			catch (StaleElementReferenceException e) {
				System.out.println("some error occured while waiting for element" + element.toString());
			}
			attempts++;
		}
	}

	public boolean ifValueIsBlank(WebElement element) {
		boolean present = false;
		String value = element.getAttribute("value");
		System.out.println("value :" + value + ":");
		if (value.isEmpty()) {
			present = true;
		}
		return present;
	}

	public static String randomString() {
		String randomString = new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(3);
		return "Auto" + randomString;
	}

	public void actionsClick(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}

	public void doubleClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
	}

	public void ObjectClick(WebElement ele) {
		ele.click();
	}

	public String getAllOptionFromSelect(WebElement ele) {
		String str = null;
		Select select = new Select(ele);
		List<WebElement> allOptions = select.getOptions();
		for (int i = 0; i < allOptions.size(); i++) {
			if (i == 0)
				str = allOptions.get(i).getText();
			else
				str = str + ";" + allOptions.get(i).getText();
		}

		return str;
	}

	public void screenCapture(String destPath) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile //method
			FileUtils.copyFile(src, new File(destPath));
		}

		catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}

	public boolean WebEditBoxSet(WebElement element, String val) {
		// setting text box value
		if (element != null) {
			element.clear();
			element.sendKeys(val);
		}
		// fetching txt value to validate function sets the value
		String vActual = element.getAttribute("value").trim();
		if (vActual.equals(val))
			return true;
		else
			return false;

	}

	public boolean WebObjectClick(WebElement element) {
		// setting text box value
		if (element != null)
			try {
				element.click();
				Thread.sleep(10000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		return true;

	}

	public boolean WebObjectExists(WebElement element) {
		// setting text box value
		if (element != null)
			return true;
		else
			return false;
	}

	public String WebElementGetText(WebElement el) {
		return el.getText();
	}

	// public String WebElementsGetText(String val)
	public String WebElementsGetText(List<WebElement> ele) {
		int i = 0;
		String values = "";
		// List<WebElement> ele=getElements(val);
		for (WebElement el : ele) {
			if (i == 0)
				values = el.getText();
			else
				values = values + ";" + el.getText();
			i = i + 1;
		}
		return values;
	}

	public String getAllLiValueforUL(WebElement ele) throws InterruptedException {
		List<WebElement> li;
		int i = 0;
		String val = "";

		ele.click();
		Thread.sleep(10000);
		li = ele.findElements(By.tagName("li"));
		// li=getAllLiObjectforUL(ele);
		if (li != null) {
			for (WebElement option : li) {
				if (i == 0) {
					val = option.getText();
				} else {
					val = val + ";" + option.getText();
				}
				i = i + 1;
			}
		}
		return val;
	}

	public List<WebElement> getAllLiObjectforUL(WebElement ele) throws InterruptedException {
		List<WebElement> options = null;
		if (WebObjectExists(ele)) {
			options = ele.findElements(By.tagName("li"));
		}
		return options;
	}

	public String WebElementVisibleStatus(WebElement el) {
		boolean vstatus;
		vstatus = el.isEnabled();
		if (vstatus == true)
			return "Enable";
		else
			return "Disable";

	}

	public void selectOption(WebElement ele, String selectBy, String txt, int index, String val) {
		Select select = new Select(ele);
		if (selectBy.equals("VisibleText")) {
			select.selectByVisibleText(txt);
		}

		if (selectBy.equals("Index")) {
			select.selectByIndex(index);
		}

		if (selectBy.equals("Value")) {
			select.selectByValue(val);
		}
	}

	public String WebElementGetAttribute(WebElement el, String atrName) {
		String str = el.getAttribute(atrName);
		if (str.equals("")) {
			return "";
		} else {
			return str;
		}
	}

	public boolean IsFileExist(String vFilePath) {
		File file = new File(vFilePath);
		return file.exists();
	}

	public void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public void UploadFile(String fileLocation, String files) {
		try {
			Robot robot = new Robot();

			// Setting clipboard with file location
			setClipboardData(fileLocation);

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(2000);

			// Setting clipboard with files
			setClipboardData(files);

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		}

		catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public String getSelectedOption(WebElement el) {
		Select select = new Select(el);
		String txt = WebElementGetText(select.getFirstSelectedOption());
		if (!txt.equals(""))
			return txt;
		else
			return "";
	}

	public File[] getFilesFromFolder(String FolderPath) {
		File folder = new File(FolderPath);
		File[] listOfFiles = folder.listFiles();
		return listOfFiles;
	}

	public String[] getFilesNameFromFolder(String FolderPath) {
		File folder = new File(FolderPath);
		String[] listOfFiles = folder.list();
		return listOfFiles;
	}

	public String getFileName(File fileName) {
		return fileName.getName();
	}

	public boolean DeleteFile(String FolderPath, String file) {
		int vFlag = 0;

		File[] files = getFilesFromFolder(FolderPath);
		for (File eachFile : files) {
			String vFileName = getFileName(eachFile);
			if (vFileName.contains(file)) {
				eachFile.delete();
				vFlag = 1;
				break;
			} else
				vFlag = 0;
		}

		if (vFlag == 0)
			return false;

		return true;
	}

	public void PageDown() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	public void PageUp() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_UP);
		robot.keyRelease(KeyEvent.VK_PAGE_UP);
	}

	public boolean AlertExist() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
			System.out.println("alert was not present");
			return false;
		} else {
			System.out.println("alert was present");
			return true;
		}
	}

	public void PressEnter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public void PressTAB() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	public boolean DeleteFiles(String FolderPath, String file) {
		int vFlag = 0;

		File[] files = getFilesFromFolder(FolderPath);
		for (File eachFile : files) {
			String vFileName = getFileName(eachFile);
			if (vFileName.contains(file)) {
				eachFile.delete();
				vFlag = 1;
			}
		}

		if (vFlag == 0)
			return false;

		return true;
	}

	public void XYCordinateWithLeftClick(WebElement el, int X, int Y) throws AWTException, InterruptedException {
		Point coordinates = el.getLocation();
		Robot robot = new Robot();
		robot.mouseMove(coordinates.getX() + X, coordinates.getY() + Y);
		Thread.sleep(5000);
		// Press Left button
		robot.mousePress(InputEvent.BUTTON1_MASK);
		// Release Left button
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(3000);
	}

	public String ReadAllContentFile(String FilePath) throws IOException {
		String strCnt;
		File file = new File(FilePath);
		strCnt = FileUtils.readFileToString(file);
		return strCnt;

	}

	public void CopyFileFromSrcToDest(File file1, File file2) throws IOException, InterruptedException {
		FileUtils.copyFile(file1, file2);
		Thread.sleep(1000);
	}

	public Boolean CheckBoxOnOff(WebElement ObjectElement, String CheckboxState) throws InterruptedException {
		if (WebObjectExists(ObjectElement)) {
			if (CheckboxState.toUpperCase().equals("ON") && !(ObjectElement.isSelected())) {
				if (WebObjectClick(ObjectElement)) {
					System.out.println("Successfully checked expected checkbox.");
				} else {
					System.out.println("Failed to check expected checkbox.");
					return false;
				}
			}

			else if (CheckboxState.toUpperCase().equals("OFF") && ObjectElement.isSelected()) {
				if (WebObjectClick(ObjectElement)) {
					System.out.println("Successfully unchecked expected checkbox.");
				} else {
					System.out.println("Failed to uncheck expected checkbox.");
					return false;
				}
			}
		} else {
			System.out.println("Expected checkbox does not exist.");
			return false;
		}
		return true;
	}

	public File createResultFile() throws IOException {
		File f = new File("./src/test/resources/testData/Driver.xlsx");
		File f1 = new File("./src/test/resources/results/Results" + getCurrentDate("dd-MMM-yyyy") + "-"
				+ System.currentTimeMillis() + ".xlsx");
		Files.copy(f, f1);
		return f1;
	}

	public static void writeIntoExcel()
			throws FileNotFoundException, IOException, EncryptedDocumentException, InvalidFormatException {
		File f = new File("./src/test/resources/testData/Driver.xlsx");
		File f1 = new File("./src/test/resources/results/Results" + getCurrentDate("dd-MMM-yyyy") + "-"
				+ System.currentTimeMillis() + ".xlsx");
		Files.copy(f, f1);
		FileInputStream fis = new FileInputStream(f1);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("TestData_UAT");
		XSSFRow row1 = sheet.getRow(2);
		XSSFCell cell1 = row1.createCell(19);
		cell1.setCellValue("Sanat");
		fis.close();
		FileOutputStream fos = new FileOutputStream(f1);
		workbook.write(fos);
		fos.close();
		System.out.println("Done");

	}
}