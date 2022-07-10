package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.FrameworkConstants;
import com.qa.opencart.util.ElementUtil;

public class AccountPage {

	private By sectionHeaderList = By.cssSelector("div#content h2");
	private By logoutlink = By.linkText("Logout");
	private By Search = By.name("search");
	private By Header = By.xpath("//a[contains(text(),'Account')]//ancestor::ul[@class='breadcrumb']");
	private By searchIcon = By.cssSelector("div#search button");

	private WebDriver driver;
	private ElementUtil eleutil;

	public AccountPage(WebDriver driver) {

		this.driver = driver;
		eleutil = new ElementUtil(this.driver);

	}

	public String getAccountPageTitle() {

		return eleutil.waitForTitleIs(FrameworkConstants.ACCOUNT_PAGE_TITLE, FrameworkConstants.DEFAULT_TIME_OUT);
	}

	public String getAccountPageURL() {

		return eleutil.waitForUrlContains(FrameworkConstants.ACCOUNT_PAGE_URL, FrameworkConstants.DEFAULT_TIME_OUT);
	}

	public String getHeaderofAccount() {

		return eleutil.doGetText(Header);
	}

	public List<String> getheaderlistAccount() {

		List<WebElement> seclist = eleutil.getElements(sectionHeaderList);
		List<String> vallist = new ArrayList<String>();
		for (WebElement e : seclist) {
			String text = e.getText();
			vallist.add(text);

		}
		return vallist;

	}

	public boolean isLogoutExist() {
		return eleutil.waitForElementVisible(logoutlink, FrameworkConstants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();

	}

	public LoginPage clickonlogout() {
		if (isLogoutExist()) {
			eleutil.doClick(logoutlink);

		}
		return new LoginPage(driver);
	}

	public Boolean isearchexist() {
		return eleutil.waitForElementVisible(Search, FrameworkConstants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();

	}

	public SearchResultsPage doSearch(String searchkey) {

		if (isearchexist()) {
			eleutil.doSendKeys(Search, searchkey);
			eleutil.doClick(searchIcon);
			return new SearchResultsPage(driver);

		} else {

			return null;
		}

	}
}
