package WT_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Base.Base;
import utilities.ExtentReportHtml;

public class WT_ReservationPage extends Base{
	
	// Locators here
	By rdoRoundTrip = By.xpath("//input[@value='roundtrip']");
	By rdoOneWay = By.xpath("//input[@value='oneway']");
	By selPassengers = By.xpath("//select[@name='passCount']"); // Selectbox
	
	By selDepartingFrom=By.xpath("//select[@name='fromPort']");
	
	By selDepartingOn_month=By.xpath("//select[@name='fromMonth']");
	By selDepartingOn_day=By.xpath("//select[@name='fromDay']");
	
	By selArrivingIn=By.xpath("//select[@name='toPort']");
	
	By selReturning_month=By.xpath("//select[@name='toMonth']");
	By selReturning_date=By.xpath("//select[@name='toDay']");
	
	By selAirline=By.xpath("//select[@name='airline']");
	
	//Coach Business First
	//input[@value='Coach']

	// Action here
	
	public void clickRoundtrip() {
		//find(rdoRoundTrip).click();
	}

	public void clickOnewayTrip() {
		//find(rdoOneWay).click();
	}
	
	public void selectPassengerNum(String Passengers) {
		//selectOptionByValue(find(selPassengers),"VisibleText",Passengers);		
	}
	
	public void selectDepartingFrom(String departingFrom) {
		//selectOptionByValue(find(selPassengers),"VisibleText",departingFrom);		
	}
	
	public void clickserviceClassRadio(String preference) {
		String xpath="//input[@value=//'"+preference+"//']";
		By rdoService=By.xpath(xpath);
	//	find(rdoService).click();
			
	}
	
	
public  boolean verifyReservationPageVisible() {
	return false;
	//return isPresentAndDisplayed(find(rdoRoundTrip));
	
	}
}