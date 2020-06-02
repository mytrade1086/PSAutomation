package WT_Testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import Base.Base;

import WT_Pages.WT_LoginPage;
import WT_Pages.WT_ReservationPage;
import scripts.ProMS.ProMS_PageObject;
import utilities.ElementUtil;
import utilities.ExcelReader;

public class WT_RegressionTest extends Base {
	WT_LoginPage objLgn;
	WT_ReservationPage objReg;

	@BeforeMethod()
	public void BeforeMethod() throws IOException {	
		initilization();
		objLgn=new WT_LoginPage();	
		System.out.println("***Launch Application*****");
	}


	@AfterMethod
	public void afterMethod(ITestResult result) throws InterruptedException, IOException {
		System.out.println("Inside aftermethod");
//
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				test.pass(MarkupHelper.createLabel("Test is Passed", ExtentColor.GREEN));
			} else if (result.getStatus() == ITestResult.FAILURE) {
				test.fail(MarkupHelper.createLabel("Test is Failed", ExtentColor.RED));
				test.error(result.getThrowable());			
				
				
				
//				MediaEntityModelProvider screenshot = createScreenCaptureFromPath(el.takeScreenshot(result.getTestName())).build();
//				test.log(Status.FAIL, desc, screenshot);
				
				
				
				
			   // test.addScreenCaptureFromPath(takeScreenshot(result.getTestName()));

			} else if (result.getStatus() == ITestResult.SKIP) {
				test.log(Status.SKIP, "Test Case Skipped");
			}

			else {
				System.out.println("something wrong");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in " + e.getMessage());
		}
//
//		finally {
//			// were commented originally. Uncommented by Sumit on 30/5/2020
//			System.out.println("***Close Application****");
//			//endTest();
//			closeDriver();
//		}
//		
//		
		afterMethod();
		driver.quit();
		System.out.println("Teardown Performed");
		
		
		
	}
	
	
	
	

		
	
//@Test(description = "myCase", dataProvider = "userData")
	@Test(dataProviderClass=ExcelReader.class,dataProvider = "userData")
	public void myCase(LinkedHashMap<String, String> data) throws InterruptedException {	
		
		objLgn.enterUsername(data.get("username"));
		objLgn.enterPassword(data.get("password"));
		objReg=objLgn.ClickSignin();
		
	//	el.addPassFailonCondition(objReg.verifyReservationPageVisible(),"Reservation page ","takeScreenshot");
		
		if(objReg.verifyReservationPageVisible()) {		
			el.addPassLog("Reservation_page is visible ","takeScreenshot");	
		}
		else {
			el.addFailLog("Reservation_page not visible");	
		}
	}
	
	
	
//	
//	
//	//@Test(description = "myCase", dataProvider = "userData")
//	public void myCaseDDDD(LinkedHashMap<String, String> data) {
//		System.out.println(data);
//		objLgn.enterUsername(data.get("username"));
//		objLgn.enterPassword(data.get("password"));
//		//WT_ReservationPage res = ClickSignin();
//		//Assert.assertTrue(res.verifyReservationPageVisible());
//	}
//
//	//@Test(description = "myCase2", dataProvider = "userData")
//	public void myCase2(LinkedHashMap<String, String> data) {
//		System.out.println(data);
//	}
//
//	//@Test(description = "WT_Register", dataProvider = "userData")
//	public void WT_Register(LinkedHashMap<String, String> data) {
//		System.out.println(data);
//		objLgn.enterUsername(data.get("username"));
//		objLgn.enterPassword(data.get("password"));
//		//WT_ReservationPage res = ClickSignin();
////		Assert.assertTrue(res.verifyReservationPageVisible());
////		res.clickOnewayTrip();
////		res.selectPassengerNum(data.get("selectPassengerNum"));
////		res.selectDepartingFrom(data.get("departing_from"));
//	}
}
