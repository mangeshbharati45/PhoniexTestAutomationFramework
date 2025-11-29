package com.API.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.API.constants.Roles.*;

import static com.API.utils.AuthTokenProvider.*;

import static com.API.utils.ConfigManager.*;

import io.restassured.http.Header;


public class UserDetailsAPITest {

	@Test
	public void userDetailsAPIRequest() throws IOException {
		
		Header authHeader = new Header("Authorization", getToken(SUP));
		given()
			.baseUri(getProperty("BASE_URI"))
			.and()
			.header(authHeader)
			.and()
			.accept(JSON)
			.log().all()
		.when()
			.get("userdetails")
		.then()
			.statusCode(200)
			.body("message", equalTo("Success"))
			.body(matchesJsonSchemaInClasspath("response-Schema/userDetailsResponseSchema.json"))
			.log().all();
	}

}
