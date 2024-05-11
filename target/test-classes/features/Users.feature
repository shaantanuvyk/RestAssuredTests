Feature: User Details

@FetchUsers @Sanity
  	Scenario: Fetch the user by ID
  	Given Verify fetching the user by "ID"
  	When using resourceurl as "GetUserresource" and httpMethod as "GET"
  	Then status code is 200
  	And the response has 21 and "<firstname>" and "<lastname>"
  	
  	Examples:
  	|firstname|lastname|
  	|Doyle	  |Ernser|