package AP_Pages;

import org.openqa.selenium.By;

import Base.Base;

public class AP_AccountCreation extends Base {

	// Locators
	By text_PersonalInformation = By.xpath("//*[contains(text(),'Your personal information')]");

	By rdo_Mr = By.xpath("//label[text()[normalize-space()='Mr.']]");
	By rdo_Mrs = By.xpath("//label[text()[normalize-space()='Mrs.']]");
	By txt_FirstName = By.id("customer_firstname");
	By txt_LastName = By.cssSelector("input#customer_lastname");

	By text_Email = By.id("email");
	By text_Password = By.id("passwd");

	By sel_Days = By.id("email");
	By sel_Months = By.id("months");
	By sel_Years = By.id("years");

	public boolean verifyAP_AccountCreation_Visible() throws InterruptedException {
		return el.waitForElementVisibilityByLocator(text_PersonalInformation);
	}

	public void clickGenderRadio(String maleFemale) {
		if (maleFemale.trim().toLowerCase().equals("male")) {
			el.doClick(rdo_Mr, "clicking " + maleFemale);
		}
		else if (maleFemale.trim().toLowerCase().equals("female")) {
			el.doClick(rdo_Mr, "clicking " + maleFemale);
		}
	}

	public void enterFirstName(String firstName) {
		el.doSendKeys(txt_FirstName, firstName);
	}

	public void enterLastName(String lastName) {
		el.doSendKeys(txt_LastName, lastName);
	}
	
	public void enterEmail(String email) {
		el.doSendKeys(text_Email, email);
	}
	
	
	public void enterPassword(String lastpassword) {
		el.doSendKeys(text_Password, lastpassword);
	}

	
	public void selectDay(String day) {
		
		
		
	}
	
	public void selectMonth(String month) {
		
		
	}
	
	public void selectYears(String years) {
		
		
	
	}
	
	
	
	
	
	
	
}