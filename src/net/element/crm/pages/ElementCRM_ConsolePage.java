package net.element.crm.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import net.element.crm.components.WebDriverManager;

public final class ElementCRM_ConsolePage extends ElementCRM_BasePage {

	private final String[] ORGINFORMATIONFIELDS = new String[] {"Company Name", "Company Address" , "Company Address 2", "County", "Postcode", "Business Description", "Employee bracket ", "Notes"};
	private final String[] PRIMARYDMFIELDS = new String[] {"Company Phone Number", "Contact Type", "Title", "First Name", "Surname", "Job Title", "Record New Note For Contact", "Left Company"};
	private final String[] OUTCOMESOPTIONS = new String[] {"Appointment Generated Face to Face Meeting", "Call Back - Spoke to DM", "Not Interested", "No Call Required", "Right To Be Forgotten Requested (Air)", "CTPS Registered (Air)", "Not Qualified Against Criteria", "Wrong Number", "Do Not Call Again", "Uncontactable", "SAR Request"};

	@FindBy(how = How.XPATH, xpath = "//div[label[text()='Data List']]/*[contains(local-name(),'select')]//input")
	private WebElement sel_DataList;

	@FindBy(how = How.XPATH, xpath = "//label[text()='Mode']/following-sibling::button")
	private WebElement btn_Mode;

	@FindBy(how=How.XPATH, xpath = "//tab[@tabtitle='Call History']//table/tbody/tr")
	private WebElement tr_CallHsitory1;
	
	@FindBy(how=How.XPATH, xpath = "//tab[@tabtitle='Call History']//table/tbody/tr")
	private List<WebElement> tr_CallHsitory;
	
	private final WebElement ele_OrgInformationFields(String fieldName) throws Exception {
		return WebDriverManager.driver.findElement(By.xpath("//div[*/ul[.//a[text()='Org. Information']]]//label[text()='" + fieldName + "']"));
	}

	private final WebElement ele_PrimaryDMFields(String fieldName) throws Exception {
		return WebDriverManager.driver.findElement(By.xpath("//div[*/ul[.//a[text()='Primary DM']]]//div[not(@hidden)]/div/div/label[text()='" + fieldName + "']"));
	}

	private final WebElement ele_Tabs(String tabName) throws Exception  {
		return WebDriverManager.driver.findElement(By.xpath("//li/a[text()='" + tabName + "']"));
	}

	private final WebElement btn_OutComesTab(String buttonName) throws Exception {
		return WebDriverManager.driver.findElement(By.xpath("//tab[@tabtitle='Outcomes']//button[text()='" + buttonName + "']"));
	}

	public ElementCRM_ConsolePage() {
		PageFactory.initElements(WebDriverManager.driver, this);
	}

	public final boolean openConsolePage() {
		actualResults = false;
		try {
			if(!ele_RibbonBar("Console").findElement(By.xpath("parent::li")).getAttribute("class").contains("active"))
				obj_ElementHandler.clickOnElement(ele_RibbonBar("Console"));
			actualResults = ele_RibbonBar("Console").findElement(By.xpath("parent::li")).getAttribute("class").contains("active");
		} catch (Exception e) {
			obj_GenericComponents.exceptionHandler(e);
		}
		return actualResults;
	}

	public final boolean updateCampaignDataList(String Campaign, String DataList) {
		actualResults = false;
		try {
			obj_ElementHandler.setDropDownValue("Campaign", Campaign);
			obj_GenericComponents.SyncElement(sel_DataList, "enable");
			actualResults = obj_ElementHandler.setDropDownValue("Data List", DataList);
		} catch (Exception e) {
			obj_GenericComponents.exceptionHandler(e);
		}
		return actualResults;
	}

	public final boolean clickOnGrabLock() {
		actualResults = false;
		try {
			obj_ElementHandler.clickOnElement(btn_Generic("Grab Lock"));
			actualResults = obj_ElementHandler.getElement(btn_Generic("Locked")).isDisplayed() && btn_Generic("Locked").getCssValue("background-color").equals("rgba(33, 136, 56, 1)");
		} catch (Exception e) {
			obj_GenericComponents.exceptionHandler(e);
		}
		return actualResults;
	}

	public final boolean verifyModeUser() {
		actualResults = false;
		try {
			actualResults = obj_ElementHandler.getElement(btn_Mode).isEnabled();
		} catch (Exception e) {
			obj_GenericComponents.exceptionHandler(e);
		}
		return actualResults;
	}

	public final boolean verifyOrgInformationEnabled() {
		actualResults = false;
		staticString_PlaceHolder = "";
		try {
			for(String oif: ORGINFORMATIONFIELDS) {
				if(oif.equalsIgnoreCase("Notes"))
					actualResults = !Boolean.valueOf(obj_ElementHandler.getElement(ele_OrgInformationFields(oif)).findElement(By.xpath("following-sibling::textarea")).getAttribute("readonly"));
				else
					actualResults = !Boolean.valueOf(obj_ElementHandler.getElement(ele_OrgInformationFields(oif)).findElement(By.xpath("following-sibling::input")).getAttribute("readonly"));
				if(!actualResults)
					staticString_PlaceHolder = staticString_PlaceHolder + oif + ", "; 
			}
			if(!staticString_PlaceHolder.isEmpty())
				actualResults = false;
		} catch (Exception e) {
			obj_GenericComponents.exceptionHandler(e);
		}
		return actualResults;
	}

	public final boolean verifyPrimaryDMEnabled() {
		actualResults = false;
		staticString_PlaceHolder = "";
		try {
			for(String pdm: PRIMARYDMFIELDS) {
				if(pdm.equalsIgnoreCase("Record New Note For Contact"))
					actualResults = !Boolean.valueOf(WebDriverManager.driver.findElement(By.xpath("//div[*/ul[.//a[text()='Primary DM']]]//div[not(@hidden)]/div/div/label[starts-with(text(),'Record New Note')]")).findElement(By.xpath("following-sibling::textarea")).getAttribute("readonly"));
				else if(pdm.equalsIgnoreCase("Left Company"))
					actualResults = !Boolean.valueOf(WebDriverManager.driver.findElement(By.xpath("//div[*/ul[.//a[text()='Primary DM']]]//div[not(@hidden)]/div/div/label[starts-with(text(),'Left')]")).findElement(By.xpath("preceding-sibling::input")).getAttribute("readonly"));
				else if(pdm.equalsIgnoreCase("Contact Type"))
					actualResults = !Boolean.valueOf(obj_ElementHandler.getElement(ele_PrimaryDMFields(pdm)).findElement(By.xpath("following-sibling::div//input")).getAttribute("disabled"));
				else
					actualResults = !Boolean.valueOf(obj_ElementHandler.getElement(ele_PrimaryDMFields(pdm)).findElement(By.xpath("following-sibling::div//input")).getAttribute("readonly"));
				if(!actualResults)
					staticString_PlaceHolder = staticString_PlaceHolder + pdm + ", "; 
			}
			if(!staticString_PlaceHolder.isEmpty())
				actualResults = false;
		} catch (Exception e) {
			obj_GenericComponents.exceptionHandler(e);
		}
		return actualResults;
	}

	public final boolean verifyOutcomesEnabled() {
		actualResults = false;
		try {
			if(!ele_Tabs("Outcomes").getAttribute("class").contains("active"))
				obj_ElementHandler.clickOnElement(ele_Tabs("Outcomes"));

			for(String oco: OUTCOMESOPTIONS) {
				actualResults = obj_ElementHandler.getElement(btn_OutComesTab(oco)).isEnabled();
				if(!actualResults)
					staticString_PlaceHolder = staticString_PlaceHolder + ", " + oco;
				if(!staticString_PlaceHolder.isEmpty())
					actualResults = false;
			}
		} catch (Exception e) {
			obj_GenericComponents.exceptionHandler(e);
		}
		return actualResults;
	}

	public final boolean verifyCompanyPhoneNumberCall() {
		actualResults = false;
		try {
			System.out.println("1");
			int_PlaceHolder1 = tr_CallHsitory.size();
			System.out.println("2");
			obj_ElementHandler.clickOnElement(ele_PrimaryDMFields("Company Phone Number").findElement(By.xpath("parent::div//a")));
			
			Thread.sleep(Integer.valueOf(obj_GenericComponents.getPropertyValue("sync.timeout.SHORT")));
			new Robot().keyPress(KeyEvent.VK_ENTER);
			
			int_PlaceHolder2 = tr_CallHsitory.size();
			actualResults = int_PlaceHolder2 == int_PlaceHolder1 + 1;
		} catch (Exception e) {
			obj_GenericComponents.exceptionHandler(e);
		}
		return actualResults;
	}

}
