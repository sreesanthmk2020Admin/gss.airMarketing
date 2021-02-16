package net.element.crm.components;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class WebDriverManager {

	public static WebDriver driver = null;
	
	public final void initChrome() {
		try {
			System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");  
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public final void launchurl() {
		driver.manage().deleteAllCookies();
		driver.get("https://elementuk-test.azurewebsites.net");
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
	}
}
