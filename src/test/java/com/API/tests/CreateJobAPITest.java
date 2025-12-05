package com.API.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.API.constants.Roles;
import com.API.pojo.CreateJobPayload;
import com.API.pojo.Customer;
import com.API.pojo.CustomerAddress;
import com.API.pojo.CustomerProduct;
import com.API.pojo.Problems;
import com.API.utils.SpecUtil;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest() {
		
		// Creating the CreateJobPayload Object
		
		Customer customer = new Customer("Mangesh", "Bharati", "7028234345","", "sampleemail123@gmail.com","");
		CustomerAddress customerAddress = new CustomerAddress("D 404", "Vasant Galaxy", "Bangur Nagar","Inorbit","Mumbai","411039","India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "23187831553551", "23187831553551", "23187831553551", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		
		given()
			.spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when()
			.post("/job/create")
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.body(matchesJsonSchemaInClasspath("response-Schema/CreateJobAPIResponseSchema.json"))
			.body("message", equalTo("Job created successfully. "))
			.body("data.mst_service_location_id", equalTo(1))
			.body("data.job_number", startsWith("JOB_"));
		
	
	}
}
