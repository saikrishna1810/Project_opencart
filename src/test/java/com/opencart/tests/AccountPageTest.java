package com.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.FrameworkConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AccountPageTest extends BaseTest {

	@Epic(value = "Epic : 1003 This epic is for Register test")
	@Story(value = "LOGIN - 1004 : Register Page Class")
	
	@BeforeClass
	public void accsetup() {
		accountpage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());

	}
	@Description("Register page Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Test()
	public void AccountPageTitleTest() {
		String Title = accountpage.getAccountPageTitle();
		Assert.assertEquals(Title, FrameworkConstants.ACCOUNT_PAGE_TITLE);

	}
	@Description("Register page URL Test")
	@Severity(SeverityLevel.NORMAL)
	@Test()
	public void AccountPageurlTest() {
		String URL = accountpage.getAccountPageURL();
		Assert.assertTrue(URL.contains(FrameworkConstants.ACCOUNT_PAGE_URL));

	}
	@Description("Register page Header Test")
	@Severity(SeverityLevel.TRIVIAL)
	@Test()
	public void AccountHeaderTest() {
		Assert.assertEquals(accountpage.getHeaderofAccount(), FrameworkConstants.ACCOUNT_PAGE_HEADER);

	}

	@Test()
	public void AccountPageSectionsTest() {
		List<String> accseclist = accountpage.getheaderlistAccount();
		System.out.println("Actual section header list" + ":" + accseclist);
		Assert.assertEquals(accseclist, FrameworkConstants.EXPECTED_ACCOUNT_PAGE_SECTIONLIST);

	}

	@Test()
	public void LogoutlinkTest() {
		Assert.assertTrue(accountpage.isLogoutExist());

	}

	@Test()
	public void SearchTest() {
		Assert.assertTrue(accountpage.isearchexist());

	}

	@DataProvider
	public Object[][] getSearchkey() {
		return new Object[][] { { "Macbook" }, { "Apple" }, { "imac" }, { "Samsung" } };
	}

	@Test(dataProvider = "getSearchkey")
	public void doSearchTest(String searchkey) {

		Assert.assertTrue(accountpage.doSearch(searchkey).SearchResultsCount() > 0);

	}

	@DataProvider
	public Object[][] getproductname() {
		return new Object[][] { { "Macbook", "MacBook" }, 
			{ "Apple", "Apple Cinema 30\"" }, 
			{ "imac", "iMac" },
			{ "Samsung", "Samsung SyncMaster 941BW" } };
	}

	@Test(dataProvider = "getproductname")
	public void selectProductTest(String searchkey , String ProductName) {
		searchresultspage = accountpage.doSearch(searchkey);
		productinfopage = searchresultspage.Selectproduct(ProductName);
		String ProductHeader = productinfopage.getproductheadername();
		System.out.println(ProductHeader);
		Assert.assertEquals(ProductHeader, ProductName);
	}

	
	
	
	
	
	
	
	
	public void logoutMessageTest() {
		accountpage.clickonlogout().getLogoutSucessMessage();
		Assert.assertEquals(loginpage.getLogoutSucessMessage(), FrameworkConstants.LOGOUT_EXPECTED_MESSAGE);

	}

}
