package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ExtentReportManagement;

public class LogoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private LogoutObjects obj;

    // Constructor to initialize WebDriver
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
        this.obj = new LogoutObjects();
    }

    public void logout() {
    	ExtentReportManagement.startTest("LogoutPage");
        try {
            Actions actions = new Actions(driver);
            wait.until(ExpectedConditions.visibilityOf(obj.accountDropdownElement));
            actions.moveToElement(obj.accountDropdownElement).click().perform();
            System.out.println("Clicked on account dropdown");

            wait.until(ExpectedConditions.visibilityOf(obj.logoutElement));
            actions.moveToElement(obj.logoutElement).click().perform();
            System.out.println("Clicked on logout");

        } catch (Exception e) {
            System.out.println("Exception in logout method: " + e.getMessage());
        }
        ExtentReportManagement.endTest();
    }

}