package com.qa.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.basepack.BaseClass;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_001_GET_Request extends BaseClass {

	@Test(priority = 1)

	public void getuserDetails() throws InterruptedException {
		RestAssured.baseURI = "http://localhost:3000/users";

		RequestSpecification httpRequest = RestAssured.given();

		// response object
		Response response = httpRequest.request(Method.GET);

		// get message as string

		// response.getbody will get json body
		String responsebody = response.getBody().asString();
		System.out.println("response bbody is :" + responsebody);

		// status code

		int statuscode = response.getStatusCode();
		System.out.println("status code is" + statuscode);

		Assert.assertEquals(statuscode, 200);

		// status line verification
		String statusLine = response.getStatusLine();
		System.out.println("STATUS LINE IS :" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		Thread.sleep(3000);
	}

	// post request

	@Test(priority = 0)

	public void registerUser() {
		RestAssured.baseURI = "http://localhost:3000/users";

		RequestSpecification httpRequest = RestAssured.given();
		// request parameters or request payload sending along with post request
		JSONObject requestparamaeters = new JSONObject();
		requestparamaeters.put("id", 4);
		requestparamaeters.put("name", "vinayy");
		requestparamaeters.put("location", "hyd");
		requestparamaeters.put("phone", "432225543");
		requestparamaeters.put("courses", "cp");

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestparamaeters.toJSONString());

		Response response = httpRequest.request(Method.POST);
		// response.getbody will get json body
		String responsebody = response.getBody().asString();
		System.out.println("response bbody is :" + responsebody);

		// status code

		int statuscode = response.getStatusCode();
		System.out.println("status code is" + statuscode);

		Assert.assertEquals(statuscode, 201);

		/*
		 * //success code validation String String
		 * successcode=response.jsonPath().get("SuccessCode");
		 * 
		 * Assert.assertEquals(successcode, "Created");											
		 */

		// validate headers by capturing details from response

		String contenttype = response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json; charset=utf-8");
		System.out.println(contenttype);

	}

	@Test(priority = 2)

	public void validateHeadersFromResponse() {

		RestAssured.baseURI = "http://localhost:3000/users";
		RequestSpecification httpRequest = RestAssured.given();
		// response object
		Response response = httpRequest.request(Method.GET);

		// response.getbody will get json body
		/*
		 * String responsebody = response.getBody().asString();
		 * System.out.println("response bbody is :" + responsebody);
		 * 
		 * Headers allheaders=response.headers();//captures all headers returned from
		 * response
		 * 
		 * for(Header headers:allheaders) {
		 * 
		 * System.out.println(headers.getName()+"   "+headers.getValue());
		 * 
		 * }
		 */
		
		//validating particular field in repsonse body
		
		//Assert.assertEquals(responsebody.contains("vinayy"), true);
		
		//if we want to extract individual values from nodes of responsponse bbody,use json path
		
		JsonPath jsonpath=response.jsonPath();//will capture complete response
		
		System.out.println(jsonpath.get("courses"));
		
	}

	
	@Test(priority = 4)
	
	public void authorizationApi() {
		
		//authentication should be specified before specifying he request
		
		PreemptiveBasicAuthScheme authscheme=new PreemptiveBasicAuthScheme();
		authscheme.setUserName("");
		authscheme.setPassword("");
		RestAssured.authentication=authscheme;//have to make authscheme to rest assured authemntication
		
		
	}
}
