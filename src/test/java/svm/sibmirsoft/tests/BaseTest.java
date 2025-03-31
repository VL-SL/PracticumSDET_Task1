package svm.sibmirsoft.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    protected final String BASE_URL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverThreadLocal.set(driver);
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    protected WebDriver getDriver() {
        return driverThreadLocal.get();
    }
}