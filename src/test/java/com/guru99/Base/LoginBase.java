package com.guru99.Base;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.guru99.pageObjects.LoginPage;

public class LoginBase extends BaseClass {

	public WebDriver driver;

	public LoginBase(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUserName(String userName) {
		LoginPage lp = new LoginPage(driver);
		lp.getLoginUserName().sendKeys(userName);
		log.debug("User name entered");
	}

	public void enterPassword(String password) {
		LoginPage lp = new LoginPage(driver);
		lp.getLoginPassword().sendKeys(password);
		log.debug("Password entered");
	}

	public void clickOnLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.getLoginButton().click();
		log.debug("Clicked on Login Button");
	}

	public void verifyValidLogin() {
		String expectedURL = driver.getCurrentUrl();
		log.debug("Verifying Login");
		Assert.assertTrue(expectedURL.contains("Managerhomepage"), "Login Not Success");
		log.debug("Login Success! You're redirected to -> " + expectedURL);
	}

	public void verifyInValidLogin() {
		log.debug("Verifying Login");
		Alert alert = driver.switchTo().alert();
		log.error("Pop up opened with error message: " + alert.getText());
		alert.accept();
		log.debug("Clicked OK on Pop up to close it");
	}
}
