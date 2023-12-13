package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public abstract class BasePage {
    final String BASE_URL = PropertyReader.getProperty("sf.base.url");
    WebDriverWait wait;

    WebDriver driver;

    BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void elementShouldNotExist(By element) {

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        //Проверяем, что коллекция пуста
        int numberOfElements = driver.findElements(element).size();
        assertEquals(numberOfElements, 0, "Элемент присутствует на странице");

        //Возвращаем таймаут
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}
