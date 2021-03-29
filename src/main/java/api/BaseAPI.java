package api;

import java.util.HashMap;
import java.util.Map;

import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

/**
 * <h3>All parent API methods</h3>
 * <p>BaseAPI describes all the methods that are important to build, manipulate or execute an API</p>
 * 
 * @author akashdeep.singh
 *
 */
public class BaseAPI 
{
	private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	private MethodType method;
	private Object body;
	private ContentType contentType;
	private String baseUri;
	private Map<String, Object> queryParams = new HashMap<>();
	private Map<String, Object> params = new HashMap<>();
	private String basePath;
	private Map<String, Object> headers = new HashMap<>();
	@SuppressWarnings("unused")
	private Response response;
    private String authName;
    private String authPassword;
	
    /*
     * Enum with all the method types that can be used
     */
	public enum MethodType {
		POST, GET, PUT, DELETE, PATCH
	}
	
	/*
	 * Returns Auth Username/name
	 */
	public String getAuthUserName() {
		return authName;
	}
	
	/*
	 * Sets the username to be used in PreemptiveBasicAuthScheme class methods
	 */
	public void setAuthUserName(String authUserName) {
		this.authName = authUserName;
	}
	
	/*
	 * Returns Authentication password
	 */
	public String getAuthPassword() {
		return authPassword;
	}
	
	/*
	 * Sets the Password to be used in PreemptiveBasicAuthScheme class methods
	 */
	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}
	
	/*
	 * Gets the method type for underlying request
	 */
	public MethodType getMethod() {
		return method;
	}
	
	/*
	 * Sets the method type for underlying request
	 */
	public void setMethod(MethodType method) {
		this.method = method;
	}
	
	/*
	 * Sets the body of the API request to be sent
	 */
	public void setBody(Object obj) {
	    this.body = obj;
        requestSpecBuilder.setBody(obj);
    }
	
	/*
	 * Gets the body of the underlying API request
	 */	
	public Object getBody(){
	    return this.body;
    }
	
	/*
	 * Sets the content type for the body that is to be sent in with the request
	 */
	public void setContentType(ContentType contentType){
	    this.contentType = contentType;
	    requestSpecBuilder.setContentType(contentType);
    }
	
	/*
	 * Gets the content type of the sent request
	 */
	public ContentType getContentType(){
	    return this.contentType;
    }
	
	/*
	 * Sets the base URI for the request
	 */
    public void setBaseUri(String baseUri){
	    this.baseUri = baseUri;
	    requestSpecBuilder.setBaseUri(baseUri);
    }

    /*
     * Gets the the BaseURI for the request
     */
    public String getBaseUri(){
	    return this.baseUri;
    }
    
    /*
     * Sets the base Path for the request (Endpoint)
     */
    public void setBasePath(String basePath){
        this.basePath = basePath;
        requestSpecBuilder.setBasePath(basePath);
    }

    /*
     * Gets the endpoint for the request
     */
    public String getBasePath(){
        return this.basePath;
    }

    /*
     * Adds headers - all at once with the help of a Map
     */
    public void addHeaders(Map<String, String> headers){
        this.headers.putAll(headers);
        requestSpecBuilder.addHeaders(headers);
    }

    /*
     * Adds one header at a time to a request in the form of key value pair
     */
    public void addHeader(String headerKey, String headerValue){
		this.headers.put(headerKey,headerValue);
		requestSpecBuilder.addHeader(headerKey,headerValue);
	}

    /*
     * Gets all the headers in a request adding them within a map
     */
    public Map<String, Object> getHeaders(){
        return this.headers;
    }


    /*
     * Adds Query parameters one at a time as Key-Value
     */
    public void addQueryParam(String paramKey, Object paramValue){
	    this.queryParams.put(paramKey,paramValue);
	    requestSpecBuilder.addQueryParam(paramKey,paramValue);
    }

    /*
     * Adds query params all at once with the help of a map
     */
    public void addQueryParams(Map<String, String> queryParams){
	    this.queryParams.putAll(queryParams);
	    requestSpecBuilder.addQueryParams(queryParams);
    }

    /*
     * Returns the query-params all at once in a map
     */
    public Map<String, Object> getQueryParams(){
	    return this.queryParams;
    }
    
    /*
     * Adds params, one at a time with the help of key-value pair
     */
    public void addParam(String paramKey, Object paramValue){
		this.params.put(paramKey,paramValue);
		requestSpecBuilder.addParam(paramKey,paramValue);
	}

    /*
     * Adds params all at once with the help of a map
     */
	public void addParams(Map<String, String> queryParams){
		this.params.putAll(queryParams);
		requestSpecBuilder.addParams(queryParams);
	}

	/*
     * Returns the params all at once in a map
     */
	public Map<String, Object> getParams(){
		return this.params;
	}
	
	/*
	 * Gets the requestspecbuilder containing all the headers params etc.
	 */
	public RequestSpecBuilder getRequestSpecBuilder() {
		return requestSpecBuilder;
	}
	
	/*
	 * Exxecutes the request after all the params, headers, auth schemes have been added
	 * Uses the RequestSpecification and RequestSpecBuilder classes to build and execute the request
	 */
	public Response execute() {
		RequestSpecification requestSpecification = requestSpecBuilder.addFilter(new RequestLoggingFilter())
				.addFilter(new ResponseLoggingFilter()).build();
		Response response;
		//TO-DO: Cover all auth schemes 
		if(this.authName!=null && this.authPassword!=null){
			PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
			basicAuth.setUserName(this.authName);
			basicAuth.setPassword(this.authPassword);
			requestSpecBuilder.setAuth(basicAuth);
		}
		
		
		switch (method) {
		case GET:
			response = given().spec(requestSpecification).when().get();
			break;
		case POST:
			response = given().spec(requestSpecification).when().post();
			break;
		case PUT:
			response = given().spec(requestSpecification).when().put();
			break;
		case DELETE:
			response = given().spec(requestSpecification).when().delete();
			break;
		case PATCH:
			response = given().spec(requestSpecification).when().patch();
			break;
		default:
			throw new RuntimeException("API method not specified");

		}
		this.response=response;
		return response;
	}


}
