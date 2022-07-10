package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.FrameworkConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	private Map<String, String> prodmetaData;
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productimages = By.cssSelector("ul.thumbnails img");
	private By productheader = By.cssSelector("div#content h1");
	private By inputqty = By.xpath("//input[@id='input-quantity']");
	private By addtocart = By.xpath("//button[contains(text(),'Add to Cart')]");
	private By cartsuccessmsg = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	private By prodmetadata = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[position()=1]/li");
	private By prodpricedata = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[position()=2]/li");
	private By cartitemtext = By.cssSelector("div#cart button.dropdown-toggle");
	private By cartclick= By.xpath("//span[@id='cart-total']");
	private By ViewCart= By.linkText("View Cart");


	public String addtocart1 = "Success: You have added";
	public String addtocart2 = "to your shopping cart! x";

	public ProductInfoPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getproductheadername() {

		return eleUtil.waitForElementVisible(productheader, FrameworkConstants.DEFAULT_TIME_OUT).getText();
	}

	public int getproductImagesCount() {

		return eleUtil.waitForElementsVisible(productimages, FrameworkConstants.DEFAULT_TIME_OUT).size();

	}

	public ProductInfoPage inputQuantity(String qty) {
		
		eleUtil.doSendKeys(inputqty, qty);
		return this;

	}

	public ProductInfoPage addtocart() {

		eleUtil.doClick(addtocart);
		return this;
	}

	public String getsucessmessageforaddingprod() {

		return eleUtil.waitForElementPresence(cartsuccessmsg, FrameworkConstants.DEFAULT_TIME_OUT).getText();

	}

	public Map<String, String> getProdInfo() {

		prodmetaData = new HashMap<String, String>();
		prodmetaData.put("name", getproductheadername());
		getProductMetaData();
		getProductPriceData();
		prodmetaData.forEach((k, v) -> System.out.println(k + ":" + v));

		return prodmetaData;
	}

	private void getProductMetaData() {

		List<WebElement> metalist = eleUtil.getElements(prodmetadata);
		System.out.println("Total : " + metalist.size());
		for (WebElement e : metalist) {
			String meta[] = e.getText().split(":");
			String metakey = meta[0].trim();
			String metaValue = meta[1].trim();
			prodmetaData.put(metakey, metaValue);
		}

	}

	private void getProductPriceData() {

		List<WebElement> pricelist = eleUtil.getElements(prodpricedata);
		String price = pricelist.get(0).getText().trim();
		String Extprice = pricelist.get(1).getText().trim();
		prodmetaData.put("pricekey", price);
		prodmetaData.put("extprice", Extprice);
	}

	public String getprodpageinfoinnertext() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String innertext = js.executeScript("return document.documentElement.innerText").toString();
		return innertext;
	}

	public String getcartitemtext() {

		return eleUtil.waitForElementVisible(cartitemtext, FrameworkConstants.DEFAULT_TIME_OUT).getText();

	}
	
	
	public ShoppingCartPage CartitemCheckout() {
		
		eleUtil.doClick(cartclick);
		eleUtil.waitForElementVisible(ViewCart, FrameworkConstants.DEFAULT_ELEMENT_TIME_OUT).click();;
		return new ShoppingCartPage(driver);

		
	}

	


}
