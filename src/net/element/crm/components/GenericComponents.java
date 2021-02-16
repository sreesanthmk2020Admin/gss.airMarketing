package net.element.crm.components;

import java.io.FileInputStream;
import java.util.Properties;

public final class GenericComponents {
	
	private Properties obj_Properties = new Properties();

	private String getPropertyValue = null;
	
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
}
