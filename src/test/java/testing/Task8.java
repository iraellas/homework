package testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static com.sun.deploy.cache.Cache.copyFile;

public class Task8 {
    WebDriver driver;
    WebDriverWait wait;
    By inputSearch = By.xpath("//input[@name='search']");
    By searchButton = By.cssSelector("button.search-form__submit");
    By firstProductSearch = By.xpath("//rz-grid/ul/li[1]//span[contains(@class,'goods-tile__title')]");
    By buttonBuySearch = By.cssSelector("button.button_with_icon.button_color_green");
    By buttonCheckoutSearch = By.cssSelector("a.button_color_green");

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);

    }

    @BeforeMethod
    public void openBrowser() {
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");

    }

    @Test
    public void test()  {
        String searchText = "Mонитор";
        WebElement searchElement = driver.findElement(inputSearch);
        searchElement.sendKeys(searchText);

        WebElement searchButtonElement = driver.findElement(searchButton);
        searchButtonElement.click();

        WebElement firstProductElement = driver.findElement(firstProductSearch);
        firstProductElement.click();
        wait.until(driver -> driver.findElement(buttonBuySearch).isDisplayed());

        WebElement searchButtonBuy = driver.findElement(buttonBuySearch);
        searchButtonBuy.click();
        wait.until(driver -> driver.findElement(buttonCheckoutSearch).isDisplayed());

        WebElement searchButtonCheckout = driver.findElement(buttonCheckoutSearch);
        searchButtonCheckout.click();
        wait.until(driver -> driver.findElement(By.cssSelector("input.button")).isDisplayed());
    }

    @AfterMethod
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
