package scripts.DCRM;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import scripts.ProMS.ProMS_PageObject;
import scripts.ProMS.WT_LoginPage;
import scripts.ProMS.WT_ReservationPage;
//public class DCRMScenarios_Test extends DCRM_PageObject {
	
	public class DCRMScenarios_Test extends WT_LoginPage {
	//WT_LoginPage

	int inCt;
	String vFileName;
	String vApplication = "WebTours";
	ProMS_PageObject pro;
	WT_LoginPage wt; //not necessary

	@BeforeClass
	public void LaunchT2Q() throws InterruptedException {
		System.out.println("Start");
		createReportFolder();
	}

	@BeforeMethod()
	public void BeforeMethod(Method result) {
		vFileName = result.getAnnotation(Test.class).description();
		startTest(vFileName);
		openBrowser();
		//driver.get(getURL("varDCRMURL"));
		driver.get(getURL("varWT"));
		System.out.println("***Launch Application*****");
	}

	//@Test(description = "Create Opportunity", dataProvider = "userData")
	/*
	 * public void TC_CreateOpportunity(String Execute, String OppAccount, String
	 * ProposalType, String LineItemAmount, String BusinessUnits, String RAItems,
	 * String CustomerIndustry, String Capability, String SubOffering, String
	 * SAPOffering) throws InterruptedException {
	 * 
	 * String[] BusinessUnitArray = getInputsInAList(BusinessUnits); String[]
	 * SAPPfferingArray = getInputsInAList(SAPOffering); String[] RAItemsArray =
	 * getInputsInAList(RAItems);
	 * 
	 * QuickCreateAndAddLineItems(OppAccount, BusinessUnits, RAItemsArray,
	 * CustomerIndustry, ProposalType, LineItemAmount, BusinessUnitArray);
	 * addOffering(BusinessUnits, Capability, SubOffering, SAPPfferingArray,
	 * BusinessUnitArray); String PropNo = submitProposalRequest();
	 * addPassLog("Opportunity created : " + PropNo, "takeScreenshot"); }
	 */

	@AfterMethod
	public void afterMethod(ITestResult result) throws InterruptedException, IOException {
		
		System.out.println("Inside aftermethod");

		try {
				if (result.getStatus() == ITestResult.SUCCESS) {
					test.pass(MarkupHelper.createLabel("Test is Passed", ExtentColor.GREEN));
				}
				else if (result.getStatus() == ITestResult.FAILURE) {
					test.fail(MarkupHelper.createLabel("Test is Failed", ExtentColor.RED));
					test.error(result.getThrowable());
					test.addScreenCaptureFromPath(takeScreenshot(result.getTestName()));
					
				}
				else if (result.getStatus() == ITestResult.SKIP) {
					test.log(Status.SKIP, "Test Case Skipped");
				}
				
				else {	
					System.out.println("Kuch to gadbad hai Daya");
				}
				
				
				
				
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in "+ e.getMessage());
		}
		
		
		finally {
		//were commented originally 30/5/2020
		System.out.println("***Close Application****");
		endTest();
		closeDriver();
		}
		
	}
	
	
	@Test(description = "myCase", dataProvider = "userData")
public void myCase(LinkedHashMap<String,String> data) {		   
		enterUsername(data.get("username"));
		enterPassword(data.get("password"));
		WT_ReservationPage res=ClickSignin();
		res.verifyReservationPageVisible();			
	}
	
	
	
	
	
	
}
