package MyRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/features/AddToCart.feature",
    glue = "StepDef",
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true
)
public class AddtoCartRunner extends AbstractTestNGCucumberTests {
}
