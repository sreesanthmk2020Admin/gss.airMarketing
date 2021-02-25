package net.element.crm.pages;

import org.openqa.selenium.support.PageFactory;

import net.element.crm.components.WebDriverManager;

public final class ElementCRM_PipelinePage  extends ElementCRM_BasePage {

	public ElementCRM_PipelinePage() {
		PageFactory.initElements(WebDriverManager.driver, this);
	}
	
}
