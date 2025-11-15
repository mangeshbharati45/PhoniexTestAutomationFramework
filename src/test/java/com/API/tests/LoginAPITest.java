package com.API.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.API.pojo.UserCredentials;

public class LoginAPITest {

	@Test
	public void loginAPITest() {
		// Rest Assured Code!!
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
		given()
		.baseUri("http://64.227.160.186:9000/v1")
	.and()
		.contentType(JSON)
		.accept(ANY)
		.body(userCredentials)
		.log().all()
	.when()
		.post("login")
	.then()
		.statusCode(200)
		.body("message", equalTo("Success"))
	.and()
		.body("data.token", notNullValue())
		.body(matchesJsonSchemaInClasspath("response-Schema/loginAPIResponseSchema.json"))
		.log().all();
		
	}
	
}
