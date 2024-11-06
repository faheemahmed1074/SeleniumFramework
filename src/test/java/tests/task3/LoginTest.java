package tests.task3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.task3.LoginPage;
import utils.ConfigReader;

public class LoginTest {
	
	LoginPage loginPage;
	RemoteWebDriver driver;
	ConfigReader configReader=new ConfigReader();
	
	@BeforeMethod
	public void setUp() {
		driver=new ChromeDriver();
		String url=configReader.getProperty("task3Url");
		driver.get(url);
		loginPage= new LoginPage(driver);
	}
	
	@Test
	public void invalidLogin() {
		loginPage.enterUsername("standard_user");
		loginPage.enterPassword("wrong_password");
		loginPage.clickLogin();
		// Assertion: Check for error message
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage, "Error message mismatch!");
        
        //driver.navigate().refresh();
	}
	
	@Test
	public void validLogin() {
		loginPage.enterUsername("standard_user");
		loginPage.enterPassword("secret_sauce");
		loginPage.clickLogin();
		 // Assert: Check the URL to confirm navigation to the dashboard
	    String expectedUrl = configReader.getProperty("task3Url") + "inventory.html";
	    Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "User did not land on the dashboard.");

	    // Assert: Check that an element unique to the dashboard is displayed (e.g., product list)
	   /* WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement inventoryList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_container")));

	    Assert.assertTrue(inventoryList.isDisplayed(), "Dashboard did not display the inventory list as expected.");*/

	}
	@AfterMethod
    public void tearDownClass() {
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.quit();
    }
}
