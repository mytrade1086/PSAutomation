package WT_Testcases;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import Base.Base;
import Utilities.ElementUtil;
import Utilities.ExcelReader;
import WT_Pages.WT_LoginPage;
import WT_Pages.WT_ReservationPage;

public class ExtentDemo extends Base {
	WT_LoginPage objLgn;
	WT_ReservationPage objReg;
	@BeforeMethod()
	public void BeforeMethod()   {
		try {
			initilization();
			objLgn = new WT_LoginPage();
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
	public void myCase(LinkedHashMap<String, String> data) throws InterruptedException {
		System.out.println(data);
		objLgn.enterUsername(data.get("username"));
		objLgn.enterPassword(data.get("password"));
	     WT_ReservationPage res = objLgn.ClickSignin();   
	     el.addPassFailonCondition(res.verifyReservationPageVisible(), "Reservation Page", "takeScreenshot","na"); 
	     
	
	}
	
	

}
