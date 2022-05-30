package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class ContactsPage extends BasePage {
	private WebDriver driver;
	ElementUtil elementUtil;
	
//	By header = By.xpath("//h1[text()='Add Employee']");
//	By txtFirstName = By.id("firstName");
//	By txtLastName = By.id("lastName");
//	By btnSave = By.id("btnSave");
//	By newContactName = By.xpath("(//h1[text()])[1]");
//	By btnPIM = By.cssSelector("#menu_pim_viewPimModule");
//	By btnAddEmployee = By.cssSelector("#menu_pim_addEmployee");
//	By btnEmployeeList = By.id("menu_pim_viewEmployeeList");
//	By empId = By.id("employeeId");
//	By txtEmployeeName = By.id("empsearch_employee_name_empName");
//	By txtEmployeeSearchId = By.id("empsearch_id");
//	By btnSearchEmp = By.id("searchBtn");
//	

	By header = By.xpath("//div[text()='Contacts']");
	By btnCreate = By.xpath("//button[text()='Create']");
	By txtFirstName = By.name("first_name");
	By txtLastName = By.name("last_name");
	By txtCompany = By.name("//div[@name='company']//input");
	By btnSave = By.xpath("//button[text()='Save']");
	By newContactName = By.xpath("//div[@id='dashboard-toolbar']//div[text()]");
	By btnDelete = By.xpath("//i[@class='trash icon']");
	By btnConfirmDeletion = By.xpath("//div[text()='Confirm Deletion']");
	By btnClickDelete = By.xpath("//i[@class='remove icon']");
	By txtNoRecords = By.xpath("//div[@class='ui warning message']//p[text()='No records found']");
	
	public ContactsPage(WebDriver driver){
		this.driver = driver;
		elementUtil =  new ElementUtil(this.driver);
	}
	
	
	@Step("getting contacts page title......")
	public String getContactsPageTitle(){
		return elementUtil.doGetPageTitleWithContains(10, Constants.CONTACTS_PAGE_TITLE);
	}
	
	@Step("getting contacts page header......")
	public String getContactsPageHeader(){
		elementUtil.waitForElementPresent(header, 10);
		return elementUtil.doGetText(header);
	}
	
	
//@Step("creating new contacts ......")
//	public String[] createContact(String firstname,String lastname){
//		elementUtil.clickwhenReady(txtFirstName, 10); 
//		elementUtil.doSendKeys(this.txtFirstName, firstname);
//		elementUtil.doSendKeys(this.txtLastName, lastname);
//		elementUtil.doActionsClick(this.btnSave);
//		String contactName =  elementUtil.waitForElementPresent(newContactName, 5).getText();
//		String id = elementUtil.waitForElementPresent(empId, 20).getText();
//		elementUtil.mouseHover(btnPIM);
//		elementUtil.mouseHover(btnEmployeeList);
//		elementUtil.clickwhenReady(btnEmployeeList, 10);
//		return new String[]{contactName, id};
//	}
	@Step("creating new contacts ......")
	public String createContact(String firstname,String lastname){
		elementUtil.clickwhenReady(btnCreate, 10); 
		elementUtil.doSendKeys(this.txtFirstName, firstname);
		elementUtil.doSendKeys(this.txtLastName, lastname);
		elementUtil.doActionsClick(this.btnSave);
		By newContactName = By.xpath("//div[text()='"+firstname+" "+lastname+"']");
		String contactName =  elementUtil.waitForElementPresent(newContactName, 5).getText();
		return contactName;
		
	}
	
@Step("deleting the contacts....")
	public String selectAndDeleteAllContacts() throws InterruptedException{
		elementUtil.waitForElementPresent(btnDelete, 20);
		elementUtil.doClick(btnDelete);
		Thread.sleep(2000);
		elementUtil.waitForElementToBeVisible(btnConfirmDeletion, 20);
		elementUtil.doClick(btnClickDelete);
		
		

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {            // waiting for 3 seconds
			
		}
		//elementUtil.dorefresh();					// refresing the page so that "0 contact" text appears
		
		elementUtil.waitForElementPresent(txtNoRecords,15); // waiting for "0 contact" text 
		return elementUtil.doGetText(txtNoRecords); // getting the text for assertion
		
	}
}
