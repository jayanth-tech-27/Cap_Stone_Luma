package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManagement {

    private static ExtentReports extent;
    public static ExtentTest test;

    public static void initializeReport() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = "Reports/ExtentReport_" + timestamp + ".html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
    }

    public static void startTest(String testName) {
        initializeReport();
        test = extent.createTest(testName);
    }

    public static void endTest() {
        if (extent != null) {
            extent.flush();
        }
    }
}
