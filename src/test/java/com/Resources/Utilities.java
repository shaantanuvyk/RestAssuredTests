package com.Resources;


import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities 
{
	public static RequestSpecification requestsb;
	public static String baseURLs;

	public RequestSpecification requestSpecification(String baseURL) throws IOException
	{
		
		if(requestsb==null)
		{
			//User PrintStream method to create a log file and print the output
			PrintStream printstream = new PrintStream("ValidLogFile.txt");
			requestsb= new RequestSpecBuilder().
					setBaseUri(getresourceparameter(baseURL)).
					addQueryParam("key", "qaclick123").
					addHeader("Content-Type", "application/json"). 
					addFilter(RequestLoggingFilter.logRequestTo(printstream)).
					addFilter(ResponseLoggingFilter.logResponseTo(printstream)).
					build();
					
		baseURLs= baseURL;		
		return requestsb;
		}
		else if(baseURLs!=baseURL)
		{
			//User PrintStream method to create a log file and print the output
			PrintStream printstream = new PrintStream("ValidLogFile1.txt");
			requestsb= new RequestSpecBuilder().
					setBaseUri(getresourceparameter(baseURL)).
					addQueryParam("key", "qaclick123").
					addHeader("Content-Type", "application/json"). 
					addFilter(RequestLoggingFilter.logRequestTo(printstream)).
					addFilter(ResponseLoggingFilter.logResponseTo(printstream)).
					build();
					
		baseURLs= baseURL;		
		return requestsb;
		}
		return requestsb;
	}
	
	public String jsonpath(Response response, String value)
	{
		String res= response.asString();
		JsonPath jpath = new JsonPath(res);
		String data= jpath.getString(value);
		return data;
	}
	
	public String getresourceparameter(String resource) throws IOException
	{
		FileReader filereader = new FileReader("C:\\Users\\ACER\\Desktop\\Shantanu Karambalkar\\Eclipse Codes\\RestAssured\\RestAssuredShantanuPractice\\src\\test\\java\\com\\Resources\\Global.properties");
		Properties properties = new Properties();	
		properties.load(filereader);
				
		return properties.getProperty(resource);
		
	}
}
