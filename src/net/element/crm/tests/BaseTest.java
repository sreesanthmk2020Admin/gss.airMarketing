package net.element.crm.tests;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
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
import net.element.crm.pages.ElementCRM_ConsolePage;
import net.element.crm.pages.ElementCRM_HomePage;
import net.element.crm.pages.ElementCRM_LoginPage;

public abstract class BaseTest {
	
	protected String testResultPlaceHolder = null;
	private static Map<String, Map<String, String>> testCaseData = null;
	protected Map<String, String> testStepData = null;
	private static Map<String, String> testRunner = null;
		
	private final WebDriverManager obj_WebDriverManager = new WebDriverManager();
	private static GenericComponents obj_GenericComponents;
	protected ElementCRM_LoginPage obj_ElementCRM_LoginPage;
	protected ElementCRM_HomePage obj_ElementCRM_HomePage;
	protected ElementCRM_ConsolePage obj_ElementCRM_ConsolePage;
	
	@BeforeSuite(alwaysRun=true)
	public final void suiteSetup() {
		obj_GenericComponents = new GenericComponents();
		testRunner = obj_GenericComponents.testRunner();
		testCaseData = obj_GenericComponents.testData();
		obj_GenericComponents.initTestReport();
		GenericComponents.dateTime = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss").format(LocalDateTime.now());
	}
	
	@BeforeTest
	public final void testSetup(ITestContext obj_ITestContext) {
		if(testRunner.get(obj_ITestContext.getName()).equalsIgnoreCase("NO"))
			throw new SkipException(obj_ITestContext.getName() + " test case is set to No Run.");
		
		testStepData = testCaseData.get(obj_ITestContext.getName());
		obj_WebDriverManager.initChrome();
		obj_WebDriverManager.launchurl();
		
		obj_ElementCRM_LoginPage = new  ElementCRM_LoginPage();
		obj_ElementCRM_HomePage = new ElementCRM_HomePage();
		obj_ElementCRM_ConsolePage = new ElementCRM_ConsolePage();
		
		obj_GenericComponents.startTestReport(obj_ITestContext.getName());
	}
	
	@BeforeClass
	public final void classSetup() {
		
	}
	
	@BeforeMethod
	public final void methodSetup() {
		
	}
	
	@AfterMethod
	public final void methodTearDown(ITestResult testResult) throws IOException {
		obj_GenericComponents.reportLogging(testResult, testResultPlaceHolder);
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
