package net.element.crm.components;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class GenericComponents {
	
	private String getPropertyValue = null;
	private String[][] getCellValue = null;
	
	private Properties obj_Properties = new Properties();
	
	public final void exceptionHandler(Exception e) {
		e.printStackTrace();
		e.getLocalizedMessage();
	}
	
	public void takeScreenshot() {
		
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

	public final void SyncElement(WebElement element) throws Exception {
		WebDriverWait obj_WebDriverWait = new WebDriverWait(WebDriverManager.driver, Long.valueOf(getPropertyValue("sync.timeout.VERYLONG")));
		obj_WebDriverWait.until(ExpectedConditions.visibilityOf(element));
	}
	
}
