package net.element.crm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(testName = "")
public final class POC_TC01_GrabLockIssue extends BaseTest {

	@Test
	private final void step01() {
		Assert.assertTrue(obj_ElementCRM_LoginPage.signIn());
	} 
	
	@Test
	private final void step02() {
		Assert.assertTrue(obj_ElementCRM_HomePage.verifyHomePage());
	}
	
	@Test
	private final void step03() {
		Assert.assertTrue(obj_ElementCRM_ConsolePage.openConsolePage());
	}
	
	@Test
	private final void step04() {
		obj_ElementCRM_ConsolePage.verifyModeUser();
		obj_ElementCRM_ConsolePage.updateCampaignDataList();
		obj_ElementCRM_ConsolePage.clickOnGrabLock();
		obj_ElementCRM_ConsolePage.verifyOrgInformationEnabled();
		obj_ElementCRM_ConsolePage.verifyPrimaryDMEnabled();
		obj_ElementCRM_ConsolePage.verifyCompanyPhoneNumberCall();
		obj_ElementCRM_ConsolePage.verifyOutcomesEnabled();
	}
	
	@Test
	private final void step05() {
		
	}
	
	@Test
	private final void step06() {
		obj_ElementCRM_HomePage.logout();
	}
	
}
