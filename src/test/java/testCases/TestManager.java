package testCases;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import requestBuilder.AccessTokenBuilder;

public class TestManager {
	protected String accessToken;
	
	@BeforeSuite(alwaysRun = true)
	public void preSuiteActions() {
		
	}
	
	@AfterSuite(alwaysRun = true)
	public void postSuiteActions() {
		
	}
	
	@BeforeTest(alwaysRun = true)
	public void preTestActions(ITestContext context) {
	}
	
	@BeforeMethod(alwaysRun = true)
	public void preTestMethodActions(ITestResult result, ITestContext context) {
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void postTestMethodActions() {
		
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
