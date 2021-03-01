package net.element.crm.components;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class GenericComponents {

	private String getPropertyValue = null;
	private String[][] getCellValue = null;
	private String testCaseName = null;
	private String[][] rawTestData = null;
	private Map<String, Map<String, String>> testCaseData = null;
	private Map<String, String> testStepData = null;
	private static int incrementor = 0;
	public static String dateTime = null;
	private static String screenshotLocation = null;

	private ExtentHtmlReporter obj_ExtentHtmlReporter = null;
	private static ExtentReports obj_ExtentReports = null;
	private ExtentTest obj_ExtentTest = null;
	private Properties obj_Properties = new Properties();

	public final void exceptionHandler(Exception e) {
		e.printStackTrace();
		e.getLocalizedMessage();
	}

	public final void takeScreenshot() {
		try {
			screenshotLocation = System.getProperty("user.dir") + "/" + getPropertyValue("screenshot.Location") + "/" + dateTime + "/" + testCaseName + ++incrementor +".jpg";
			File screenshotFile = ((TakesScreenshot) WebDriverManager.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(screenshotLocation));
		} catch (Exception e) {
			exceptionHandler(e);
		}
	}

	public final String getPropertyValue(String propertykey) {
		try {
			obj_Properties.load(new FileInputStream("testConfig\\config.properties"));
			getPropertyValue = obj_Properties.getProperty(propertykey);
		} catch (Exception e) {
			exceptionHandler(e);
		}
		return getPropertyValue;
	}

	public final String[][] getCellValue(String sheetName) {
		try {
			XSSFWorkbook obj_XSSFWorkbook = new XSSFWorkbook(new File("testData\\air_marketing_testData.xlsx"));
			XSSFSheet obj_XSSFSheet = obj_XSSFWorkbook.getSheet(sheetName);
			getCellValue = new String[obj_XSSFSheet.getLastRowNum()][obj_XSSFSheet.getRow(0).getLastCellNum()];

			for(int i=1; i<=obj_XSSFSheet.getLastRowNum();i++) 
				for(int j=0; j<obj_XSSFSheet.getRow(0).getLastCellNum(); j++) 
					getCellValue[i-1][j] = obj_XSSFSheet.getRow(i).getCell(j).getStringCellValue();

			obj_XSSFWorkbook.close();
		} catch (Exception e) {
			exceptionHandler(e);
		}
		return getCellValue;
	}

	public final void SyncElement(WebElement element, String waitFor) throws Exception {
		WebDriverWait obj_WebDriverWait = new WebDriverWait(WebDriverManager.driver, Integer.valueOf((getPropertyValue("sync.timeout.SHORT"))));
		if (waitFor.equals("visibility"))
			obj_WebDriverWait.until(ExpectedConditions.visibilityOf(element));
		else if (waitFor.equals("enable"))
			obj_WebDriverWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public final void initTestReport() {
		try {
			obj_ExtentHtmlReporter = new ExtentHtmlReporter("Dashboard.html");
			obj_ExtentHtmlReporter.config().setDocumentTitle(getPropertyValue("report.DocumentTitle"));
			obj_ExtentHtmlReporter.config().setReportName(getPropertyValue("report.ReportName"));
			obj_ExtentHtmlReporter.config().setTheme(Theme.DARK);
			
			//obj_ExtentHtmlReporter.setSystemAttributeContext(systemAttributeContext);

			obj_ExtentReports = new ExtentReports();
			obj_ExtentReports.setSystemInfo("Host Name", getPropertyValue("report.HostName"));
			obj_ExtentReports.setSystemInfo("Environment", getPropertyValue("report.Environment"));
			obj_ExtentReports.setSystemInfo("User Name", getPropertyValue("TEST.Username.Admin"));
			obj_ExtentReports.attachReporter(obj_ExtentHtmlReporter);
		} catch (Exception e) {
			exceptionHandler(e);
		}
	}

	public final void startTestReport(String testName) {
		obj_ExtentTest = obj_ExtentReports.createTest(testName);
		testCaseName = testName;
	}

	public final void reportLogging(ITestResult testResult, String testLog) {
		try {
			if(testResult.getStatus() == ITestResult.SUCCESS) {
				if(Boolean.valueOf(getPropertyValue("screenshot.PASS").toLowerCase())) {
					takeScreenshot();
					obj_ExtentTest.log(Status.PASS, testLog, MediaEntityBuilder.createScreenCaptureFromPath(screenshotLocation).build());
				} else
					obj_ExtentTest.log(Status.PASS, testLog);
			}
			else if(testResult.getStatus() == ITestResult.FAILURE) {
				if(Boolean.valueOf(getPropertyValue("screenshot.FAIL").toLowerCase())) {
					takeScreenshot();
					obj_ExtentTest.log(Status.FAIL, testLog, MediaEntityBuilder.createScreenCaptureFromPath(screenshotLocation).build());
				} else 
					obj_ExtentTest.log(Status.FAIL, testLog);
			}
			 //logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
		} catch (Exception e) {
			exceptionHandler(e);
		}
	}

	public final void endTestReport() {
		obj_ExtentReports.flush();
	}

	public final void tearDownTestReport() {
		try {
			obj_ExtentHtmlReporter.flush();
			
			if(!new File(getPropertyValue("report.Location")).exists())
				FileUtils.forceMkdir(new File(getPropertyValue("report.Location")));
			FileUtils.copyFile(new File("Dashboard.html"), new File(getPropertyValue("report.Location") + "/" + dateTime + ".html"));
		} catch (Exception e) {
			exceptionHandler(e);
		}
	}

	public final Map<String, Map<String, String>> testData() {
		testCaseData = new HashMap<String, Map<String,String>>();
		testStepData = new HashMap<String,String>();
		try {
			rawTestData = getCellValue("testData");
			for(int i=0; i<rawTestData.length; i++) {
				if(testCaseName!=null) 
					if(!testCaseName.equalsIgnoreCase(rawTestData[i][0])) {
						testCaseData.put(testCaseName, testStepData);	
						testStepData = new HashMap<String,String>();
					}
				testCaseName = rawTestData[i][0];
				testStepData.put(rawTestData[i][1], rawTestData[i][2]); 
			}
		} catch (Exception e) {
			exceptionHandler(e);
		}
		return testCaseData;
	}

	public final Map<String, String> testRunner() {
		testStepData = new HashMap<String,String>();
		try {
			rawTestData = getCellValue("testRunner");
			for(int i=0; i<rawTestData.length; i++)
				testStepData.put(rawTestData[i][0], rawTestData[i][1]);
		} catch (Exception e) {
			exceptionHandler(e);
		}
		return testStepData;
	}

}
