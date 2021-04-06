package com.qa.basepack;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	public static RequestSpecification httpRequest;

	public static Response response;

	public Logger logger;

	/*
	 * @BeforeClass
	 * 
	 * public void setup() {
	 * 
	 * }
	 */

	@BeforeClass

	public void getallusers() throws InterruptedException {

		logger = Logger.getLogger(getClass());

		PropertyConfigurator
				.configure("C:\\Users\\VinayKumarDeva\\eclipse-workspace\\RestDemoProj\\Resources\\log4j.properties");
		logger.setLevel(Level.DEBUG);
		logger.info("***********getting all users***********");
		RestAssured.baseURI = "http://localhost:3000/users";

		RequestSpecification httpRequest = RestAssured.given();

		// response object
		response = httpRequest.request(Method.GET);

		Thread.sleep(3000);
	}

}
