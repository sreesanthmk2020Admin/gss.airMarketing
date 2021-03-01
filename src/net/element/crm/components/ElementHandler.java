package net.element.crm.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public final class ElementHandler {

	GenericComponents obj_GenericComponents = new GenericComponents();

	public final boolean clickOnElement(WebElement element) throws Exception {
		obj_GenericComponents.SyncElement(element, "visibility");
		element.click();
		return true;
	}

	public final boolean setElementText(WebElement element, String value) throws Exception {
		obj_GenericComponents.SyncElement(element, "visibility");
		element.sendKeys(value);
		element.clear();
		return element.getAttribute("ng-reflect-model").equals(value);
	}

	public final String getElementText(WebElement element) throws Exception {
		obj_GenericComponents.SyncElement(element, "visibility");
		return element.getText();
	}

	public final WebElement getElement(WebElement element) throws Exception {
		obj_GenericComponents.SyncElement(element, "visibility");
		return element;
	}

	public final boolean setDropDownValue(WebElement element, String value) throws Exception {
		obj_GenericComponents.SyncElement(element, "visibility");
		element.click();
		element.clear();
		for(char v:value.toCharArray()) {
			element.sendKeys(String.valueOf(v));
			Thread.sleep(1000);
		}
		element.findElement(By.xpath("parent::*/parent::*/parent::*/following-sibling::*[contains(local-name(),'dropdown')]//*[text()=\"" + value + "\"]")).click();;
		return element.findElement(By.xpath("parent::div/parent::div//span[text()=\"" + value + "\"]")).isDisplayed();
	}

	public final boolean setDropDownValue(String LabelName, String value) throws Exception {
		obj_GenericComponents.SyncElement(WebDriverManager.driver.findElement(By.xpath("//div[label[text()='" + LabelName + "']]/*[contains(local-name(),'select')]//input")), "visibility");
		WebDriverManager.driver.findElement(By.xpath("//div[label[text()='" + LabelName + "']]/*[contains(local-name(),'select')]//input")).click();
		WebDriverManager.driver.findElement(By.xpath("//div[label[text()='" + LabelName + "']]/*[contains(local-name(),'select')]//input")).clear();
		for(char v:value.toCharArray()) {
			WebDriverManager.driver.findElement(By.xpath("//div[label[text()='" + LabelName + "']]/*[contains(local-name(),'select')]//input")).sendKeys(String.valueOf(v));
			Thread.sleep(1000);
		}
		WebDriverManager.driver.findElement(By.xpath("//div[label[text()='" + LabelName + "']]/*[contains(local-name(),'select')]//input/parent::*/parent::*/parent::*/following-sibling::*[contains(local-name(),'dropdown')]//*[text()=\"" + value + "\"]")).click();;
		return WebDriverManager.driver.findElement(By.xpath("//div[label[text()='" + LabelName + "']]/*[contains(local-name(),'select')]//input/parent::div/parent::div//span[text()=\"" + value + "\"]")).isDisplayed();
	}

}
