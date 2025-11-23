package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class Login extends BasePage {

	public Login(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "input-email")
	WebElement txtEmail;
	@FindBy(id = "input-password")
	WebElement txtPassword;
	@FindBy(xpath = "//input[@type='submit' and @value='Login']")
	WebElement btnLogin;
	@FindBy(css = ".alert-danger")
	WebElement alertMessage;

	public boolean isErrorDisplayed() {
	    return driver.findElements(By.cssSelector(".alert-danger")).size() > 0;
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);

	}

	public void setPassword(String password) {
		txtPassword.sendKeys(password);

	}

	public void clickLogin() {
		btnLogin.click();
	}

}
