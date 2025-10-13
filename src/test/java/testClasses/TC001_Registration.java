package testClasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.Registration;
import pages.home;

public class TC001_Registration {

	home home;
	Registration reg;
	WebDriver driver;
	
	@BeforeClass
	void setup()
	{
		driver = new ChromeDriver();
		home = new home(driver);
		reg = new Registration(driver);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://tutorialsninja.com/demo/");
		
	}
	
	@Test
	void enterFname()
	{
		
		home.clicMyAccount();
		home.clickRegister();
		reg.setFirstName("virendra");
		
	}
	
	
	
}
