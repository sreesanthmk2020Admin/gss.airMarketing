package net.element.crm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import net.element.crm.components.WebDriverManager;

public final class ElementCRM_HomePage extends ElementCRM_BasePage {
	
	private final String HOME_PAGE_HEADER = "Welcome to Element CRM";
	
	@FindBy(how = How.TAG_NAME, tagName = "h1")
	private WebElement lbl_PageHeader;

	@FindBy(how = How.ID, id = "account-menu")
	private WebElement ele_AccountMenu;
	
	@FindBy(how = How.ID, id = "logout")
	private WebElement ele_Logout;
	
	public ElementCRM_HomePage() {
		PageFactory.initElements(WebDriverManager.driver, this);
	}
	
	public final boolean verifyHomePage() {
		actualResults = false;
		try {
			actualResults = obj_ElementHandler.getElementText(lbl_PageHeader).equalsIgnoreCase(HOME_PAGE_HEADER);
		} catch (Exception e) {
			obj_GenericComponents.exceptionHandler(e);
		}
		return actualResults;
	}
	
	public final boolean logout() {
		actualResults = false;
		try {
			obj_ElementHandler.clickOnElement(ele_AccountMenu);
			obj_ElementHandler.clickOnElement(ele_Logout);
		} catch (Exception e) {
			obj_GenericComponents.exceptionHandler(e);
		}
		return actualResults;
	}

}
