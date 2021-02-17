package net.element.crm.components;

import org.openqa.selenium.WebElement;

public final class ElementHandler {
	
	GenericComponents obj_GenericComponents = new GenericComponents();
	
	public boolean clickOnElement(WebElement element) throws Exception {
		obj_GenericComponents.SyncElement(element);
		element.click();
		return true;
	}
	
	public boolean setElementText(WebElement element, String value) throws Exception {
		obj_GenericComponents.SyncElement(element);
		element.sendKeys(value);
		return element.getAttribute("ng-reflect-model").equals(value);
	}
	
}
