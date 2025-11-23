package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.*;

public class BaseTest {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    public WebDriver driver; // Needed for ExtentManager reflection

    public static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    protected Properties prop;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(String br) throws IOException {
        // Load properties
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);
        fis.close();

        // Initialize browser
        WebDriver localDriver;
        switch (br.toLowerCase()) {
            case "chrome":
                localDriver = new ChromeDriver();
                break;
            case "edge":
                localDriver = new EdgeDriver();
                break;
            case "firefox":
                localDriver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser: " + br);
        }

        // Set ThreadLocal and public driver
        driverThread.set(localDriver);
        this.driver = localDriver;

        // Maximize and set timeouts
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Open URL from properties
        driver.get(prop.getProperty("url"));

        logger.info("Browser initialized: " + br);
    }

    // Thread-safe getter for WebDriver
    public static WebDriver getDriver() {
        return driverThread.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
            logger.info("Browser closed");
        }
    }
}
