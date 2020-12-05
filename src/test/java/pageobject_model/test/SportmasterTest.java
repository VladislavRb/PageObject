package pageobject_model.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobject_model.page.SportmasterMensHikingHoodiesPage;
import pageobject_model.page.SportmasterNikeMdRunner2Page;

import java.util.Arrays;
import java.util.List;

public class SportmasterTest {
    private WebDriver driver;
    private JavascriptExecutor jsExecutor;

    @BeforeTest (alwaysRun = true)
    public void browserSetup() {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "\\src\\main\\resources\\chromedriver.exe");

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

    @Test
    public void filterByBrandTest() {
        List<String> allItemTitlesList = new SportmasterMensHikingHoodiesPage(driver)
                .openPage()
                .clickOnIcePeakCategoryLink()
                .readAllItemTitlesOnPage();

        Assert.assertTrue(allItemTitlesList.stream().allMatch(itemTitle -> itemTitle.contains("IcePeak")));
    }

    @AfterTest (alwaysRun = true)
    public void browserQuit() {
        driver.quit();
        driver = null;
    }
}
