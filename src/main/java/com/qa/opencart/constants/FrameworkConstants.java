package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class FrameworkConstants {

	public final static String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL = "route=account/login";
	public static final int DEFAULT_TIME_OUT = 5;
	public static final int DEFAULT_ELEMENT_TIME_OUT = 10;
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_URL = "route=account/account";
	public static final String ACCOUNT_PAGE_HEADER = "Account";
	public static final List<String> EXPECTED_ACCOUNT_PAGE_SECTIONLIST =
			Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	public static final String LOGOUT_EXPECTED_MESSAGE = "Account Logout";
	public static final String SEARCH_KEY = "Macbook";
	public static final CharSequence ACCOUNT_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created";
	public static final String REGISTER_SHEET_NAME = "registerData";
	public static final String PRODUCT_SHEET_NAME = "productData";

}
