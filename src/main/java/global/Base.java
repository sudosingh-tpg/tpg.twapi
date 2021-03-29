package global;

import api.BaseAPI;
import io.restassured.response.Response;

/*
 * Abstract class for POJO methods
 */
public abstract class Base extends BaseAPI {
	
	public abstract Response getAPIResponse();
	
	public ResponsePojo getResponsePojo() {
		return null;
	}
	
	public RequestPojo getRequestPojo() {
		return null;
	}
	
	public static abstract class ResponsePojo{
		
	}
	
	public static abstract class RequestPojo{
		
	}
}