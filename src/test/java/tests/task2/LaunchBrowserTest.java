package tests.task2;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.task2.LaunchBrowserPage;

public class LaunchBrowserTest extends BaseTest{
    public LaunchBrowserPage launchBrowserPage;
	@BeforeClass
	public void Driverinstance() {
	launchBrowserPage=new LaunchBrowserPage(driver);
	}
    @Test(priority=1)
    public void verifyPageTitle() {
    	String a=configReader.getProperty("baseUrl");
    	System.out.println(a);
        /*driver.get(a);
        String title = launchBrowserPage.getPageTitle();
        System.out.println("Page title is: " + title);
        Assert.assertNotNull(title, "Page title should not be null");*/
    }
    @Test(priority=2)
    public void testSum() throws SQLException {
        System.out.print("sd");
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        String n1 = "15";
        String n2 = "20";

        // Capture the result from getSum()
        String sumResult = launchBrowserPage.getSum(n1, n2);
        String expectedSum = "35";
        Assert.assertEquals(sumResult, expectedSum, "Sum should match the value");
        // Fetch expected sum from the database
        /*String query = "SELECT expected_sum FROM TestData WHERE input1 = 15 AND input2 = 20";
        ResultSet resultSet = null;
        
        try {
            resultSet = executeQuery(query);
            
            // Verify that the resultSet is not null and contains data
            if (resultSet != null && resultSet.next()) {
                String expectedSum = resultSet.getString("expected_sum");
                Assert.assertEquals(sumResult, expectedSum, "Sum should match the database value");
            } else {
                Assert.fail("No data found in database for the given inputs.");
            }
        } finally {
            // Close the resultSet if it's not null
            if (resultSet != null) {
                resultSet.close();
            }*/
        }
        
    }


