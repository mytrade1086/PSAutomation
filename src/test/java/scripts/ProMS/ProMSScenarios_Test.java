package scripts.ProMS;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.common.io.Files;

import scripts.DCRM.DCRM_PageObject;

public class ProMSScenarios_Test extends ProMS_PageObject {

	int inCt;
	String vFileName;
	String vApplication = "T2Q";
	DCRM_PageObject dcrm;

	@BeforeClass
	public void LaunchT2Q() throws InterruptedException, IOException {
		System.out.println("Start");
		createReportFolder();
	}

	@BeforeMethod()
	public void BeforeMethod(Method result) throws IOException {
		vFileName = result.getAnnotation(Test.class).description();
		startTest(vFileName);
		openBrowser();
		System.out.println("***Launch Application*****");

	}

	// @Test(description = "Verify SAP Quote fields in ProMS", dataProvider =
	// "userData")
	public void TC_VerifySAPQuoteFieldsInProMS(String Execute, String QuoteId, String QuotedPrice,
			String MarginPercentage) throws InterruptedException, AWTException, IOException {

		goToSap();
		// createQuote();
		HashMap<String, String> sapFieldValues = searchQuoteInSap(QuoteId);
		goToProMS();
		selectAccount();
		searchProposal("QuoteId", QuoteId);
		verifySapFieldInProMS(sapFieldValues);
//		fillValidDate();
//		changeStatusToReview(QuoteId, QuotedPrice, MarginPercentage);
//		changeToProposed(QuoteId);

	}

	// @Test(description = "PCB Reverse Flow", dataProvider = "userData")
//	public void TC_PCBReverseFlow(String Execute, String Contact, String SoldToParty, String EndCustomer,
//			String ShipToParty, String QuoteDesc, String MainBU, String Industry, String UserStatus, String Probability,
//			String Type, String CloseDate, String DelDate, String ProductID, String Quantity)
//			throws InterruptedException, AWTException, IOException {
//
//		goToSap();
//		String QuoteId = createQuote(Contact, SoldToParty, EndCustomer, ShipToParty, QuoteDesc, MainBU, Industry,
//				UserStatus, Probability, Type, CloseDate, DelDate, ProductID, Quantity);
//		// HashMap<String, String> sapFieldValues = searchQuoteInSap(QuoteId);
//		goToProMS();
//		selectAccount();
//		searchProposal("QuoteId", QuoteId);
//		checkIfProposalStatusInDev();
////		 verifySapFieldInProMS(sapFieldValues);
////		fillValidDate();
////		changeStatusToReview(QuoteId, QuotedPrice, MarginPercentage);
////		changeToProposed(QuoteId);
//
//	}

	@Test(description = "Create Opportunity", dataProvider = "userData")
	public void TC_CreateOpportunity(String Execute, String OppName, String OppAccount, String EstCloseDate,
			String SpecPosition, String FundingResult, String OppIndustry, String DemandSource, String ChannelToMarket,
			String LineItemAmounts, String BusinessUnits, String RAItems, String ProposalType,
			String InitialProposalDate, String BudgetAmount, String TermsAndConditions) throws Exception {

		dcrm = new DCRM_PageObject();
		String[] BusinessUnitArray = getInputsInAList(BusinessUnits);
		String[] LineItemAmountArray = getInputsInAList(LineItemAmounts);
		// String[] SAPPfferingArray = getInputsInAList(SAPOffering);
		String[] RAItemsArray = getInputsInAList(RAItems);
		dcrm.QuickCreateAndAddLineItems(OppName, OppAccount, EstCloseDate, SpecPosition, FundingResult, OppIndustry,
				DemandSource, ChannelToMarket, BusinessUnits, RAItemsArray, LineItemAmountArray, BusinessUnitArray,
				ProposalType, InitialProposalDate, BudgetAmount, TermsAndConditions);
		// dcrm.addOffering(BusinessUnits, Capability, SubOffering, SAPPfferingArray,
		// BusinessUnitArray);
		dcrm.submitProposalRequest();

	}

	@Test(description = "Forward Flow - Budgetary Quote", dataProvider = "userData")
	public void TC_BudgetaryQuote(String Execute, String OppAccount, String ProposalType, String LineItemAmount,
			String BusinessUnits, String RAItems, String CustomerIndustry, String SAPOffering, String Capability,
			String SubOffering, String QuotedPrice, String MarginPercentage)
			throws InterruptedException, AWTException, IOException {

		dcrm = new DCRM_PageObject();
		String[] BusinessUnitArray = getInputsInAList(BusinessUnits);
		// String[] SAPPfferingArray = getInputsInAList(SAPOffering);
		String[] RAItemsArray = getInputsInAList(RAItems);
		dcrm.QuickCreateAndAddLineItems(OppAccount, BusinessUnits, RAItemsArray, CustomerIndustry, ProposalType,
				LineItemAmount, BusinessUnitArray);
		// dcrm.addOffering(BusinessUnits, Capability, SubOffering, SAPPfferingArray,
		// BusinessUnitArray);
		String PropNo = dcrm.submitProposalRequest();
		goToProMS();
		selectAccount();
		searchProposal("proposalId", PropNo);
		acceptProposalInProMS();
		goToHome();
		goToMyProposal();
		changeStatusToPlanning(PropNo, BusinessUnitArray);
		changeStatusToDevelopment(PropNo, BusinessUnitArray);
		fillValidDate();
		changeStatusToReview(PropNo, ProposalType, BusinessUnitArray, QuotedPrice, MarginPercentage);
		changeToProposedAfterReview(PropNo);
		dcrm.verifyProposalChangesInDCRM(PropNo, QuotedPrice);

	}

	@Test(description = "Forward Flow - Fixed Priced Quote", dataProvider = "userData")
	public void TC_FixedPricedQuote(String Execute, String OppAccount, String ProposalType, String LineItemAmount,
			String BusinessUnits, String RAItems, String CustomerIndustry, String SAPOffering, String Capability,
			String SubOffering, String QuotedPrice, String MarginPercentage)
			throws InterruptedException, AWTException, IOException, EmailException {

		// dcrm = new DCRM_PageObject();
		String[] BusinessUnitArray = getInputsInAList(BusinessUnits);
		String[] RAItemsArray = getInputsInAList(RAItems);
//		dcrm.QuickCreateAndAddLineItems(OppAccount, BusinessUnits, RAItemsArray, CustomerIndustry, ProposalType,
//				LineItemAmount, BusinessUnitArray);
		// String PropNo = dcrm.submitProposalRequest();
		String PropNo = "30086627.1";
		goToProMS();
		selectAccount();
		searchProposal("proposalId", PropNo);
		addBusinesssegmentIfSSB(BusinessUnitArray);
		acceptProposalInProMS();
		goToHome();
		goToMyProposal();
		searchProposalLeftMenu(PropNo);
		changeStatusToPlanning(PropNo, BusinessUnitArray);
		changeStatusToDevelopment(PropNo, BusinessUnitArray);
		fillValidDate();
		changeStatusToReview(PropNo, ProposalType, BusinessUnitArray, QuotedPrice, MarginPercentage);
		riskReview(BusinessUnitArray);
		assignApprovers();
		goToHome();
		goToPendingProposals();
		approvePendingApprovals();
		goToHome();
		waitAfterPendingApproval();
		goToMyProposal();
		searchProposalLeftMenu(PropNo);
		changeToProposed(PropNo);
		changeToPOReceived(PropNo, BusinessUnitArray);
		changeToWon(PropNo);
		dcrm.verifyProposalChangesInDCRM(PropNo, QuotedPrice);

	}

	@Test(description = "Forward Flow - Fixed Priced Quote - Lost", dataProvider = "userData")
	public void TC_FixedPricedQuoteLost(String Execute, String OppAccount, String ProposalType, String LineItemAmount,
			String BusinessUnits, String RAItems, String CustomerIndustry, String SAPOffering, String Capability,
			String SubOffering, String LineItemLostReason, String HeaderLostReason)
			throws InterruptedException, AWTException, IOException {

		dcrm = new DCRM_PageObject();
		String[] BusinessUnitArray = getInputsInAList(BusinessUnits);
		// String[] SAPPfferingArray = getInputsInAList(SAPOffering);
		String[] RAItemsArray = getInputsInAList(RAItems);
		dcrm.QuickCreateAndAddLineItems(OppAccount, BusinessUnits, RAItemsArray, CustomerIndustry, ProposalType,
				LineItemAmount, BusinessUnitArray);
		// dcrm.addOffering(BusinessUnits, Capability, SubOffering, SAPPfferingArray,
		// BusinessUnitArray);
		String PropNo = dcrm.submitProposalRequest();
		// String PropNo = "30085790.1";
		addPassLog("Opportunity created : " + PropNo, "takeScreenshot");
		goToProMS();
		selectAccount();
		searchProposal("proposalId", PropNo);
		addBusinesssegmentIfSSB(BusinessUnitArray);
		acceptProposalInProMS();
		goToHome();
		goToMyProposal();
		changeStatusToPlanning(PropNo, BusinessUnitArray);
		changeToLostBULineItem(LineItemLostReason);
		changetoLostHeaderStatus(HeaderLostReason);
//		changeStatusToDevelopment(PropNo, BusinessUnitArray);
//		fillValidDate();
//		dcrm.verifyProposalChangesInDCRM(PropNo, QuotedPrice);

	}

	@Test(description = "PM Routing", dataProvider = "userData")
	public void TC_PMRouting(String Execute, String OppAccount, String ProposalType, String LineItemAmount,
			String BusinessUnits, String RAItems, String CustomerIndustry, String SAPOffering, String Capability,
			String SubOffering, String ProposalManagers) throws InterruptedException, AWTException, IOException {

		dcrm = new DCRM_PageObject();
		String[] BusinessUnitArray = getInputsInAList(BusinessUnits);
		// String[] SAPPfferingArray = getInputsInAList(SAPOffering);
		String[] RAItemsArray = getInputsInAList(RAItems);
		String[] ProposalManagerArray = getInputsInAList(ProposalManagers);
		dcrm.QuickCreateAndAddLineItems(OppAccount, BusinessUnits, RAItemsArray, CustomerIndustry, ProposalType,
				LineItemAmount, BusinessUnitArray);
		// dcrm.addOffering(BusinessUnits, Capability, SubOffering, SAPPfferingArray,
		// BusinessUnitArray);
		String PropNo = dcrm.submitProposalRequest();
		goToProMS();
		selectAccount();
		searchProposal("proposalId", PropNo);
		verifyProposalOwner(ProposalManagerArray);

	}

	@Test(description = "Forward Flow - New Version", dataProvider = "userData")
	public void TC_ProposalNewVersion(String Execute, String OppAccount, String ProposalType, String LineItemAmount,
			String BusinessUnits, String RAItems, String CustomerIndustry, String SAPOffering, String Capability,
			String SubOffering, String QuotedPrice, String MarginPercentage, String NewVersionChangedQuotedPrice)
			throws InterruptedException, AWTException, IOException {

		dcrm = new DCRM_PageObject();
		String[] BusinessUnitArray = getInputsInAList(BusinessUnits);
		// String[] SAPPfferingArray = getInputsInAList(SAPOffering);
		String[] RAItemsArray = getInputsInAList(RAItems);
		dcrm.QuickCreateAndAddLineItems(OppAccount, BusinessUnits, RAItemsArray, CustomerIndustry, ProposalType,
				LineItemAmount, BusinessUnitArray);
		// dcrm.addOffering(BusinessUnits, Capability, SubOffering, SAPPfferingArray,
		// BusinessUnitArray);
		String PropNo = dcrm.submitProposalRequest();
		// String PropNo = "30085984.1";
		goToProMS();
		selectAccount();
		searchProposal("proposalId", PropNo);
		acceptProposalInProMS();
		goToHome();
		goToMyProposal();
		changeStatusToPlanning(PropNo, BusinessUnitArray);
		changeStatusToDevelopment(PropNo, BusinessUnitArray);
		fillValidDate();
		changeStatusToReview(PropNo, ProposalType, BusinessUnitArray, QuotedPrice, MarginPercentage);
		changeToProposed(PropNo);
		String NewVersionPropNo = createNewVersion();
		fillValidDate();
		changeStatusToReview(NewVersionPropNo, ProposalType, BusinessUnitArray, NewVersionChangedQuotedPrice,
				MarginPercentage);
		changeToProposed(NewVersionPropNo);
		goToHome();
		searchProposal("proposalId", PropNo);
		checkIfPreviousVersionIsSuperseded();
		dcrm.verifyProposalChangesInDCRM(NewVersionPropNo, NewVersionChangedQuotedPrice);

	}

	@Test(description = "DA Calculator for SSB", dataProvider = "userData")
	public void TC_DACalculatorSSB(String Execute, String OppAccount, String ProposalType, String LineItemAmount,
			String BusinessUnits, String RAItems, String CustomerIndustry, String SAPOffering, String Capability,
			String SubOffering, String ReportingPrices, String ReportingCost, String NegativeCashFlow,
			String DALevelsMargin, String DALevelsOverall, String DALevelOrder)
			throws InterruptedException, AWTException, IOException {

		dcrm = new DCRM_PageObject();
		String[] BusinessUnitArray = getInputsInAList(BusinessUnits);
		String[] SAPPfferingArray = getInputsInAList(SAPOffering);
		String[] RAItemsArray = getInputsInAList(RAItems);
		String[] ReportingPricesArray = getInputsInAList(ReportingPrices);
		String[] ReportingCostArray = getInputsInAList(ReportingCost);
		String[] NegativeCashFlowArray = getInputsInAList(NegativeCashFlow);
		String[] DALevelsArrayOverall = getInputsInAList(DALevelsOverall);
		String[] DALevelsArrayMargin = getInputsInAList(DALevelsMargin);
		String[] DALevelOrderArray = getInputsInAList(DALevelOrder);
//		dcrm.QuickCreateAndAddLineItems(OppAccount, BusinessUnits, RAItemsArray, CustomerIndustry, ProposalType,
//				LineItemAmount, BusinessUnitArray);
		// dcrm.addOffering(BusinessUnits, Capability, SubOffering, SAPPfferingArray,
		// BusinessUnitArray);
		// String PropNo = dcrm.submitProposalRequest();
		String PropNo = "30085835.1";
		addPassLog("Opportunity created : " + PropNo, "takeScreenshot");
		goToProMS();
		selectAccount();
		searchProposal("proposalId", PropNo);
//		addBusinesssegmentIfSSB(BusinessUnitArray);
//		acceptProposalInProMS();
//		goToHome();
//		goToMyProposal();
//		Map<String, Integer> DAOrder = readDAOrder(DALevelOrderArray);
//		CalculateOverallDA(DALevelOrderArray, DAOrder);

		CalculateDASSB(ReportingPricesArray, ReportingCostArray, NegativeCashFlowArray, DALevelOrderArray);

	}

	@Test(description = "DA Calculator for CSM", dataProvider = "userData")
	public void TC_DACalculatorCSM(String Execute, String OppAccount, String ProposalType, String LineItemAmount,
			String BusinessUnits, String RAItems, String CustomerIndustry, String SAPOffering, String Capability,
			String SubOffering, String ReportingPrices, String ReportingCost, String NegativeCashFlow,
			String HardwareMargin, String DALevelOrder) throws InterruptedException, AWTException, IOException {

		dcrm = new DCRM_PageObject();
		String[] BusinessUnitArray = getInputsInAList(BusinessUnits);
		// String[] SAPPfferingArray = getInputsInAList(SAPOffering);
		String[] RAItemsArray = getInputsInAList(RAItems);
		String[] ReportingPricesArray = getInputsInAList(ReportingPrices);
		String[] ReportingCostArray = getInputsInAList(ReportingCost);
		String[] NegativeCashFlowArray = getInputsInAList(NegativeCashFlow);
		String[] DALevelOrderArray = getInputsInAList(DALevelOrder);
		String[] HardwareMarginArray = getInputsInAList(HardwareMargin);
		dcrm.QuickCreateAndAddLineItems(OppAccount, BusinessUnits, RAItemsArray, CustomerIndustry, ProposalType,
				LineItemAmount, BusinessUnitArray);
		String PropNo = dcrm.submitProposalRequest();
		// String PropNo = "30086014.1";
		addPassLog("Opportunity created : " + PropNo, "takeScreenshot");
		goToProMS();
		selectAccount();
		searchProposal("proposalId", PropNo);
		addHardwareOfferingCSM(BusinessUnitArray);
		acceptProposalInProMS();
		goToHome();
		goToMyProposal();
		searchProposalLeftMenu(PropNo);
		changeToCustom();
		CalculateDACSM(ReportingPricesArray, ReportingCostArray, NegativeCashFlowArray, HardwareMarginArray,
				DALevelOrderArray);

	}

	@Test(description = "Reverse Flow", dataProvider = "userData")
	public void TC_ReverseFlow(String Execute, String Contact, String SoldToParty, String EndCustomer,
			String ShipToParty, String QuoteDesc, String MainBU, String Industry, String UserStatus, String Probability,
			String Type, String CloseDate, String DelDate, String ProductID, String Quantity, String PriceElement1,
			String Price1, String Currency1, String PriceElement2, String Price2, String Currency2,
			String NoItemPricing) throws InterruptedException, AWTException, IOException {

//		String[] ReportingPricesArray = getInputsInAList(ReportingPrices);
//		String[] ReportingCostArray = getInputsInAList(ReportingCost);
//		String[] DALevelOrderArray = getInputsInAList(DALevelOrder);
		String[] ProductIDArray = getInputsInAList(ProductID);
		String[] QuantityArray = getInputsInAList(Quantity);
		String[] PriceElement1Array = getInputsInAList(PriceElement1);
		String[] Price1Array = getInputsInAList(Price1);
		String[] Currency1Array = getInputsInAList(Currency1);
		String[] PriceElement2Array = getInputsInAList(PriceElement2);
		String[] Price2Array = getInputsInAList(Price2);
		String[] Currency2Array = getInputsInAList(Currency2);
		goToSap();
		createQuote(Contact, SoldToParty, EndCustomer, ShipToParty, QuoteDesc, MainBU, Industry, UserStatus,
				Probability, Type, CloseDate, DelDate, ProductIDArray, QuantityArray, PriceElement1Array, Price1Array,
				Currency1Array, PriceElement2Array, Price2Array, Currency2Array, NoItemPricing);

	}

	@AfterMethod
	public void afterMethod(ITestResult result)
			throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException {

		try {
			if (result.getStatus() == ITestResult.SUCCESS) {

				test.pass(MarkupHelper.createLabel("Test is Passed", ExtentColor.GREEN));
			}

			else if (result.getStatus() == ITestResult.FAILURE) {

				test.fail(MarkupHelper.createLabel("Test is Failed", ExtentColor.RED));
				test.error(result.getThrowable());
				System.out.println("Kol");
				test.addScreenCaptureFromPath(takeScreenshot("Failure"));

			}

			else if (result.getStatus() == ITestResult.SKIP) {

				test.log(Status.SKIP, "Test Case Skipped");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("***Close Application****");
		endTest();
		// closeDriver();

	}

}
