package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.FrameworkConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By LoginBtn = By.xpath("//input[@value='Login']");
	private By forgotpasssword = By.linkText("Forgotten Password");
	private By registerlink = By.linkText("Register");
	private By LogoutMessage = By.cssSelector("div#common-success h1");
	private By RegisterLink = By.linkText("Register");
	private By fuck= By.id("test");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	@Step("getting login page title")
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(FrameworkConstants.LOGIN_PAGE_TITLE, FrameworkConstants.DEFAULT_TIME_OUT);

	}

	@Step("getting login URL ")

	public String getLoginPageUrl() {

		return eleUtil.waitForUrlContains(FrameworkConstants.LOGIN_PAGE_URL, FrameworkConstants.DEFAULT_TIME_OUT);
	}

	@Step("user is able to login with username: {0} and password: {1}")

	public AccountPage doLogin(String un, String pw) {

		eleUtil.waitForElementVisible(username, FrameworkConstants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(un);
		;
		eleUtil.doSendKeys(password, pw);
		eleUtil.doClick(LoginBtn);
		return new AccountPage(driver);
	}

	@Step("isForgotPasswordLinkExist")

	public boolean isForgotPasswordLinkExist() {
		return eleUtil.doIsDisplayed(forgotpasssword);

	}

	@Step("isRegisterLinkExist")
	public boolean isRegisterLinkExist() {

		return eleUtil.doIsDisplayed(registerlink);
	}

	@Step("getLogoutSucessMessage")

	public String getLogoutSucessMessage() {

		return eleUtil.waitForElementVisible(LogoutMessage, FrameworkConstants.DEFAULT_TIME_OUT).getText();

	}

	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(RegisterLink);
		return new RegisterPage(driver);
	}
}
