package net.element.crm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.element.crm.components.ElementHandler;
import net.element.crm.components.GenericComponents;
import net.element.crm.components.WebDriverManager;

public abstract class ElementCRM_BasePage {
	
	protected boolean actualResults = false;
	public static String staticString_PlaceHolder = "";
	public int int_PlaceHolder1 = 0;
	public int int_PlaceHolder2 = 0;
	
	
	public final GenericComponents obj_GenericComponents = new GenericComponents();
	public final ElementHandler obj_ElementHandler = new ElementHandler();

	protected final WebElement ele_RibbonBar(String elementText) throws Exception {
		return WebDriverManager.driver.findElement(By.xpath("//a[.//span[text()='" + elementText +"']]"));
	}
	
	protected final WebElement sel_Generic(String selectName) throws Exception {
		return WebDriverManager.driver.findElement(By.xpath("//label[text()='" + selectName + "']/following-sibling::*//input"));
	}
	
	protected final WebElement btn_Generic(String buttonName) throws Exception {
		return WebDriverManager.driver.findElement(By.xpath("//button[span[text()='" + buttonName + "']]"));
	}
	
}
