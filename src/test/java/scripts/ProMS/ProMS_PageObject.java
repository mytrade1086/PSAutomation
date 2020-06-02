package scripts.ProMS;

import org.apache.commons.lang3.Range;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExtentReportHtml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class ProMS_PageObject extends ExtentReportHtml {

	String quoteId;

	HashMap<String, String> hmap;
	HashMap<String, String> OldVersionValues;
	Actions actions;

	By eleSelectAccount = By.xpath("//span[contains(text(),'Rockwell and Sensia Employee')]");
	By eleSearchProposalIcon = By.xpath("//div[contains(text(),'Search Proposals')]");
	By btnSearch = By.xpath("//button/span/span[contains(text(),'Search')]");
	By txtboxSearch = By.xpath("//label[contains(text(),'Proposal ID')]/following::input");
	By txtboxQuoteID = By.xpath("//label[contains(text(),'Quote ID')]/following::input");
	By linktxtProposal = By.xpath("//a[contains(text(),'30010305.1')]");
	By eleProposalTeam = By.xpath("//div[contains(text(),'ProposalTeam')]");
	By eleProposalRequests = By.xpath("//div[contains(text(),'Proposal Requests')]");
	By eleMyProposals = By.xpath("//div[contains(text(),'My Proposals')]");
	By elePendingApprovals = By.xpath("(//div[contains(text(),'My Pending Approvals')])[2]");
	By eleProposalMgmtSolution = By
			.xpath("//div[contains(text(),'Prod Support')]/preceding::div[contains(text(),'Proposal Mgmt Solution')]");
	By eleProposalMgmtSolutionHome = By.xpath(
			"(//div[contains(text(),'Prod Support')]/preceding::div[contains(text(),'Proposal Mgmt Solution')])[2]");
	By eleOppInfo = By.xpath("//div[contains(text(),'Opp Info')]");
	By eleProposalInfo = By.xpath("//div[contains(text(),'Proposal Info')]");
	By eleBusinessUnit = By.xpath("//div[contains(text(),'Business Unit')]");
	By eleApprovals = By.xpath("(//div[contains(text(),'Approvals')])[2]");
	By eleOME = By.xpath("//div[contains(text(),'OME')]");
	By chkboxNoneOfThese = By.xpath(
			"(//label[contains(text(),'None of these High Risks listed, including Export Embargo and Onshore Oil & Gas under Trade Compliance Risks apply')]/following::div)[6]");
	By eleEdit = By.xpath("(//button/span/span[contains(text(),'Edit')])[2]");
	By eleInActive = By.xpath("//h1[contains(text(),'Business Unit – In-Active Lines')]");
	By eleInActiveExpand = By
			.xpath("//span[@class='sapMPanelExpandableIcon sapUiIcon sapUiIconMirrorInRTL sapUiIconPointer']");
	By eleInactiveTabBUStatus = By.xpath("(//span[contains(text(),'Lost Not to be Pursued by RA')])[1]");
	By eleInactiveTabBUStatus2 = By.xpath("(//span[contains(text(),'Lost Not to be Pursued by RA')])[2]");

	By eleInactiveTabReason2 = By.xpath("(//span[contains(text(),'Not to be pursued by RA')])[3]");
	By inputSearchProposals = By.xpath("//input[@placeholder='Search Proposals']");
	By eleRiskReviewEdit = By.xpath("(//button/span/span[contains(text(),'Edit')])[1]");
	By tabLineItem = By.xpath("(//tr)[2]");
	By tabLineItem2 = By.xpath("(//tr)[3]");
	By eleCancelLineItem = By.xpath("//span[@title='Cancel Item']");
	By eleCancelYes = By.xpath("//span[contains(text(),'Yes')]");
	By eleDropdownBULineStatus = By.xpath("(//label[contains(text(),'BU Line Status')]/following::span)[1]");
	By eleDropdownBULineStatusProposed = By.xpath("(//label[contains(text(),'In Review')]/following::span)[1]");
	By eleDropdownBULineStatusProposedAfterApproval = By
			.xpath("((//label[contains(text(),'Approved')])[2]/following::span)[1]");
	By eleDropdownBULineStatusPOReceived = By.xpath("(//label[contains(text(),'Proposed')]/following::span)[1]");
	By eleDropdownHeaderStatusPlanning = By.xpath("(//label[contains(text(),'Request Accepted')]/following::span)[1]");
	By eleDropdownHeaderStatusDevelopment = By.xpath("(//label[contains(text(),'In Planning')]/following::span)[1]");

	By eleDropdownHeaderNotToBePursued = By.xpath("//label[contains(text(),'Not to be Pursued by RA')]");

	By eleInPanningStatus = By.xpath("(//label[contains(text(),'In Planning')])[1]");
	By eleInDevelopmentStatus = By.xpath("//label[contains(text(),'In Development')]");
	By eleInReviewStatus = By.xpath("//label[contains(text(),'In Review')]");
	By eleApprovedStatus = By.xpath("(//label[contains(text(),'Approved')])[2]");
	By elePOReceivedStatus = By.xpath("//label[contains(text(),'PO Received')]");
	By eleProposedStatus = By.xpath("(//label[contains(text(),'Proposed')])[2]");

	// label[contains(text(),'Proposed')]

	By eleDropdownHeaderStatusReview = By.xpath("(//label[contains(text(),'In Development')])[2]");
	By eleDropdownPricingSystem = By.xpath("//label[contains(text(),'Pricing/Configurator System')]/following::span");
	By eleRALaborMargin = By.xpath("//label[contains(text(),'RA Labor Margin (%)')]/following::input");
	By eleThirdPartyMargin = By.xpath("//label[contains(text(),'3rd Party Margin (%)')]/following::input");
	By eleRALaborTCV = By.xpath("//label[contains(text(),'RA Labor % of TCV')]/following::input");
	By eleRAProductTCV = By.xpath("//label[contains(text(),'RA Product % of TCV')]/following::input");
	By eleDACalculator = By.xpath("//span[@title='DA Calculator']");
	By btnCalculateDA = By.xpath("//button/span/span[contains(text(),'Calculate DA')]");
	By inputReportingPrice = By.xpath("(//span[contains(text(),'Reporting Price')]/following::td//input)[1]");
	By inputReportingCost = By.xpath("(//span[contains(text(),'Reporting Cost')]/following::td//input)[1]");
	By chkboxMilestonePayment = By.xpath("(//span[contains(text(),'Milestone Payment')]/following::td//div/div)[1]");
	By inputNegativeCashFlow = By.xpath("(//span[contains(text(),'Negative Cash Flow')]/following::input)[1]");

	By labelDAlevelNegativeCashFlow = By
			.xpath("(//span[contains(text(),'Negative Cash Flow')]/following::td//span)[1]");
	By inputDistributorRisk = By.xpath("(//span[contains(text(),'Distributor Risk')]/following::div/div)[1]");
	By labelDALevelDistributorRisk = By.xpath("(//span[contains(text(),'Distributor Risk')]/following::td//span)[1]");
	By inputMSA = By.xpath("(//span[contains(text(),'Modernization Service Agreement')]/following::div/div)[1]");
	By labelDALevelMSA = By
			.xpath("(//span[contains(text(),'Modernization Service Agreement')]/following::td//span)[1]");
	By inputPartnerFee = By.xpath("(//span[contains(text(),'Partnering Fee Risk')]/following::div/div)[1]");
	By labelDALevelPartnerFee = By.xpath("(//span[contains(text(),'Partnering Fee Risk')]/following::td//span)[1]");
	By inputStateOwnedEntity = By.xpath("(//span[contains(text(),'State owned Entity')]/following::div/div)[1]");
	By labelDALevelStateOwnedEntity = By
			.xpath("(//span[contains(text(),'State owned Entity')]/following::td//span)[1]");
	By inputThirdParty = By.xpath("(//span[contains(text(),'Large Third Party')]/following::div/div)[1]");
	By labelDALevelThirdParty = By.xpath("(//span[contains(text(),'Large Third Party')]/following::td//span)[1]");
	By inputLaborLending = By.xpath("(//span[contains(text(),'Labor Lending')]/following::div/div)[1]");
	By labelDALevelLaborLending = By.xpath("(//span[contains(text(),'Labor Lending')]/following::td//span)[1]");
	By inputHighRisk = By.xpath("(//span[contains(text(),'High Risk')]/following::div/div)[1]");
	By labelDALevelHighRisk = By.xpath("(//span[contains(text(),'High Risk')]/following::td//span)[1]");
	By inputTechnicalRisk = By.xpath("(//span[contains(text(),'High Technical Risk')]/following::div/div)[1]");
	By labelDALevelTechnicalRisk = By.xpath("(//span[contains(text(),'High Technical Risk')]/following::td//span)[1]");
	By dropDownTermsNConditions = By.xpath("(//span[contains(text(),'Terms and Condition')]/following::label)[1]");
	By eleSelectExistingCustomerAgreement = By.xpath("(//li[contains(text(),'Existing Customer Agreement')])[2]");
	By eleSelectStandard = By.xpath("(//li[contains(text(),'Standard')])[6]");
	By labelDALevelTermsNConditions = By
			.xpath("(//span[contains(text(),'Terms and Condition')]/following::td//span)[2]");
	By eleClose = By.xpath("//span[contains(text(),'Close')]");
	By labelDALevelReportingPrice = By.xpath("(//span[contains(text(),'Reporting Price')]/following::td//span)[5]");
	By labelDALevelOverall = By.xpath("(//span[contains(text(),'Overall DA Level')]/following::span)[1]");
	By inputRALaborMargin = By.xpath("((//span[contains(text(),'RA Labor Margin (%)')])[2]/following::input)[1]");
	By labelDALevelRALaborMargin = By.xpath("((//span[contains(text(),'RA Labor Margin (%)')])[1]/following::span)[1]");
	By inputThirdPartyMargin = By.xpath("((//span[contains(text(),'3rd Party Margin (%)')])[2]/following::input)[1]");
	By labelDALevelThirdPartyMargin = By
			.xpath("((//span[contains(text(),'3rd Party Margin (%)')])[2]/following::span)[1]");
	By inputRALaborPercentage = By.xpath("((//span[contains(text(),'RA Labor % of TCV')])[2]/following::input)[1]");
	By labelDALevelRALaborPercentage = By
			.xpath("((//span[contains(text(),'RA Labor % of TCV')])[2]/following::span)[1]");

	By eleProdSupport = By.xpath("(//div[contains(text(),'Prod Support')])[3]");
	By eleInPlanning = By.xpath("(//li[contains(text(),'In Planning')])[1]");
	By eleHeaderInPlanning = By.xpath("(//li[contains(text(),'In Planning')])[2]");
	By eleGlobalHeadquarters = By.xpath("(//li[contains(text(),'Global Headquarters')])[1]");
	By eleInDevelopment = By.xpath("(//li[contains(text(),'In Development')])[1]");
	By eleHeaderInDevelopment = By.xpath("(//li[contains(text(),'In Development')])[2]");
	By eleLost = By.xpath("(//li[contains(text(),'Lost Not to be Pursued by RA')])[1]");
	By eleInPOReceived = By.xpath("(//li[contains(text(),'PO Received')])[2]");
	By eleInReview = By.xpath("(//li[contains(text(),'In Review')])[1]");
	By eleHeaderInReview = By.xpath("(//li[contains(text(),'In Review')])[2]");
	By eleHeaderPOReceived = By.xpath("(//li[contains(text(),'PO Received')])[1]");
	By eleHeaderLost = By.xpath("(//li[contains(text(),'Not to be Pursued by RA')])[2]");
	By eleHeaderLostReason = By.xpath("(//li[contains(text(),'Not to be pursued by RA')])[1]");
	By btnAddBUItem = By.xpath("//button/span/span[contains(text(),'Add BU/items')]");
	By dropdownBusiness = By.xpath("((//label[contains(text(),'Business')])[2]/following::label)[1]");
	By eleSSB = By.xpath("(//li[contains(text(),'SSB')])[1]");
	By dropdownSubBU = By.xpath("((//label[contains(text(),'Sub BU')])[1]/following::label)[1]");
	By eleSubBU = By.xpath("(//li[contains(text(),'System & Solutions Business')])[1]");
	By eleSubBUHardware = By.xpath("(//li[contains(text(),'Field Service Eng')])[1]");
	By dropdownOffering = By.xpath("//label[contains(text(),'Offering')]/following::label");
	By dropdownSubOffering = By.xpath("//label[contains(text(),'Sub Offering')]/following::label");
	By eleOfferingCSM = By.xpath("(//li[contains(text(),'Assurance')])[1]");
	By eleOfferingCSMHardware = By.xpath("(//li[contains(text(),'Conversion Projects')])[1]");
	By eleSubOfferingCSM = By.xpath("(//li[contains(text(),'Assurance PLS')])[1]");
	By eleOfferingSSB = By.xpath("(//li[contains(text(),'ISB - Information Solutions')])[1]");
	By dropdownCapability = By.xpath("(//label[contains(text(),'Capability')]/following::label)[1]");
	By eleCapability = By.xpath("(//li[contains(text(),'Information Solutions')])[2]");
	By dropdownSuboff = By.xpath("(//label[contains(text(),'Sub Offering')]/following::label)[1]");
	By eleSubOff = By.xpath("(//li[contains(text(),'Line Performance (NFAA/M)')])[1]");
	By eleSubOffHardware = By.xpath("(//li[contains(text(),'Conversion Projects')])[2]");
	By dropdownBusinessType = By.xpath("((//label[contains(text(),'Business Type')])[1]/following::label)[1]");
	By eleBusinessType = By.xpath("(//li)[3]");
	By dropdownBusinessSegment = By.xpath("((//label[contains(text(),'Business Segment')])[1]/following::label)[1]");
	By eleBusinessSegment = By.xpath("(//li[contains(text(),'Global Headquarters')])[1]");
	By eleHeaderStatus = By.xpath("//span[@id='__xmlview31--idMyPropSelHdrStatus-arrow']");
	By eleProposed = By.xpath("(//li[contains(text(),'Proposed')])[1]");
	By eleCustomSystem = By.xpath("(//li[contains(text(),'Custom System')])[1]");
	By ele2500IEC = By.xpath("//li[contains(text(),'2500 IEC LV MCC')]");
	By eleAcceptRequest = By.xpath("//button/span/span[contains(text(),'Accept Request')]");
	By txtAreaProposalAcceptNote = By.xpath("//textarea[@id='__area1-inner']");
	By txtFieldFirstName = By.xpath("(//input)[3]");
	By btnSearchAcceptRequest = By.xpath("//button/span/span[contains(text(),'Search')]");
	By btnUpdate = By.xpath("//button/span/span[contains(text(),'Update')]");
	By btnSave = By.xpath("//button/span/span[contains(text(),'Save')]");
	By btnApprove = By.xpath("//button/span/span[contains(text(),'Approve')]");
	By btnNewVersion = By.xpath("//button/span/span[contains(text(),'New Version')]");
	By chkboxMinorVersion = By
			.xpath("(//label[contains(text(),'Is there Minor Version')]/following::div//div//div)[1]");
	By inputNoteNewVersion = By.xpath("//label[contains(text(),'Note for new version')]/following::textarea");
	By btnNewVersionContinue = By.xpath("//button/span/span[contains(text(),'Continue to Create New Version')]");
	By inputNewVersionDate = By.xpath("((//label[contains(text(),'Confirmed Due Date')])[2]/following::input)[1]");
	By AcceptDate = By.xpath("((//label[contains(text(),'Confirmed Due Date')])[1]/following::input)[1]");
	By inputCloseDateLost = By.xpath("//input[@placeholder='DD-MMM-YYYY']");
	By labelSelectReason = By.xpath("//label[contains(text(),'Select Reason')]/following::span");
	By inputRadioNewVersion = By.xpath("(//div[@class='sapMRbBInn'])[5]");
	By eleFirstSearchedRow = By.xpath("(//tr)[2]");
	By btnAccept = By.xpath("//button/span/span[contains(text(),'Accept')]");
	By eleBackFromProposalRequest = By.xpath("//span[@id='__xmlview4--idPgPropReqMaster-navButton-iconBtn']");
	By eleBack = By.xpath("//a[@id='backBtn']");
	By inputQuotedPrice = By.xpath("(//label[contains(text(),'Quoted Price')]/following::input)[2]");
	By inputCostPrice = By.xpath("(//label[contains(text(),'Quoted Cost')]/following::input)[2]");
	By btnCalcPrice = By.xpath("//span[contains(text(),'Calculate')]");
	By linkOpenTask = By
			.xpath("//span[@id='__status19-__xmlview31--idVwMyPropApproval--idMyPropApproReqRevTable-0-text']");
	By dropDownTaskStatus = By.xpath("//label[contains(text(),'In Process')]");
	By eleTaskStatus = By.xpath("(//li[contains(text(),'Completed')])[1]");
	By eleBusinessAppAssign = By.xpath("//label[contains(text(),'Business C1')]/following::span");
	By eleFinanceAppAssign = By.xpath("//span[@id='__xmlview31--idVwMyPropApproval--idInpFinanceAppr-vhi']");
	By eleCNAppAssign = By.xpath(
			"//span[@id='__xmlview31--idVwMyPropApproval--idInpAddCandNApr-__xmlview31--idVwMyPropApproval--idListDAEpandAddCandNAppr-0-vhi']");
	By inputSearchApprover = By.xpath("//input[@placeholder='Search Approver']");
	By btnStartApproval = By.xpath("//button/span/span[contains(text(),'Start Approval')]");
	By eleHome = By.xpath("//a[@id='homeBtn']//span");
	By eleInfo = By.xpath("//span[@title='Information']");
	By eleInfoDate = By.xpath("(//label[contains(text(),'Fixed Date')]/following::input)[1]");
	By eleInitiate = By.xpath("//a[contains(text(),'Initiate')]");
	By eleNo = By.xpath("//label[contains(text(),'No')]");
	By inputOrderNo = By.xpath("(//label[contains(text(),'Order No')]/following::input)[1]");
	By inputProjNo = By.xpath("(//label[contains(text(),'Project Nunber')]/following::input)[1]");
	By inputUsernameTxtfield = By.xpath("//input[@name='loginfmt']");
	By linkPS = By.xpath("//a[contains(text(),'PS Portal - Login Prompt')]");
	By inputRockwellUsername = By.xpath("//input[@id='userNameInput']");
	By inputRockwellPassword = By.xpath("//input[@id='passwordInput']");
	By btnSubmitLogin = By.xpath("//span[@id='submitButton']");
	By linkCustRenMgmt = By.xpath("//a[contains(text(),'Customer Relationship Mgmt')]");
	By linkETOQuotes = By.xpath("//a[contains(text(),'ETO Quotes')]");
	By eleOrderManagement = By.xpath("//a[contains(text(),'Order Management')]");
	By linkQuotation = By.xpath("(//a[contains(text(),'Quotation')])[3]");
	By linkQuotations = By.xpath("(//a[contains(text(),'Quotation')])[1]");
	By inputContact = By.xpath("//input[@id='C34_W108_V111_V112_btpartnerset_contact_name']");
	By inputSoldToParty = By.xpath("//input[@id='C34_W108_V111_V112_btpartnerset_soldto_name']");
	By inputEndCustomer = By.xpath("//input[@id='C34_W108_V111_V112_btpartnerset_end_customer_id']");
	By inputShipTo = By.xpath("//input[@id='C34_W108_V111_V112_btpartnerset_shipto_name']");
	By inputQuoteDesc = By.xpath("//input[@id='C34_W108_V111_V112_btadminh_struct.description']");
	By next = By.xpath("//input[@type='submit']");
	By eleOrganizationalUnit = By.xpath("//span[@title='US/01/00/Z02']");
	By btnAssignApprovers = By.xpath("//button/span/span[contains(text(),'Assign Approvers')]");
	By btnStartApprovals = By.xpath("//button/span/span[contains(text(),'Start Approval')]");
	By btnPendingApprove = By.xpath("//button/span/span[contains(text(),'Approve')]");
	By btnPendingApproveNote = By.xpath("//textarea[@id='confirmDialogTextarea-inner']");
	By btnSubmit = By.xpath("//button/span/span[contains(text(),'Submit')]");
	By labelSuccess = By.xpath("//span[contains(text(),'Success')]");
	By NewVersionProposalMsg = By.xpath("//span[contains(text(),'New Proposal')]");
	By eleOK = By.xpath("//span[contains(text(),'OK')]");
	By eleYes = By.xpath("//span[contains(text(),'Yes')]");
	By labelSuperseded = By.xpath("(//label[contains(text(),'Superseded')])[2]");
	By labelProposalType = By.xpath("(//label[contains(text(),'Proposal Type')]/following::label)[1]");
	By labelLeadBU = By.xpath("(//label[contains(text(),'Lead BU')]/following::input)[1]");
	By labelOppName = By.xpath("(//label[contains(text(),'Opportunity Name')]/following::input)[1]");
	By labelCloseDate = By.xpath("((//label[contains(text(),'Est. Close Date')])[2]/following::input)[1]");
	By dropDownBusinessSegment = By.xpath("((//label[contains(text(),'Business Segment')])[1]/following::label)[1]");
	By dropDownOffering = By.xpath("((//label[contains(text(),'Offering')])[1]/following::label)[1]");
	By labelLedByValue = By.xpath("(//label[contains(text(),'Led By')]/following::label)[1]");
	By labelProbabilityValue = By.xpath("(//label[contains(text(),'Probability (%)')]/following::label)[1]");
	By labelOppIndustryValue = By.xpath("(//label[contains(text(),'Opportunity Industry')]/following::label)[1]");
	By labelSoldToValue = By.xpath("(//label[contains(text(),'Business Partner')])[2]/following::label");
	By labelEndCustomerValue = By.xpath("(//label[contains(text(),'Business Partner')])[3]/following::label");
	By labelShipToValue = By.xpath("(//label[contains(text(),'Business Partner')])[4]/following::label");
	By dropdownBusinessApproval = By.xpath("//span[@id='__xmlview3--idVwMyPropApproval--idInpBusiAppr-vhi']");
	By dropdownFinanceApproval = By.xpath("//span[@id='__xmlview3--idVwMyPropApproval--idInpFinanceAppr-vhi']");
	By dropdownCNApproval = By.xpath(
			"//span[@id='__xmlview3--idVwMyPropApproval--idInpAddCandNApr-__xmlview3--idVwMyPropApproval--idListDAEpandAddCandNAppr-0-vhi']");
	By dropDownMainBU = By
			.xpath("//a[@id='C34_W108_V111_V112_btcustomerh_struct.zzafld00005n-btn']//img[@class='th-ip-img']");
	By dropDownIndustry = By
			.xpath("//a[@id='C34_W108_V111_V112_btcustomerh_ext.zzcustsic-btn']//img[@class='th-ip-img']");
	By dropDownProbability = By
			.xpath("//a[@id='C34_W108_V111_V112_btcustomerh_ext.zzafld00005m-btn']//img[@class='th-ip-img']");
	By dropdownUserStatus = By.xpath("//input[@id='C34_W108_V111_V112_btadminh_lcstatus']");
	By inputCloseDate = By.xpath("//input[@id='C34_W108_V111_V112_zawarddate_btactdate']");
	By inputDelDate = By.xpath("//input[@id='C34_W108_V111_V112_btsalesset_struct.req_dlv_date']");
	By eleInsert = By.xpath("//b[contains(text(),'Insert')]");
	By inputProductID = By.xpath("//input[@id='C38_W121_V122_V124_btadmini_table[1].ordered_prod']");
	By inputQuantity = By.xpath("//input[@id='C38_W121_V122_V124_btadmini_table[1].quantity']");
	By eleEmployeeName = By.xpath("//span[contains(text(),'HR20003157')]");
	By inputType = By.xpath("//input[@id='C34_W108_V111_V112_btcustomerh_ext.zzafld00005j']");
	By eleOffering = By.xpath("//span[@id='__xmlview22--idMyPropSelOffering-arrow']");
	By eleProposalHeaderStatus = By.xpath("//span[@id='__xmlview3--idMyPropSelHdrStatus-arrow']");
	By eleOpenApproval = By.xpath("(//span[contains(text(),'Open')])[2]");
	By eleContactNameValue = By.xpath("//a[@id='C34_W108_V111_V112_btpartnerset_contact_name']");
	By eleSoldToValue = By.xpath("//a[@id='C34_W108_V111_V112_btpartnerset_soldto_name']");
	By eleEndCustomerIdValue = By.xpath("//a[@id='C34_W108_V111_V112_btpartnerset_end_customer_id']");
	By eleShipToValue = By.xpath("//a[@id='C34_W108_V111_V112_btpartnerset_shipto_name']");
	By eleDescValue = By.xpath("//span[@id='C34_W108_V111_V112_btadminh_struct.description']");
	By eleMainBUValue = By.xpath("//span[@id='C34_W108_V111_V112_btcustomerh_struct.zzafld00005n']");
	By eleIndustryValue = By.xpath("//span[@id='C34_W108_V111_V112_btcustomerh_ext.zzcustsic']");
	By eleLedByValue = By.xpath("//span[@id='C34_W108_V111_V112_btcustomerh_ext.zzafld0000j9']");
	By eleUserStatusValue = By.xpath("//span[@id='C34_W108_V111_V112_btadminh_lcstatus']");
	By eleQuoteNumberValue = By.xpath("//span[@id='C34_W108_V111_V112_btadminh_struct.object_id']");
	By eleCloseDateValue = By.xpath("//span[@id='C34_W108_V111_V112_zawarddate_btactdate']");
	By eleReqDateValue = By.xpath("//span[@id='C34_W108_V111_V112_btsalesset_struct.req_dlv_date']");
	By eleProbValue = By.xpath("//span[@id='C34_W108_V111_V112_btcustomerh_ext.zzafld00005m']");
	By eleProMSIDValue = By.xpath("//span[@id='C34_W108_V111_V112_btcustomerh_struct.zzcustomer_h2702']");
	By eleUserStatusReview = By.xpath("(//a[contains(text(),'Review')])[2]");
	By eleUserStatusDAReview = By.xpath("//a[contains(text(),'Review') and contains(text(),'DA')]");
	By eleUserStatusApproved = By.xpath("(//a[contains(text(),'Approved')])[2]");
	By eleUserStatusOverallApproved = By.xpath("(//a[contains(text(),'Approved')])[1]");
	By eleEditRAQuote = By.xpath("(//b[contains(text(),'Edit')])[1]");

	By elePriceDetails = By.xpath("//b[contains(text(),'Price Details')]");

	By eleRADefinedFields = By.xpath("//b[contains(text(),'RA-Defined Fields')]");

	By eleLineItemEditBack = By.xpath("//img[@alt='Back']");

	By checkBoxNoTemPricing = By.xpath("((//label[@title='No Item Pricing:'])[2]/following::td/div/div/a/img)[1]");

	By eleEditQuestionaire = By.xpath("(//img[@alt='Edit'])[9]");

	By eleShipDate = By.xpath("(//td[@class='th-ip-td1']/input)[2]");

	By eleQuesSaveNBack = By.xpath("//b[contains(text(),'Save and Back')]");

	By eleEditListItems = By.xpath("(//b[contains(text(),'Edit List')])[1]");

	By btnSaveSAP = By.xpath("//a[@id='C34_W108_V111_thtmlb_button_1']");

	By eleSAPSuccess = By.xpath("//img[@id='th-mes-success']");

	By txtboxQuoteIdSap = By
			.xpath("(//span[@class='th-if-wrapper']/input[@title='Choose the value of criterion Quotation ID'])[1]");
	By btnSearchSAP = By.xpath("//a[@id='C32_W100_V101_Searchbtn']");
	By linkFoundQuote = By.xpath("//a[@id='C32_W100_V101_V103_btqrsrvquot_table[1].object_id']");

	By eleItemStatusSelect = By.xpath("//span[contains(text(),'Open')]");
	By eleItemStatus = By.xpath("(//li/a[contains(text(),'Approved')])[2]");

	public void selectAccount() {
		find(eleSelectAccount).click();
	}

	public void searchProposal(String type, String id) {

		waitForElementPresent(eleProposalMgmtSolution);
		find(eleProposalMgmtSolution).click();
		waitForElementPresent(eleSearchProposalIcon);
		find(eleSearchProposalIcon).click();
		sleep(3000);
		waitForElementPresent(txtboxSearch);
		if (type.equals("proposalId")) {
			find(txtboxSearch).sendKeys(id);
		} else {
			find(txtboxQuoteID).sendKeys(id);
		}

		waitForElementPresent(btnSearch);
		find(btnSearch);
		find(btnSearch).click();
		WebElement proposal = driver.findElement(By.xpath("(//a)[5]"));

		if (proposal.isDisplayed()) {
			proposal.click();
			addPassLog("Proposal is found in ProMS", "takeScreenshot");
		} else {
			addFailLog("Proposal is not found in ProMS");
		}

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

	}

	public void addHardwareOfferingCSM(String[] BusinessUnitArray) {

		find(eleBusinessUnit).click();
		for (int i = 0; i < BusinessUnitArray.length; i++) {
			if (BusinessUnitArray[i].equals("CSM Plant Services")) {
				WebElement tabLineItem = driver.findElement(By.xpath("(//tr)[" + (i + 2) + "]"));
				tabLineItem.click();
				sleep(8000);
				find(eleRiskReviewEdit).click();
				sleep(2000);
				find(dropdownSubBU).click();
				sleep(1000);
				find(eleSubBUHardware).click();
				sleep(1000);
				find(dropdownOffering).click();
				find(eleOfferingCSMHardware).click();
				sleep(1000);
				find(dropdownSuboff).click();
				find(eleSubOffHardware).click();
				sleep(1000);
				find(btnUpdate).click();
				sleep(3000);
			}
		}

	}

	public void addBusinesssegmentIfSSB(String[] BusinessUnitArray) {

		waitForElementPresent(eleBusinessUnit);
		sleep(15000);
		find(eleBusinessUnit).click();
		for (int i = 0; i < BusinessUnitArray.length; i++) {
			if (BusinessUnitArray[i].equals("System & Solutions Business")) {
				WebElement tabLineItem = driver.findElement(By.xpath("(//tr)[" + (i + 2) + "]"));
				tabLineItem.click();
				waitForElementPresent(eleRiskReviewEdit);
				find(eleRiskReviewEdit).click();
				sleep(2000);
				find(dropdownBusinessType).click();
				find(eleBusinessType).click();
				sleep(1000);
//				find(dropdownOffering).click();
//				find(eleOfferingSSB).click();
//				sleep(1000);
//				find(dropdownCapability).click();
//				find(eleCapability).click();
//				sleep(1000);
//				find(dropdownSuboff).click();
//				find(eleSubOff).click();
//				sleep(1000);
//				find(dropdownBusinessType).click();
//				find(eleBusinessType).click();
//				sleep(1000);
				find(dropDownBusinessSegment).click();
				find(eleGlobalHeadquarters).click();
				sleep(1000);
				find(btnUpdate).click();
				sleep(3000);

			}

		}

	}

	public void calculateMarginDA(String type, String reportingPricesArray, String reportingCostArray) {
		String ReportingPriceDALevel = null;
		String MarginDALevel;
		sleep(3000);
		find(inputReportingPrice).click();
		sleep(2000);
		find(inputReportingPrice).clear();
		sleep(2000);
		find(eleClose).click();
		find(inputReportingPrice).sendKeys(reportingPricesArray);
		sleep(3000);
		find(btnCalculateDA).click();
		sleep(2000);
		if (type.equalsIgnoreCase("PCB"))
			ReportingPriceDALevel = calculateDAPCB(reportingPricesArray, "0");
		else if (type.equalsIgnoreCase("CSM"))
			ReportingPriceDALevel = calculateDACSMReportingMargin(reportingPricesArray, "0");
		else
			System.out.println("Enter Proper BU");
		System.out.println("Expected DA Level for Reporting Price: " + ReportingPriceDALevel
				+ "Actual DA Level for reporting Price: " + find(labelDALevelReportingPrice).getText());
		find(labelDALevelReportingPrice).getText().equals(ReportingPriceDALevel);
		sleep(2000);
		find(inputReportingCost).click();
		sleep(2000);
		find(inputReportingCost).clear();
		sleep(2000);
		find(eleClose).click();
		System.out.println(reportingCostArray);
		find(inputReportingCost).sendKeys(reportingCostArray);
		sleep(2000);
		find(btnCalculateDA).click();
		sleep(4000);
		MarginDALevel = calculateDACSMReportingMargin(reportingPricesArray, reportingCostArray);
		System.out.println("Expected DA Level for Reporting Margin: " + MarginDALevel
				+ "Actual DA Level for Reporting Margin: " + find(labelDALevelReportingPrice).getText());
		find(labelDALevelReportingPrice).getText().equals(MarginDALevel);

	}

	public void calculateHardwareDALevel(String hardwareMarginArray, List<String> DALevelsArrayGenerated,
			Map<String, Integer> DAOrderMap) {

		find(inputRALaborMargin).click();
		sleep(2000);
		find(inputRALaborMargin).clear();
		sleep(2000);
		find(eleClose).click();
		find(inputRALaborMargin).sendKeys(hardwareMarginArray);
		sleep(2000);
		find(btnCalculateDA).click();
		sleep(5000);
		String RALaborTCVDALevel = calculateDARALaborMarginAndThirdPartyMargin(hardwareMarginArray);
		System.out.println("Expected RA Labor Margin: " + RALaborTCVDALevel + "Actual RA Labor Margin"
				+ find(labelDALevelRALaborMargin).getText());
		find(labelDALevelRALaborMargin).getText().equals(RALaborTCVDALevel);
		find(labelDALevelOverall).click();

		DALevelsArrayGenerated.add(find(labelDALevelRALaborMargin).getText());
		System.out.println("RA Labor Margin Level: " + find(labelDALevelMSA).getText());
		String OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
		checkIfOverallDACorrect(OverallDA);
		sleep(2000);
		DALevelsArrayGenerated.remove(DALevelsArrayGenerated.size() - 1);
		for (String levels : DALevelsArrayGenerated) {
			System.out.println("DA Levels till now: " + levels);
		}

		find(inputThirdPartyMargin).click();
		sleep(2000);
		find(inputThirdPartyMargin).clear();
		sleep(2000);
		find(eleClose).click();
		find(inputThirdPartyMargin).sendKeys(hardwareMarginArray);
		sleep(2000);
		find(btnCalculateDA).click();
		sleep(5000);
		String ThridPartyDALevel = calculateDARALaborMarginAndThirdPartyMargin(hardwareMarginArray);
		System.out.println("Expected Third Party Margin: " + ThridPartyDALevel + "Actual Third Party Margin"
				+ find(labelDALevelThirdPartyMargin).getText());
		find(labelDALevelThirdPartyMargin).getText().equals(ThridPartyDALevel);
		find(labelDALevelOverall).click();
		DALevelsArrayGenerated.add(find(labelDALevelRALaborMargin).getText());
		System.out.println("Third Party Margin Level: " + find(labelDALevelMSA).getText());
		OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
		checkIfOverallDACorrect(OverallDA);
		sleep(2000);
		DALevelsArrayGenerated.remove(DALevelsArrayGenerated.size() - 1);
		for (String levels : DALevelsArrayGenerated) {
			System.out.println("DA Levels till now: " + levels);
		}

		find(inputRALaborPercentage).click();
		sleep(2000);
		find(inputRALaborPercentage).clear();
		sleep(2000);
		find(eleClose).click();
		find(inputRALaborPercentage).sendKeys(hardwareMarginArray);
		sleep(2000);
		find(btnCalculateDA).click();
		sleep(5000);
		String RALaborDALevel = calculateDARALaborPercProjectTCV(hardwareMarginArray);
		System.out.println("Expected RA Labor Perc Level: " + RALaborDALevel + "Actual RA Labor Perc Level: "
				+ find(labelDALevelRALaborPercentage).getText());
		find(labelDALevelRALaborPercentage).getText().equals(RALaborDALevel);
		find(labelDALevelOverall).click();
		DALevelsArrayGenerated.add(find(labelDALevelRALaborMargin).getText());
		System.out.println("RA Labor TCV Level: " + find(labelDALevelMSA).getText());
		OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
		checkIfOverallDACorrect(OverallDA);
		sleep(2000);
		DALevelsArrayGenerated.remove(DALevelsArrayGenerated.size() - 1);
		for (String levels : DALevelsArrayGenerated) {
			System.out.println("DA Levels till now: " + levels);
		}
	}

	public void caluclateNegativeCashflow(String negativeCashFlowArray) {
		String NegativeCashFlowDALevel;
		find(inputNegativeCashFlow).click();
		sleep(2000);
		find(inputNegativeCashFlow).clear();
		sleep(2000);
		find(eleClose).click();
		find(inputNegativeCashFlow).sendKeys(negativeCashFlowArray);
		sleep(2000);
		find(btnCalculateDA).click();
		sleep(5000);
		NegativeCashFlowDALevel = calculateDANegativeCashFlow(negativeCashFlowArray);
		find(labelDAlevelNegativeCashFlow).getText().equals(NegativeCashFlowDALevel);
		find(labelDALevelOverall).click();
	}

	public void CalculateDAPCB(String[] reportingPricesArray, String[] reportingCostArray, String[] dALevelOrderArray) {

		find(eleDACalculator).click();
		sleep(2000);

		for (int i = 0; i < reportingPricesArray.length; i++) {
			List<String> DALevelsArrayGenerated = new ArrayList<String>();
			calculateMarginDA("PCB", reportingPricesArray[i], reportingCostArray[i]);
			DALevelsArrayGenerated.add(find(labelDALevelReportingPrice).getText());
			System.out.println("Reporting Price and Margin DA: " + find(labelDALevelReportingPrice).getText());
			sleep(2000);
			Map<String, Integer> DAOrderMap = readDAOrder(dALevelOrderArray);
			String OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
			checkIfOverallDACorrect(OverallDA);
		}
	}

	public void CalculateDACSM(String[] reportingPricesArray, String[] reportingCostArray,
			String[] negativeCashFlowArray, String[] hardwareMarginArray, String[] dALevelOrderArray) {

		find(eleDACalculator).click();
		sleep(2000);
		find(chkboxMilestonePayment).click();

		for (int i = 0; i < reportingPricesArray.length; i++) {

			List<String> DALevelsArrayGenerated = new ArrayList<String>();
			calculateMarginDA("CSM", reportingPricesArray[i], reportingCostArray[i]);
			DALevelsArrayGenerated.add(find(labelDALevelReportingPrice).getText());
			System.out.println("Reporting Price and Margin DA: " + find(labelDALevelReportingPrice).getText());
			sleep(2000);
			caluclateNegativeCashflow(negativeCashFlowArray[i]);
			DALevelsArrayGenerated.add(find(labelDAlevelNegativeCashFlow).getText());
			System.out.println("Negative Cashflow DA: " + find(labelDAlevelNegativeCashFlow).getText());
			Map<String, Integer> DAOrderMap = readDAOrder(dALevelOrderArray);
			String OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
			checkIfOverallDACorrect(OverallDA);

			calculateHardwareDALevel(hardwareMarginArray[i], DALevelsArrayGenerated, DAOrderMap);

			CheckDALevelEscalations(DALevelsArrayGenerated, DAOrderMap);
		}

	}

	public void checkIfOverallDACorrect(String OverallDA) {
		if (find(labelDALevelOverall).getText().equals(OverallDA)) {
			System.out.println("Overall DA is correct");
			addPassLog("Overall DA is correct", "takeScreenshot");
		} else {
			addFailLog("Overall DA is incorrect");
		}
	}

	public void CalculateDASSB(String[] reportingPricesArray, String[] reportingCostArray,
			String[] negativeCashFlowArray, String[] dALevelOrderArray) {

		find(eleDACalculator).click();
		sleep(2000);
		find(chkboxMilestonePayment).click();

		for (int i = 0; i < reportingPricesArray.length; i++) {

			List<String> DALevelsArrayGenerated = new ArrayList<String>();
			String ReportingPriceDALevel;
			String MarginDALevel;
			String NegativeCashFlowDALevel;
			sleep(3000);
			find(inputReportingPrice).click();
			sleep(2000);
			find(inputReportingPrice).clear();
			sleep(2000);
			find(eleClose).click();
			find(inputReportingPrice).sendKeys(reportingPricesArray[i]);
			sleep(3000);
			find(btnCalculateDA).click();
			sleep(2000);
			ReportingPriceDALevel = calculateDACSMReportingMargin(reportingPricesArray[i], "0");
			find(labelDALevelReportingPrice).getText().equals(ReportingPriceDALevel);
			sleep(2000);
			find(inputReportingCost).click();
			sleep(2000);
			find(inputReportingCost).clear();
			sleep(2000);
			find(eleClose).click();
			System.out.println(reportingCostArray[i]);
			find(inputReportingCost).sendKeys(reportingCostArray[i]);
			sleep(2000);
			find(btnCalculateDA).click();
			sleep(5000);
			MarginDALevel = calculateDACSMReportingMargin(reportingPricesArray[i], reportingCostArray[i]);
			find(labelDALevelReportingPrice).getText().equals(MarginDALevel);
			DALevelsArrayGenerated.add(find(labelDALevelReportingPrice).getText());
			System.out.println("Reporting Price and Margin DA: " + find(labelDALevelReportingPrice).getText());
			sleep(2000);
			find(inputNegativeCashFlow).click();
			sleep(2000);
			find(inputNegativeCashFlow).clear();
			sleep(2000);
			find(eleClose).click();
			find(inputNegativeCashFlow).sendKeys(negativeCashFlowArray[i]);
			sleep(2000);
			find(btnCalculateDA).click();
			sleep(5000);
			NegativeCashFlowDALevel = calculateDANegativeCashFlow(negativeCashFlowArray[i]);
			find(labelDAlevelNegativeCashFlow).getText().equals(NegativeCashFlowDALevel);
			find(labelDALevelOverall).click();
			DALevelsArrayGenerated.add(find(labelDAlevelNegativeCashFlow).getText());
			System.out.println("Negative Cashflow DA: " + find(labelDAlevelNegativeCashFlow).getText());
			Map<String, Integer> DAOrderMap = readDAOrder(dALevelOrderArray);
			String OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
			// System.out.println("The Overall Found DA is: " +
			// find(labelDALevelOverall).getText());
			checkIfOverallDACorrect(OverallDA);

			CheckDALevelEscalations(DALevelsArrayGenerated, DAOrderMap);
		}
	}

	public void CheckDALevelEscalations(List<String> DALevelsArrayGenerated, Map<String, Integer> DAOrderMap) {

		for (String levels : DALevelsArrayGenerated) {
			System.out.println("DA Levels till now: " + levels);
		}
		find(inputDistributorRisk).click();
		sleep(2000);
		find(btnCalculateDA).click();
		sleep(3000);
		find(labelDALevelDistributorRisk).getText().equals("A");
		DALevelsArrayGenerated.add(find(labelDALevelDistributorRisk).getText());
		System.out.println("Distributor Risk DA Level: " + find(labelDALevelDistributorRisk).getText());
		String OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
		checkIfOverallDACorrect(OverallDA);
		DALevelsArrayGenerated.remove(DALevelsArrayGenerated.size() - 1);
		for (String levels : DALevelsArrayGenerated) {
			System.out.println("DA Levels till now: " + levels);
		}

		sleep(2000);
		find(inputDistributorRisk).click();
		sleep(2000);
		find(inputMSA).click();
		sleep(2000);
		find(btnCalculateDA).click();
		sleep(2000);
		find(labelDALevelMSA).getText().equals("VP");
		sleep(2000);
		DALevelsArrayGenerated.add(find(labelDALevelMSA).getText());
		System.out.println("MSA Level: " + find(labelDALevelMSA).getText());
		OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
		checkIfOverallDACorrect(OverallDA);
		find(inputMSA).click();
		sleep(2000);
		DALevelsArrayGenerated.remove(DALevelsArrayGenerated.size() - 1);
		for (String levels : DALevelsArrayGenerated) {
			System.out.println("DA Levels till now: " + levels);
		}
		find(inputStateOwnedEntity).click();
		sleep(2000);
		find(btnCalculateDA).click();
		sleep(2000);
		find(labelDALevelStateOwnedEntity).getText().equals("A");
		sleep(2000);
		DALevelsArrayGenerated.add(find(labelDALevelStateOwnedEntity).getText());
		System.out.println("State Owned Entity DA Level: " + find(labelDALevelStateOwnedEntity).getText());
		OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
		DALevelsArrayGenerated.remove(DALevelsArrayGenerated.size() - 1);
		for (String levels : DALevelsArrayGenerated) {
			System.out.println("DA Levels till now: " + levels);
		}
		checkIfOverallDACorrect(OverallDA);
		find(inputStateOwnedEntity).click();
		sleep(2000);
		find(inputThirdParty).click();
		sleep(2000);
		find(btnCalculateDA).click();
		sleep(2000);
		find(labelDALevelThirdParty).getText().equals("VP");
		sleep(2000);
		DALevelsArrayGenerated.add(find(labelDALevelThirdParty).getText());
		System.out.println("Third Party DA Level: " + find(labelDALevelThirdParty).getText());
		OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
		checkIfOverallDACorrect(OverallDA);
		DALevelsArrayGenerated.remove(DALevelsArrayGenerated.size() - 1);
		for (String levels : DALevelsArrayGenerated) {
			System.out.println("DA Levels till now: " + levels);
		}
		find(inputThirdParty).click();
		sleep(2000);
		find(inputLaborLending).click();
		sleep(2000);
		find(btnCalculateDA).click();
		sleep(2000);
		find(labelDALevelLaborLending).getText().equals("A");
		sleep(2000);
		DALevelsArrayGenerated.add(find(labelDALevelLaborLending).getText());
		System.out.println("Labor Lending DA Level: " + find(labelDALevelLaborLending).getText());
		OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
		checkIfOverallDACorrect(OverallDA);
		DALevelsArrayGenerated.remove(DALevelsArrayGenerated.size() - 1);
		for (String levels : DALevelsArrayGenerated) {
			System.out.println("DA Levels till now: " + levels);
		}
		find(inputLaborLending).click();
		sleep(2000);
		find(inputHighRisk).click();
		sleep(2000);
		find(btnCalculateDA).click();
		sleep(2000);
		find(labelDALevelHighRisk).getText().equals("A");
		sleep(2000);
		DALevelsArrayGenerated.add(find(labelDALevelHighRisk).getText());
		System.out.println("High Risk DA Level: " + find(labelDALevelHighRisk).getText());
		OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
		checkIfOverallDACorrect(OverallDA);
		DALevelsArrayGenerated.remove(DALevelsArrayGenerated.size() - 1);
		for (String levels : DALevelsArrayGenerated) {
			System.out.println("DA Levels till now: " + levels);
		}
		find(inputHighRisk).click();
		sleep(2000);
		find(inputTechnicalRisk).click();
		sleep(2000);
		find(btnCalculateDA).click();
		sleep(2000);
		find(labelDALevelTechnicalRisk).getText().equals("A");
		sleep(3000);
		DALevelsArrayGenerated.add(find(labelDALevelTechnicalRisk).getText());
		System.out.println("Technical Risk DA Level: " + find(labelDALevelTechnicalRisk).getText());
		OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
		sleep(2000);
		checkIfOverallDACorrect(OverallDA);
		DALevelsArrayGenerated.remove(DALevelsArrayGenerated.size() - 1);
		for (String levels : DALevelsArrayGenerated) {
			System.out.println("DA Levels till now: " + levels);
		}
		find(inputTechnicalRisk).click();
		sleep(2000);
		find(dropDownTermsNConditions).click();
		sleep(2000);
		find(eleSelectExistingCustomerAgreement).click();
		sleep(2000);
		find(btnCalculateDA).click();
		sleep(2000);
		find(labelDALevelTermsNConditions).getText().equals("A");
		DALevelsArrayGenerated.add(find(labelDALevelTermsNConditions).getText());
		System.out.println("Terms and Conditions DA Level: " + find(labelDALevelTermsNConditions).getText());
		OverallDA = CalculateOverallDA(DALevelsArrayGenerated, DAOrderMap);
		checkIfOverallDACorrect(OverallDA);
		DALevelsArrayGenerated.remove(DALevelsArrayGenerated.size() - 1);
		for (String levels : DALevelsArrayGenerated) {
			System.out.println("DA Levels till now: " + levels);
		}
		find(dropDownTermsNConditions).click();
		sleep(2000);
		find(eleSelectStandard).click();
		sleep(2000);
		find(btnCalculateDA).click();
	}

	public String calculateDANegativeCashFlow(String negativeCashflowArray) {

		String DALevel = null;
		int negativeCashflow;

		Range<Integer> DALevelB2NegCashFlow = Range.between(0, 100000);
		Range<Integer> DALevelB1NegCashFlow = Range.between(100001, 200000);
		Range<Integer> DALevelANegCashFlow = Range.between(200001, 300000);
		Range<Integer> DALevelVPRepPrice = Range.between(2500001, 5000000);
		Range<Integer> DALevelSVPRepPrice = Range.between(5000001, 20000000);

		// Negative cashflow doesnt matter in when reporting price is VP, SVP and CEO.
		negativeCashflow = Integer.parseInt(negativeCashflowArray);
		if (DALevelB2NegCashFlow.contains(negativeCashflow)) {
			System.out.println("The DA Level is B2");
			DALevel = "B2";
		} else if (DALevelB1NegCashFlow.contains(negativeCashflow)) {
			System.out.println("The DA Level is B1");
			DALevel = "B1";
		} else if (DALevelANegCashFlow.contains(negativeCashflow)) {
			System.out.println("The DA Level is A");
			DALevel = "A";
		} else {

		}
		return DALevel;

	}

	public String calculateDAPCB(String reportingPricesArray, String reportingCostArray) {

		double diff;
		double marginPercentage;
		double deviatedMarginPercentage = 0;
		int reportingPrice;
		int reportingCost;
		String DALevel = null;

		reportingPrice = Integer.parseInt(reportingPricesArray);
		reportingCost = Integer.parseInt(reportingCostArray);
		diff = reportingPrice - reportingCost;
		System.out.println("Reporting Price: " + reportingPrice);
		System.out.println("Reporting Cost: " + reportingCost);
		marginPercentage = (diff / reportingPrice) * 100;
		System.out.println("Margin Percentage: " + marginPercentage);

		Range<Integer> DALevelAutoReportingPrice = Range.between(0, 500000);
		Range<Integer> DALevelC1ReportingPrice = Range.between(500001, 1000000);
		Range<Integer> DALevelB1ReportingPrice = Range.between(1000001, 2000000);
		Range<Integer> DALevelAReportingPrice = Range.between(2000001, 3000000);
		Range<Integer> DALevelVPReportingPrice = Range.between(3000001, 5000000);

		Range<Double> DALevelC1Margin = Range.between(25.01, 29.99);
		Range<Double> DALevelB1Margin = Range.between(20.01, 24.99);
		Range<Double> DALevelAMargin = Range.between(10.01, 19.99);
		Range<Double> DALevelVPMargin = Range.between(0.0, 9.99);

		if (DALevelAutoReportingPrice.contains(reportingPrice)) {
			if (marginPercentage >= 30) {
				System.out.println("The DA Level is Auto");
				DALevel = "Auto";
			} else if (DALevelC1Margin.contains(marginPercentage)) {
				System.out.println("The DA Level is C1");
				DALevel = "C1";
			} else if (DALevelB1Margin.contains(marginPercentage)) {
				System.out.println("The DA Level is B1");
				DALevel = "B1";
			} else if (DALevelAMargin.contains(marginPercentage)) {
				System.out.println("The DA Level is A");
				DALevel = "A";
			} else if (DALevelVPMargin.contains(marginPercentage)) {
				System.out.println("The DA Level is VP");
				DALevel = "VP";
			} else {
				System.out.println("The DA Level is Default Value");
				DALevel = "Default Value";
			}
		} else if (DALevelC1ReportingPrice.contains(reportingPrice)) {
			if (marginPercentage >= 25) {
				System.out.println("The DA Level is C1");
				DALevel = "C1";
			} else if (DALevelB1Margin.contains(marginPercentage)) {
				System.out.println("The DA Level is B1");
				DALevel = "B1";
			} else if (DALevelAMargin.contains(marginPercentage)) {
				System.out.println("The DA Level is A");
				DALevel = "A";
			} else if (DALevelVPMargin.contains(marginPercentage)) {
				System.out.println("The DA Level is VP");
				DALevel = "VP";
			} else {
				System.out.println("The DA Level is Default Value");
				DALevel = "Default Value";
			}
		} else if (DALevelB1ReportingPrice.contains(reportingPrice)) {
			if (marginPercentage >= 20) {
				System.out.println("The DA Level is B1");
				DALevel = "B1";
			} else if (DALevelAMargin.contains(marginPercentage)) {
				System.out.println("The DA Level is A");
				DALevel = "A";
			} else if (DALevelVPMargin.contains(marginPercentage)) {
				System.out.println("The DA Level is VP");
				DALevel = "VP";
			} else {
				System.out.println("The DA Level is Default Value");
				DALevel = "Default Value";
			}
		} else if (DALevelAReportingPrice.contains(reportingPrice)) {
			if (marginPercentage >= 10) {
				System.out.println("The DA Level is A");
				DALevel = "A";
			} else if (DALevelVPMargin.contains(marginPercentage)) {
				System.out.println("The DA Level is VP");
				DALevel = "VP";
			} else {
				System.out.println("The DA Level is Default Value");
				DALevel = "Default Value";
			}
		} else if (DALevelAReportingPrice.contains(reportingPrice)) {
			if (marginPercentage >= 0) {
				System.out.println("The DA Level is VP");
				DALevel = "VP";
			} else {
				System.out.println("The DA Level is Default Value");
				DALevel = "Default Value";
			}
		}
		return DALevel;

	}

	public String calculateDARALaborPercProjectTCV(String StrHardwareMarginPerc) {

		String DALevel = null;
		Range<Double> DALevelB2Devt = Range.between(10.00, 15.00);
		Range<Double> DALevelADevt = Range.between(5.0, 9.9);

		double HardwareMarginPerc = Double.parseDouble(StrHardwareMarginPerc);

		if (HardwareMarginPerc > 15) {
			System.out.println("The DA Level is C1");
			DALevel = "C1";
		} else if (DALevelB2Devt.contains(HardwareMarginPerc)) {
			System.out.println("The DA Level is B2");
			DALevel = "B2";
		} else if (DALevelADevt.contains(HardwareMarginPerc)) {
			System.out.println("The DA Level is A");
			DALevel = "A";
		} else {
			System.out.println("The DA Level is VP");
			DALevel = "VP";
		}

		return DALevel;

	}

	public String calculateDARALaborMarginAndThirdPartyMargin(String StrHardwareMarginPerc) {

		String DALevel = null;
		Range<Double> DALevelB2Devt = Range.between(14.00, 15.00);
		Range<Double> DALevelB1Devt = Range.between(12.00, 13.99);
		Range<Double> DALevelADevt = Range.between(5.0, 11.9);

		double HardwareMarginPerc = Double.parseDouble(StrHardwareMarginPerc);

		if (HardwareMarginPerc > 15) {
			System.out.println("The DA Level is C1");
			DALevel = "C1";
		} else if (DALevelB2Devt.contains(HardwareMarginPerc)) {
			System.out.println("The DA Level is B2");
			DALevel = "B2";
		} else if (DALevelB1Devt.contains(HardwareMarginPerc)) {
			System.out.println("The DA Level is B1");
			DALevel = "B1";
		} else if (DALevelADevt.contains(HardwareMarginPerc)) {
			System.out.println("The DA Level is A");
			DALevel = "A";
		} else {
			System.out.println("The DA Level is VP");
			DALevel = "VP";
		}

		return DALevel;

	}

	public String calculateDACSMReportingMargin(String reportingPricesArray, String reportingCostArray) {

		double diff;
		double marginPercentage;
		double deviatedMarginPercentage = 0;
		int reportingPrice;
		int reportingCost;
		String DALevel = null;

		reportingPrice = Integer.parseInt(reportingPricesArray);
		reportingCost = Integer.parseInt(reportingCostArray);
		diff = reportingPrice - reportingCost;
		System.out.println("Reporting Price: " + reportingPrice);
		System.out.println("Reporting Cost: " + reportingCost);
		marginPercentage = (diff / reportingPrice) * 100;
		deviatedMarginPercentage = 37 - marginPercentage;
		System.out.println("Margin Percentage: " + marginPercentage);
		System.out.println("Deviated Margin Percentage: " + deviatedMarginPercentage);

		// Range<Double> DALevelC1Devt = Range.between(0.0, 3.0);
		Range<Double> DALevelB2Devt = Range.between(3.0, 4.0);
		Range<Double> DALevelB1Devt = Range.between(4.0, 6.0);
		Range<Double> DALevelADevt = Range.between(6.0, 8.0);
		Range<Integer> DALevelC1RepPrice = Range.between(0, 250000);
		Range<Integer> DALevelB2RepPrice = Range.between(250001, 500000);
		Range<Integer> DALevelB1RepPrice = Range.between(500001, 1000000);
		Range<Integer> DALevelARepPrice = Range.between(1000001, 2500000);
		Range<Integer> DALevelVPRepPrice = Range.between(2500001, 5000000);
		Range<Integer> DALevelSVPRepPrice = Range.between(5000001, 20000000);

		if (DALevelC1RepPrice.contains(reportingPrice)) {
			if (deviatedMarginPercentage < 3.0) {
				System.out.println("The DA Level is C1");
				DALevel = "C1";
			} else if (DALevelB2Devt.contains(deviatedMarginPercentage)) {
				System.out.println("The DA Level is B2");
				DALevel = "B2";
			} else if (DALevelB1Devt.contains(deviatedMarginPercentage)) {
				System.out.println("The DA Level is B1");
				DALevel = "B1";
			} else if (DALevelADevt.contains(deviatedMarginPercentage)) {
				System.out.println("The DA Level is A");
				DALevel = "A";
			} else {
				System.out.println("The DA Level is VP");
				DALevel = "VP";
			}
		} else if (DALevelB2RepPrice.contains(reportingPrice)) {
			if (deviatedMarginPercentage < 4.0) {
				System.out.println("The DA Level is B2");
				DALevel = "B2";
			} else if (DALevelB1Devt.contains(deviatedMarginPercentage)) {
				System.out.println("The DA Level is B1");
				DALevel = "B1";
			} else if (DALevelADevt.contains(deviatedMarginPercentage)) {
				System.out.println("The DA Level is A");
				DALevel = "A";
			} else {
				System.out.println("The DA Level is VP");
				DALevel = "VP";
			}
		} else if (DALevelB1RepPrice.contains(reportingPrice)) {
			if (deviatedMarginPercentage < 6.0) {
				System.out.println("The DA Level is B1");
				DALevel = "B1";
			} else if (DALevelADevt.contains(deviatedMarginPercentage)) {
				System.out.println("The DA Level is A");
				DALevel = "A";
			} else {
				System.out.println("The DA Level is VP");
				DALevel = "VP";
			}
		} else if (DALevelARepPrice.contains(reportingPrice)) {

			if (deviatedMarginPercentage < 8.0) {
				System.out.println("The DA Level is A");
				DALevel = "A";
			} else {
				System.out.println("The DA Level is VP");
				DALevel = "VP";
			}

		} else if (DALevelVPRepPrice.contains(reportingPrice)) {
			System.out.println("The DA Level is VP");
			DALevel = "VP";
		} else if (DALevelSVPRepPrice.contains(reportingPrice)) {
			System.out.println("The DA Level is SVP");
			DALevel = "SVP";
		} else if (reportingPrice > 20000000) {
			System.out.println("The DA Level is CEO");
			DALevel = "CEO";
		}

		return DALevel;
	}

	public String calculateDASSBReportingMargin(String reportingPricesArray, String reportingCostArray) {

		double diff;
		double marginPercentage;
		double deviatedMarginPercentage = 0;
		int reportingPrice;
		int reportingCost;
		String DALevel = null;

		reportingPrice = Integer.parseInt(reportingPricesArray);
		reportingCost = Integer.parseInt(reportingCostArray);
		diff = reportingPrice - reportingCost;
		System.out.println("Reporting Price: " + reportingPrice);
		System.out.println("Reporting Cost: " + reportingCost);
		marginPercentage = (diff / reportingPrice) * 100;
		deviatedMarginPercentage = 35 - marginPercentage;
		System.out.println("Margin Percentage: " + marginPercentage);
		System.out.println("Deviated Margin Percentage: " + deviatedMarginPercentage);

		// Range<Double> DALevelC1Devt = Range.between(0.0, 3.0);
		Range<Double> DALevelB2Devt = Range.between(3.0, 4.0);
		Range<Double> DALevelB1Devt = Range.between(4.0, 6.0);
		Range<Double> DALevelADevt = Range.between(6.0, 8.0);
		Range<Integer> DALevelC1RepPrice = Range.between(0, 250000);
		Range<Integer> DALevelB2RepPrice = Range.between(250001, 500000);
		Range<Integer> DALevelB1RepPrice = Range.between(500001, 1000000);
		Range<Integer> DALevelARepPrice = Range.between(1000001, 2500000);
		Range<Integer> DALevelVPRepPrice = Range.between(2500001, 5000000);
		Range<Integer> DALevelSVPRepPrice = Range.between(5000001, 20000000);

		if (DALevelC1RepPrice.contains(reportingPrice)) {
			if (deviatedMarginPercentage < 3.0) {
				System.out.println("The DA Level is C1");
				DALevel = "C1";
			} else if (DALevelB2Devt.contains(deviatedMarginPercentage)) {
				System.out.println("The DA Level is B2");
				DALevel = "B2";
			} else if (DALevelB1Devt.contains(deviatedMarginPercentage)) {
				System.out.println("The DA Level is B1");
				DALevel = "B1";
			} else if (DALevelADevt.contains(deviatedMarginPercentage)) {
				System.out.println("The DA Level is A");
				DALevel = "A";
			} else {
				System.out.println("The DA Level is VP");
				DALevel = "VP";
			}
		} else if (DALevelB2RepPrice.contains(reportingPrice)) {
			if (deviatedMarginPercentage < 4.0) {
				System.out.println("The DA Level is B2");
				DALevel = "B2";
			} else if (DALevelB1Devt.contains(deviatedMarginPercentage)) {
				System.out.println("The DA Level is B1");
				DALevel = "B1";
			} else if (DALevelADevt.contains(deviatedMarginPercentage)) {
				System.out.println("The DA Level is A");
				DALevel = "A";
			} else {
				System.out.println("The DA Level is VP");
				DALevel = "VP";
			}
		} else if (DALevelB1RepPrice.contains(reportingPrice)) {
			if (deviatedMarginPercentage < 6.0) {
				System.out.println("The DA Level is B1");
				DALevel = "B1";
			} else if (DALevelADevt.contains(deviatedMarginPercentage)) {
				System.out.println("The DA Level is A");
				DALevel = "A";
			} else {
				System.out.println("The DA Level is VP");
				DALevel = "VP";
			}
		} else if (DALevelARepPrice.contains(reportingPrice)) {

			if (deviatedMarginPercentage < 8.0) {
				System.out.println("The DA Level is A");
				DALevel = "A";
			} else {
				System.out.println("The DA Level is VP");
				DALevel = "VP";
			}

		} else if (DALevelVPRepPrice.contains(reportingPrice)) {
			System.out.println("The DA Level is VP");
			DALevel = "VP";
		} else if (DALevelSVPRepPrice.contains(reportingPrice)) {
			System.out.println("The DA Level is SVP");
			DALevel = "SVP";
		} else if (reportingPrice > 20000000) {
			System.out.println("The DA Level is CEO");
			DALevel = "CEO";
		}

		return DALevel;
	}

	public Map<String, Integer> readDAOrder(String[] DAOrderArray) {
		Map<String, Integer> DAOrderMap = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < DAOrderArray.length; i++) {
			DAOrderMap.put(DAOrderArray[i], i);

		}
		System.out.println("DA Order: " + DAOrderMap);
		return DAOrderMap;
	}

	public String CalculateOverallDA(List<String> DALevels, Map<String, Integer> DAOrderMap) {

		Map<Integer, String> DALevelMap = null;
		List<Integer> keysArray = new ArrayList<Integer>();
		DALevelMap = new LinkedHashMap<Integer, String>();
		for (String da : DALevels) {

			int weightage = DAOrderMap.get(da);
			DALevelMap.put(weightage, da);
			for (Integer key : DALevelMap.keySet()) {
				keysArray.add(key);
			}
		}
		int largestKey = getLargestKey(keysArray);
		String OverallDA = DALevelMap.get(largestKey);
		System.out.println("The Overall DA calculated is: " + OverallDA.toString());
		return OverallDA.toString();

	}

	public Integer getLargestKey(List<Integer> keysArray) {
		int largest, smallest, number;
		largest = (int) keysArray.get(0);
		smallest = (int) keysArray.get(0);

		for (int list1 : keysArray) {
			number = list1;
			if (number > largest) {
				largest = number;
			} else if (number < smallest) {
				smallest = number;
			}
		}
		return largest;
	}

	public void CalculateEscalationDASSB(String[] ReportingPricesArray, String[] ReportingCostArray,
			String[] NegativeCashFlowArray, String[] DALevelsArrayMargin, String[] DALevelsArrayOverall) {

	}

	public void searchProposalLeftMenu(String PropNo) {
		waitForElementPresent(inputSearchProposals);
		sleep(2000);
		find(inputSearchProposals).sendKeys(PropNo);
		sleep(1000);
		By eleSearchedProposal = By.xpath("(//span[contains(text(),'" + PropNo + "')])[1]");
		if (find(eleSearchedProposal).isDisplayed() == true) {
			find(eleSearchedProposal).click();
		} else {
			addFailLog("Proposal Search failed in Left Menu");
		}

	}

	public HashMap<String, String> readProposalFieldValues() {

		find(eleProposalInfo).click();
		OldVersionValues = new HashMap<String, String>();
		String ProposalTypeOldVersion = find(labelProposalType).getText();
		// System.out.println(ProposalTypeOldVersion);
		OldVersionValues.put("ProposalType", ProposalTypeOldVersion);
		String LeadBUOldVersion = find(labelLeadBU).getAttribute("value");
		// System.out.println(LeadBUOldVersion);
		OldVersionValues.put("LeadBU", LeadBUOldVersion);
		String OppNameOldVersion = find(labelOppName).getText();
		// System.out.println(OppNameOldVersion);
		OldVersionValues.put("OppName", OppNameOldVersion);
		String CloseDateOldVersion = find(labelCloseDate).getText();
		// System.out.println(CloseDateOldVersion);
		OldVersionValues.put("CloseDate", CloseDateOldVersion);
		return OldVersionValues;

	}

	public void verifyOldVersionValuesWithNewVersion(HashMap<String, String> OldVersionValues) {

		find(eleProposalInfo).click();

		OldVersionValues.get("ProposalType").equals(find(labelProposalType).getText());
		// System.out.println(find(labelProposalType).getText());
		addPassLog("Old Version ProposalType Value: " + OldVersionValues.get("ProposalType") + " ||  ProMS Value is: "
				+ find(labelProposalType).getText(), "No");
//
//		OldVersionValues.get("LeadBUOldVersion").equals(find(labelLeadBU).getText());
//		System.out.println(find(labelLeadBU).getText());
//		addPassLog("Old Version ProposalType Value: " + OldVersionValues.get("LeadBUOldVersion")
//				+ " ||  ProMS Value is: " + find(labelLeadBU).getText(), "No");

		OldVersionValues.get("OppNameOldVersion").equals(find(labelOppName).getText());
		// System.out.println(find(labelOppName).getText());
		addPassLog("Old Version ProposalType Value: " + OldVersionValues.get("OppNameOldVersion")
				+ " ||  ProMS Value is: " + find(labelOppName).getText(), "No");

		OldVersionValues.get("CloseDateOldVersion").equals(find(labelCloseDate).getText());
		// System.out.println(find(labelCloseDate).getText());
		addPassLog("Old Version ProposalType Value: " + OldVersionValues.get("CloseDateOldVersion")
				+ " ||  ProMS Value is: " + find(labelCloseDate).getText(), "No");

	}

	public void verifySapFieldInProMS(HashMap<String, String> sapValues) {
		sapValues.get("LedBy").equals(find(labelLedByValue).getText());
		// System.out.println(find(labelLedByValue).getText());
		addPassLog("SAP Quote Value: " + sapValues.get("LedBy") + " ||  ProMS Value is: "
				+ find(labelLedByValue).getText(), "No");

		sapValues.get("Probablity").equals(find(labelProbabilityValue).getText());
		// System.out.println(find(labelProbabilityValue).getText());
		addPassLog("SAP Quote Value: " + sapValues.get("Probablity") + " ||  ProMS Value is: "
				+ find(labelProbabilityValue).getText(), "No");

		sapValues.get("Industry").equals(find(labelOppIndustryValue).getText());
		// System.out.println(find(labelOppIndustryValue).getText());
		addPassLog("SAP Quote Value: " + sapValues.get("Industry") + " ||  ProMS Value is: "
				+ find(labelOppIndustryValue).getText(), "No");

		sapValues.get("SoldTo").equals(find(labelSoldToValue).getText());
		// System.out.println(find(labelSoldToValue).getText());
		addPassLog("SAP Quote Value: " + sapValues.get("SoldTo") + " ||  ProMS Value is: "
				+ find(labelSoldToValue).getText(), "No");

		sapValues.get("EndCustomer").equals(find(labelEndCustomerValue).getText());
		// System.out.println(find(labelEndCustomerValue).getText());
		addPassLog("SAP Quote Value: " + sapValues.get("EndCustomer") + "  ||  ProMS Value is: "
				+ find(labelEndCustomerValue).getText(), "No");

		sapValues.get("ShipTo").equals(find(labelShipToValue).getText());
		// System.out.println(find(labelShipToValue).getText());
		addPassLog("SAP Quote Value: " + sapValues.get("ShipTo") + " ||  ProMS Value is: "
				+ find(labelShipToValue).getText(), "No");

		addPassLog("The Fields match", "takeScreenshot");

	}

	public void acceptProposalInProMS() {
		find(eleAcceptRequest).click();
		waitForElementPresent(txtAreaProposalAcceptNote);
		find(txtAreaProposalAcceptNote).sendKeys("Desc");
		find(txtFieldFirstName).sendKeys("Sanat");
		find(btnSearchAcceptRequest).click();
		sleep(3000);
		find(eleFirstSearchedRow).click();
		sleep(1000);
		find(AcceptDate).clear();
		find(AcceptDate).sendKeys(getCurrentDate("dd-MMM-yyyy"));
		sleep(3000);
		find(btnAccept).click();

	}

	public void goToHome() {
		waitForElementPresent(eleHome);
		find(eleHome).click();
		waitForElementPresent(eleProposalMgmtSolution);
		find(eleProposalMgmtSolution).click();

	}

	public void goToHome2() {
		find(eleHome).click();
		sleep(4000);
		find(eleProposalMgmtSolutionHome).click();
		sleep(9000);
	}

	public void goToMyProposal() {

		waitForElementPresent(eleMyProposals);
		find(eleMyProposals).click();
	}

	public void verifyProposalOwner(String[] ProposalManagerArray) {

		find(eleProposalTeam).click();
		for (int i = 0; i < ProposalManagerArray.length; i++) {
			WebElement eleProposalManager = driver
					.findElement(By.xpath("//span[contains(text(),'" + ProposalManagerArray[i] + "')]"));
			if (eleProposalManager.isDisplayed()) {
				addPassLog("The Proposal Manager" + ProposalManagerArray[i] + " is properly assigned",
						"takeScreenshot");
			}
		}

	}

	public void goToPendingProposals() {

		find(elePendingApprovals).click();
		sleep(45000);
	}

	public void changeToCustom() {

		find(eleBusinessUnit).click();
		sleep(7000);
		find(eleRiskReviewEdit).click();
		sleep(1000);
		find(tabLineItem).click();
		sleep(13000);
		find(eleDropdownPricingSystem).click();
		find(eleCustomSystem).click();
		sleep(1000);
		find(btnUpdate).click();
		sleep(1000);
		find(btnSave).click();
		sleep(15000);
	}

	public void changeStatusToPlanning(String PropNo, String[] BusinessUnitArray) {

		waitForElementPresent(eleBusinessUnit);
		sleep(10000);
		find(eleBusinessUnit).click();
		waitForElementPresent(eleRiskReviewEdit);
		find(eleRiskReviewEdit).click();
		sleep(1000);
		for (int i = 0; i < BusinessUnitArray.length; i++) {

			find(eleInActive).click();
			WebElement tabLineItem = driver.findElement(By.xpath("(//tr)[" + (i + 2) + "]"));
			tabLineItem.click();
			waitForElementPresent(dropdownOffering);
			if (BusinessUnitArray[i].equals("CSM Plant Services")) {
				find(dropdownOffering).click();
				sleep(1200);
				find(eleOfferingCSM).click();
				sleep(800);
				find(dropdownSubOffering).click();
				sleep(800);
				find(eleSubOfferingCSM).click();
			}
			waitForElementPresent(eleNo);
			find(eleNo).click();
			find(eleDropdownBULineStatus).click();
			find(eleInPlanning).click();
			sleep(5000);
			find(btnUpdate).click();
			sleep(5000);
			// find(btnSave).click();
			// sleep(12000);
		}

		// find(eleRiskReviewEdit).click();
		find(eleDropdownHeaderStatusPlanning).click();
		find(eleHeaderInPlanning).click();
		sleep(5000);
		find(btnSave).click();
		sleep(18000);
		if (find(eleInPanningStatus).isDisplayed() == true) {
			addPassLog(PropNo + " is successfully changed to Planning status", "takeScreenshot");
		} else {
			addFailLog(PropNo + " is not changed to Planning status");
		}
	}

	public void changeStatusToDevelopment(String PropNo, String[] BusinessUnitArray) {

		find(eleRiskReviewEdit).click();
		sleep(2000);
		for (int i = 2; i <= BusinessUnitArray.length + 1; i++) {
			WebElement tabLineItem = driver.findElement(By.xpath("(//tr)[" + i + "]"));
			find(eleInActive).click();
			tabLineItem.click();
			sleep(8000);
			find(eleDropdownBULineStatus).click();
			find(eleInDevelopment).click();
			sleep(3500);
			find(btnUpdate).click();
			sleep(5000);
			// find(btnSave).click();
			// sleep(17000);
		}
		// find(eleRiskReviewEdit).click();
		find(eleDropdownHeaderStatusDevelopment).click();
		find(eleHeaderInDevelopment).click();
		sleep(4000);
		find(btnSave).click();

		if (find(eleInDevelopmentStatus).isDisplayed() == true) {
			addPassLog(PropNo + " is successfully changed to Development status", "takeScreenshot");
		} else {
			addFailLog(PropNo + " is not changed to Development status");
		}

	}

	public void changeStatusToReview(String PropNo, String ProposalType, String[] BusinessUnitArray, String QuotedPrice,
			String MarginPercentage) {

		find(eleRiskReviewEdit).click();
		for (int i = 0; i < BusinessUnitArray.length; i++) {
			WebElement tabLineItem = driver.findElement(By.xpath("(//tr)[" + (i + 2) + "]"));
			find(eleInActive).click();
			tabLineItem.click();
			sleep(7000);
			// find(eleEdit).click();
			find(eleDropdownPricingSystem).click();
			find(eleCustomSystem).click();
			find(inputQuotedPrice).clear();
			find(inputQuotedPrice).sendKeys(QuotedPrice);
			find(inputCostPrice).clear();
			find(inputCostPrice).sendKeys(MarginPercentage);
			find(btnCalcPrice).click();
			sleep(1500);
			find(eleDropdownBULineStatus).click();
			find(eleInReview).click();
			sleep(2800);
			find(eleRALaborMargin).sendKeys(MarginPercentage);
			find(eleThirdPartyMargin).sendKeys(MarginPercentage);
			find(eleRALaborTCV).sendKeys(MarginPercentage);
			find(eleRAProductTCV).sendKeys(MarginPercentage);

			find(btnUpdate).click();
			// sleep(5000);
			// find(btnSave).click();
		}
		// find(eleRiskReviewEdit).click();
		sleep(3000);
		find(eleDropdownHeaderStatusReview).click();
		find(eleHeaderInReview).click();
		sleep(4000);
		find(eleProposalInfo).click();
		sleep(1000);
		find(chkboxNoneOfThese).click();
		sleep(1000);
		find(eleBusinessUnit).click();
		sleep(1000);
		find(btnSave).click();
		sleep(30000);

		if (ProposalType.equals("Budgetary Quote")) {
			if (find(eleApprovedStatus).isDisplayed() == true) {
				addPassLog(PropNo + " is successfully changed to Approved status", "takeScreenshot");
			} else {
				addFailLog(PropNo + " is not changed to Approved status");
			}
		} else {
			if (find(eleInReviewStatus).isDisplayed() == true) {
				addPassLog(PropNo + " is successfully changed to Review status", "takeScreenshot");
			} else {
				addFailLog(PropNo + " is not changed to Review status");
			}
		}

	}

	public void riskReview(String[] BusinessUnitArray) {
		find(eleApprovals).click();
		sleep(4000);
		find(eleRiskReviewEdit).click();
		sleep(3000);
		for (int i = 1; i < BusinessUnitArray.length + 1; i++) {
			WebElement linkOpenTask = driver.findElement(By.xpath("//table//span[contains(text(),'Open')]"));
			linkOpenTask.click();
			sleep(10000);
			find(dropDownTaskStatus).click();
			find(eleTaskStatus).click();
			sleep(2000);
			WebElement btnSaveReview = driver
					.findElement(By.xpath("(//button/span/span[contains(text(),'Save')])[" + i + "]"));

			btnSaveReview.click();
			sleep(7000);
		}

	}

	public void approvePendingApprovals() {
		find(btnApprove).click();
		find(btnPendingApproveNote).sendKeys("Note");
		find(btnSubmit).click();
		sleep(13000);
//		find(btnApprove).click();
//		find(btnPendingApproveNote).sendKeys("Note");
//		find(btnSubmit).click();

	}

	public void fillValidDate() {
		find(eleRiskReviewEdit).click();
		find(eleInfo).click();
		sleep(2000);
		find(eleInfoDate).sendKeys(getCurrentDate("dd-MMM-yyyy"));
		sleep(2000);
		find(eleInitiate).click();
		find(btnSave).click();
		sleep(16000);
	}

	public void waitAfterPendingApproval() {
		sleep(30000);
	}

	public void changeToProposed(String PropNo) {

		find(eleRiskReviewEdit).click();
		find(eleDropdownBULineStatusProposedAfterApproval).click();
		find(eleProposed).click();
		sleep(4000);
		find(btnSave).click();
		sleep(25000);
		if (find(eleProposedStatus).isDisplayed() == true) {
			addPassLog(PropNo + " is successfully changed to Proposed status", "takeScreenshot");
		} else {
			addFailLog(PropNo + " is not changed to Proposed status");
		}

	}

	public void changeToProposedAfterReview(String PropNo) {

		find(eleRiskReviewEdit).click();
		find(eleDropdownBULineStatusProposed).click();
		find(eleProposed).click();
		sleep(4000);
		find(btnSave).click();
		sleep(25000);
		addPassLog(PropNo + " is successfully changed to Proposed status", "takeScreenshot");

	}

	public void changeToPOReceived(String PropNo, String[] BusinessUnitArray) {

		find(eleRiskReviewEdit).click();
		for (int i = 2; i <= BusinessUnitArray.length + 1; i++) {
			WebElement tabLineItem = driver.findElement(By.xpath("(//tr)[" + i + "]"));
			find(eleBusinessUnit).click();
			find(eleInActive).click();
			tabLineItem.click();
			sleep(5000);
			find(eleDropdownBULineStatus).click();
			find(eleInPOReceived).click();
			sleep(3500);
			find(btnUpdate).click();
			sleep(3500);
		}
		find(btnSave).click();
		sleep(9500);
		addPassLog(PropNo + " is successfully changed to PO Received", "takeScreenshot");

	}

	public void changeToWon(String PropNo) {

		find(tabLineItem).click();
		sleep(5000);
		find(eleRiskReviewEdit).click();
		sleep(3000);
		find(inputOrderNo).sendKeys("OR1234");
		find(eleOME).click();
		find(inputProjNo).sendKeys("PRJ1234");
		find(btnUpdate).click();

		find(eleDropdownBULineStatusPOReceived).click();
		find(eleHeaderPOReceived).click();
		find(btnSave).click();
		sleep(5000);

	}

	public void changeToLostBULineItem(String reason) {

		find(eleBusinessUnit).click();
		find(eleRiskReviewEdit).click();
		sleep(2000);
		find(eleCancelLineItem).click();
		find(eleCancelYes).click();
		sleep(2000);
		find(inputCloseDateLost).sendKeys(getCurrentDate("dd-MMM-yyyy"));
		sleep(2000);
		find(labelSelectReason).click();
		sleep(2000);
		WebElement eleLostReason = driver.findElement(By.xpath("//li[contains(text(),'" + reason + "')]"));
		eleLostReason.click();
		sleep(2000);
		find(btnSubmit).click();
		sleep(2000);
		find(btnSave).click();
		sleep(20000);
		find(eleInActiveExpand).click();
		WebElement eleInactiveTabReason = driver
				.findElement(By.xpath("(//span[contains(text(),'" + reason + "')])[1]"));
		eleInactiveTabReason.isDisplayed();
		find(eleInactiveTabBUStatus).isDisplayed();
		if (eleInactiveTabReason.isDisplayed() == true && find(eleInactiveTabBUStatus).isDisplayed() == true) {
			addPassLog("The LineItem was Cancelled", "takeScreenshot");
		} else {
			addFailLog("The LineItem was not Cancelled properly");
		}

	}

	public void changetoLostHeaderStatus(String reason) {
		find(eleRiskReviewEdit).click();
		find(eleDropdownHeaderStatusDevelopment).click();
		find(eleHeaderLost).click();
		find(labelSelectReason).click();
		find(eleHeaderLostReason).click();
		find(btnSubmit).click();
		sleep(20000);
		if (find(eleDropdownHeaderNotToBePursued).isDisplayed() == true) {
			addPassLog("Header Status changed to Not To be Pursued", "takeScreenshot");
		}

		find(eleInActiveExpand).click();
		WebElement eleInactiveTabReason = driver
				.findElement(By.xpath("(//span[contains(text(),'Not to be pursued by RA')])[2]"));
		eleInactiveTabReason.isDisplayed();
		find(eleInactiveTabBUStatus2).isDisplayed();
		if (eleInactiveTabReason.isDisplayed() == true && find(eleInactiveTabBUStatus2).isDisplayed() == true) {
			addPassLog("The Remaining LineItem was Cancelled", "takeScreenshot");
		} else {
			addFailLog("The Remaining LineItem was not Cancelled properly");
		}

	}

	public void AddBULineItem() {

		find(eleRiskReviewEdit).click();
		sleep(4000);
		find(btnAddBUItem).click();
		sleep(3000);
		find(dropdownBusiness).click();
		find(eleSSB).click();
		find(dropdownSubBU).click();
		find(eleSubBU).click();
		find(dropdownOffering).click();
		find(eleOfferingSSB).click();
		find(dropdownCapability).click();
		find(eleCapability).click();
		find(dropdownSuboff).click();
		find(eleSubOff).click();
		find(dropdownBusinessType).click();
		find(eleBusinessType).click();
		find(dropdownBusinessSegment).click();
		find(eleBusinessSegment).click();
		find(btnUpdate).click();
		sleep(5000);
	}

	public String splitProposalNumber(String PropNo) {
		String[] PropNoSplit = PropNo.split("\\.");
		// System.out.println("Proposal No after Split: " + PropNoSplit[0]);
		WebElement proposalVersion = driver.findElement(By.xpath("//span[contains(text(),PropNoSplit[0])]"));
		return proposalVersion.getText();

	}

	public void assignApprovers() {

		find(btnAssignApprovers).click();
		find(eleBusinessAppAssign).click();
		find(inputSearchApprover).sendKeys("Sanat");
		sleep(2000);
		find(tabLineItem).click();
		sleep(3000);
//		find(eleCNAppAssign).click();
//		find(inputSearchApprover).sendKeys("Sanat");
//		find(tabLineItem).click();
//		sleep(2000);
		find(btnStartApproval).click();
		sleep(10000);

	}

	public String createNewVersion() {

		find(btnNewVersion).click();
		sleep(8000);
		find(inputRadioNewVersion).click();
		find(chkboxMinorVersion).click();
		find(inputNoteNewVersion).sendKeys("Note");
		find(btnNewVersionContinue).click();
		find(inputNewVersionDate).sendKeys(getCurrentDate("dd-MMM-yyyy"));
		find(btnSubmit).click();
		if (isPresentAndDisplayed(find(labelSuccess)) == true) {
			addPassLog("Creation of New Version is successful", "takeScreenshot");

		} else {
			addFailLog("Creation of New Version Unsuccessful");
		}
		String NewVersionMessage = find(NewVersionProposalMsg).getText();
		find(eleOK).click();
		addPassLog(NewVersionMessage, "takeScreenshot");
		String NewVersionPropNumber = NewVersionMessage.substring(13, 23);
		// System.out.println("Proposal New Version is : " + NewVersionPropNumber);
		sleep(16000);
		return NewVersionPropNumber;

	}

	public void checkIfPreviousVersionIsSuperseded() {

		if (isPresentAndDisplayed(find(labelSuperseded)) == true) {
			addPassLog("Previous Version of proposal has Superseded status", "takeScreenshot");

		} else {
			addFailLog("Previous Version of proposal does not have Superseded status");
		}

	}

	public void checkIfProposalStatusInDev() {

		if (isPresentAndDisplayed(find(eleInDevelopmentStatus)) == true) {
			addPassLog("Proposal has Development status", "takeScreenshot");

		} else {
			addFailLog("Proposal does not have Development status");
		}

	}

	public HashMap<String, String> searchQuoteInSap(String quoteId) {
		for (String winHandle : driver.getWindowHandles()) {
			if (driver.switchTo().window(winHandle).getTitle().contains("Quote:")) {
				driver.manage().window().maximize();
				driver.switchTo().frame("CRMApplicationFrame");
				driver.switchTo().frame("WorkAreaFrame1");
//				find(eleOrderManagement).click();
//				find(linkQuotations).click();
//				sleep(4000);
//				find(txtboxQuoteIdSap).sendKeys(quoteId);
//				find(btnSearchSAP).click();
//				sleep(3000);
//				find(linkFoundQuote).click();
				sleep(4000);
				hmap = new HashMap<String, String>();
				String contactName = find(eleContactNameValue).getText();
				hmap.put("ContactName", contactName);
				String soldTo = find(eleSoldToValue).getText();
				hmap.put("SoldTo", soldTo);
				String endCustomer = find(eleEndCustomerIdValue).getText();
				hmap.put("EndCustomer", endCustomer);
				String shipTo = find(eleShipToValue).getText();
				hmap.put("ShipTo", shipTo);
				String desc = find(eleDescValue).getText();
				hmap.put("Desc", desc);
				String mainBU = find(eleMainBUValue).getText();
				hmap.put("MainBU", mainBU);
				String industry = find(eleIndustryValue).getText();
				hmap.put("Industry", industry);
				String ledBy = find(eleLedByValue).getText();
				hmap.put("LedBy", ledBy);
				String userStatus = find(eleUserStatusValue).getText();
				hmap.put("UserStatus", userStatus);
				String quoteNumber = find(eleQuoteNumberValue).getText();
				hmap.put("QuoteNumber", quoteNumber);
				String closeDate = find(eleCloseDateValue).getText();
				hmap.put("CloseDate", closeDate);
				String reqDate = find(eleReqDateValue).getText();
				hmap.put("ReqDate", reqDate);
				String probability = find(eleProbValue).getText();
				hmap.put("Probablity", probability);
				String proMSId = find(eleProMSIDValue).getText();
				hmap.put("ProMSId", proMSId);
				// System.out.println(hmap);
				if (!proMSId.equals("")) {
					addPassLog("The ProMS ID is: " + proMSId, "takeScreenshot");
				} else {
					addFailLog("The ProMS ID has not been created");
				}

			}
		}
		return hmap;
	}

	public void goToSap() {

		driver.get(getURL("varSapURL"));
		find(inputUsernameTxtfield).sendKeys("zuser0784@rockwellautomation.com");
		find(next).click();
		find(linkPS).click();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		sleep(4000);
		find(inputRockwellUsername).sendKeys("zuser0784@rockwellautomation.com");
		find(inputRockwellPassword).sendKeys("Welcome_1");
		find(btnSubmitLogin).click();
		find(linkCustRenMgmt).click();
		sleep(4000);
		find(linkCustRenMgmt).click();
		find(linkETOQuotes).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.numberOfWindowsToBe(3));
		sleep(35000);

	}

	public String createQuote(String Contact, String SoldToParty, String EndCustomer, String ShipToParty,
			String QuoteDesc, String MainBU, String Industry, String UserStatus, String Probability, String Type,
			String CloseDate, String DelDate, String[] ProductIDArray, String[] QuantityArray, String[] PriceElement1,
			String[] Price1, String[] Currency1, String[] PriceElement2, String[] Price2, String[] Currency2,
			String NoItemPricing) {

		for (String winHandle : driver.getWindowHandles()) {
			System.out.println("Window Handles : " + driver.switchTo().window(winHandle).getTitle());
			if (driver.switchTo().window(winHandle).getTitle().equals("My Workspace - [SAP]")) {
				driver.manage().window().maximize();
				driver.switchTo().frame("CRMApplicationFrame");
				driver.switchTo().frame("WorkAreaFrame1");
				sleep(3000);
				find(eleOrderManagement).click();
				find(linkQuotation).click();
				find(inputContact).clear();
				find(inputContact).sendKeys(Contact);
				sleep(4000);
				find(inputSoldToParty).sendKeys(SoldToParty + "\n");
				sleep(10000);
				for (String winHandle3 : driver.getWindowHandles()) {
					if (driver.switchTo().window(winHandle3).getTitle().equals("Select Organizational Data")) {
						driver.switchTo().frame("WorkAreaFrame1popup");
						find(eleOrganizationalUnit).click();
						sleep(5000);
						for (String winHandle4 : driver.getWindowHandles()) {
							if (driver.switchTo().window(winHandle4).getTitle()
									.contains("Partner Selection Document header")) {
								System.out.println("Henlo");
								sleep(2000);
								driver.switchTo().frame("WorkAreaFrame1popup");
								By elePartnerSelection = By.xpath("//span[contains(text(),'" + SoldToParty + "')]");
								find(elePartnerSelection).click();
							}
						}
					}
				}
				sleep(6000);
				for (String winHandle2 : driver.getWindowHandles()) {
					if (driver.switchTo().window(winHandle2).getTitle().equals("Quote: New - [SAP]")) {
						System.out.println("Back Out");
						String winHandleSAPQuote = driver.getWindowHandle();
						driver.switchTo().frame("CRMApplicationFrame");
						driver.switchTo().frame("WorkAreaFrame1");
						find(inputEndCustomer).sendKeys(EndCustomer + "\n");
						sleep(10000);
						find(inputQuoteDesc).sendKeys(QuoteDesc);
						sleep(10000);
						find(dropDownMainBU).click();
						By eleCustomerCareServiceParts;
						if (MainBU.contains(" ")) {
							String MainBUArr[] = MainBU.split(" ");
							System.out.println(MainBUArr[0] + MainBUArr[1]);
							eleCustomerCareServiceParts = By.xpath("//a[contains(text(),'" + MainBUArr[0]
									+ "') and contains(text(),'" + MainBUArr[1] + "')]");
						} else {
							eleCustomerCareServiceParts = By.xpath("//a[contains(text(),'" + MainBU + "')]");
						}

						find(eleCustomerCareServiceParts).click();
						sleep(5000);
						for (String winHandle4 : driver.getWindowHandles()) {
							if (driver.switchTo().window(winHandle4).getTitle().equals("Employee Responsible")) {
								driver.switchTo().frame("WorkAreaFrame1popup");
								By eleEmployeeName = By.xpath("//span[contains(text(),'HR20003157')]");
								find(eleEmployeeName).click();
							}
						}
						driver.switchTo().window(winHandleSAPQuote);
						driver.switchTo().frame("CRMApplicationFrame");
						driver.switchTo().frame("WorkAreaFrame1");
						sleep(7000);
						find(dropDownIndustry).click();
						By eleAirports;
						if (Industry.contains(" ")) {
							String IndustryArr[] = Industry.split(" ");
							System.out.println(IndustryArr[0] + IndustryArr[1]);
							eleAirports = By.xpath("//a[contains(text(),'" + IndustryArr[0] + "') and contains(text(),'"
									+ IndustryArr[1] + "')]");
						} else {
							eleAirports = By.xpath("//a[contains(text(),'" + Industry + "')]");
						}
						find(eleAirports).click();
						sleep(6000);
						find(inputType).sendKeys(Type);
						sleep(4000);
						find(dropdownUserStatus).click();
						find(eleUserStatusReview).click();
						sleep(8000);
						find(dropDownProbability).click();
						WebElement eleProbabilityNumber = driver
								.findElement(By.xpath("//a[contains(text(),'" + Probability + "')]"));
						eleProbabilityNumber.click();
						sleep(7000);
						String Date = getCurrentDate("MM/dd/yyyy");
						find(inputCloseDate).sendKeys(Date);
						sleep(2000);
						find(inputDelDate).sendKeys(Date);
						sleep(2000);

						for (int i = 1; i < ProductIDArray.length + 1; i++) {
							By inputProductID = By.xpath("(//fieldset//input[contains(@id,'prod')])[" + i + "]");
							find(inputProductID).sendKeys(ProductIDArray[i - 1]);
							By inputQuantity = By.xpath(
									"((//fieldset//input[contains(@id,'prod')])[" + i + "]/following::span//input)[2]");
							find(inputQuantity).sendKeys(QuantityArray[i - 1]);
						}
						sleep(5000);
						find(btnSaveSAP).click();
						sleep(20000);
						quoteId = find(eleQuoteNumberValue).getText();
						System.out.println(quoteId);
						addPassLog("The Quote ID is: " + quoteId, "takeScreenshot");
						if (isPresentAndDisplayed(find(eleSAPSuccess)) == true)
							addPassLog("SAP Quote creation successful", "takeScreenshot");
						else
							addFailLog("SAP Quote generated with errors");
						find(eleEditRAQuote).click();
						sleep(8000);
						for (int i = 0; i < ProductIDArray.length; i++) {
							By eleEditLineItem = By
									.xpath("(//table//tr//a//img[contains(@id,'EDIT')])[" + (i + 1) + "]");
							find(eleEditLineItem).click();
							sleep(7000);
							for (String winHandle4 : driver.getWindowHandles()) {
								System.out
										.println("Window Handles : " + driver.switchTo().window(winHandle4).getTitle());
								if (driver.switchTo().window(winHandle4).getTitle().contains("Quote Item")) {
									String winHandleQuestionnaires = driver.getWindowHandle();
									driver.switchTo().window(winHandleQuestionnaires);
									driver.switchTo().frame("CRMApplicationFrame");
									driver.switchTo().frame("WorkAreaFrame1");
									find(elePriceDetails).click();
									sleep(7000);

									if (!PriceElement1[i].equalsIgnoreCase("NA")) {
										enterPriceDetails(PriceElement1[i], Price1[i], Currency1[i]);
									}

									if (!PriceElement2[i].equalsIgnoreCase("NA")) {
										enterPriceDetails(PriceElement2[i], Price2[i], Currency2[i]);
									}

									sleep(2000);
									find(elePriceDetails).click();
									sleep(2000);
									if (NoItemPricing.equalsIgnoreCase("Yes")) {
										find(eleRADefinedFields).click();
										sleep(1000);
										find(checkBoxNoTemPricing).click();
										sleep(4000);
										find(eleRADefinedFields).click();
										sleep(4000);
									}
									find(eleLineItemEditBack).click();
								}
							}
						}

						sleep(5000);
						find(dropdownUserStatus).click();
						if (UserStatus.equals("Submitted for DA Review")) {
							find(eleUserStatusDAReview).click();
						} else if (UserStatus.equals("Approved")) {
							find(eleUserStatusOverallApproved).click();
						} else if (UserStatus.equals("Accepted")) {
							find(eleUserStatusOverallApproved).click();
						} else {
							System.out.println("Please give proper status value");
						}
						sleep(5000);
						find(btnSaveSAP).click();
						sleep(8000);
						driver.close();

//						find(eleEditQuestionaire).click();
//						sleep(7000);
//						for (String winHandle4 : driver.getWindowHandles()) {
//							System.out.println("Window Handles : " + driver.switchTo().window(winHandle4).getTitle());
//							if (driver.switchTo().window(winHandle4).getTitle().contains("Questionnaires")) {
//								String winHandleQuestionnaires = driver.getWindowHandle();
//								driver.switchTo().window(winHandleQuestionnaires);
//								driver.switchTo().frame("CRMApplicationFrame");
//								driver.switchTo().frame("WorkAreaFrame2");
//								find(eleShipDate).sendKeys(Date);
//								find(eleQuesSaveNBack).click();
//							}
//						}
//						sleep(8000);
//						find(eleEditListItems).click();
//						sleep(3000);
//						find(inputQuantity).sendKeys(Keys.PAGE_DOWN);
//						sleep(2000);
//						find(eleItemStatusSelect).click();
//						sleep(2000);
////						// By eleItemStatus = By.xpath("//li/a[contains(text(),'" + ItemStatus + "')]");
//						find(eleItemStatus).click();
//						sleep(4000);

//						find(eleEditRAQuote).click();
//						sleep(5000);
//						find(dropdownUserStatus).click();
//						find(eleUserStatusApproved).click();
//						find(btnSaveSAP).click();

						// sleep(900000);
					}
				}
			}
		}
		System.out.println("Quote Id : " + quoteId);
		return quoteId;

	}

	public void enterPriceDetails(String priceElement, String price, String currency) {
		find(eleInsert).click();
		sleep(5000);
		int priceDetailsListCount = driver.findElements(By.xpath("//img[contains(@alt,'Price Element')]")).size();

		By priceElementID = By.xpath("((//img[contains(@alt,'Price Element')])[" + priceDetailsListCount
				+ "]/following::td/div/table/tbody/tr/td/fieldset/input)[1]");

		find(priceElementID).sendKeys(priceElement);
		sleep(1000);

		By priceElementPrice = By.xpath("(((//img[contains(@alt,'Price Element')])[" + priceDetailsListCount
				+ "]/following::td/div/table/tbody/tr/td/fieldset/input)[1]/following::input)[2]");

		find(priceElementPrice).sendKeys(price);
		sleep(1000);

		By priceElementUnit = By.xpath("(((//img[contains(@alt,'Price Element')])[" + priceDetailsListCount
				+ "]/following::td/div/table/tbody/tr/td/fieldset/input)[1]/following::input)[3]");

		find(priceElementUnit).sendKeys(currency);
		sleep(2000);
	}

	public void goToProMS() {
		openBrowser();
		driver.get(getURL("varProMSURL"));
	}
}