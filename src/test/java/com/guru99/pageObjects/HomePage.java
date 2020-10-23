package com.guru99.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	private By logOut = By.linkText("Log out");
	private By managerId = By.xpath("//table[@class='layout']//table//tr[3]");

	public WebElement getLogOut() {
		return driver.findElement(logOut);
	}

	public WebElement getManagerId() {
		return driver.findElement(managerId);
	}
}
