package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class Home extends BasePage {

	public Home(WebDriver driver) {
		super(driver);
	}
	
@FindBy(xpath = "//span[normalize-space()='My Account']") WebElement lnkMyAccount;
@FindBy(xpath = "//a[normalize-space()='Register']") WebElement lnkRegister;
@FindBy(xpath = "//a[text()='Login']") WebElement lnkLogin;

public void clicMyAccount()
{
	lnkMyAccount.click();
}	

public void clickRegister()
{
lnkRegister.click();	
}

public void clickLoginLink()
{
	lnkLogin.click();
}

}
