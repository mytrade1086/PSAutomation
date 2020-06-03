package AP_Pages;

import org.openqa.selenium.By;

import Base.Base;

public class AP_HomePage extends Base {

//Locators Here
	By lnk_Signin = By.linkText("Sign in");
	
	
	
	

//Actions

	public AP_Login_Registration clickSignin(String stepDescription) {
		el.doClick(lnk_Signin, stepDescription);
		return new AP_Login_Registration();
	}

}