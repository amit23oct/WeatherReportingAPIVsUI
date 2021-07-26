package org.testing.TestSteps;
import static io.restassured.RestAssured.*;

import java.util.Properties;

import io.restassured.response.Response;

public class HttpMethods {
	Properties pr;
	public HttpMethods(Properties pr) {
		this.pr = pr;
	}

	//Get HTTP Method
	public Response getResponse() {
		
		Response res=
				given()
				.queryParam("q", pr.getProperty("place"))
				.queryParam("appid", pr.getProperty("appKey"))
				.when()
				.get(pr.getProperty("uriValue"));
				return res;
	}
	
	
}
