package WT_Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.Base;
import Utilities.ElementUtil;
import Utilities.ExtentReportHtml;
public class WT_LoginPage extends Base {
	
	//Locators
	By inpUserName = By.name("userName");
	By inpPassWord = By.name("password");
	By lnkSignin=By.xpath("//input[@value='Login']");
	By lnkRegister=By.linkText("REGISTER");

//Page actions here
//	public WT_LoginPage() throws IOException  {
//		el=new ElementUtil(driver);
//	}

	public void enterUsername(String uname) {
		//find(inpUserName).sendKeys(uname);
		
		System.out.println("el value is "+el);
		el.doSendKeys(inpUserName, uname);
	}
	
	public void enterPassword(String pword) {
		//find(inpPassWord).sendKeys(pword);
		el.doSendKeys(inpPassWord, pword);
	}
	
	public WT_ReservationPage  ClickSignin() {
		//find(lnkSignin).click();		
		el.doClick(lnkSignin, "Clicking on Sign in button");
		return new WT_ReservationPage() ;
	}

//	public Reservation_Page ClickSignin(String stepDesc) throws IOException, InterruptedException {
//		
//	}
//	
//	public Register_Page clickRegister(String stepDescription ) throws InterruptedException, IOException {
//		
//	}
}
