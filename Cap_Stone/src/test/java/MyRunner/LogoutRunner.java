package MyRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
     features = "C:\\Users\\JAYANTH\\eclipse-workspace\\Cap_Stone\\src\\test\\java\\features\\Logout.feature", // Corrected path
     glue = { "StepDef", "Hooks" }, // Ensure correct package names
     plugin = { "pretty", "html:target/cucumber-reports" }
)
public class LogoutRunner extends AbstractTestNGCucumberTests {
}
