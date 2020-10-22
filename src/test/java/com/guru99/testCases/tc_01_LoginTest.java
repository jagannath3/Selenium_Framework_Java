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
 * SS1: Enter valid user name & password 
 *      Expected: Login successful, home page shown 
 * SS2: Enter invalid user name & invalid password 
 * SS3: Enter valid user name & invalid password 
 * SS4: Enter invalid user name & valid password 
 *      Expected: A pop-up “User or Password is not valid” is shown
 * 
 * @throws Exception
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
		lb.verifyLogin();
	}

	@Test(dataProvider = "invalidLoginData", dataProviderClass = DataProviders.class)
	public void invalidLoginTest(String username, String password) {
		lb = new LoginBase(driver);
		lb.enterUserName(username);
		lb.enterPassword(password);
		lb.clickOnLogin();
		lb.verifyLogin();
	}

	@AfterSuite
	public void tearDown() {
		driver.close();
		log.info("Browser closed");
		log.info("***********************End of Tests***********************");
	}

}
