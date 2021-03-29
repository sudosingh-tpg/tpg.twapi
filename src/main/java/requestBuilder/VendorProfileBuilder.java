package requestBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.util.Arrays;

import api.BaseAPI;
import global.Base;
import global.resources.Endpoints;
import io.restassured.response.Response;
import utils.Assert;
import utils.PropertyLoader;

/**
 * <h3>Builds API to get Vendor profile details</h3>
 * <p>Vendor Profile API is used to get various parameters like client criticality, its products etc etc</p>
 * @author akashdeep.singh
 *
 */
public class VendorProfileBuilder extends Base{
	
	private Response response;
	private String vendorProfileBasepath = Endpoints.VendorProfile.VENDOR_PROFILE;
	
	/**
	 * Constructor for the current class
	 * Builds the API for execution and sets all the required parameters
	 * @param accessToken Requires the access toke generated with the help of client secret
	 */
	public VendorProfileBuilder(String accessToken) {
		setMethod(BaseAPI.MethodType.GET);
		setBaseUri(PropertyLoader.HOST_RSD_URL);
		setBasePath(vendorProfileBasepath);
		addParam("vendorKey", PropertyLoader.VENDOR_CRITICAL_KEY);
		addHeader("Authorization", "Bearer "+accessToken);
	}
	
	/**
	 * Executes the built API, uses the BaseAPI's execute method only
	 * Might add additional tweaks to the API here e.g: Setting the body
	 * @return returns the generated response
	 */
	public Response executeVendorProfileAPI() {
		response = super.execute();
		return response;
	}
	
	/**
	 * Asserts the response code for the current API
	 */
	public void assertResponseCode() {
		Assert.isValueEqual(200, response.getStatusCode());
	}
	
	/**
	 * Asserts the name of the Vendor is same as expected
	 */
	public void assertVendorname() {
		HashMap<String, String> vendorProfileMap = response.jsonPath().get("");
		Assert.isValueEqual(PropertyLoader.VENDOR_CRITICAL_NAME, vendorProfileMap.get("name"));
	}
	
	/**
	 * Asserts whether the products are same as expecetd for the Vendor
	 */
	public void assertProducts() {
		HashMap<String, Object> vendorProfileMap = response.jsonPath().get("");
		String[] products =  PropertyLoader.VENDOR_CRITICAL_PRODUCTS.split(",");
		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, String>> vendorProducts = (ArrayList<HashMap<String, String>>) vendorProfileMap.get("products");
		List<String> vendorProductsNames = new ArrayList<String>();
		for(int vpListItr = 0; vpListItr < vendorProducts.size(); vpListItr++) {
			vendorProductsNames.add(vendorProducts.get(vpListItr).get("name"));
		}
		Assert.isTrue(Arrays.asList(products).equals(vendorProductsNames));
	}

	/**
	 * Implementation of the Abstract class parent's method 
	 * Returns the generated response for this API
	 */
	@Override
	public Response getAPIResponse() {
		return response;
	}
	
}
