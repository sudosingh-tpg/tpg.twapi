package testCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import reports.ExtentTestManager;
import requestBuilder.VendorProfileBuilder;

public class VendorProfile extends TestManager{
	
  @Test(description = "Verify Vendor Profile Fields", groups = {"pocTest"})
  public void basicVendorProfileAssertions() {
	  VendorProfileBuilder vpb = new VendorProfileBuilder(super.accessToken);
	  vpb.executeVendorProfileAPI();
	  ExtentTestManager.getTest().log(Status.INFO, "Vendor Profile API Execution completed");
	  vpb.assertResponseCode();
	  ExtentTestManager.getTest().log(Status.INFO, "Response code assertion completed");
	  vpb.assertVendorname();
	  ExtentTestManager.getTest().log(Status.INFO, "Vendor Name assertion completed");
	  vpb.assertProducts();
	  ExtentTestManager.getTest().log(Status.INFO, "Vendor Products assertion completed");
  }
}
