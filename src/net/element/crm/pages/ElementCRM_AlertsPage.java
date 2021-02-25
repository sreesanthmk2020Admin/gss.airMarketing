package net.element.crm.pages;

import org.openqa.selenium.support.PageFactory;

import net.element.crm.components.WebDriverManager;

public final class ElementCRM_AlertsPage extends ElementCRM_BasePage {

	public ElementCRM_AlertsPage() {
		PageFactory.initElements(WebDriverManager.driver, this);
	}
	
}
