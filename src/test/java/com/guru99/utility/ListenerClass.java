package com.guru99.utility;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ListenerClass extends ScreenshotClass implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReportClass.setExtent();
	ThreadLocal<ExtentTest> tl = new ThreadLocal<ExtentTest>(); // thread safe - to run parallel tests without
																// overriding the objects
	ScreenshotClass screenshotClass;

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		tl.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		tl.get().log(Status.PASS, result.getMethod().getMethodName() + " Passed");
	}

	public void onTestFailure(ITestResult result) {

		tl.get().fail(result.getThrowable());
		WebDriver driver = null;
		String testMethodName = result.getMethod().getMethodName();
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			screenshotClass = new ScreenshotClass();
			tl.get().addScreenCaptureFromPath(screenshotClass.captureScreenshot(testMethodName, driver),
					result.getMethod().getMethodName());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
