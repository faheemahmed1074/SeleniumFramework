package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener {

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest test;
    private String reportName;

    @Override
    public void onStart(ITestContext testContext) {
        // Get suite name for dynamic report naming
        String suiteName = testContext.getSuite().getName();
        
        // Time stamp for unique report name
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test-Report-" + suiteName + "-" + timeStamp + ".html";

        // Set up report directory and file path
        File reportDir = new File("./reports");
        if (!reportDir.exists()) {
            reportDir.mkdirs(); // Create directory if it doesn't exist
        }
        sparkReporter = new ExtentSparkReporter("./reports/" + reportName); // Location of the report
        sparkReporter.config().setDocumentTitle("Selenium Tasks Project"); // Title of report
        sparkReporter.config().setReportName("Tasks - " + suiteName); // Name of the report with suite name
        sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK); // Set theme

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // System and environment info
        extent.setSystemInfo("Application", "Selenium Testing");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "faheem");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage()); // Capture the error message
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush(); // Write the report to the file
    }
}
