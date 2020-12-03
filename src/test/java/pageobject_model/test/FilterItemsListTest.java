package pageobject_model.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.SportmasterMensHikingHoodiesPage;

import java.util.List;

public class FilterItemsListTest {

    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test
    public void filterByBrandTest() {
        List<String> allItemTitlesList = new SportmasterMensHikingHoodiesPage(driver)
                .openPage()
                .clickOnIcePeakCategoryLink()
                .readAllItemTitlesOnPage();

        Assert.assertTrue(allItemTitlesList.stream().allMatch(itemTitle -> itemTitle.contains("IcePeak")));
    }

    @AfterMethod (alwaysRun = true)
    public void browserQuit() {
        driver.quit();
        driver = null;
    }
}
