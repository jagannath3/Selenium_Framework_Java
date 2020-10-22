package com.guru99.Base;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.guru99.pageObjects.LoginPage;

public class LoginBase extends BaseClass {

	public WebDriver driver;
	String actualUrl;

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

	public void verifyLogin() {
		log.debug("Verifying Login");
		try {
			Alert alert = driver.switchTo().alert();
			String error = alert.getText();
			log.error("Alert box opened with error message: " + error);
			alert.accept();
			log.debug("Clicked OK on Alert box");
			if (error.contains("not valid")) {
				actualUrl = driver.getCurrentUrl();
				log.info("You're redirected to: " + actualUrl + " Please Login with Valid credentials");
			} else {
				actualUrl = driver.getCurrentUrl();
				log.info("Login Success! You're redirected to: " + actualUrl);
			}
		} catch (NoAlertPresentException Ex) {
			actualUrl = driver.getCurrentUrl();
			if (actualUrl.contains("Managerhomepage")) {
				log.info("Login Success! You're redirected to: " + actualUrl);
			} else {
				log.error("Login Not Success");
				actualUrl = driver.getCurrentUrl();
				log.info("You're redirected to: " + actualUrl + " Please Login with Valid credentials");
			}

		}
	}
}
