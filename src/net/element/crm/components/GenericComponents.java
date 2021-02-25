package net.element.crm.components;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public final class GenericComponents {
	
	private String getPropertyValue = null;
	private String[][] getCellValue = null;
	
	private ExtentHtmlReporter obj_ExtentHtmlReporter = null;
	private static ExtentReports obj_ExtentReports = null;
	private Properties obj_Properties = new Properties();
	
	public final void exceptionHandler(Exception e) {
		e.printStackTrace();
		e.getLocalizedMessage();
	}
	
	public final void takeScreenshot() {
		try {
			File screenshotFile = ((TakesScreenshot) WebDriverManager.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File("test123.jpg"));
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
		WebDriverWait obj_WebDriverWait = new WebDriverWait(WebDriverManager.driver, Long.valueOf(getPropertyValue("sync.timeout.VERYLONG")));
		if (waitFor.equals("visibility"))
			obj_WebDriverWait.until(ExpectedConditions.visibilityOf(element));
		else if (waitFor.equals("enable"))
			obj_WebDriverWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public final void initTestReport() {
		try {
			obj_ExtentHtmlReporter = new ExtentHtmlReporter(obj_Properties.getProperty("report.Location") + obj_Properties.getProperty("report.FileName") + DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss").format(LocalDateTime.now()) + ".html");
			obj_ExtentReports = new ExtentReports();
			obj_ExtentReports.attachReporter(obj_ExtentHtmlReporter);
		} catch (Exception e) {
			exceptionHandler(e);
		}
	}
	
	public final ExtentTest startTestReport(String testName) {
		return obj_ExtentReports.createTest(testName);
	}
	
	public final void endTestReport() {
		obj_ExtentReports.flush();
	}
	
	public final void tearDownTestReport() {
		obj_ExtentHtmlReporter.flush();
	}
	
}
