package testing;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task7 {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();

    }

    @BeforeMethod
    public void openBrowser() {
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");
    }

    @Test
    public void test() {
        String searchText = "Mонитор";
        WebElement searchElement = driver.findElement(By.xpath("//input[@name='search']"));
        searchElement.sendKeys(searchText);

        WebElement searchButtonElement = driver.findElement(By.cssSelector("button.search-form__submit"));
        searchButtonElement.click();

        WebElement firstProductElement = driver.findElement(By.xpath("//rz-grid/ul/li[1]//span[contains(@class,'goods-tile__title')]"));
        String actualText = firstProductElement.getText();
        Assert.assertTrue(actualText.contains(searchText));
    }

    @AfterMethod
    public void quitBrowser() {
        driver.quit();
    }
}
