package com.guru99.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportClass extends ScreenshotClass {

	public WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	//public ExtentTest test;

	public static ExtentReports setExtent() {
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\HtmlReports\\HtmlReport_" + timeStamp + ".html");
		htmlReporter.config().setDocumentTitle("HTML REPORT");
		htmlReporter.config().setReportName("Test case Results");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Tester", "Jagannath");
		return extent;
	}
}
