package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "User is not logged in or wrong page is opened");
    }

    @DataProvider(name = "Данные для логина")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"standar_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"}
        };
    }


    @Test(dataProvider = "Данные для логина")
    public void negativeLogin(String user, String password, String expectedError) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(
                loginPage.getError(),
                expectedError,
                "Wrong error message is shown");
    }

    @Test
    public void emptyLogin() {
        loginPage.open();
        loginPage.login("", "");
        assertEquals(
                loginPage.getError(),
                "Epic sadface: Username is required",
                "SO BAAAAD");
    }

    @Test
    public void emptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(
                loginPage.getError(),
                "Epic sadface: Password is required",
                "SO BAAAAD");
    }

    @Test
    public void wrongLoginOrPassword() {
        loginPage.open();
        loginPage.login("standart_user", "secret_sauce");
        assertEquals(
                loginPage.getError(),
                "Epic sadface: Username and password do not match any user in this service",
                "SO BAAAAD");
    }
}
