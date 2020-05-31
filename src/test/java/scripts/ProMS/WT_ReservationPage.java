package scripts.ProMS;

import org.openqa.selenium.By;
import org.testng.Assert;

import utilities.ExtentReportHtml;

public class WT_ReservationPage extends ExtentReportHtml{
	By rdoOneWay = By.xpath("//input[@value='oneway']");

	
	public  void verifyReservationPageVisible() {
	Assert.assertTrue(isPresentAndDisplayed(find(rdoOneWay)));
	
	
	}
}
