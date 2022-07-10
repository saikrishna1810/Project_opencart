package com.opencart.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.FrameworkConstants;
import com.qa.opencart.util.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void accsetup() {
		accountpage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		softassert = new SoftAssert();

	}

	@DataProvider
	public Object[][] getproductData() {
		return new Object[][] { { "Macbook", "MacBook", 5 }, { "Samsung", "Samsung SyncMaster 941BW", 1 } };
	}

//	@DataProvider
//	public Object[][] getproductData() {
//		return ExcelUtil.getTestData(FrameworkConstants.PRODUCT_SHEET_NAME);
//
//	}

	@Test(dataProvider = "getproductData")
	public void productImageTest(String searchkey, String ProductName, int imgcount) {
		searchresultspage = accountpage.doSearch(searchkey);
		productinfopage = searchresultspage.Selectproduct(ProductName);
		softassert.assertEquals(productinfopage.getproductImagesCount(), imgcount);
		productinfopage.inputQuantity("3").addtocart();
		softassert.assertTrue(productinfopage.getsucessmessageforaddingprod().contains(productinfopage.addtocart1));
		softassert.assertTrue(productinfopage.getcartitemtext().contains("item(s)"));
		softassert.assertAll();

	}

	@Test
	public void productInfoTest() {
		searchresultspage = accountpage.doSearch("MacBook");
		productinfopage = searchresultspage.Selectproduct("MacBook");
		Map<String, String> ActprodInfoMap = productinfopage.getProdInfo();
		softassert.assertEquals(ActprodInfoMap.get("name"), ("MacBook"));
		softassert.assertEquals(ActprodInfoMap.get("Brand"), ("Apple"));
		softassert.assertEquals(ActprodInfoMap.get("Product Code"), ("Product 16"));
		softassert.assertEquals(ActprodInfoMap.get("pricekey"), ("$602.00"));
		softassert.assertAll();

	}

	@Test
	public void productinfoinnertextTest() {
		searchresultspage = accountpage.doSearch("MacBook");
		productinfopage = searchresultspage.Selectproduct("MacBook");
		String desc = productinfopage.getprodpageinfoinnertext();
		softassert.assertTrue(desc.contains("Intel Core 2 Duo processor"));

	}

	
	@Test(dataProvider = "getproductData")
	public void addtoCartTest(String searchkey, String ProductName) {
		
		searchresultspage = accountpage.doSearch(searchkey);
		productinfopage = searchresultspage.Selectproduct(ProductName);
		productinfopage.inputQuantity("3").addtocart();
		softassert.assertTrue(productinfopage.getsucessmessageforaddingprod().contains(productinfopage.addtocart1));
		softassert.assertTrue(productinfopage.getcartitemtext().contains("6 item(s) - $2,532.00"));
		softassert.assertAll();
	}
	
	@Test
	public void CartTxtTest() {
		searchresultspage = accountpage.doSearch("MacBook");
		productinfopage = searchresultspage.Selectproduct("MacBook");
		productinfopage.inputQuantity("3").addtocart();
		softassert.assertTrue(productinfopage.getcartitemtext().contains("3 item(s) - $1,806.00"));
		productinfopage.CartitemCheckout();

	}
}
