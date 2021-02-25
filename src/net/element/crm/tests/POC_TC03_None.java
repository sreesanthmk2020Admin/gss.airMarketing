package net.element.crm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(testName = "POC_TC03_None")
public final class POC_TC03_None extends BaseTest {

	@Test
	private final void step01() {
		Assert.assertTrue(true);
	} 
	
	@Test
	private final void step02() {
		Assert.assertTrue(false);
	}
	
	@Test
	private final void step03() {
		Assert.assertTrue(true);
	}
	
	@Test
	private final void step04() {
		Assert.assertTrue(false);
	}
	
	@Test
	private final void step05() {
		Assert.assertTrue(true);
	}
	
	@Test
	private final void step06() {
		Assert.assertTrue(true);
	}
	
}
