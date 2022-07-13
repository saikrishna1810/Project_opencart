package com.qa.opencart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.FrameworkExceptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	/**
	 * 
	 * this method is used to initialize the webdriver on the basis of given browser
	 * name it returns the driver
	 *
	 */
	Properties prop;
	WebDriver driver;
	OptionsManager optionsmanager;

	public static ThreadLocal<WebDriver> threadlocal = new ThreadLocal<WebDriver>();

	public WebDriver init_driver(Properties prop) {
		
		String browserName = System.getProperty("browser");

		optionsmanager = new OptionsManager(prop);

		//String browserName = prop.getProperty("browser").trim();
		System.out.println("Browsername" + ":" + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsmanager.getchromeoptions());
			threadlocal.set(new ChromeDriver(optionsmanager.getchromeoptions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsmanager.getfirefoxoptions());
			threadlocal.set(new FirefoxDriver(optionsmanager.getfirefoxoptions()));

		}

		else if (browserName.equalsIgnoreCase("safari")) {
			// driver = new SafariDriver();
			threadlocal.set(new SafariDriver());
		}

		else {

			System.out.println("Please pass the correct browser" + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();

	}

	/**
	 * 
	 * 
	 * get the thread local copy of driver
	 * 
	 * @return
	 */

	public static WebDriver getDriver() {

		return threadlocal.get();
	}

	/**
	 * This method is used to initialize the properties
	 * 
	 */

	public Properties init_prop() {
		prop = new Properties();
		FileInputStream FS = null;

		// mvn clean install -Denv="qa"
		String envName = System.getProperty("env");
		System.out.println("Running Tests on Environment :" + envName);

		if (envName == null) {
			System.out.println("No environment is given , hence running tests on QA env");
			try {
				FS = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					System.out.println("running it on qa Env");
					FS = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
					break;
				case "stage":
					System.out.println("running it on stage Env");
					FS = new FileInputStream(".\\src\\test\\resources\\config\\stage.config.properties");
					break;
				case "dev":
					System.out.println("running it on dev Env");
					FS = new FileInputStream(".\\src\\test\\resources\\config\\dev.config.properties");
					break;
				case "uat":
					System.out.println("running it on uat Env");
					FS = new FileInputStream(".\\src\\test\\resources\\config\\uat.config.properties");
					break;
				case "prod":
					System.out.println("running it on prod Env");
					FS = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
					break;

				default:
					System.out.println("Please pass the righ environment" + envName);
					throw new FrameworkExceptions("No environment is found");
				}

			} catch (Exception e) {

			}

		}

		try {

			prop.load(FS);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	/*
	 * 
	 * Screenshot code
	 */

	public String getScreenshot() {
		File srcfile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "./" + "screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcfile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}

}
