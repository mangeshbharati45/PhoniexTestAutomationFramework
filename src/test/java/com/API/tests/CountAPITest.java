package com.API.tests;

import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static com.API.constants.Roles.*;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import com.API.utils.SpecUtil;

public class CountAPITest {

	@Test
	public void verifyCountAPIResponse() {
		
		given()
			.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
			.get("/dashboard/count")
		.then()
			.log().all()
			.spec(SpecUtil.responseSpec_OK())
			.body("data", notNullValue())
			.body("data.size()", equalTo(3))
			.body("data.count", everyItem(greaterThanOrEqualTo(0)))
			.body("data.label",everyItem(not(blankOrNullString())))
			.body("data.key", containsInAnyOrder("pending_fst_assignment","pending_for_delivery","created_today"))
			.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));

	}
	
	@Test
	public void countAPITest_MissingAuthToken() {
		given()
			.spec(SpecUtil.requestSpec())
		.when()
			.get("/dashboard/count")
		.then()
			.spec(SpecUtil.responseSpec_TEXT(401));
	}
}
