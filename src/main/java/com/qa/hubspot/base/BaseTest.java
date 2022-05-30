package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.hubspot.pages.LoginPage;

public class BaseTest {
	
public	Properties prop;
public	BasePage basePage;
public	WebDriver driver;
public	LoginPage loginPage;

    @BeforeClass
//    @Parameters("browser")
//	public void setUp(String browserName)
	public void setUp()
	{
	basePage = new BasePage();
	prop = basePage.init_prop();
//	prop.setProperty("browser", browserName);
	driver =basePage.init_driver(prop);
	loginPage = new LoginPage(driver);
		
	}
	
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	
}
