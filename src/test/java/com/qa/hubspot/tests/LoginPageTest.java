package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.testlisteners.TestAllureListener;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


//@Epic("Epic -101: Design Login Page Features" )
//@Feature("US-201 : Test Login page title, Signup Link and Login form modules ")
//@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {
	
	
//	@Description("Verify Sign Up Link on Login Page")
//	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 1)
	public void verifySignUpLinkTest(){
		Assert.assertEquals(loginPage.isSignUpLinkExists(),true);
	}
	
	@Description("Verify Title on Login Page")
	@Severity(SeverityLevel.NORMAL)
	
	@Test(priority = 2)
	public void verifyLoginPageTitleTest(){
	String title =	loginPage.getLoginPageTitle();
	System.out.println("running loginpage title test");
	System.out.println("The login page title is "+title);
	Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	
	@Description("Verify user Login ")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void loginTest(){
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
	

}
