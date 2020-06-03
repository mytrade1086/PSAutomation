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
import Utilities.ElementUtil;
import Utilities.ExcelReader;
import WT_Pages.WT_LoginPage;
import WT_Pages.WT_ReservationPage;
import scripts.ProMS.ProMS_PageObject;

public class WT_RegressionTest extends Base {
	WT_LoginPage objLgn;
	WT_ReservationPage objReg;

	@BeforeMethod()
	public void BeforeMethod() {
		try {
			initilization();
			objLgn = new WT_LoginPage();
			System.out.println("***Launch Application*****");
		} catch (Exception e) {

			System.err.println("Problem while launching browser: " + e.getMessage());
		}

	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws InterruptedException, IOException {
		System.out.println("Inside aftermethod");
		ElementUtil.afterMethod(result);
	}

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "userData")
	public void myCase(LinkedHashMap<String, String> data) throws InterruptedException {
		objLgn.enterUsername(data.get("username"));
		objLgn.enterPassword(data.get("password"));
		objReg = objLgn.ClickSignin();

		if (objReg.verifyReservationPageVisible()) {
			el.addPassLog("Reservation_page is visible ", "takeScreenshot");
		} else {
			el.addFailLog("Reservation_page not visible");
		}
	}


	@Test(dataProviderClass = ExcelReader.class, dataProvider = "userData")
	public void WT_Register(LinkedHashMap<String, String> data) {
		System.out.println(data);
		objLgn.enterUsername(data.get("username"));
		objLgn.enterPassword(data.get("password"));
		// el.addPassLog("manually added", "takeScreenshot");
		el.addFailLog("manually added failure");

	}
}
