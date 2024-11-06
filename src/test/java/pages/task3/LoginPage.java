package pages.task3;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private RemoteWebDriver driver;

	//locate elements
	@FindBy(id="user-name")
	WebElement usernameField;
	
	@FindBy(id="password")
	WebElement passField;
	
	@FindBy(id="Login-button")
	WebElement loginField;

	@FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(xpath = "//div[@class='error-message-container error']")
    WebElement errorMessage;

	//initialize driver
    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    //sendKeys
    public void enterUsername(String username) {
    	usernameField.sendKeys(username);
    }
    public void enterPassword(String pass) {
    	passField.sendKeys(pass);
    }
    public void clickLogin() {
    	loginButton.click();
    }
    public String getErrorMessage() {
        return errorMessage.getText();
    }

}
