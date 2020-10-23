package com.guru99.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotClass {

	public WebDriver driver;

	public String captureScreenshot(String testCaseName, WebDriver driver) throws IOException {
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\Screenshots\\" + testCaseName + "_" + timeStamp
				+ ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}
}
