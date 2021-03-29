package testCases;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import requestBuilder.AccessTokenBuilder;

public class TestManager {
	protected String accessToken;
	public static ExtentTest test;
	public static ExtentReports report;
	
	@BeforeSuite(alwaysRun = true)
	public void preSuiteActions() {
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/ExtentReport.html"));
		report = new ExtentReports();
		report.attachReporter(extent);
	}
	
	@AfterSuite(alwaysRun = true)
	public void postSuiteActions() {
		
	}
	
	@BeforeTest(alwaysRun = true)
	public void preTestActions() {
		
	}
	
	@AfterTest(alwaysRun = true)
	public void postTestActions() {
		
	}
	
	@BeforeClass(alwaysRun = true)
	public void preClassActions() {
		AccessTokenBuilder atBuilder = new AccessTokenBuilder();
		atBuilder.executeAccessTokenAPI();
		accessToken = atBuilder.assertSuccess();
	}
	
	@AfterClass(alwaysRun = true)
	public void postClassActions() {
		
	}
}
