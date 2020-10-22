package com.guru99.Base;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.guru99.resources.DataProviders;

public class BaseClass extends DataProviders {

	public final static Logger log=LogManager.getLogger(BaseClass.class);
	
	public WebDriver initialization() {
		System.setProperty("webdriver.chrome.driver", Chrome_Path);
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
		ChromeOptions option=new ChromeOptions();
		option.merge(cap);
		//option.setCapability("UNEXPECTED_ALERT_BEHAVIOUR", UnexpectedAlertBehaviour.IGNORE);
		WebDriver driver = new ChromeDriver(option);
		log.info("***********************Starting Tests***********************");
		log.info("Driver initialized");
		driver.get(URL);
		log.info("Redirected to -> "+URL);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}
