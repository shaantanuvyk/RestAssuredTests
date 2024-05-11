package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks 
{
	String name;
	StepDefinition sd = new StepDefinition();

	@Before ("@GetValidPlace")
	public void beforeMethod() throws IOException
	{
		
		if(sd.PlaceID==null)
		{
			sd.verify_adding("Shantanu","Amb","9028288206");
			sd.user_calls_with_http_request("Addresource", "POST");
			sd.verify_the_status_code_is_and_status_is_OK(200, "OK");
			sd.fetch_the("place_id");
		}
	}
	
	@Before ("@DeleteValidPlace")
	public void beforeDeleteMethod() throws IOException
	{
		beforeMethod();
	}
	
	@Before ("@UpdateValidPLace")
	public void beforeUpdateMethod() throws IOException
	{
		beforeMethod();
	}
	
	@After ("@UpdateValidPLace")
	public void afterUpdateMethod() throws IOException
	{
		sd.verify_fetching_the_location();
		sd.using_resourceurl_as_with_http_request("Getresouce", "GET");
		sd.verify_the("Shantanu", "Belgaum", "9028288206");
		sd.verify_the_status_code_is(200);
	}
	
	
	
	
	
}
