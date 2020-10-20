package com.guru99.testCases;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.guru99.Base.BaseClass;
import com.guru99.pageObjects.LoginPage;
import com.guru99.resources.DataProviders;
import com.guru99.utility.ClickUtility;

/**
 * 
 * @author Jagannath 
 * 		Test Script Day 1 
 * 		************** 
 * 		Test Steps 
 * 		1) Go to http://www.demo.guru99.com/V4/ 
 * 		2) Enter valid UserId and Password. 
 * 		3) Verify Login and Logout 
 * 		4) Enter invalid UserID and Password. 
 * 		5) Verify Login
 */

public class tc_01_LoginTest extends BaseClass {

	public WebDriver driver;
	public LoginPage lp;
	public ClickUtility cu;

	@BeforeTest
	public void initDriver() {
		driver = initialization();
	}

	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviders.class)
	public void validLoginTest(String username, String password) throws NoAlertPresentException {
		log.info("***********************Running validLoginTest***********************");
		lp = new LoginPage(driver);
		cu = new ClickUtility(driver);
		lp.getLoginUserName().sendKeys(username);
		log.debug("user name entered");
		lp.getLoginPassword().sendKeys(password);
		log.debug("password entered");
		cu.clickLogin();
		log.debug("Clicked on Login");
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("Managerhomepage"), "Login Successfull");
		log.info("Login Successfull! You're redirected to " + currentUrl);
		cu.clickLogout();
		log.debug("Clicked on Logout");
		String logOutMsg = driver.switchTo().alert().getText();
		Assert.assertTrue(ExpectedConditions.alertIsPresent() != null, logOutMsg);
		log.info("Popup displayed with message: " + logOutMsg);
		driver.switchTo().alert().accept();
		log.info("Clicked OK on Popup and it is closed");
		log.info("***********************End of validLoginTest***********************");
	}

	@Test(dataProvider = "invalidLoginData", dataProviderClass = DataProviders.class)
	public void invalidLoginTest(String username, String password) {
		log.info("***********************Running invalidLoginTest***********************");
		lp = new LoginPage(driver);
		cu = new ClickUtility(driver);
		lp.getLoginUserName().sendKeys(username);
		log.debug("user name entered");
		lp.getLoginPassword().sendKeys(password);
		log.debug("password entered");
		cu.clickLogin();
		log.debug("Clicked on Login");
		String loginErrorMsg = driver.switchTo().alert().getText();
		Assert.assertTrue(ExpectedConditions.alertIsPresent() != null, loginErrorMsg);
		log.error("Popup displayed with error message: " + loginErrorMsg);
		driver.switchTo().alert().accept();
		log.info("Clicked OK on Popup and it is closed");
		log.info("***********************End of invalidLoginTest***********************");
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		log.info("Browser closed");
		log.info("***********************End of Tests***********************");
	}
}
