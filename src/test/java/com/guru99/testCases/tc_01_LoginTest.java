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
	public DataProviders dp;

	@BeforeTest
	public void initDriver() {

		driver = initialization();
	}

	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviders.class)
	public void validLoginTest(String username, String password) throws NoAlertPresentException {
		lp = new LoginPage(driver);
		cu = new ClickUtility(driver);
		lp.getLoginUserName().sendKeys(username);
		lp.getLoginPassword().sendKeys(password);
		cu.clickLogin();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("Managerhomepage"), "Login Successfull");
		System.out.println("Login Successfull! You're redirected to " + currentUrl);
		cu.clickLogout();
		String logOutMsg = driver.switchTo().alert().getText();
		Assert.assertTrue(ExpectedConditions.alertIsPresent() != null, logOutMsg);
		driver.switchTo().alert().accept();
		System.out.println(logOutMsg);
	}

	@Test(dataProvider = "invalidLoginData", dataProviderClass = DataProviders.class)
	public void invalidLoginTest(String username, String password) {
		lp = new LoginPage(driver);
		cu = new ClickUtility(driver);
		lp.getLoginUserName().sendKeys(username);
		lp.getLoginPassword().sendKeys(password);
		cu.clickLogin();
		String loginErrorMsg = driver.switchTo().alert().getText();
		Assert.assertTrue(ExpectedConditions.alertIsPresent() != null, loginErrorMsg);
		System.out.println(loginErrorMsg);
		driver.switchTo().alert().accept();
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
