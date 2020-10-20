package com.guru99.Base;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.guru99.resources.DataProviders;

public class BaseClass extends DataProviders {

	public final static Logger log=LogManager.getLogger(BaseClass.class);
	
	public WebDriver initialization() {
		System.setProperty("webdriver.chrome.driver", Chrome_Path);
		WebDriver driver = new ChromeDriver();
		log.info("***********************Starting Tests***********************");
		log.info("Driver initialized");
		driver.get(URL);
		log.info("Redirected to -> "+URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}
