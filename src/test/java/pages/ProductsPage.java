package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector(".title");
    private final String ADD_TO_CART_PATTERN = "//*[text()='%s']/ancestor::*[@class='inventory_item']//button";
    private final By CART_LINK = By.id("shopping_cart_container");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "inventory.html");
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        //NoSuchElementException
        return driver.findElement(TITLE).getText();
    }

    public ProductsPage addToCart(String product) {
        By addToCartButton = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
        driver.findElement(addToCartButton).click();
        return this;
    }

    public void addToCart(int index) {
        driver.findElements(By.xpath("//*[@class='inventory_item']//button")).get(index).click();
    }

    public CartPage openCart() {
        driver.findElement(CART_LINK).click();
        return new CartPage(driver);
    }
}
