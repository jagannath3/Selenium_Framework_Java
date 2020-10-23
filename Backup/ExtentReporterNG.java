package com.guru99.utility;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReporterNG extends ScreenshotClass implements IReporter {
	private ExtentReports extent;
	ExtentHtmlReporter htmlReporter;
	WebDriver driver;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		String timeStamp = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\Reports\\htmlreport-" + timeStamp + ".html");
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setReportUsesManualConfiguration(true);
		extent.setSystemInfo("Tester", "Jagannath");
		htmlReporter.config().setDocumentTitle("Test Execution Results");
		htmlReporter.config().setReportName("Deatiled Test Case Results");
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				try {
					buildTestNodes(context.getPassedTests(), Status.PASS);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					buildTestNodes(context.getFailedTests(), Status.FAIL);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					buildTestNodes(context.getSkippedTests(), Status.SKIP);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		extent.flush();
	}

	private void buildTestNodes(IResultMap tests, Status status) throws IOException {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				String testCaseName = result.getMethod().getMethodName();
				test = extent.createTest(testCaseName);

				// test.getTest() = getTime(result.getStartMillis());
				// test.getTest().endedTime = getTime(result.getEndMillis());

				// getTime(result.getStartMillis());
				// getTime(result.getEndMillis());

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				// String message = "Test " + status.toString().toLowerCase() + "ed";

				if (result.getThrowable() != null) {
					try {
						driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
								.get(result.getInstance());
					} catch (Exception e) {

					}
					String screenShotPath = captureScreenshot(testCaseName, driver);
					String message = result.getThrowable().getMessage();
					test.log(status, message, MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
					// test.log(status, message);
				}
				// extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}