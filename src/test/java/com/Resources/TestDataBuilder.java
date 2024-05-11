package com.Resources;

import java.util.ArrayList;
import java.util.List;

import com.Pojo.AddLocationPayload;
import com.Pojo.Location;

public class TestDataBuilder 
{
	
	
	public AddLocationPayload AddLocationPayloadTDB(String name, String address, String phoneNo)
	{
		AddLocationPayload addlocationpayload = new AddLocationPayload();
		
		Location location = new Location();
		
		location.setLat(-58.2525);
		location.setLng(-21.2525);
		addlocationpayload.setLocation(location);
		
		addlocationpayload.setAccuracy(27);
		addlocationpayload.setName(name);
		addlocationpayload.setPhone_number(phoneNo);
		addlocationpayload.setAddress(address);
		
		List<String> types = new ArrayList<String>();
		types.add("Cricket");
		types.add("Football");
		types.add("Hockey");
		addlocationpayload.setTypes(types);
		
		addlocationpayload.setWebsite("www.svyk.com");
		addlocationpayload.setLanguage("Bhojpuri");
		
		return addlocationpayload;
	}
	
	public String updateLocation(String key, String address)
	{
		String payload= "{\r\n"
				+ "    \"place_id\": \""+key+"\",\r\n"
				+ "    \"address\": \""+address+"\",\r\n"
				+ "    \"key\": \"qaclick123\"\r\n"
				+ "}";
		
		return payload;
	}
	
	public String deleteLocation(String key)
	{
		return key= "{\r\n"
				+ "    \"place_id\":\""+key+"\"\r\n"
				+ "}";
	}
}
