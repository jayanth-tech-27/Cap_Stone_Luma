package StepDef;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductAddtoCart {
	Hooks hooks = new Hooks(); 
    private WebDriver driver;
    private LoginPage login_page;

    // Constructor to assign WebDriver instance from Hooks
    public ProductAddtoCart() {
        this.driver = Hooks.driver;  // Accessing  the WebDriver from Hooks
        this.login_page = new LoginPage(driver); 
    }

    @Given("User logs in with valid credentials")
    public void user_logs_in() throws IOException, InterruptedException {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Check Hooks setup.");
        }
        driver.get("https://magento.softwaretestingboard.com/");  // Ensure the driver is not null
        driver.findElement(By.linkText("Sign In")).click();
        
        // Call method to read data from Excel
        readDataFromExcel();
    }

    // Retrieve login data from Excel
    public void readDataFromExcel() throws IOException, InterruptedException {
        FileInputStream excel = new FileInputStream("C:\\Users\\JAYANTH\\eclipse-workspace\\Cap_Stone\\src\\test\\resources\\Excel\\Luma_Project.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(excel);
        XSSFSheet sheet = workbook.getSheet("SignupPage");

        login_page.email().sendKeys(sheet.getRow(0).getCell(0).getStringCellValue());
        Thread.sleep(2000);
        login_page.password().sendKeys(sheet.getRow(1).getCell(0).getStringCellValue());
        Thread.sleep(2000);
        login_page.signIn().click();

        workbook.close();
        excel.close();
    }

    @When("User selects Men -> Tops > Antonia Racer Tank -> XL -> Purple")
    public void user_selects_fixed_category() throws InterruptedException {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        
        driver.findElement(By.linkText("Men")).click();
        driver.findElement(By.linkText("Tops")).click();
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300);");
        
        driver.findElement(By.linkText("Atlas Fitness Tank")).click();
        Thread.sleep(3000);
        
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0, 300);");
        
        // Select XL Size
        driver.findElement(By.id("option-label-size-143-item-170")).click();
        Thread.sleep(3000);
        
        // Select Purple Color
        driver.findElement(By.id("option-label-color-93-item-50")).click();
        
        // Set Quantity to 3
        WebElement quantityInput = driver.findElement(By.id("qty"));
        quantityInput.clear();
        quantityInput.sendKeys("2");
        Thread.sleep(1000);
        
        // Add product to the cart
        driver.findElement(By.xpath("//button[@id='product-addtocart-button']")).click();
        Thread.sleep(3000);
        
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollBy(0, -300);");
        Thread.sleep(3000);
  
    }

    @Then("Item should be added to cart successfully")
    public void item_added_successfully() {
        driver.quit();
    }
    }
