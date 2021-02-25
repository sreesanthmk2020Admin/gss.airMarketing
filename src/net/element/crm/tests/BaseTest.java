package net.element.crm.tests;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import net.element.crm.components.GenericComponents;
import net.element.crm.components.WebDriverManager;
import net.element.crm.pages.ElementCRM_ConsolePage;
import net.element.crm.pages.ElementCRM_HomePage;
import net.element.crm.pages.ElementCRM_LoginPage;

public abstract class BaseTest {
	
	private ExtentTest obj_htmlTestReport = null;
	
	private final WebDriverManager obj_WebDriverManager = new WebDriverManager();
/*	final GenericComponents obj_GenericComponents = new GenericComponents();
	final ElementCRM_LoginPage obj_ElementCRM_LoginPage = new  ElementCRM_LoginPage();
	final ElementCRM_HomePage obj_ElementCRM_HomePage = new ElementCRM_HomePage();
	final ElementCRM_ConsolePage obj_ElementCRM_ConsolePage = new ElementCRM_ConsolePage();*/

	/*WebDriverManager obj_WebDriverManager;*/
	private GenericComponents obj_GenericComponents;
	protected ElementCRM_LoginPage obj_ElementCRM_LoginPage;
	protected ElementCRM_HomePage obj_ElementCRM_HomePage;
	protected ElementCRM_ConsolePage obj_ElementCRM_ConsolePage;
	
	@BeforeSuite(alwaysRun=true)
	public final void suiteSetup(ITestContext testContext) {
		/*obj_WebDriverManager = new WebDriverManager();*/
	
		obj_GenericComponents = new GenericComponents();
		obj_GenericComponents.initTestReport();
	}
	
	@BeforeTest
	public final void testSetup(ITestContext obj_ITestContext) {
		obj_WebDriverManager.initChrome();
		obj_WebDriverManager.launchurl();
		
		
		obj_ElementCRM_LoginPage = new  ElementCRM_LoginPage();
		obj_ElementCRM_HomePage = new ElementCRM_HomePage();
		obj_ElementCRM_ConsolePage = new ElementCRM_ConsolePage();
		
		obj_htmlTestReport = obj_GenericComponents.startTestReport(obj_ITestContext.getName());
	}
	
	@BeforeClass
	public final void classSetup() {
		
	}
	
	@BeforeMethod
	public final void methodSetup() {
		
	}
	
	@AfterMethod
	public final void methodTearDown(ITestResult result) throws IOException {
		obj_GenericComponents.takeScreenshot();
		if(result.getStatus() == ITestResult.SUCCESS)
			obj_htmlTestReport.log(Status.PASS, "");
		else if(result.getStatus() == ITestResult.FAILURE)
			obj_htmlTestReport.log(Status.FAIL, "");
		obj_htmlTestReport.addScreenCaptureFromPath("test123.jpg");
	}
	
	@AfterClass
	public final void classTearDown() {
		
	}
	
	@AfterTest
	public final void testTearDown() {
		WebDriverManager.driver.quit();
		obj_GenericComponents.endTestReport();
	}
	
	@AfterSuite
	public final void suiteTearDown() {
		WebDriverManager.driver.quit();
		obj_GenericComponents.tearDownTestReport();
	}

}
