package test.utils;

import com.sun.tools.javadoc.JavaScriptScanner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
/**
 * Basic steps for initialization/destruction of WebDriver. All test files will extend this class.
 * @author UlisesM
 *
 */

public class SetupDestroyWebDriver {

     protected static WebDriver driver;

    @BeforeClass
    public static void setupApplication()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.phptravels.net/home");
    }

    @AfterClass
    public static void closeApplication()
    {
        driver.quit();

    }
}