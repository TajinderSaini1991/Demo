package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize driver on the basis of given browser
	 * name
	 * 
	 * @param browser
	 * @return driver
	 */

	public WebDriver init_driver(Properties prop) {

		optionsManager = new OptionsManager(prop);

		String browser = prop.getProperty("browser").trim();
		System.out.println("browser name is : " + browser);

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver(browser);
			}

			else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}

		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver(browser);
			} else {
				tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			}
		}

		else if (browser.equalsIgnoreCase("Safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println(browser + "is not found, please pass a correct browser");

		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize remote WebDriver
	 * 
	 * @param browserName
	 */

	public void init_remoteWebDriver(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), cap));
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFireFoxOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")), cap));
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
		}

	}

	/**
	 * This method is used to initialize properties from the config.properties
	 * file
	 * 
	 * @return prop
	 */

	public Properties init_prop() {
		prop = new Properties();
		String path = null;
		String env = null;

		try {
			env = System.getProperty("env");
			System.out.println("Running on environment  ---> " + env);
			if (env == null) {
				path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
			} else {
				switch (env) {
				case "qa":
					path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties";
					break;
				case "dev":
					path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\dev.config.properties";
					break;
				default:
					System.out.println("Please pass the correct env value...");
					break;
				}
			}

			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	// take screenshot
	public String getScreenshot() {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots" + System.currentTimeMillis() + ".png";
		File des = new File(path);
		try {
			FileUtils.copyFile(src, des);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;

	}

}
