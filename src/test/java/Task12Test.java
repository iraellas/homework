import Task12.HomePage;
import Task12.ProductPage;
import Task12.SearchResultPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Task12Test {
    WebDriver driver;

    @BeforeSuite
    public void setProperties() {
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://rozetka.com.ua/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void searchProduct() {
        String searchText = "Микрофон";
        HomePage homePage = new HomePage(driver);
        SearchResultPage searchResultPage = homePage.searchProduct(searchText);

        ProductPage productPage = searchResultPage.clickFirstProduct();
        String actualText = productPage.getTitleOfFirstProduct();
        Assert.assertTrue(actualText.contains(searchText));


    }

    @AfterMethod(alwaysRun = true)
    public void quitBrowser() {

        driver.quit();
    }

}
