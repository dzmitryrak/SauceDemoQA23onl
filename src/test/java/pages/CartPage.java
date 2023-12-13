package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void isOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout")));
    }

    @Step("Check if product exist in the cart")
    public boolean isProductInTheCart(String product) {
        return driver.findElement(
                By.xpath(String.format("//div[@class='cart_item']//*[text()='%s']",product)))
                .isDisplayed();
    }

    public String getProductFromCart(int index) {
        return driver.findElements(By.cssSelector(".inventory_item_name"))
                .get(index)
                .getText();
    }

    public ArrayList<String> getProductNames() {
        List<WebElement> allProductElements = driver.findElements(By.cssSelector(".inventory_item_name"));
        ArrayList<String> names = new ArrayList<>();
        for(WebElement product:allProductElements) {
            names.add(product.getText());
        }
        return names;
    }

    public String getProductPrice(String product) {
        return driver.findElement(
                By.xpath(String.format("//*[text()='%s']/ancestor::div[@class='cart_item']//*[@class='inventory_item_price']", product))).getText();
    }
}
