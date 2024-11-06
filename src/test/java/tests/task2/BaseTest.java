package tests.task2;

import utils.ConfigReader;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    RemoteWebDriver driver;
    public ConfigReader configReader = new ConfigReader();
    public Connection connection;

    @SuppressWarnings("deprecation")
	@BeforeClass
    public void setup() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        String browser = configReader.getProperty("browser").toLowerCase();
        switch (browser) {
            case "chrome":
            	capabilities.setCapability("browserName", "Chrome");
                capabilities.setCapability("browserVersion", "latest");
                break;
            case "firefox":
            	capabilities.setCapability("browserName", "Firefox");
                capabilities.setCapability("browserVersion", "132");
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        
        capabilities.setCapability("LT:Options", getLambdaTestOptions());

        try {
            String lambdaUrl = configReader.getProperty("lambda.url");
            driver = new RemoteWebDriver(new URL(lambdaUrl), capabilities);
            //driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //connectToDatabase();
    }

    private Map<String, Object> getLambdaTestOptions() {
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", configReader.getProperty("username"));
        ltOptions.put("accessKey", configReader.getProperty("accessKey"));
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("build", "Automated Test Run");
        ltOptions.put("project", "Sample Project");
        return ltOptions;
    }
    
 // Database connection setup
   /* private void connectToDatabase() {
        String dbUrl = configReader.getProperty("dbUrl");
        String dbUsername = configReader.getProperty("dbUsername");
        String dbPassword = configReader.getProperty("dbPassword");

        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    // Database query method
    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }*/


    @AfterTest
    public void tearDown() {
        // Close database connection
        /*if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
        if (driver != null) {
            driver.quit();
        }
    }
}
