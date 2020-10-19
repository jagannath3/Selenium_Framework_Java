package com.guru99.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public WebDriver driver;

	private By username = By.name("uid");
	private By password = By.name("password");
	private By loginButton = By.name("btnLogin");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getLoginUserName() {
		return driver.findElement(username);
	}

	public WebElement getLoginPassword() {
		return driver.findElement(password);
	}

	public WebElement getLoginButton() {
		return driver.findElement(loginButton);
	}
}
