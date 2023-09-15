package StepDefinitions;

import cucumber.api.CucumberOptions;

@CucumberOptions(
	    features = "/careers.test/src/test/resources/Features/testing.feature",
	    glue = "/careers.test/src/test/java/StepDefinitions/CareersTests.java",
	    tags = {"@SmokeTest", "@RegressionTest", "@Sanity"}
	)

public class TestRunner extends CareersTests
{

}
