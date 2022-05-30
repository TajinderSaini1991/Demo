package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class HomePage extends BasePage{
	
	private WebDriver driver;
	ElementUtil elementUtil;
	
	By header = By.xpath("//b[text()='DemoCompany']");
	By contacts  = By.xpath("//span[text()='Contacts']");
	
	
//	By header = By.xpath("//h1[text()='Dashboard']");
//	By btnPIM = By.cssSelector("#menu_pim_viewPimModule");
//	By btnAddEmployee = By.cssSelector("#menu_pim_addEmployee");
//	By headerAddEmployee = By.xpath("//h1[text()='Add Employee']");
//	By accountName = By.id("welcome");

	
	
	
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	
//	@Step("getting home page title.....")
	public String getHomePageTitle(){
	return	elementUtil.doGetPageTitleWithTitleIs(10, Constants.HOME_PAGE_TITLE);
	}
	

	//@Step("getting home page header.....")
	public String getHomePageHeader(){
		if(elementUtil.doIsDisplayed(header))
			return elementUtil.doGetText(header);
	
		return null;
	}
	
//	@Step("getting logged in account name.....")
//	public String getLoggedInAccountName(){
//		//elementUtil.doClick(accountMenu);
//		By accountName = 
//		if(elementUtil.doIsDisplayed(accountName))
//			return elementUtil.doGetText(accountName);
//		return null;
//		
//		
//	}
		/*if (driver.findElement(accountName).isDisplayed())
			return driver.findElement(accountName).getText();
		return null;*/
	
		@Step("going to contacts page...")
		public ContactsPage  goToContactsPage(){
			elementUtil.mouseHover(contacts);
			elementUtil.doClick(contacts);
			
			//clickOnContacts();
			return new ContactsPage(driver);
		}
		
		
		
		
//		private void clickOnContacts(){
//			elementUtil.waitForElementPresent(contactsPrimaryLink, 5);
//			elementUtil.doClick(contactsPrimaryLink);
//			elementUtil.doClick(contactsSecondaryLink); //
//			
//		}
	
	
	
}

