package pages.task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage {
    private RemoteWebDriver driver;

    public DropdownPage(RemoteWebDriver driver) {
        this.driver = driver;
        System.out.println("DropdownPage initialized successfully");
    }

    public void selectSingleDropdown(String value) {
        System.out.println("In selectSingleDropdown method");
        WebElement singleDropdown = driver.findElement(By.id("select-demo"));
        System.out.println("Single dropdown element found");
        
        Select select = new Select(singleDropdown);
        select.selectByVisibleText(value);
    }

    public void selectMultipleDropdown(String... values) {
        System.out.println("In selectMultipleDropdown method");
        WebElement multiDropdown = driver.findElement(By.id("multi-select"));
        Select select = new Select(multiDropdown);
        for (String value : values) {
            System.out.println("Selecting value: " + value);
            select.selectByVisibleText(value);
        }
    }

    public String getSelectedSingleValue() {
        WebElement singleDropdown = driver.findElement(By.id("select-demo"));
        Select select = new Select(singleDropdown);
        return select.getFirstSelectedOption().getText();
    }
}
