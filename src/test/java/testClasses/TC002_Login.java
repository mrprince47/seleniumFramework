package testClasses;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.Home;
import pages.Login;

public class TC002_Login extends BaseTest {
    
    Home home;
    Login login;
    
    @BeforeMethod
    public void setupPages() {
        home = new Home(getDriver());
        login = new Login(getDriver());
    }
    
    @Test
    public void verifyTitle() {
        home.clicMyAccount();
        home.clickLoginLink();
        
        logger.info("Test - verifyTitle - Started");
        Assert.assertTrue(getDriver().getTitle().equals("Account Login"));
    }
    
    @Test
    public void VerifySuccessfullLogin()
	{
    	logger.info("Test - VerifySuccessfullLogin - started");
		home.clicMyAccount();
		home.clickLoginLink();
		login.setEmail("virendraqa@gmail.com");
		login.setPassword("Test@123");
		login.clickLogin();
		assertTrue(getDriver().getTitle().equals("My Account"));
		
	}
}