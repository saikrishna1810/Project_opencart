package com.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadlessConcept {
	
	Properties prop;

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions co = new ChromeOptions();
		co.setHeadless(false);
	 co.addArguments("--incognito");
		 WebDriver driver =new ChromeDriver(co);
		 driver.get("https://www.google.co.in");
		 System.out.println(driver.getTitle());
		 driver.quit();
		
	}

}
