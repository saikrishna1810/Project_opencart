package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.FrameworkConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By SearchResults = By.cssSelector("div.product-layout.product-grid");

	public SearchResultsPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public int SearchResultsCount() {
		return eleUtil.waitForElementsVisible(SearchResults, FrameworkConstants.DEFAULT_TIME_OUT).size();
	}

	
	public ProductInfoPage Selectproduct(String productname) {
		
		By productnamelink= By.linkText(productname);
		eleUtil.doClick(productnamelink);
		return new ProductInfoPage(driver);
	}
	
}
