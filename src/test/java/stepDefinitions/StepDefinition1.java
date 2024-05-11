package stepDefinitions;
import com.Resources.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import java.io.IOException;
import static org.junit.Assert.*;

public class StepDefinition1 extends Utilities
{
	static RequestSpecification request;
	static Response response;
	
	@Given("Verify fetching the user by {string}")
	public void verify_fetching_the_user_by(String ID) throws IOException
	{
		request= given().spec(requestSpecification("UsersbaseURL"));
	}
	@When("using resourceurl as {string} and httpMethod as {string}")
	public void using_resourceurl_as_and_http_method_as(String resourceURL, String httpMethod) throws IOException 
	{
		response= request.
				when().get(getresourceparameter(resourceURL)).
				then().extract().response();
	}
	@Then("status code is {int}")
	public void status_code_is(int statusCode) 
	{
		assertEquals(response.getStatusCode(), statusCode);
	}
	@Then("the response has {int} and {string} and {string}")
	public void the_response_has_and_and(int Id, String fname, String lname) 
	{
		String countOfID= jsonpath(response, "users.size()");
		int count= Integer.parseInt(countOfID);
			
		System.out.println("Count of ID: "+count);
		String IDnumber= jsonpath(response, "users.id[0]");
		System.out.println("IDnumber is: "+IDnumber);
		
		for(int a=0; a<count; a++)
		{
			if(a==(Id-1))
			{			
				String userID= jsonpath(response, "users.id[" +a+ "]");
				//assertEquals(userID, (Id));
				String firstname= jsonpath(response, "users["+a+"].firstName");
				assertEquals(firstname, fname);
				String lastname= jsonpath(response, "users["+a+"].lastName");
				assertEquals(lastname, lname);
				System.out.println("ID is: " +userID+ " FNAME is: " +firstname+ " and LNAME is: " +lastname);
				break;
			}
		}
		
		//assertEquals(firstname, fname);
		//assertEquals(lastname, lname);
	}



}
