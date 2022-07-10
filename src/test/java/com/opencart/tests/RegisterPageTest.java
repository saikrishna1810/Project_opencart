package com.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.FrameworkConstants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registersetup() {

		registerpage = loginpage.goToRegisterPage();

	}

	@DataProvider
	public Object[][] getRegisterexcelData() {
		return ExcelUtil.getTestData(FrameworkConstants.REGISTER_SHEET_NAME);

	}

	@Test(dataProvider = "getRegisterexcelData")
	public void registerTest(String firstName, String lastName, String telephone, String password, String subsribe) {

		Assert.assertTrue(registerpage.registerUser(firstName, lastName, registerpage.getrandomemail(), telephone,
				password, subsribe));

	}
}
