package com.guru99.utility;

import org.openqa.selenium.WebDriver;

import com.guru99.pageObjects.HomePage;
import com.guru99.pageObjects.LoginPage;

public class ClickUtility {

	public WebDriver driver;

	public ClickUtility(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage lp;
	public HomePage hp;

	public void clickLogin() {
		lp = new LoginPage(driver);
		lp.getLoginButton().click();
	}

	public void clickLogout() {
		hp = new HomePage(driver);
		hp.getLogOut().click();
	}
}
