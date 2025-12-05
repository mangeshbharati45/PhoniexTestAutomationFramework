package com.API.utils;

import static io.restassured.RestAssured.*;

import static com.API.constants.Roles.*;
import com.API.constants.Roles;
import com.API.pojo.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	private AuthTokenProvider() {

	}

	public static String getToken(Roles role) {
		// I want to make the request for the login api and we want to extract token and
		// print it on the console!!
		com.API.pojo.UserCredentials userCredentials = null;
		if (role == FD) {
			userCredentials = new com.API.pojo.UserCredentials("iamfd", "password");
		} 
		
		else if (role == SUP) {
			userCredentials = new UserCredentials("iamsup", "password");
		} 
		
		else if (role == ENG) {
			userCredentials = new UserCredentials("iameng", "password");
		} 
		
		else if (role == QC) {
			userCredentials = new UserCredentials("iamqc", "password");
		}

		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON)
				.body(userCredentials).when().post("login").then().log().ifValidationFails().statusCode(200).extract()
				.body().jsonPath().getString("data.token");

		return token;

	}

}
