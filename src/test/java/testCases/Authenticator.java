package testCases;

import org.testng.annotations.Test;

import requestBuilder.VendorProfileBuilder;

public class Authenticator extends TestManager{
	
  @Test(description = "Verify Vendor Profile Fields", groups = {"pocTest"})
  public void basicVendorProfileAssertions() {
	  VendorProfileBuilder vpb = new VendorProfileBuilder(super.accessToken);
	  vpb.executeVendorProfileAPI();
	  vpb.assertResponseCode();
	  vpb.assertVendorname();
	  vpb.assertProducts();
  }
}
