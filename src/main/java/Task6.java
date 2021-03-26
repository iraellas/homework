import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task6 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");

        String searchFiledXpath = "//input[@title='Поиск']";
        WebElement searchElement = driver.findElement(By.xpath(searchFiledXpath));
        searchElement.sendKeys("SoftServe it academy");
        searchElement.submit();
        searchElement = driver.findElement(By.className("yuRUbf"));
        searchElement.click();
       String currentUrl = driver.getCurrentUrl();
        driver.quit();

    }
}
