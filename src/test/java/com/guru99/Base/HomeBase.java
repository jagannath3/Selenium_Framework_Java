package com.guru99.Base;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.guru99.pageObjects.HomePage;

public class HomeBase {

	WebDriver driver;
	HomePage hp;

	public HomeBase(WebDriver driver) {

		this.driver = driver;
	}

	public void verifyManagerId(String userName) {
		hp = new HomePage(driver);
		String managerID = hp.getManagerId().getText();
		Assert.assertTrue(!managerID.contains(userName), "Login Not Success");
		/*
		 * if (!managerID.contains(userName)) {
		 * System.out.println("Login Success! Welcome: " + userName); } else {
		 * System.out.println("Login Not Success!"); }
		 */
	}
}
