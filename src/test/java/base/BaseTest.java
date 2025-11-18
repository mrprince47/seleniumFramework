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
    
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    protected Properties prop;
    
    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);
        fis.close();
        
        String br = "chrome";
        
        switch (br) {
            case "chrome":
                driver.set(new ChromeDriver());
                break;
            case "edge":
                driver.set(new EdgeDriver());
                break;
            case "firefox":
                driver.set(new FirefoxDriver());
                break;
            default:
                throw new RuntimeException("Invalid browser name: " + br);
        }
        
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
        
        getDriver().get(prop.getProperty("url"));
    }
    
    public static WebDriver getDriver() {
        return driver.get();
    }
    
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}