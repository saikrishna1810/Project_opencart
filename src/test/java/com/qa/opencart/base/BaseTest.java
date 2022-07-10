package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.ShoppingCartPage;

public class BaseTest {

	public DriverFactory df;
	public WebDriver driver;
	public Properties prop;
	public SoftAssert softassert;

	protected LoginPage loginpage;
	protected AccountPage accountpage;
	protected SearchResultsPage searchresultspage;
	protected ProductInfoPage productinfopage;
	protected RegisterPage registerpage;
	protected ShoppingCartPage shoppingcartpage;
	

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		loginpage = new LoginPage(driver);
	}

	@AfterTest
	public void terdown() {
		driver.quit();

	}

}
