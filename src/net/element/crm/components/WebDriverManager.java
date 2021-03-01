package net.element.crm.components;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class WebDriverManager {

	public static WebDriver driver = null;
	
	public final void initChrome() {
		try {
			/*chrome_options = webdriver.ChromeOptions()
			chrome_options.add_argument('--disable-notifications')
			prefs = {'profile.default_content_setting_values.notifications': 2}
			chrome_options.add_experimental_option('prefs', prefs)
			driver = webdriver.Chrome(chrome_options=chrome_options, executable_path=r'C:\Users\jeffg\Desktop\WebScraping\chromedriver.exe'
			
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches",
			Arrays.asList("disable-popup-blocking"));
			
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", "/directory/path");
			options.setExperimentalOption("prefs", prefs);*/
			
			//https://chromedriver.chromium.org/capabilities
				
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
