package global.resources;

/*
 * Manages Endpoints for all the API's
 */
public class Endpoints {
	
	public static class Authentication{
		public static final String BEARER_TOKEN = "/connect/token";
	}
	
	public static class VendorProfile{
		public static final String VENDOR_PROFILE = "/api/v1/Vendors/GetVendorProfile";
	}
}
