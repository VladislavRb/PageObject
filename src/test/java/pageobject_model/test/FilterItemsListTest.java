package pageobject_model.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FilterItemsListTest {

    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test
    public void filterByBrandTest() {
        List<String> allManufacturerNames = new SportmasterNikeMdRunner2Page(driver)
                .openPage()
                .clickOnIcePeakCategoryLink()
                .readManufacturerNameOfAllHoodiesOnPage();

        Assert.assertEquals(actualResults, expectedResults);
    }

    @AfterMethod (alwaysRun = true)
    public void browserQuit() {
        driver.quit();
        driver = null;
    }
}
