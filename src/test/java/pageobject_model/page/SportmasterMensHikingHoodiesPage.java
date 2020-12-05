package pageobject_model.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SportmasterMensHikingHoodiesPage extends AbstractPage {
    private static final String HOMEPAGE_URL = "http://www.sportmaster.by/catalog/mugskaya_odegda88-dgemperi_i_sviteri26/?filter-170=1866";

    @FindBy(xpath = "//a[contains(text(), 'IcePeak')]")
    private WebElement IcePeakCategoryLink;

    @FindBy(xpath = "//a[@class='products-list__box-name']/div[1]")
    private List<WebElement> filteredHoodieTitlesList;

    public SportmasterMensHikingHoodiesPage(WebDriver driver) {
        super(driver);
    }

    public SportmasterMensHikingHoodiesPage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public SportmasterMensHikingHoodiesPage clickOnIcePeakCategoryLink() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", IcePeakCategoryLink);
        return this;
    }

    public List<String> readAllItemTitlesOnPage() {
        return filteredHoodieTitlesList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
