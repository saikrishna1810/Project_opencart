package com.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.FrameworkConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic(value = "Epic : 1001 This epic is for Login test")
@Story(value = "LOGIN - 1002 : Login Page Class")
public class LoginPageTest extends BaseTest {

	
	
	@Description("Login page Title Test")
	@Severity(SeverityLevel.MINOR)
	
	@Test(priority = 1)
	public void LoginPagetitleTest() {

		String title = loginpage.getLoginPageTitle();
		System.out.println("Login Page Title " + title);
		Assert.assertEquals(title, FrameworkConstants.LOGIN_PAGE_TITLE);

	}
	@Description("Login page URL Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void LoginPageUrlTest() {

		String Url = loginpage.getLoginPageUrl();
		System.out.println("URL:" + " " + Url);
		Assert.assertTrue(Url.contains(FrameworkConstants.LOGIN_PAGE_URL));
	}
	@Description("Login page forgotpassword Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void ForgotpasswordExistTest() {

		Assert.assertTrue(loginpage.isRegisterLinkExist());

	}
	@Description("Login page Register link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void RegisterLinkExistTest() {

		Assert.assertTrue(loginpage.isRegisterLinkExist());
	}

	@Description("Login page  Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void LoginTest() {

		Assert.assertTrue(loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim())
				.isLogoutExist());

	}

}
