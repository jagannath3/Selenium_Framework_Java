package com.guru99.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.guru99.Base.BaseClass;
import com.guru99.Base.LoginBase;
import com.guru99.pageObjects.LoginPage;
import com.guru99.resources.DataProviders;

/**
 * 
 * @author Jagannath 
 * 		Test Script Day 1 
 * 		************** 
 * 		Test Steps 
 * 		1) Go to http://www.demo.guru99.com/V4/ 
 * 		2) Enter valid UserId and Password. 
 * 		3) Verify Login
 * 		4) Enter invalid UserID and Password. 
 * 		5) Verify Login
 */

public class tc_01_LoginTest extends BaseClass {

	public WebDriver driver;
	public LoginPage lp;
	public LoginBase lb;

	@BeforeSuite
	public void initDriver() {
		driver = initialization();
	}

	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviders.class)
	public void validLoginTest(String username, String password) {
		lb = new LoginBase(driver);
		lb.enterUserName(username);
		lb.enterPassword(password);
		lb.clickOnLogin();
		lb.verifyValidLogin();
	}

	@Test(dataProvider = "invalidLoginData", dataProviderClass = DataProviders.class)
	public void invalidLoginTest(String username, String password) {
		lb = new LoginBase(driver);
		lb.enterUserName(username);
		lb.enterPassword(password);
		lb.clickOnLogin();
		lb.verifyInValidLogin();
	}

	@AfterSuite
	public void tearDown() {
		driver.close();
		log.info("Browser closed");
		log.info("***********************End of Tests***********************");
	}

}
