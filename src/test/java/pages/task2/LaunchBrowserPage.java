package pages.task2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LaunchBrowserPage {
    private RemoteWebDriver driver;

    public LaunchBrowserPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

	public String getPageTitle() {
        return driver.getTitle();
    }
	
	public String getSum(String n1, String n2) {
	    System.out.print("andr hn");
	    WebElement aInput = driver.findElement(By.id("sum1"));
	    String str = aInput.getAttribute("placeholder");
	    System.out.println("Placeholder: " + str);
	    
	    // Clear any pre-existing values before sending keys
	    aInput.clear();
	    aInput.sendKeys(n1);
	    
	    WebElement bInput = driver.findElement(By.id("sum2"));
	    bInput.clear(); // Clear the second input before entering the value
	    bInput.sendKeys(n2);
	    
	    // Click the button to calculate the sum
	    driver.findElement(By.xpath("//button[text()='Get Sum']")).click();

	    // Wait for the result message to be visible before trying to access it
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement resultMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addmessage")));
	    
	    String text = resultMessage.getText();
	    System.out.println("The sum is: " + text); // Print the sum for debugging
	    return text;
	}

}
