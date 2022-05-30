package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Epic("Epic-103 : Design Contacts Page Features")
@Feature("US-203 : Test contacts page title, header,creating new contacts, deleting contacts")
public class ContactsPageTest extends BaseTest {
	HomePage homePage;
	ContactsPage contactsPage;
	String empId = null;
	
	
	@BeforeClass
	public void ContactsPageSetup(){
	homePage =	loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	contactsPage =	homePage.goToContactsPage();
	
	}

	
	@Description("verify contacts page title")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void verifyContactsPageTitleTest(){
		String title = contactsPage.getContactsPageTitle();
		System.out.println("title of contacts page is : "+title);
		Assert.assertEquals(title,Constants.CONTACTS_PAGE_TITLE);
		
	}
	
	@Description("verify contacts page header")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void verifyContactsPageHeaderTest(){
		String header = contactsPage.getContactsPageHeader();
		System.out.println("header of contacts page is : " +header);
		Assert.assertEquals(header, Constants.CONTACTS_PAGE_HEADER);
		
	}
	@DataProvider //(parallel = true)
	public  Object[][] getTestData(){
	Object[][]data =ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
	return data;
		
	}
	
	
	@Description("verify creation of new contacts")
	@Severity(SeverityLevel.CRITICAL)

	@Test(priority = 3, dataProvider = "getTestData",enabled= true)
	public void createNewContactTest(String fname, String lname){
    String newContactName = contactsPage.createContact(fname, lname);
	Assert.assertEquals(newContactName, fname+" "+lname);
	System.out.println("Contact creation successful " );
	}

	@Description("verify deletion of  contacts")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4, enabled = true)
	public void deleteAllContacts() throws InterruptedException{
	
	String text = contactsPage.selectAndDeleteAllContacts();
	Assert.assertEquals(text, "No records found");
		System.out.println("deleted all contacts");
	}
	
	
	
	
}
