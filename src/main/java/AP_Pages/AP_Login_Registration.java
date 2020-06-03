package AP_Pages;

import org.openqa.selenium.By;

import Base.Base;

public class AP_Login_Registration extends Base {

	// input[@id='email_create']
	By txt_Email_Create = By.xpath("//input[@id='email_create']");
	By btn_CreateAccount = By.xpath("//button[@id='SubmitCreate']");
   By err_CreateAccount=By.id("//div[@id='create_account_error']");

	//

	
// Actions
	public boolean verifyAP_Login_RegistrationVisible() throws InterruptedException {
		return el.waitForElementVisibilityByLocator(txt_Email_Create);
	}
	
	
public void enterCreateAccountEmail(String emailAddress) {
		el.doSendKeys(txt_Email_Create, emailAddress);
	}
	
	public AP_AccountCreation clickCreateAccountbrn(String stepdescription) {
		el.doClick(txt_Email_Create, stepdescription);		
		return new AP_AccountCreation();
	}


	public boolean verifyCreateAccountErrorMsg() {
		

		return false;
	}
	
	
	
	
	
	
}
