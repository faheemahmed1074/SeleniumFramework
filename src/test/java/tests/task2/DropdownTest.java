package tests.task2;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.task2.DropdownPage;


public class DropdownTest extends BaseTest{
	public DropdownPage dropdownPage;
	@BeforeClass
	public void Driverinstance() {
	dropdownPage=new DropdownPage(driver);
	}
	
    @Test(priority=3)
    public void testSingleDropdown(){
    	String url=configReader.getProperty("baseUrl") + "select-dropdown-demo";
        driver.get(url);
        // Fetch expected dropdown value from the database
        /*String query = "SELECT value FROM DropdownOptions WHERE day = 'Sunday'";
        ResultSet resultSet = executeQuery(query);
        String expectedValue = "";

        if (resultSet.next()) {
            expectedValue = resultSet.getString("value");
        }*/
        String expectedValue = "Sunday";
        dropdownPage.selectSingleDropdown("Sunday");
        Assert.assertEquals(dropdownPage.getSelectedSingleValue(), expectedValue, "Single dropdown value should match value");
       }
       
    @Test(priority=4)
    public void testMultipleDropdown() {
         driver.get("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");
         /*WebElement multipleDropdown = driver.findElement(By.id("multi-select"));
         Select select = new Select(multipleDropdown);
         select.selectByVisibleText("Texas");*/
         dropdownPage.selectMultipleDropdown("Texas","California");

           // Add assertions here as well
       }

}
