package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	
	private WebDriver driver;
	ElementUtil elementUtil;
	
	// By locators : object repository Orange HRM
//	By loginUsername =By.id("txtUsername");
//	By password = By.id("txtPassword");
//	By loginBtn =By.id("btnLogin");	
//	By signUpLink = By.linkText("Forgot your password?");
	
	By loginUsername = By.name("email");
	By password = By.name("password");
	By loginBtn = By.xpath("//div[text()='Login']");
	By signUpLink = By.linkText("Forgot your password?");

	
	
	//constructor of page class
	public LoginPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	
	// page actions
	@Step("getting login page title...")
	public String getLoginPageTitle(){
		//return driver.getTitle();
		return	elementUtil.doGetPageTitleWithTitleIs(10, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Step("getting signup link presence...")
	public boolean isSignUpLinkExists(){
	//return	driver.findElement(signUpLink).isDisplayed();
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	
	@Step("login with username : {0} and password : {1}")
	public HomePage doLogin(String username, String pwd){
		/*driver.findElement(emailId).sendKeys(username);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();*/
		System.out.println("login to the app with "+username +":"+pwd);
		elementUtil.waitForElementToBeVisible(loginUsername, 10).sendKeys(username);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
		
		return new HomePage(driver);
	}
	

}
