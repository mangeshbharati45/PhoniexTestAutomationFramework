package com.API.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.API.pojo.UserCredentials;
import static com.API.utils.ConfigManager.*;

public class LoginAPITest {

	@Test
	public void loginAPITest() throws IOException {
		// Rest Assured Code!!
		
		// Read the property value that is going to be passed from terminal!
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
	given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.contentType(JSON)
		.accept(ANY)
		.body(userCredentials)
		.log().method()
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
