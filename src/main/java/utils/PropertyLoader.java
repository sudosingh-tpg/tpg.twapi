package utils;

public class PropertyLoader {
	
	public static final String HOST_AUTH_URL;
	public static final String HOST_RSD_URL;
	public static final String APIAUT_CLIENT_ID;
	public static final String APIAUT_CLIENT_SECRET;
	public static final String EMAIL_RECIPIENTS;
	public static final String ENVIRONMENT;
	public static final String VENDOR_CRITICAL_KEY;
	public static final String VENDOR_CRITICAL_NAME;
	public static final String VENDOR_CRITICAL_PRODUCTS;
	
	static {
		try {
			PropertyUtil.getInstance().load("config.properties");
			HOST_AUTH_URL = PropertyUtil.getInstance().getValue("HOST_AUTH_URL");
			HOST_RSD_URL = PropertyUtil.getInstance().getValue("HOST_RSD_URL");
			APIAUT_CLIENT_ID = PropertyUtil.getInstance().getValue("APIAUT_CLIENT_ID");
			APIAUT_CLIENT_SECRET = PropertyUtil.getInstance().getValue("APIAUT_CLIENT_SECRET");
			EMAIL_RECIPIENTS = PropertyUtil.getInstance().getValue("EMAIL_RECIPIENTS");
			ENVIRONMENT = PropertyUtil.getInstance().getValue("ENVIRONMENT");
			VENDOR_CRITICAL_KEY = PropertyUtil.getInstance().getValue("VENDOR_CRITICAL_KEY");
			VENDOR_CRITICAL_NAME = PropertyUtil.getInstance().getValue("VENDOR_CRITICAL_NAME");
			VENDOR_CRITICAL_PRODUCTS = PropertyUtil.getInstance().getValue("VENDOR_CRITICAL_PRODUCTS");
		}
		catch(Exception exception) {
			exception.printStackTrace();
			throw new RuntimeException("Config File error, Please Check!!");
		}
	}
	
}
