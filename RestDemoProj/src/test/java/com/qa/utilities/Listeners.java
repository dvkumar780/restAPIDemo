package com.qa.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {

	public ExtentHtmlReporter htmlreporter;
	public ExtentTest test;
	public ExtentReports extent;
	
	public void onStart(ITestContext testContext) {
	
		htmlreporter=new ExtentHtmlReporter("C:\\Users\\VinayKumarDeva\\eclipse-workspace\\RestDemoProj\\reports\\myreport.html");
		htmlreporter.config().setDocumentTitle("API testing demo report");
		htmlreporter.config().setReportName("vinay");
		htmlreporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Host name", "local host");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "vinay");
		
		
	}
	

	public void onTestSuccess(ITestResult itestresult) {
		
		test=extent.createTest(itestresult.getName());
		test.log(Status.PASS, "test case is passed is"+itestresult.getName());
		
		
	}
	
	public void onTestFailure(ITestResult itestresult) {

		test=extent.createTest(itestresult.getName());
		test.log(Status.FAIL, "test case is failed is"+itestresult.getName());
		test.log(Status.FAIL, "test case is failed and exception is"+itestresult.getThrowable());
	}
	
	public void onFinish(ITestContext testcontext) {
		
		extent.flush();
	}
	

}
