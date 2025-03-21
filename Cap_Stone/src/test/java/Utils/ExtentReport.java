package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
    public static ExtentReports extent;
    public static ExtentTest test;
    private static ExtentSparkReporter spark;
    WebDriver driver;

    @BeforeClass
    @Parameters("reportPath")
    public static void startTest(@Optional("C:\\Users\\JAYANTH\\eclipse-workspace\\Cap_Stone\\ExtentReport.html") String reportPath) {
        spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Test
    @Parameters("browser")
    public void extentReportsDemo(@Optional("chrome") String browser) throws InterruptedException {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        //  we Add other browser conditions if required (e.g., Edge, Firefox)

        test = extent.createTest("LoginPage");
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
        Thread.sleep(3000);
        test.log(Status.INFO, "Login Page Opened");

        try {
            if (driver.getTitle().equals("Customer Login")) {
                test.log(Status.PASS, "Navigated to the specified URL");
            } else {
                test.log(Status.FAIL, "Test Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Thread.sleep(3000);
            driver.quit();
        }
    }

    @AfterClass
    public static void endTest() {
        extent.flush();
    }

	
	}

