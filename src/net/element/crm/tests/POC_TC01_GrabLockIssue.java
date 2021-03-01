package net.element.crm.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import net.element.crm.components.RetryAnalyzer;
import net.element.crm.pages.ElementCRM_ConsolePage;

public final class POC_TC01_GrabLockIssue extends BaseTest {

	@Test(retryAnalyzer=RetryAnalyzer.class)
	private final void step01() {
		testResultPlaceHolder = "Login failed, user unable to login";
		Assert.assertTrue(obj_ElementCRM_LoginPage.signIn());
		testResultPlaceHolder = "User is able to login successfully";
	} 

	@Test(retryAnalyzer=RetryAnalyzer.class, dependsOnMethods= {"step01"})
	private final void step02() {
		testResultPlaceHolder = "Did not navigate to homepage, Verification failed";
		Assert.assertTrue(obj_ElementCRM_HomePage.verifyHomePage());
		testResultPlaceHolder = "Successfully navigated to 'Home page'";
	}

	@Test(retryAnalyzer=RetryAnalyzer.class, dependsOnMethods= {"step01"})
	private final void step03() {
		testResultPlaceHolder = "Did not navigate to console page";
		Assert.assertTrue(obj_ElementCRM_ConsolePage.openConsolePage());
		testResultPlaceHolder = "Successfully navigated to 'Console page'";
	}

	@Test(retryAnalyzer=RetryAnalyzer.class, dependsOnMethods= {"step01"})
	private final void step04() {
		testResultPlaceHolder = "Mode is not in User, verification failed";
		Assert.assertTrue(obj_ElementCRM_ConsolePage.verifyModeUser());
		testResultPlaceHolder = "Successfully verified user mode";
	}

	@Test(retryAnalyzer=RetryAnalyzer.class, dependsOnMethods= {"step01"}, dataProvider = "campaignDataList")
	private final void step05(String paramter1, String parameter2) {
		testResultPlaceHolder = "Did not update campaign '" + paramter1 + "' and datalist '" + parameter2 + "'";
		Assert.assertTrue(obj_ElementCRM_ConsolePage.updateCampaignDataList(paramter1, parameter2));
		testResultPlaceHolder = "Successfully updated campaign '" + paramter1 + "' and datalist '" + parameter2 + "'";
	}

	@Test(retryAnalyzer=RetryAnalyzer.class, dependsOnMethods= {"step01"})
	private final void step06() {
		testResultPlaceHolder = "User did not click on 'GrabLock'";
		Assert.assertTrue(obj_ElementCRM_ConsolePage.clickOnGrabLock());
		testResultPlaceHolder = "Successfully clicked on 'Grablock' button and text displayed as 'Locked' with green backgrounvd color";
	}

	@Test(retryAnalyzer=RetryAnalyzer.class, dependsOnMethods= {"step01"})
	private final void step07() {
		testResultPlaceHolder = ElementCRM_ConsolePage.staticString_PlaceHolder + " Org Information fields are not enabled, verification failed";
		Assert.assertTrue(obj_ElementCRM_ConsolePage.verifyOrgInformationEnabled());
		testResultPlaceHolder = "Successfully verified, all the Org Information fields are enabled";
	}

	@Test(retryAnalyzer=RetryAnalyzer.class, dependsOnMethods= {"step01"})
	private final void step08() {
		testResultPlaceHolder = ElementCRM_ConsolePage.staticString_PlaceHolder + " Primary DM fields are not enabled, verification failed";
		Assert.assertTrue(obj_ElementCRM_ConsolePage.verifyPrimaryDMEnabled());
		testResultPlaceHolder = "Successfully verified, all the Primary DM fields are enabled";
	}

	@Test(retryAnalyzer=RetryAnalyzer.class, dependsOnMethods= {"step01"})
	private final void step09() {
		testResultPlaceHolder = "Clicking on Call button not placing any call, verifcation failed";
		Assert.assertTrue(obj_ElementCRM_ConsolePage.verifyCompanyPhoneNumberCall());
		testResultPlaceHolder = "Successfully verified, clicking on call button placed a call with log added in call history";
	}

	@Test(retryAnalyzer=RetryAnalyzer.class, dependsOnMethods= {"step01"})
	private final void step10() {
		testResultPlaceHolder = "Outcomes tab options are not enabled, verification failed";
		Assert.assertTrue(obj_ElementCRM_ConsolePage.verifyOutcomesEnabled());
		testResultPlaceHolder = "Successfully verified, all the outcome tab options are enabled";
	}

	@Test(retryAnalyzer=RetryAnalyzer.class, dependsOnMethods= {"step01"})
	private final void step11() {
		testResultPlaceHolder = "Logout failed, user unable to logout";
		Assert.assertTrue(obj_ElementCRM_HomePage.logout());
		testResultPlaceHolder = "User is able to logout successfully";
	}

	@DataProvider(name = "campaignDataList")
	private final Object[][] campaignDataList() {
		return new Object[][] {{testStepData.get("Campaign"), testStepData.get("Data List")}};
	}

}
