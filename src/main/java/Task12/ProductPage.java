package Task12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {
    private By productTypeMain = By.id("#scrollArea");
    private By productTitle = By.cssSelector("h1.product__title");


    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleOfFirstProduct() {
        WebElement element = driver.findElement(productTitle);
        return element.getText();
    }
}
