package com.API.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.API.utils.SpecUtil;

import static com.API.constants.Roles.*;



public class UserDetailsAPITest {

	@Test
	public void userDetailsAPIRequest() throws IOException {
		
		given()
			.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
			.get("userdetails")
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.and()
			.body(matchesJsonSchemaInClasspath("response-Schema/userDetailsResponseSchema.json"));
	}

}
