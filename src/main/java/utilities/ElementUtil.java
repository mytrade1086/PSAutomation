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

import Base.Base;
import Reports.myListener;


public class ElementUtil  extends myListener{
   
	WebDriverWait wait;
	Actions act;
	public String flash="yes";
	//myListener ml;

	public ElementUtil() {
		wait = new WebDriverWait(Base.driver, 20);
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
	
	//Inprocess
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
		 //placeholder = wait.until(ExpectedConditions.(element));		
		 return placeholder;	
	}
	
	//el.IGF_clickDate(MonthYear, days, "25", "3", "2020");
	
	/***
	 * Sample Call:  el.IGF_clickDate(MonthYear, days, "25", "3", "2020")   
	 * Note that  "dd","MM","yyyy" is input. Also month is one month behind in application. 3 denotes April and so on
	 * @param parentMonthYear  
	 * @param days
	 * @param day
	 * @param month
	 * @param year
	 */
	
	public void IGF_clickDate(List<WebElement> parentMonthYear,List<WebElement> days,String day,String month,String year) {	
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
			wait
			.ignoring(StaleElementReferenceException.class)	;
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
		  boolean status=false;
		  int count=1;
		while(status==false) {
		status= ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
		Thread.sleep(1000);
		count++;
		if(count>30)
		break;
	}}
	

	public static String screenshot() {
		File scr = ((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.FILE);
		String strImagepath=System.getProperty("user.dir")+"\\"+"reports\\ExtentScreenshots\\"+getDatetime()+".png";
		System.out.println("Screenshotpath Generated "+strImagepath);
		try {
			FileUtils.copyFile(scr, new File(strImagepath));
		} catch (IOException e) {
			System.out.println("error while copying file :" + e.getMessage());
		}
		return strImagepath;
	}
	

	
	public static String  getDatetime() {	
		SimpleDateFormat sdf=new SimpleDateFormat("dd-M-yyyy hh:mm:ss");//18-4-2020 07:01:23
		Date d=new Date();
		d.getTime();
		String data=sdf.format(d.getTime());
		return data.replace(":", "_");//18-4-2020 07_03_41
		
	}

	/**
	 * Takes format and daystoadd to currentdate
	 * Ex getDate("dd/MM/yyyy",0)
	 * @param plusminusDays
	 * @return currentdate+plusminusDays
	 */
	public static String getDate(String formatofDate,int plusminusDays) {	
		SimpleDateFormat sdf = new SimpleDateFormat(formatofDate); //M should always be Capital
		//Getting current date
		Calendar cal = Calendar.getInstance();
		//Displaying current date in the desired format
		//System.out.println("Current Date: "+sdf.format(cal.getTime()));
		   
		//Number of Days to add
	     cal.add(Calendar.DAY_OF_MONTH, plusminusDays);  
		//Date after adding the days to the current date
		String datevalue = sdf.format(cal.getTime());  
		//Displaying the new Date after addition of Days to current date
		//System.out.println("Date after Addition: "+newDate);	
		
		return datevalue;
		
	}
	
	
	
public void selectDatejs(WebElement element, String dateVal) {
	JavascriptExecutor js = ((JavascriptExecutor) driver);
	js.executeScript("arguments[0].setAttribute('value', '" + dateVal + "');", element);
}
	
	public static void main(String[] args) {
		System.out.println(getDate("dd/MM/yyyy",0));
		System.out.println(getDate("dd/MM/yyyy",9));
		System.out.println(getDate("dd/MM/yyyy",15));	
//		
//		21/04/2020
//		30/04/2020
//		06/05/2020
		
		
	}
	
	public WebElement getElement(By locator) {
		waitForElementPresent(locator);
		WebElement element = null;
		//try {
			element = driver.findElement(locator);
//		if (flash.equalsIgnoreCase("yes")) {
//			JavaScriptUtil.flash(element, driver);
//			}
//		} catch (Exception e) {
			//System.out.println("Some exception occurred while creating webelement " + locator);
		//}
		return element;
	}
	
	
	public void waitForElementPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void doSendKeys(By locator, String value) {
	//	try {
			getElement(locator).clear();
			getElement(locator).sendKeys(value);
			//myListener.test.info("<b>"+ value+"</b>" + " entered in inputbox ");
			//} catch (Exception e) {
		//	System.out.println("Some exception occurred while sending to webelement " + locator);
		//}
	}
	
	
	public void doClick(By locator,String stepDescription) {
		//try {
			getElement(locator).click();
			//myListener.test.info(stepDescription);
			
		
		//} catch (Exception e) {
		//	System.out.println("Some exception occurred while clicking on webelement " + locator);
		//}
	}	
	
	
	
	
	
	
}


