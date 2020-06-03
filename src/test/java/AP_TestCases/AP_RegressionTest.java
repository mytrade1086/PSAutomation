package AP_TestCases;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import AP_Pages.AP_AccountCreation;
import AP_Pages.AP_HomePage;
import AP_Pages.AP_Login_Registration;
import Base.Base;
import Utilities.ElementUtil;
import Utilities.ExcelReader;
import WT_Pages.WT_LoginPage;
import WT_Pages.WT_ReservationPage;

public class AP_RegressionTest extends  Base {
	
	AP_HomePage objLgn;
	AP_Login_Registration objLgnReg;
	AP_AccountCreation objAcctCreation ;
	@BeforeMethod()
	public void BeforeMethod()   {
		try {
			initilization();
			objLgn = new AP_HomePage();
			System.out.println("***Launch Application*****");
		} catch (Exception e) {		
			System.err.println("Problem while launching browser: "+e.getMessage());			
		}
	}
				
	@AfterMethod
	public void afterMethod(ITestResult result) throws InterruptedException, IOException {
		System.out.println("Inside aftermethod");
		ElementUtil.afterMethod(result);
	}
	
	
	
	@Test(dataProviderClass = ExcelReader.class, dataProvider = "userData")
	public void AT_01_NewAccount(LinkedHashMap<String, String> data) throws InterruptedException {
	   
		objLgn.verifyAP_HomePageVisible();
		objLgnReg=objLgn.clickSignin("Clicked Sign In link");
		el.addPassFailonCondition(objLgnReg.verifyAP_Login_RegistrationVisible(), "Register Page checkpoint", "takeScreenshot","NA");	
		objLgnReg.enterCreateAccountEmail(data.get("emailaddress"));
		
		objAcctCreation=objLgnReg.clickCreateAccountbtn("Clicked create account button");
		el.addPassFailonCondition(objAcctCreation.verifyAP_AccountCreation_Visible(), "Checkpoint for Account Creation", "takeScreenshot","NA");	
		objAcctCreation.clickGenderRadio(data.get("gender"));
		objAcctCreation.enterFirstName(data.get("firstname"));
		objAcctCreation.enterLastName(data.get("lastname"));
		objAcctCreation.enterEmail(data.get("emailaddress"));
		objAcctCreation.enterPassword(data.get("password"));		
		el.addPassLog("Reached password field", "takeScreenshot");
		
		
		
		
		

		
		
	
	     
	
	}
	

}
