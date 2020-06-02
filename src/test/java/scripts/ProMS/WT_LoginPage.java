package scripts.ProMS;

import java.io.IOException;

import org.openqa.selenium.By;

import Utilities.ExtentReportHtml;
public class WT_LoginPage extends ExtentReportHtml {
	
	By inpUserName = By.name("userName");
	By inpPassWord = By.name("password");
	By lnkSignin=By.xpath("//input[@value='Login']");
	By lnkRegister=By.linkText("REGISTER");

//Page actions here
	public WT_LoginPage()  {
		
		
	}

	public void enterUsername(String uname) {
		find(inpUserName).sendKeys(uname);
	}

	public void enterPassword(String pword) {
		find(inpPassWord).sendKeys(pword);
	}
	
	public WT_ReservationPage  ClickSignin() {
		find(lnkSignin).click();
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
