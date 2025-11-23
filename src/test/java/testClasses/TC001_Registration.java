package testClasses;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.Registration;
import pages.Home;

public class TC001_Registration extends BaseTest {

	Home home;
	Registration reg;
	String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

	@BeforeMethod(groups = "sanity")
	public void pageSetup() {
		home = new Home(getDriver());

		
		reg = new Registration(getDriver());

	}

	@Test(groups = "sanity")
	void enterFname() {
		logger.info("clicked register");
		home.clicMyAccount();
		home.clickRegister();
		reg.setFirstName("virendra");
		logger.info("entered first name");
	}

	@Test
	void enterLname() {
		home.clicMyAccount();
		home.clickRegister();
		Assert.assertTrue(getDriver().getTitle().contains("Register"));
		
		reg.setLastName("Swami");
		logger.info("entered last name");
	}

	@Test
	public void verifyRegistrationPageHeading() {
		home.clicMyAccount();
		home.clickRegister();
		String heading = reg.getHeading();
		Assert.assertEquals(heading, "Register Account");
	}

	@Test
	public void verifyUserCanRegisterSuccessfully() {
		logger.info("started test verify successfull login");
		home.clicMyAccount();
		home.clickRegister();

		reg.setFirstName("John");
		reg.setLastName("Doe");
		reg.setEmail("qa"+timestamp +"@gmail.com");
		reg.setPhone("9999999999");
		reg.setPassword("Test@123");
		reg.setConfirmedPassword("Test@123");
		reg.clickSubscribe();
		reg.clickPolicy();
		reg.clickContinue();
		String msg = getDriver().findElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']"))
				.getText();

		Assert.assertEquals(msg, "Your Account Has Been Created!");
		logger.info("verifyUserCanRegisterSuccessfully -passed");
	}

	@Test
	public void verifyErrorWhenMandatoryFieldsMissing() {

		logger.info("Test case - verifyErrorWhenMandatoryFieldsMissing started ");
		home.clicMyAccount();
		home.clickRegister();
		reg.clickContinue();
		String warning = getDriver().findElement(By.cssSelector(".alert-danger")).getText();
		Assert.assertTrue(warning.contains("Warning: You must agree to the Privacy Policy!"));
		logger.info("Test case - verifyErrorWhenMandatoryFieldsMissing finished ");

	}
}
