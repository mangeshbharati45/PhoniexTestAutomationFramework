package com.API.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;


import java.util.ArrayList;
import java.util.List;


import org.testng.annotations.Test;

import com.API.constants.Model;
import com.API.constants.OEM;
import com.API.constants.Platform;
import com.API.constants.Problem;
import com.API.constants.Product;
import com.API.constants.Roles;
import com.API.constants.ServiceLocation;
import com.API.constants.Warranty_Status;
import com.API.pojo.CreateJobPayload;
import com.API.pojo.Customer;
import com.API.pojo.CustomerAddress;
import com.API.pojo.CustomerProduct;
import com.API.pojo.Problems;
import static com.API.utils.DateTimeUtil.*;
import com.API.utils.SpecUtil;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest() {
		
		// Creating the CreateJobPayload Object

		Customer customer = new Customer("Mangesh", "Bharati", "7028234345","", "sampleemail123@gmail.com","");
		CustomerAddress customerAddress = new CustomerAddress("D 404", "Vasant Galaxy", "Bangur Nagar", "Inorbit","Mumbai", "411039", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "23187831553553", "23187831553553", "23187831553553", getTimeWithDaysAgo(10), 
				Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemList);
		
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
