package Utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import Pages.LoginPage;

public class LoginExcelTC extends BaseClass {
 
    private LoginPage login_page;

    @BeforeTest
    @Parameters({"browser"})
    public void openBrowser(@Optional("chrome") String browser) { // Default browser set to "chrome"
        ExtentReportManagement.startTest("login_page");
        
        invokeBrowser(browser); // Initialize the driver with the parameterized browser

        driver.get("https://magento.softwaretestingboard.com");
        driver.findElement(By.linkText("Sign In")).click();

        login_page = new LoginPage(driver);
    }

    @Test
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
        takeScreenshot();
        
    }

    @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
        ExtentReportManagement.endTest();
    }
}
