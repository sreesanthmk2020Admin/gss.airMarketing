package net.element.crm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import net.element.crm.components.WebDriverManager;

public final class ElementCRM_LoginPage extends ElementCRM_BasePage {
	
	private boolean actualResults = false;
	
	@FindBy(how = How.XPATH, xpath="//form[.//input[@id='username'] and .//input[@id='password']]")
	private WebElement ele_LoginForm;
	
	@FindBy(how = How.LINK_TEXT, linkText = "here")
	private WebElement lnk_Here;

	@FindBy(how = How.ID, id = "username")
	private WebElement txt_UserName;
	
	@FindBy(how = How.ID, id = "password")
	private WebElement txt_Password;
	
	@FindBy(how = How.XPATH, xpath = "//button[text()='Sign in']")
	private WebElement btn_SignIn;
	
	public ElementCRM_LoginPage() {
		PageFactory.initElements(WebDriverManager.driver, this);
	}
	
	public final boolean signIn() {
		try {
			obj_ElementHandler.clickOnElement(lnk_Here);
			
			obj_ElementHandler.setElementText(txt_UserName, obj_GenericComponents.getPropertyValue("SIT.Username.Admin"));
			obj_ElementHandler.setElementText(txt_Password, obj_GenericComponents.getPropertyValue("SIT.Password.Admin"));
			actualResults = obj_ElementHandler.clickOnElement(btn_SignIn);
		} catch (Exception e) {
			obj_GenericComponents.exceptionHandler(e);
		}
		return actualResults;
	}

}
