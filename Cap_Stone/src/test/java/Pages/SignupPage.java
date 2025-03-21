package Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import Utils.ExtentReportManagement;

public class SignupPage extends BaseClass {
    // Declaring variables to store properties from the file
    String first_name, last_name, email, password, confirm_password;
	public String url;
	String browser;

    @BeforeTest
    public void readData() throws IOException {
        // Reading properties file to get input data for signup
        FileInputStream fis = new FileInputStream("C:\\Users\\JAYANTH\\eclipse-workspace\\Cap_Stone\\src\\main\\java\\data\\SignupPage.properties");
        Properties prop = new Properties();
        prop.load(fis);

        // Assigning property values to variables
        first_name = prop.getProperty("first_name");
        last_name = prop.getProperty("last_name");
        email = prop.getProperty("email");
        password = prop.getProperty("password");
        confirm_password = prop.getProperty("confirm_password");
        url = prop.getProperty("url");
        browser = prop.getProperty("browser");
    }

    @Test
    public void Signup() {
        // Printing values to verify they are read correctly from properties file
        System.out.println(first_name);
        System.out.println(last_name);
        System.out.println(email);
        System.out.println(password);
        System.out.println(confirm_password);
        System.out.println(url);
        System.out.println(browser);
    }

    @Test(enabled = true)
    public void login() throws IOException, InterruptedException {
        ExtentReportManagement.startTest("SignupPage");
    	// Launching browser with the specified type from properties file
        invokeBrowser(browser);
        
        // Navigating to the URL provided in properties file
        driver.get(url);
        
        // Waiting for the page to load 
        Thread.sleep(3000);

     // Validating if the "Create an Account" link is present before clicking
        WebElement createAccountLink = driver.findElement(By.linkText("Create an Account"));

        if (createAccountLink.isDisplayed() && createAccountLink.isEnabled()) {
            System.out.println("Create an Account link is present. Clicking on it...");
            createAccountLink.click();
        } else {
            System.out.println("Create an Account link is not available. Test cannot proceed.");
        }
        
        
        
        // Filling the signup form with values from the properties file
        driver.findElement(By.xpath("//input[@class='input-text required-entry']")).sendKeys(first_name);
        driver.findElement(By.id("lastname")).sendKeys(last_name);
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("password-confirmation")).sendKeys(confirm_password);
        
        // Adding implicit to wait to 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Clicking on the "Submit" button to complete the signup process
        WebElement element = driver.findElement(By.xpath("//button[@class='action submit primary']"));
        element.click();
        driver.quit();
        ExtentReportManagement.endTest();
    }
}
