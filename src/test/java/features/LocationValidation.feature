Feature: Verify Adding Location with Valid details

  @AddValidPlace 
  Scenario Outline: Verify adding new location using valid details
    Given Verify adding "<name>" "<address>" "<phone_no>" 
    When user calls "Addresource" with "POST" http request
    Then verify the status code is 200 and status is "OK"
    And fetch the "place_id"
    
    Examples:
    |name    |address|phone_no  |
    |Shantanu|Amb    |9028288206|
    
  @GetValidPlace @Regression
    Scenario Outline: Verify fetching the location using valid placeID
    Given Verify fetching the location 
    When Using resourceurl as "Getresouce" with "GET" http request
    Then Verify the "<name>" "<address>" "<phone_no>"
    And Verify the status code is 200 
    
     Examples:
    |name    |address|phone_no  |
    |Shantanu|Amb    |9028288206|
    
  @UpdateValidPLace @LightRegression @Regression
  Scenario Outline: Verify updating existing location details
  Given Verify updating "<address>" using "PlaceID"
  When User calls resource as "Updateresource" using the "PUT" http request
  Then Verify the message in response should be "Address successfully updated"
  And Verify the Status Code should be 200
  
  Examples:
  |address|
  |Belgaum|
  
  
   @GetValidPlace 
    Scenario Outline: Verify fetching the location using valid placeID
    Given Verify fetching the location 
    When Using resourceurl as "Getresouce" with "GET" http request
    Then Verify the "<name>" "<address>" "<phone_no>"
    And Verify the status code is 200 
    
	Examples:
  |name |address|phone_no|
  |Shantanu|Belgaum|9028288206|	
  
  @DeleteValidPlace @LightRegression @Regression
  	Scenario: Verify Deleting valid location
   	Given Verify deleting the "PlaceID"
   	When using Delete resourceurl as "Deleteresource" with "POST" http request
   	Then Verify the Status is "Ok"
   	And Status Code is 200
   	