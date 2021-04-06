package com.qa.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.basepack.BaseClass;
import com.qa.utilities.utilities;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DatadrivenTest extends BaseClass {

	@Test(dataProvider ="userDataprovider")

	public void addnewUser(String uid,String uname,String ulocation) {

		logger.info("*********************Started getting the data**************");
		RestAssured.baseURI = "http://localhost:3000/users";

		RequestSpecification httpRequest = RestAssured.given();
		// request parameters or request payload sending along with post request
		JSONObject requestparamaeters = new JSONObject();
		requestparamaeters.put("id", uid);
		requestparamaeters.put("name", uname);
		requestparamaeters.put("location",ulocation);
		
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

	}
	
	/*
	 * @DataProvider(name="userDataprovider") public String[][] getUserData() {
	 * 
	 * String userData[][]= {{"9","ger","ind"},{"10","pond","jap"}}; return
	 * userData; }
	 */
	@DataProvider(name="userDataprovider")
	public Object[][] getUserData() {
		
		Object[][] userData= utilities.getTestData("sheet1");
		return userData;
	}

}
