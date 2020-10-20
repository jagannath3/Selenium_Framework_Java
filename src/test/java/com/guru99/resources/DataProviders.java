package com.guru99.resources;

import org.testng.annotations.DataProvider;

public class DataProviders {

	public static final String Chrome_Path = System.getProperty("user.dir") + "\\browserDriver\\chromedriver.exe";
	public static final String URL = "http://www.demo.guru99.com/V4/";

	@DataProvider(name = "validLoginData")
	public Object[][] getValidLogindata() {
		Object[][] data = new Object[1][2];
		data[0][0] = "mngr291004";
		data[0][1] = "ehybegY";
		return data;
	}

	@DataProvider(name = "invalidLoginData")
	public Object[][] getInValidLogindata() {
		Object[][] data = new Object[3][2];
		data[0][0] = "hello";
		data[0][1] = "1234";
		data[1][0] = "mngr291004";
		data[1][1] = "1234";
		data[2][0] = "hello";
		data[2][1] = "ehybegY";
		return data;
	}

}
