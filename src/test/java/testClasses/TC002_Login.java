package testClasses;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.CaseFormat;

import base.BaseTest;
import pages.Home;
import pages.Login;
import pages.MyAccount;
import utilities.ExcelDataProvider;

public class TC002_Login extends BaseTest {
    
    Home home;
    Login login;
    MyAccount myacc;
    
    @BeforeMethod
    public void setupPages() {
        home = new Home(getDriver());
        login = new Login(getDriver());
        myacc = new MyAccount(getDriver());
       
    }
    
    @Test(groups = "sanity")
    public void verifyTitle() {
        home.clicMyAccount();
        home.clickLoginLink();
        
        logger.info("Test - verifyTitle - Started");
        Assert.assertTrue(getDriver().getTitle().equals("Account Login"));
    }
    
    @Test(dataProvider = "logindata", dataProviderClass = ExcelDataProvider.class)
    public void VerifySuccessfullLogin(String username, String password, String result) throws InterruptedException
	{
    	logger.info("Test - VerifySuccessfullLogin - started");
		home.clicMyAccount();
		home.clickLoginLink();
		login.setEmail(username);
		login.setPassword(password);
		login.clickLogin();
		Thread.sleep(10000);
		
		if (result.equals("valid")) {
			assertTrue(getDriver().getTitle().equals("My Account"));
			assertTrue(myacc.myAccHeading());
		}else if (result.equals("invalid")) {
			assertTrue(login.isErrorDisplayed());
		
		}
		logger.info("Test - VerifySuccessfullLogin - Finished");
		
		
	}
}