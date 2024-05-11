package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.io.IOException;

import static org.junit.Assert.*;
import com.Resources.TestDataBuilder;
import com.Resources.Utilities;

import io.restassured.specification.RequestSpecification;

public class StepDefinition extends Utilities
{
	static RequestSpecification request;
	TestDataBuilder testdatabuilder = new TestDataBuilder();
	static Response response;
	static String PlaceID;
	
	@Given("Verify adding {string} {string} {string}")
	public void verify_adding(String name, String address, String phone_no) throws IOException 
	{
	    request=given().spec(requestSpecification("LocationbaseURL")).body(testdatabuilder.AddLocationPayloadTDB(name, address, phone_no));
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String requestparameter, String httpMethod) throws IOException 
	{
	    if(httpMethod.equalsIgnoreCase("post"))
	    {
	    	response= request.
	    			when().post(getresourceparameter(requestparameter)).
	    			then().extract().response();
	    			
	    }
	}
	
	@Then("verify the status code is {int} and status is {string}")
	public void verify_the_status_code_is_and_status_is_OK(int StatusCode, String abc) 
	{
		assertEquals(response.getStatusCode(),StatusCode);
		assertEquals(jsonpath(response, "status"),abc);
	}

	@Then("fetch the {string}")
	public void fetch_the(String placeid) 
	{
		PlaceID = jsonpath(response,"place_id");
		System.out.println("Add PLace ID is: " +PlaceID);
	}


	@Given("Verify fetching the location")
	public void verify_fetching_the_location() throws IOException 
	{
		request= given().spec(requestSpecification("LocationbaseURL")).queryParam("place_id", PlaceID);
	}
	@When("Using resourceurl as {string} with {string} http request")
	public void using_resourceurl_as_with_http_request(String resource, String method) throws IOException 
	{
		if(method.equalsIgnoreCase("GET"))
		{
			response=	request.
						when().get(getresourceparameter(resource)).
						then().extract().response();
		}
	}
	@Then("Verify the {string} {string} {string}")
	public void verify_the(String name, String address, String phoneno) 
	{
		String getname= jsonpath(response, "name");	
		String getaddress= jsonpath(response, "address");
		String getno= jsonpath(response, "phone_number");
		
		System.out.println("Name is: " +getname+ " Address is: " +getaddress+ " and Phone Number is: " +getno);
	
		assertEquals(getname, name);
		assertEquals(getaddress, address);
		assertEquals(getno, phoneno);
	}
	
	@Then("Verify the status code is {int}")
	public void verify_the_status_code_is(int statusCode) 
	{
		assertEquals(response.getStatusCode(), statusCode);
	}
	

	@Given("Verify updating {string} using {string}")
	public void verify_updating_using(String address, String PlaceID) throws IOException 
	{
		PlaceID= this.PlaceID;
		request = given().spec(requestSpecification("LocationbaseURL")).body(testdatabuilder.updateLocation(PlaceID, address));
		System.out.println("REQUEST IS: "+request);
	}
	
	
	
	@When("User calls resource as {string} using the {string} http request")
	public void user_calls_resource_as_using_the_http_request(String resource, String httpMethod) throws IOException 
	{
		if(httpMethod.equalsIgnoreCase("GET"))
		{
			response= request.
		    		when().get(getresourceparameter(resource)).
		    		then().extract().response();
		}
		
		if(httpMethod.equalsIgnoreCase("PUT"))
		{
	    response= request.
	    		when().put(getresourceparameter(resource)).
	    		then().extract().response();
	    		
		}
	}
	@Then("Verify the message in response should be {string}")
	public void verify_the_message_in_response_should_be(String responseMessage) 
	{
	    assertEquals(jsonpath(response,"msg"),responseMessage);
	}
	@Then("Verify the Status Code should be {int}")
	public void verify_the_status_code_should_be(Integer statusCode) 
	{
		verify_the_status_code_is(statusCode);
	}



	
	
	@Given("Verify deleting the {string}")
	public void verify_deleting_the(String PlaceID) throws IOException 
	{
		PlaceID= this.PlaceID;
		request= given().spec(requestSpecification("LocationbaseURL")).body(testdatabuilder.deleteLocation(PlaceID));
	}
	@When("using Delete resourceurl as {string} with {string} http request")
	public void using_Delete_resourceurl_as_with_http_request(String resource, String httpMethod) throws IOException 
	{
	    if(httpMethod.equalsIgnoreCase("POST"))
	    {
	    	response= request.
	    			when().post(getresourceparameter(resource));
	    }
	}
	@Then("Verify the Status is {string}")
	public void verify_the_status_is(String statusMessage) 
	{
		jsonpath(response, statusMessage);
	}
	@Then("Status Code is {int}")
	public void status_code_is(int statusCode) 
	{
		assertEquals(response.statusCode(), statusCode);
	}

}
