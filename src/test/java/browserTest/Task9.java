package browserTest;

import browserTest.DriverType;
import browserTest.WebDriverFactory;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static com.sun.deploy.cache.Cache.copyFile;

public class Task9 {
    WebDriver driver;
    DriverType driverType = DriverType.IE;

    String url = "https://www.google.com/";

    @BeforeMethod
    public void setUp() {
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.createWebDriver(driverType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    }

    @Test
    public void test() {
        driver.get(url);
        String searchFiledXpath = "//input[@title='Поиск']";
        WebElement searchElement = driver.findElement(By.xpath(searchFiledXpath));
        searchElement.sendKeys("SoftServe");
        searchElement.submit();

        WebElement searchFirstLink = driver.findElement(By.cssSelector("div.yuRUbf"));
        String actualText = searchFirstLink.getText();
        Assert.assertTrue(actualText.contains("SoftServe"));

        searchFirstLink.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.softserveinc.com/uk-ua"));

    }

    @AfterMethod(alwaysRun = true)
    public void quitBrowser() {

        driver.quit();
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenshot(ITestResult result) {
        if (!result.isSuccess())
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                copyFile(scrFile, new File(result.getName() + "[" + LocalDate.now() + "][" + System.currentTimeMillis() + "].png"));
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
    }
}
