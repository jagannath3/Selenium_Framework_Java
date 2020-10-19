package com.guru99.resources;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "validLoginData")
	public Object[][] getValidLogindata() {
		Object[][] data = new Object[1][2];
		data[0][0] = "mngr291004";
		data[0][1] = "ehybegY";
		return data;
	}

	@DataProvider(name = "invalidLoginData")
	public Object[][] getInValidLogindata() {
		Object[][] data = new Object[1][2];
		data[0][0] = "hello";
		data[0][1] = "1234";
		return data;
	}
}
