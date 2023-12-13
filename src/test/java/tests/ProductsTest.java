package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {

    @BeforeMethod
    public void blabla() {
        //DO SOMETHING USEFUL
    }

    @Test(description = "Должна быть возможность купить продукт")
    public void buyProduct() {
        loginPage.open();
        loginPage.login("performance_glitch_user", "secret_sauce");
        productsPage.
                addToCart("Sauce Labs Backpack").
                addToCart("Sauce Labs jknasdljasdjkln").
                openCart().
                isOpened();
        assertTrue(
                cartPage.isProductInTheCart("Sauce Labs Backpack")
        );
        assertEquals(
                cartPage.getProductFromCart(0),
                "Sauce Labs Backpack"
        );
        assertTrue(
                cartPage.getProductNames().contains("Sauce Labs Backpack")
        );
        assertEquals(
                cartPage.getProductPrice("Sauce Labs Backpack"),
                "$29.99"
        );

    }
}
