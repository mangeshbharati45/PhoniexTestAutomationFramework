package com.API.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.API.pojo.UserCredentials;
import com.API.utils.SpecUtil;


public class LoginAPITest {

	@Test
	public void loginAPITest() throws IOException {
		// Rest Assured Code!!
		
		// Read the property value that is going to be passed from terminal!
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
	given()
		.spec(SpecUtil.requestSpec(userCredentials))

	.when()
		.post("login")
	.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", equalTo("Success"))
		.and()
		.body("data.token", notNullValue())
		.body(matchesJsonSchemaInClasspath("response-Schema/loginAPIResponseSchema.json"))
		.log().all();
		
	}
	
}
