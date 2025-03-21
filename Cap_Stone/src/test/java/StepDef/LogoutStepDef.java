package StepDef;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Pages.LoginPage;
import Pages.LogoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;

public class LogoutStepDef {
	
	LoginPage login;
    LogoutPage logout;
    WebDriverWait wait;
    private WebDriver driver;

    // Constructor to assign WebDriver instance from Hooks
    public LogoutStepDef() {
        this.driver = Hooks.driver;  // Access the WebDriver from Hooks
        this.login = new LoginPage(driver);  // Ensure LoginPage is properly initialized
  
        this.driver = Hooks.driver;  // Get WebDriver instance from Hooks
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait
        this.logout = new LogoutPage(driver); // Ensure LogoutPage is initialized
    }

    @And("User navigates to the URL {string}")
    public void user_navigates_to_the_application_URL(String url) {
        driver.get(url);
    }

    @When("User clicks the link {string}")
    public void user_clicks_the_link(String linkText) {
        WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
        signInLink.click();
    }

    @And("User enters valid email {string} and password {string}")
    public void user_enters_valid_email_and_password(String email, String password) {
        login = new LoginPage(driver);
        login.email().sendKeys(email);
        login.password().sendKeys(password);
    }

    @And("User clicks the {string} button")
    public void user_clicks_the_button(String buttonText) {
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'" + buttonText + "')]")));
        signInButton.click();
        System.out.println("Successfully signed in");
    }

    @And("User clicks {string} from {string} page")
    public void user_clicks_logout(String logoutText, String page) {
        logout.logout(); // Perform logout action
        System.out.println("User logged out successfully");
    }

    @Then("User verifies that the session ends and the user is redirected to the homepage")
    public void user_verifies_that_the_session_ends() {
        wait.until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/"));
        String currentURL = driver.getCurrentUrl();
        if (currentURL.equals("https://magento.softwaretestingboard.com/")) {
            System.out.println("Session ended and user redirected to homepage");
        } else {
            System.out.println("Session not ended properly");
        }
    }

    @And("User verifies the {string} is displayed")
    public void user_verifies_the_home_page_is_displayed(String pageTitle) {
        wait.until(ExpectedConditions.titleContains(pageTitle));
        assert driver.getTitle().contains(pageTitle) : "Home Page is not displayed.";
    }
}