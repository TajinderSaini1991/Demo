package com.qa.hubspot.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method is used to create/find the WebElement on the basis of By
	 * locator
	 * 
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(By locator) {

		WebElement element = null;

		try {

			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("Element not found " + locator);
		}
		return element;
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public void dorefresh(){
		driver.navigate().refresh();
	}
	/************Actions Class utils****************/
	public  void doActionsSendkeys(By locator, String value){
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
		
		
	}
	public  void doActionsClick(By locator){
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	/******************* Drop down utils ********************/

	public void doSelectByVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	/**
	 * This method returns the List of all the options in the select drop down
	 * using Select class
	 * 
	 * @param locator
	 * @return List of Strings
	 */
	public List<String> getDropDownArrayList(By locator) {
		List<String> optionsList = new ArrayList<String>();
		Select select = new Select(getElement(locator));

		List<WebElement> options = select.getOptions();

		for (WebElement i : options) {
			optionsList.add(i.getText());
		}

		return optionsList;
	}

	public void getOptions(By locator, String value) {
		List<WebElement> dropDownList = driver.findElements(locator);
		for (WebElement i : dropDownList) {
			String text = i.getText();
			if (text.equals(value)) {
				i.click();
				break;
			}

		}

	}

	/**************************WaitUtils************************************/
	public  String doGetPageTitleWithContains(int timeOut,String title){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleContains(title));	
		return driver.getTitle();
	}
	
	public  String doGetPageTitleWithTitleIs(int timeOut,String title){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	public  String doGetPageCurrentUrl(int timeOut,String urlValue){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.urlContains(urlValue));
		return driver.getCurrentUrl();
	}
	
	public  WebElement waitForElementPresent(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
	return	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	
	public  WebElement waitForElementToBeClickable(By locator,int timeOut){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		return	wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public  void clickwhenReady(By locator,int timeOut){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		WebElement element =	wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	public  WebElement waitForElementToBeVisible(By locator,int timeOut){
		 WebDriverWait wait = new WebDriverWait(driver,timeOut);
		 return   wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
		    
	}
	public List<WebElement> visibilityOfAllElements(By locator,int timeOut){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		return   wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	
	public Alert waitForAlertPresent(int timeOut){
		  WebDriverWait wait = new WebDriverWait(driver,timeOut);
		  return  wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public boolean isElementDisplayed(By locator, int timeout) {
		WebElement element = null;
		boolean flag = false;
		for (int i = 0; i < timeout; i++) {

			try {
				element = driver.findElement(locator);
				
				flag = element.isDisplayed();
				break;
			} catch (Exception e) {
				System.out.println("waiting for element to be present on the page -->" + i + "secs");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
			}

		}
		
		return flag;

	}
	
	public WebElement elementDisplayed(By locator,int timeOut){
				WebElement element = null;
				Boolean flag = false;
			for(int i =1;i<=timeOut;i++){
		try{
				element = driver.findElement(locator);
			flag = element.isDisplayed();
			break;
			}
		
		catch(Exception e){
		System.out.println("waiting fo element to be displayed on the page--" +i+ "secs");	
		try{
			Thread.sleep(1000);
		}catch(Exception e1){
		}
		}
	}
	if(!flag){
		System.out.println("Element not found");
	}
    return element;
		
}
	
	public void mouseHover(By locator){
		Actions action = new Actions(driver);
		WebElement ele = getElement(locator);
		waitForElementPresent(locator, 2000);
		//Performing the mouse hover action on the target element.
		action.moveToElement(ele).perform();
		
	}
	public void uploadFile(){
		
	}
}
