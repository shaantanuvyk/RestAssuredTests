package com.Cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features",
		glue={"stepDefinitions"},
		plugin ="html:target/jsonReports/cucumber-report.html"
		//,tags="@Sanity"
		)
public class TestRunner 
{

}


