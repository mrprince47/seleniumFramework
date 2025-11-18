package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class home extends BasePage {

	public home(WebDriver driver) {
		super(driver);
	}
	
@FindBy(xpath = "//span[normalize-space()='My Account']") WebElement lnkMyAccount;
@FindBy(xpath = "//a[normalize-space()='Register']") WebElement lnkRegister;


public void clicMyAccount()
{
	lnkMyAccount.click();
}

public void clickRegister()
{
lnkRegister.click();	
}

}
