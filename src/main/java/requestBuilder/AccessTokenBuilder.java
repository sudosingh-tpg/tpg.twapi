package requestBuilder;

import java.util.HashMap;

import api.BaseAPI;
import global.Base;
import global.resources.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Assert;
import utils.PropertyLoader;

/**
 * <h3>Access Token Builder for Authentication</h3>
 * <p>Access token builder builds the token generate API and uses the client secret to get access token that helps in execution of other requests<br></p>
 * <p>This class methods should mostly be called before other test methods begin i.e in @BeforeTest</p>
 * @author akashdeep.singh
 *
 */
public class AccessTokenBuilder extends Base {
	
	private Response response;
	private String bearerBasepath = Endpoints.Authentication.BEARER_TOKEN;
	
	/**
	 * Constructor for the current class
	 * Builds the API for execution and sets all the required parameters
	 */
	public AccessTokenBuilder() {
		setMethod(BaseAPI.MethodType.POST);
		setBaseUri(PropertyLoader.HOST_AUTH_URL);
		setContentType(ContentType.URLENC);
		setBasePath(bearerBasepath);
		setAuthUserName(PropertyLoader.APIAUT_CLIENT_ID);
		setAuthPassword(PropertyLoader.APIAUT_CLIENT_SECRET);
	}
	
	/**
	 * Executes the built API, uses the BaseAPI's execute method only
	 * Might add additional tweaks to the API here e.g: Setting the body
	 * @return Returns the response received after the execution of the API
	 */
	public Response executeAccessTokenAPI() {
		try {
			String body = "grant_type=client_credentials&scope=venminderApi";
			setBody(body);
			response = super.execute();
		}
		catch(Exception exception) {
			System.out.println("Problem with Access Token Builder Execute");
			exception.printStackTrace();
		}
		return response;
	}
	
	/**
	 * Used to extract the Access token out of the response
	 * @return returns the extracted access token
	 */
	public String getAccessToken() {
		try {
			HashMap<String, String> tokenResponseMap = new HashMap<String, String>();
			tokenResponseMap = response.jsonPath().get("");
			String access_token = tokenResponseMap.get("access_token");
			return access_token;
		}
		catch(Exception exception) {
			System.out.println("Problem with get access token function");
			exception.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Asserts whether the token generate API has generated expected Response
	 * @return
	 */
	public String assertSuccess() {
		this.execute();
		try {
			Assert.isValueEqual(200, response.getStatusCode());
			Assert.isNotNullOrEmpty(getAccessToken());
			return getAccessToken();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
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