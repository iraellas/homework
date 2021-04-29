package Task12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends BasePage {
    private By resultsGrid = By.cssSelector("rz-grid");
    private By firstProductSearch = By.xpath("//rz-grid/ul/li[1]//span[contains(@class,'goods-tile__title')]");


    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        driver.findElement(resultsGrid).isEnabled();

    }


    public ProductPage clickFirstProduct() {
        driver.findElement(firstProductSearch).click();
        return new ProductPage(driver);
    }
}
