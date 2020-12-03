package pageobject_model.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public class SportmasterNikeMdRunner2Page extends AbstractPage {

    private JavascriptExecutor jsExecutor;

    private final By locatorForPopupWindowHeader = By.xpath("//p[@class='cb-item-popup-head-heading']");
    private final By locatorForSneakersName = By.xpath("//div[@class='cb-item-popup-body-text']");
    private final By locatorForSneakersPrice = By.xpath("//div[@class='cb-item-price-old']");
    private final By locatorForBasketLink = By.xpath("//a[contains(@class, 'go_to_order_basket')]");

    private static final String HOMEPAGE_URL = "http://www.sportmaster.by/catalogitem/krossovki_mugskie_nike_md_runner_2749794n06010/";

    @FindBy(xpath = "//li[@class='cb-item-actions-data-sizes']//li")
    private List<WebElement> sneakersSizes;

    @FindBy(xpath = "//a[text()='В корзину']")
    private WebElement goToBasketLink;

    @FindBy(xpath = "//div[@class='cb-item-popup']")
    private WebElement itemPopupWindow;

    private String extractSneakersInfo(String rawSneakersString) {
        int vendorCodeStartIndex = rawSneakersString.indexOf("\n");

        return rawSneakersString.substring(0, vendorCodeStartIndex);
    }

    public SportmasterNikeMdRunner2Page(WebDriver driver, JavascriptExecutor jsExecutor) {
        super(driver);
        this.jsExecutor = jsExecutor;
    }

    public SportmasterNikeMdRunner2Page openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public SportmasterNikeMdRunner2Page chooseFirstAvailableSneakersSize() {
        WebElement firstAvailableSizeInput = sneakersSizes.stream()
                .filter(webElement -> webElement.findElement(By.tagName("input")).isEnabled())
                .findFirst()
                .get()
                .findElement(By.tagName("input"));

        jsExecutor.executeScript(String.format("document.getElementById('%s').setAttribute('class', 'checked')",
                firstAvailableSizeInput.getAttribute("id")));

        return this;
    }

    public SportmasterNikeMdRunner2Page pressOnInBasketButton() {
        jsExecutor.executeScript("arguments[0].click()", goToBasketLink);

        return this;
    }

    public List<String> readPopupWindowTitleAndSneakersOrderingStatus() {
        return Arrays.asList(
                itemPopupWindow.findElement(locatorForPopupWindowHeader).getAttribute("innerText").trim(),
                extractSneakersInfo(itemPopupWindow.findElement(locatorForSneakersName).getAttribute("innerText").trim()),
                itemPopupWindow.findElement(locatorForSneakersPrice).getText(),
                itemPopupWindow.findElement(locatorForBasketLink).getAttribute("innerText").trim()
        );
    }
}
