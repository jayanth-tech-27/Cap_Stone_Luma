package MyRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "C:\\Users\\JAYANTH\\eclipse-workspace\\Cap_Stone\\src\\test\\java\\features\\ProductAddtoCart.feature",
    glue = {"StepDef", "Hooks"},  // Corrected: Passed as an array
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html"
    },
    tags = "@ProductAdd"  
)
public class ProductAddtoCartRunner extends AbstractTestNGCucumberTests {
}
