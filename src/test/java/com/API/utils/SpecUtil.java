package com.API.utils;

import static com.API.utils.ConfigManager.getProperty;

import org.hamcrest.Matchers;

import com.API.constants.Roles;
import com.API.pojo.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	// Static method!!
	
	// GET-- DEL
	public static RequestSpecification requestSpec() {
		// To take care of the common request sections (methods)
		RequestSpecification request =new RequestSpecBuilder()
		.setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.log(LogDetail.METHOD)
		.log(LogDetail.URI)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		
		return request;
	}
	
	
	//POST-PUT-PATCH
	public static RequestSpecification requestSpec(Object userCreds) {
		// To take care of the common request sections (methods)
		RequestSpecification requestSpecification =new RequestSpecBuilder()
		.setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBody(userCreds)
		.log(LogDetail.METHOD)
		.log(LogDetail.URI)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		
		return requestSpecification;
	}
	
	public static RequestSpecification requestSpecWithAuth(Roles role) {
		RequestSpecification requestSpecification=new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
				return requestSpecification;
	}
	
	public static RequestSpecification requestSpecWithAuth(Roles role, Object Payload) {
		RequestSpecification requestSpecification=new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.setBody(Payload)
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
				return requestSpecification;
	}
	
	public static ResponseSpecification responseSpec_OK() {
		ResponseSpecification responseSpecification =new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectResponseTime(Matchers.lessThan(3000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		ResponseSpecification responseSpecification =new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(3000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_TEXT(int statusCode) {
		ResponseSpecification responseSpecification =new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(3000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
}
