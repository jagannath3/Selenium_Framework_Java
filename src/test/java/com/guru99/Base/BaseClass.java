package com.guru99.Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.guru99.resources.DataProviders;

public class BaseClass extends DataProviders {

	public WebDriver initialization() {
		System.setProperty("webdriver.chrome.driver", Chrome_Path);
		WebDriver driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}
