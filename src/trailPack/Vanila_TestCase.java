package trailPack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.formula.functions.T;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import net.element.crm.tests.BaseTest;

public class Vanila_TestCase extends BaseTest {

	@Test
	void foo() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println(dtf.format(now));  
		   
		/*obj_ElementCRM_LoginPage.signIn();
		obj_ElementCRM_HomePage.verifyHomePage();
		
		obj_ElementCRM_ConsolePage.openConsolePage();*/
	/*	extent = new ;
        extent.attachReporter(htmlReporter);
         
        //To add system or environment info by using the setSystemInfo method.
        extent.setSystemInfo("OS", OS);
        extent.setSystemInfo("Browser", browser);
        
        //configuration items to change the look and feel
        //add content, manage tests etc
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		
		ExtentHtmlReporter obj = new ExtentHtmlReporter("extentreport.html");
		ExtentReports e = new ExtentReports();
		e.attachReporter(obj);
		ExtentTest t = e.createTest("test");
		t.log(Status.PASS, "PASS SRee");
		t.log(Status.FAIL, "fale");
		e.flush();
		e.flush();*/
		
	}
}
