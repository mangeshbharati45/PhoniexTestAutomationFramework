package com.API.tests;

import static com.API.constants.Roles.FD;
import static com.API.utils.AuthTokenProvider.getToken;
import static com.API.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;


import org.testng.annotations.Test;

import com.API.utils.SpecUtil;

public class MasterAPITest {

	@Test
	public void masterAPITest() {
		
		given()
			.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
			.post("master") //default content-type application/url-formencoded
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.body("message", equalTo("Success"))
			.body("data", notNullValue())
			.body("data", hasKey("mst_oem"))
			.body("data", hasKey("mst_model"))
			.body("$", hasKey("message"))
			.body("$", hasKey("data"))
			.body("data.mst_oem.size()",equalTo(2)) // Check the size of the JSON Array with Matchers
			.body("data.mst_model.size()",greaterThan(0))
			.body("data.mst_oem.id", everyItem(notNullValue()))
			.body("data.mst_oem.name",everyItem(notNullValue()))
			.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
		
	}
	
	@Test
	public void invalidTokenMasterAPITest() {
		given()
			.spec(SpecUtil.requestSpec())
		.when()
			.post("master") //default content-type application/url-formencoded
		.then()
			.spec(SpecUtil.responseSpec_TEXT(401));
	}
}
