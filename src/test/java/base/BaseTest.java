package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.*;

public class BaseTest {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    public WebDriver driver;

    public static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    protected Properties prop;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(String br) throws IOException {

        // Load config.properties
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);
        fis.close();

        String executionEnv = prop.getProperty("execution_env", "local");
        boolean isHeadless = prop.getProperty("headless", "false").equalsIgnoreCase("true");
        String gridURL = prop.getProperty("grid_url", "http://localhost:4444/wd/hub");

        WebDriver localDriver;

        // ---------- LOCAL EXECUTION ----------
        if (executionEnv.equalsIgnoreCase("local")) {

            switch (br.toLowerCase()) {

                case "chrome": {
                    ChromeOptions options = new ChromeOptions();
                    if (isHeadless) {
                        options.addArguments("--headless=new");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--window-size=1920,1080");
                    }
                    localDriver = new ChromeDriver(options);
                    break;
                }

                case "edge": {
                    EdgeOptions options = new EdgeOptions();
                    if (isHeadless) {
                        options.addArguments("--headless=new");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--window-size=1920,1080");
                    }
                    localDriver = new EdgeDriver(options);
                    break;
                }

                case "firefox": {
                    FirefoxOptions options = new FirefoxOptions();
                    if (isHeadless) {
                        options.addArguments("--headless");
                    }
                    localDriver = new FirefoxDriver(options);
                    break;
                }

                default:
                    throw new RuntimeException("Invalid browser: " + br);
            }


        // ---------- SELENIUM GRID EXECUTION ----------
        } else {

            switch (br.toLowerCase()) {

                case "chrome": {
                    ChromeOptions options = new ChromeOptions();
                    if (isHeadless) {
                        options.addArguments("--headless=new");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--window-size=1920,1080");
                    }
                    localDriver = new RemoteWebDriver(new URL(gridURL), options);
                    break;
                }

                case "edge": {
                    EdgeOptions options = new EdgeOptions();
                    if (isHeadless) {
                        options.addArguments("--headless=new");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--window-size=1920,1080");
                    }
                    localDriver = new RemoteWebDriver(new URL(gridURL), options);
                    break;
                }

                case "firefox": {
                    FirefoxOptions options = new FirefoxOptions();
                    if (isHeadless) {
                        options.addArguments("--headless");
                    }
                    localDriver = new RemoteWebDriver(new URL(gridURL), options);
                    break;
                }

                default:
                    throw new RuntimeException("Invalid remote browser: " + br);
            }
        }

        driverThread.set(localDriver);
        this.driver = localDriver;

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(prop.getProperty("url"));
        logger.info("Browser initialized: " + br + " | Headless = " + isHeadless + " | Env = " + executionEnv);
    }

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
