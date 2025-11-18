package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class MyAccount extends BasePage{

	public MyAccount(WebDriver driver) {
		super(driver);
	}
	
@FindBy(xpath = "//h2[text()='My Account']") WebElement headingAndTitle;
@FindBy(css = "a.list-group-item[href*='logout']") WebElement logout;
@FindBy(xpath = "//p[text()='You have been logged off your account. It is now safe to leave the computer.']") WebElement logoutCnfMsg;


}
