package TestRunner;

import org.testng.annotations.Test;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features={"src//test//java//featureFiles"},
					glue={"stepDefinations","utilities"},
					plugin = {"pretty", "html:target/cucumber"},
					monochrome = true,
					strict = true, 
					dryRun = false 
		)
@Test
public class RunTest extends AbstractTestNGCucumberTests{

}
