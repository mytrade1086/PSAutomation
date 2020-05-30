package scripts.DCRM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import utilities.ExtentReportHtml;

public class DCRM_PageObject extends ExtentReportHtml {

	Actions actions;

	By createOpportunity = By.xpath("//button[@data-id='quickCreateLauncher']");
	By signInRockwell = By.xpath("//input[@type='email']");
	By next = By.xpath("//input[@type='submit']");
	By continueButton = By.xpath("//*[@id='cancelButton']");
	By opportunity = By.xpath("(//span[contains(text(),'Opportunity')])[2]");
	By opportunityName = By.xpath("//input[@aria-label='Opportunity Name']");
	By account = By.xpath("//input[@aria-label='Account, Lookup']");
	By selectAccdropDown = By.xpath("//ul[@aria-label='Lookup Search Results']/li");
	By eleChannelToMarket = By.xpath("//select[@aria-label='Channel to Market']");
	By dropdownIPPInvolvement = By.xpath("//select[@aria-label='IPP Involvement']");
	By eleDemandSource = By.xpath("//select[@aria-label='Demand Source']");
	By specificPosition = By.xpath("//select[@aria-label='Specification Position']");
	By eleFundingResult = By.xpath("//select[@aria-label='Funding Result']");
	By revenueType = By.xpath("//select[@aria-label='Revenue Type']");
	By inputEstCloseDate = By.xpath("//input[@aria-label='Est. Close Date']");
	By inputBudgetAmount = By.xpath("//input[@aria-label='Budget Amount']");
	By saveAndClose = By.xpath("//div[contains(text(),'Save and Close')]");
	By saveNClose = By.xpath("//button[@aria-label='Save & Close']");
	By viewRecord = By.xpath("//button[@id='action1_link']");
	By lineItemQuickCreate = By.xpath("//span[@aria-label='Line Item Quick Create']");
	By createProposalRequest = By.xpath("//span[@aria-label='Create Proposal Request']");
	By save = By.xpath("//span[@aria-label='Save']");
	By lineItemAmount = By.xpath("(//td/label[contains(text(),'Automation Software')]/following::td/input)[5]");
	By lineItemSaveAndClose = By.xpath("//span[@id='SaveClose']");
	By desc = By.xpath("//textarea[@data-id='ra_briefdescofcustprojectandrascopeofwork.fieldControl-text-box-text']");
	By eleProposalType = By.xpath("//select[@aria-label='Proposal Type']");
	By proposalRequestDetails = By.xpath("//li[@aria-label='Proposal Request Details']");
	By proposalLineItem = By.xpath("//li[@aria-label='Proposal Line Item']");
	By initailProposalDueDate = By.xpath("//input[@aria-label='Initial Proposal Due Date']");
	By inputTermsAndConditions = By.xpath("//select[@aria-label='Terms and Conditions (T&C)']");
	By raItem = By.xpath("//input[@aria-label='BU Lookup / RA Item, Lookup']");

	By proposalLineItemSelectEle = By.xpath("//a[@title='LV Motor Control Centers']");
	By enteredOffering = By.xpath("//div[contains(text(),'Power & Energy')]");
	By clickSearch = By.xpath("(//div[contains(text(),'Power & Energy')]/following::button)[1]");
	By deleteOppIndustry = By.xpath(
			"//button[@data-id='ra_opportunityindustryid.fieldControl-LookupResultsDropdown_ra_opportunityindustryid_selected_tag_delete']");
	By eleOppIndustry = By.xpath("//input[@aria-label='Opportunity Industry, Lookup']");

	By sapOffering = By.xpath("//input[@aria-label='SAP Offering, Lookup']");
	By capability = By.xpath("//input[@aria-label='Capability  Lookup']");
	By subOffering = By.xpath("//input[@aria-label='Sub-Offering  Lookup']");

	By newProposalLineItem = By.xpath("//span[contains(text(),'New Proposal Line Item')]");
	By amount = By.xpath("//input[@aria-label='Amount']");
	By selectDropDown = By.xpath("//ul[@aria-label='Lookup Search Results']/li");
	By productBU = By.xpath("//input[@aria-label='Product BU  Lookup']");
	By productBUSelect = By.xpath("//ul[@aria-label='Lookup Search Results']/li");
	By ProposalLineItemSaveAndClose = By.xpath("//button[@aria-label='Save & Close']");
	By submitProposalRequest = By.xpath("//span[@aria-label='Submit proposal Request']");
	By ok = By.xpath("//button[@aria-label='OK']");
	By proposalNumber = By.xpath("//div[contains(text(),'ProMS Proposal ID')]/preceding-sibling::div/div");
	By search = By.xpath("//button[@aria-label='Search']");
	By searchBox = By.xpath("//input[@aria-label='Search box']");
	By search2 = By.xpath("(//button[@aria-label='Search'])[2]");
	By eleQuotedPriceInDCRM = By.xpath("//label[contains(text(),'$')]");

	public void QuickCreateAndAddLineItems(String oppName, String accountName, String estCloseDate, String specPosition,
			String fundingResult, String oppIndustry, String demandSource, String channelToMarket, String businessUnits,
			String[] rAItems, String[] lineItemAmount, String[] businessUnitArray, String proposalType,
			String initialProposalDate, String budgetAmount, String termsAndConditions)

			throws InterruptedException {

		driver.get(getURL("varDCRMURL"));
		find(signInRockwell).sendKeys("ZUSER1720@rockwellautomation.com");
		find(next).click();
		addFailLog("Doom");
		Thread.sleep(15000);
		find(continueButton).click();
		find(createOpportunity).click();
		find(opportunity).click();
		Thread.sleep(2000);
		find(opportunityName).sendKeys(oppName);
		find(account).click();
		Thread.sleep(800);
		find(account).sendKeys(accountName);
		find(selectAccdropDown).click();
		selectOption(find(eleChannelToMarket), "VisibleText", channelToMarket, 0, "");
		selectOption(find(eleDemandSource), "VisibleText", demandSource, 0, "");
		find(inputEstCloseDate).sendKeys(estCloseDate);
		selectOption(find(specificPosition), "VisibleText", specPosition, 0, "");
		selectOption(find(eleFundingResult), "VisibleText", fundingResult, 0, "");
//		find(eleOppIndustry).click();
//		find(eleOppIndustry).sendKeys(oppIndustry);
//		find(selectAccdropDown).click();
		find(saveAndClose).click();
		if (find(viewRecord).isDisplayed()) {
			find(viewRecord).click();
		} else {
			addFailLog("Quick Create Failed");
		}

		String parentWinHandle = driver.getWindowHandle();
		find(lineItemQuickCreate).click();
		Set<String> winHandles = driver.getWindowHandles();

		for (String handle : winHandles) {
			if (!handle.equals(parentWinHandle)) {
				driver.switchTo().window(handle);
				Thread.sleep(1000);

				for (int i = 0; i < businessUnitArray.length; i++) {

					WebElement lineItemBU = driver.findElement(By.xpath(
							"(//td/label[contains(text(),'" + businessUnitArray[i] + "')]/following::td/input)[1]"));
					lineItemBU.sendKeys(lineItemAmount[i]);
				}
				find(lineItemSaveAndClose).click();
			}
		}
		driver.switchTo().window(parentWinHandle);
		Thread.sleep(2000);
		for (int i = 0; i < businessUnitArray.length; i++) {
			WebElement elementLocator = driver.findElement(By.xpath("//div[@data-id='cell-" + i + "-2']"));
			actions = new Actions(driver);
			if (elementLocator.isDisplayed()) {
				actions.doubleClick(elementLocator).perform();
				Thread.sleep(7000);
			} else {
				addFailLog(businessUnitArray + " LineItem creation failed");
			}
			find(raItem).click();
			find(raItem).clear();
			find(raItem).sendKeys(rAItems[i]);
			Thread.sleep(2000);
			find(selectDropDown).click();
			find(saveNClose).click();
			Thread.sleep(7000);

		}
		find(createProposalRequest).click();
		Thread.sleep(20000);
		find(desc).click();
		Thread.sleep(1000);
		find(desc).clear();
		Thread.sleep(1000);
		find(desc).sendKeys("Brief Description");
		Thread.sleep(2000);
		jsClick(find(save));
		addPassLog("wow ", "takeScreenshot");
		Thread.sleep(4500);
		find(proposalRequestDetails).click();
		selectOption(find(dropdownIPPInvolvement), "VisibleText", "Involved", 0, "");
		selectOption(find(eleProposalType), "VisibleText", proposalType, 0, "");
		find(initailProposalDueDate).sendKeys(getCurrentDate(initialProposalDate));
		find(inputBudgetAmount).sendKeys(budgetAmount);
		find(inputBudgetAmount).sendKeys(Keys.PAGE_DOWN);
		selectOption(find(inputTermsAndConditions), "VisibleText", termsAndConditions, 0, "");
		find(save).click();
		Thread.sleep(5000);
	}

	public void QuickCreateAndAddLineItems(String AccountName, String BusinessUnits, String[] RAItems,
			String CustomerIndustry, String quoteType, String LineItemAmount, String[] BusinessUnitArray)
			throws InterruptedException {

		driver.get(getURL("varDCRMURL"));
		find(signInRockwell).sendKeys("sshetty9@rockwellautomation.com");
		find(next).click();
		waitForElementPresent(continueButton);
		find(continueButton).click();
		find(createOpportunity).click();
		find(opportunity).click();
		Thread.sleep(2000);
		find(opportunityName).sendKeys(randomString());
		find(account).click();
		Thread.sleep(1000);
		find(account).sendKeys(AccountName);
		find(selectAccdropDown).click();
		selectOption(find(eleChannelToMarket), "VisibleText", "Distributor", 0, "");
		selectOption(find(eleDemandSource), "VisibleText", "Sales Generated", 0, "");
		find(inputEstCloseDate).sendKeys(getCurrentDate("MM/dd/yyyy"));
		selectOption(find(specificPosition), "VisibleText", "RA Preferred or No Equal", 0, "");
		selectOption(find(eleFundingResult), "VisibleText", "Funded (F)", 0, "");
		find(saveAndClose).click();
		if (find(viewRecord).isDisplayed()) {
			find(viewRecord).click();
		} else {
			addFailLog("Quick Create Failed");
		}

		String parentWinHandle = driver.getWindowHandle();
		find(lineItemQuickCreate).click();
		Set<String> winHandles = driver.getWindowHandles();

		for (String handle : winHandles) {
			if (!handle.equals(parentWinHandle)) {
				driver.switchTo().window(handle);
				Thread.sleep(1000);

				for (int i = 0; i < BusinessUnitArray.length; i++) {

					WebElement lineItemBU = driver.findElement(By.xpath(
							"(//td/label[contains(text(),'" + BusinessUnitArray[i] + "')]/following::td/input)[1]"));
					lineItemBU.sendKeys(LineItemAmount);
				}
				find(lineItemSaveAndClose).click();
			}
		}
		driver.switchTo().window(parentWinHandle);
		Thread.sleep(2000);
		for (int i = 0; i < BusinessUnitArray.length; i++) {
			WebElement elementLocator = driver.findElement(By.xpath("//div[@data-id='cell-" + i + "-2']"));
			actions = new Actions(driver);
			if (elementLocator.isDisplayed()) {
				actions.doubleClick(elementLocator).perform();
				waitForElementPresent(raItem);
			} else {
				addFailLog(BusinessUnitArray + " LineItem creation failed");
			}
			find(raItem).click();
			find(raItem).clear();
			find(raItem).sendKeys(RAItems[i]);
			Thread.sleep(2000);
			find(selectDropDown).click();
			find(saveNClose).click();
			waitForElementPresent(createProposalRequest);

		}
		find(createProposalRequest).click();
		Thread.sleep(20000);
		find(desc).click();
		Thread.sleep(1000);
		find(desc).clear();
		Thread.sleep(1000);
		find(desc).sendKeys("Brief Description");
		actions.moveToElement(find(enteredOffering)).perform();
		Thread.sleep(2000);
		find(clickSearch).click();
		Thread.sleep(1000);
		// find(deleteOppIndustry).click();
		find(eleOppIndustry).sendKeys(CustomerIndustry);
		Thread.sleep(2000);
		find(selectDropDown).click();
		Thread.sleep(2000);
		jsClick(find(save));
		waitForElementPresent(proposalRequestDetails);
		find(proposalRequestDetails).click();
		selectOption(find(dropdownIPPInvolvement), "VisibleText", "Involved", 0, "");
		selectOption(find(eleProposalType), "VisibleText", quoteType, 0, "");
		find(initailProposalDueDate).sendKeys(getCurrentDate("MM/dd/yyyy"));
		find(inputBudgetAmount).sendKeys("50000");
		find(inputBudgetAmount).sendKeys(Keys.PAGE_DOWN);
		selectOption(find(inputTermsAndConditions), "VisibleText", "Standard", 0, "");
		find(save).click();
		Thread.sleep(5000);
	}

	public void addOffering(String BusinessUnit, String Capability, String SubOffering, String[] SAPOffering,
			String[] BusinessUnitArray) throws InterruptedException {

		find(proposalLineItem).click();
		Thread.sleep(3000);

		for (int i = 0; i < BusinessUnitArray.length; i++) {
			actions = new Actions(driver);
			WebElement elementLocator = driver
					.findElement(By.xpath("(//a[contains(text(),'" + BusinessUnitArray[i] + "')]/following::div)[3]"));
			actions.doubleClick(elementLocator).perform();
			if (BusinessUnitArray[i].equals("System & Solutions Business")) {

				find(sapOffering).click();
				find(sapOffering).sendKeys(SAPOffering[i]);
				Thread.sleep(2000);
				find(selectDropDown).click();
				Thread.sleep(2000);
				find(capability).click();
				find(capability).sendKeys(Capability);
				find(selectDropDown).click();
				find(subOffering).click();
				find(subOffering).sendKeys(SubOffering);
				find(selectDropDown).click();
			} else {
				find(sapOffering).click();
				find(sapOffering).sendKeys(SAPOffering[i]);
				find(selectDropDown).click();
			}
			find(ProposalLineItemSaveAndClose).click();
			Thread.sleep(7000);
			find(proposalLineItem).click();
		}

	}

	public String submitProposalRequest() throws InterruptedException {

		if (find(submitProposalRequest).isDisplayed()) {
			find(submitProposalRequest).click();
		} else {
			addFailLog("Creating Proposal request failed");
		}
		find(ok).click();
		Thread.sleep(7000);
		String ProposalNumber = find(proposalNumber).getText();
		System.out.println("Proposal Number: " + ProposalNumber);
		if (ProposalNumber != "---") {
			addPassLog("Opportunity is successfully created " + ProposalNumber, "takeScreenshot");
		} else {
			addFailLog("Opportunity was not created successfully");
		}
		return ProposalNumber;

	}

	public void verifyProposalChangesInDCRM(String PropNo, String QuotedPrice) throws InterruptedException {

		driver.get(getURL("varDCRMURL"));
		find(signInRockwell).sendKeys("sshetty9@rockwellautomation.com");
		find(next).click();
		Thread.sleep(15000);
		find(continueButton).click();
		find(search).click();
		find(searchBox).sendKeys(PropNo);
		find(search2).click();
		WebElement propFound = driver.findElement(By.xpath("//label[contains(text(),PropNo)]"));
		propFound.click();
		Thread.sleep(8000);
		find(proposalLineItem).click();
		Thread.sleep(4000);
		QuotedPrice = QuotedPrice.replace("\\.", "");
		String quotedPriceInDCRM = find(eleQuotedPriceInDCRM).getText();
		quotedPriceInDCRM = quotedPriceInDCRM.replace("$", "");
		quotedPriceInDCRM = quotedPriceInDCRM.replace(",", "");
		if (quotedPriceInDCRM.equals(QuotedPrice + "0")) {
			addPassLog("The amount in ProMS matches with amount in DCRM", "takeScreenshot");
		}

	}

}