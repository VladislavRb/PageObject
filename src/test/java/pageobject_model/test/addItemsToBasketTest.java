package pageobject_model.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.SportmasterNikeMdRunner2Page;

import java.util.Arrays;
import java.util.List;

public class AddItemsToBasketTest {

    private WebDriver driver;
    private JavascriptExecutor jsExecutor;

    @BeforeMethod (alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void addSneakersToBasketListTest() {
        List<String> expectedResults = Arrays.asList(
                "Товар добавлен в корзину",
                "Кроссовки мужские Nike Md Runner 2",
                "176,00 руб.",
                "Перейти в корзину"
        );

        List<String> actualResults = new SportmasterNikeMdRunner2Page(driver, jsExecutor)
                .openPage()
                .chooseFirstAvailableSneakersSize()
                .pressOnInBasketButton()
                .readPopupWindowTitleAndSneakersOrderingStatus();

        Assert.assertEquals(actualResults, expectedResults);
    }

    @AfterMethod (alwaysRun = true)
    public void browserQuit() {
        driver.quit();
        driver = null;
    }
}
