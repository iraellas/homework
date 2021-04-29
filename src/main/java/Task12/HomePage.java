package Task12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    private By searchInput = By.xpath("//input[@name='search']");
    private By searchButton = By.cssSelector("button.search-form__submit");
    private By sideBar = By.cssSelector("sidebar-fat-menu");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        //add validation
        driver.findElement(sideBar).isDisplayed();

    }

    public SearchResultPage searchProduct(String searchText) {
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(searchText);
        driver.findElement(searchButton).click();
        return new SearchResultPage(driver);
    }
}
