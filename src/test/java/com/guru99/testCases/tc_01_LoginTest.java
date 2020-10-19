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
		Assert.assertTrue(driver.getCurrentUrl().contains("Managerhomepage"), "Login Successfull");
		cu.clickLogout();
		Assert.assertTrue(ExpectedConditions.alertIsPresent() != null, driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
	}

	@Test(dataProvider = "invalidLoginData", dataProviderClass = DataProviders.class)
	public void invalidLoginTest(String username, String password) {
		lp = new LoginPage(driver);
		cu = new ClickUtility(driver);
		lp.getLoginUserName().sendKeys(username);
		lp.getLoginPassword().sendKeys(password);
		cu.clickLogin();
		Assert.assertTrue(ExpectedConditions.alertIsPresent() != null, driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
