package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic -102: Design Home Page Features" )
@Feature("US-202 : Test Home page title, header and Account name modules ")
public class HomePageTest extends BaseTest {
	HomePage homePage;
	
	@BeforeClass
	public void homePageSetup(){
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	
	@Description("Verify Title on Home Page")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void verifyHomePageTitleTest(){
	String title = 	homePage.getHomePageTitle();
	System.out.println("Home Page title is : "+ title);
	Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	
	}
	
	@Description("Verify Header on Home Page")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void verifyHomePageHeaderTest(){
		String header = homePage.getHomePageHeader();
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}
	
//	@Description("Verify Logged in username on Home Page")
//	@Severity(SeverityLevel.NORMAL)
//	@Test(priority = 3)
//	public void verifyLoggedInAccountNameTest(){
//		String accountName = homePage.getLoggedInAccountName();
//		System.out.println("Logged in user account name is : " + accountName);
//	//	Assert.assertEquals(accountName,"Welcome "+ prop.getProperty("accountname"));
//	
//	}
	
	
	
	
	
	
	
	
	
	

}
