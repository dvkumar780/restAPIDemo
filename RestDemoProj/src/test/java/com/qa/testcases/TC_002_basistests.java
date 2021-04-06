package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.Test;

import com.qa.basepack.BaseClass;

public class TC_002_basistests extends BaseClass {

	@Test

	public void verifyResonseBody() {
		logger.info("***********getting response body***********");
		String responsebody = response.getBody().asString();
		logger.info("response bbody is :" + responsebody);
		Assert.assertTrue(responsebody != null);
	}

	@Test

	public void verifyStatusCode() {
		logger.info("***********getting status code***********");
		int statuscode = response.getStatusCode();
		logger.info("status code is" + statuscode);
		Assert.assertEquals(statuscode, 200);
	}

	@Test

	public void verifyResponseTime() {
		logger.info("***********verifying response time***********");
		long responsetime = response.getTime();
		logger.info("response time is" + responsetime);
		if (responsetime > 2000)
			logger.warn("response time is greater than 2000");
		Assert.assertTrue(responsetime < 2000);

	}

	/*
	 * @Test
	 * 
	 * public void verfiyContentLenght() {
	 * logger.info("***********verifying content length***********");
	 * 
	 * String contentlength = response.header("Content-Length");
	 * logger.info("content length is " + contentlength); if
	 * (Integer.parseInt(contentlength) < 100)
	 * logger.warn("content length is less than 100");
	 * Assert.assertTrue(Integer.parseInt(contentlength) > 100); }
	 */

	@Test
	public void verifyCookies() {

		String cookie = response.getCookie("PHPSESSID");
		logger.info("cookiee found" + cookie);
	}
	
	@AfterClass

	public void tearDown() {
		logger.info("****************finished getting all users********************");
	}

}
