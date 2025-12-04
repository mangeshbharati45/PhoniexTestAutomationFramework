package com.API.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.API.constants.Roles;
import com.API.pojo.CreateJobPayload;
import com.API.pojo.Customer;
import com.API.pojo.CustomerAddress;
import com.API.pojo.CustomerProduct;
import com.API.pojo.Problems;
import com.API.utils.SpecUtil;

public class CreateJobAPITest {
	// Creating the CreateJobPayload Object
	
	
	
	@Test
	public void createJobAPITest() {
		
		Customer customer = new Customer("Mangesh", "Bharati", "7028234345","", "sampleemail123@gmail.com","");
		CustomerAddress customerAddress = new CustomerAddress("D 404", "Vasant Galaxy", "Bangur Nagar","Inorbit","Mumbai","411039","India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "23187831553556", "23187831553556", "23187831553556", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0] = problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		
		
		
		given()
			.spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when()
			.post("/job/create")
		.then()
			.spec(SpecUtil.responseSpec_OK());
	}
}
