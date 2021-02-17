package net.element.crm.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import net.element.crm.components.GenericComponents;
import net.element.crm.components.WebDriverManager;

public abstract class BaseTest {

	WebDriverManager obj_WebDriverManager = null;
	GenericComponents obj_GenericComponents = null;
	
	@BeforeSuite
	public final void suiteSetup() {
		obj_WebDriverManager = new WebDriverManager();
		obj_GenericComponents = new GenericComponents();
	}
	
	@BeforeTest
	public final void testSetup() {
//		obj_WebDriverManager.initChrome();
//		obj_WebDriverManager.launchurl();
		System.out.println(obj_GenericComponents.getPropertyValue("sync.timeout.SHORT"));
		obj_GenericComponents.getCellValue("testRunner");
	}
	
	@BeforeClass
	public final void classSetup() {
		
	}
	
	@BeforeMethod
	public final void methodSetup() {
		
	}
	
	@AfterMethod
	public final void methodTearDown() {
		
	}
	
	@AfterClass
	public final void classTearDown() {
		
	}
	
	@AfterTest
	public final void testTearDown() {
		WebDriverManager.driver.quit();
	}
	
	@AfterSuite
	public final void suiteTearDown() {
		WebDriverManager.driver.quit();
	}

}
