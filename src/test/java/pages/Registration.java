package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class Registration extends BasePage {
	
	
	public Registration(WebDriver driver) {
		super(driver);
	}
	

@FindBy(xpath = "//h1[normalize-space()='Register Account']") WebElement hdRegisterAccount;
@FindBy(xpath = "//input[@id='input-firstname']") WebElement txtFirstName;
@FindBy(xpath = "//input[@id='input-lastname']") WebElement txtLastName;
@FindBy(xpath = "//input[@id='input-email']") WebElement txtEmail;
@FindBy(xpath = "//input[@id='input-telephone']") WebElement txtPhone;
@FindBy(xpath = "//input[@id='input-password']") WebElement txtPassword;
@FindBy(xpath = "//input[@id='input-confirm']") WebElement txtConfirmPassword;
@FindBy(xpath = "//label[normalize-space()='Yes']") WebElement rdoSubscribe;
@FindBy(xpath = "//input[@name='agree']") WebElement cboxPolicy;
@FindBy(xpath = "//input[@value='Continue']") WebElement btnContinue;


public String getHeading()
{
	return hdRegisterAccount.getText();
	
}
public void setFirstName(String fname)
{
	txtFirstName.sendKeys(fname);

}
public void setLastName(String lname)
{
txtLastName.sendKeys(lname);
}

public void setEmail(String email)
{
	txtEmail.sendKeys(email);
}
public void setPhone(String phone)
{
	txtPhone.sendKeys(phone);
}
public void setPassword(String pwd)
{
	txtPassword.sendKeys(pwd);
}

public void setConfirmedPassword(String cpwd)
{
	txtConfirmPassword.sendKeys(cpwd);
}

public void clickSubscribe()
{
	rdoSubscribe.click();
	
}

public void clickPolicy()
{
	cboxPolicy.click();
}

public void clickContinue()
{
	btnContinue.click();	
}

}
