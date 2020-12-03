package pageobject_model.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class SportmasterNikeMdRunner2Page extends AbstractPage {

    private JavascriptExecutor jsExecutor;
    private Wait<WebDriver> wait;

    private static final String HOMEPAGE_URL = "http://www.sportmaster.by/catalogitem/krossovki_mugskie_nike_md_runner_2749794n06010/";

    private WebElement getWebElementByXpath(Wait<WebDriver> wait, String xpath) {
        return wait
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath(xpath)));
    }

    private String extractSneakersInfo(String rawSneakersString) {
        int vendorCodeStartIndex = rawSneakersString.indexOf("\n");

        return rawSneakersString.substring(0, vendorCodeStartIndex);
    }

    public SportmasterNikeMdRunner2Page(WebDriver driver, JavascriptExecutor jsExecutor) {
        super(driver);
        this.jsExecutor = jsExecutor;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Timeout for waiting was exceeded!");
    }

    public SportmasterNikeMdRunner2Page openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public SportmasterNikeMdRunner2Page chooseFirstAvailableSneakersSize() {
        List<WebElement> sneakersSizes = wait
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//li[@class='cb-item-actions-data-sizes']//li")));

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
        WebElement goToBasketLink = getWebElementByXpath(wait, "//a[text()='В корзину']");
        jsExecutor.executeScript("arguments[0].click()", goToBasketLink);

        return this;
    }

    public List<String> readPopupWindowTitleAndSneakersOrderingStatus() {
        WebElement itemPopupWindow = getWebElementByXpath(wait, "//div[@class='cb-item-popup']");

        return Arrays.asList(
                itemPopupWindow.findElement(By.xpath("//p[@class='cb-item-popup-head-heading']")).getAttribute("innerText").trim(),
                extractSneakersInfo(itemPopupWindow.findElement(By.xpath("//div[@class='cb-item-popup-body-text']")).getAttribute("innerText").trim()),
                itemPopupWindow.findElement(By.xpath("//div[@class='cb-item-price-old']")).getText(),
                itemPopupWindow.findElement(By.xpath("//a[contains(@class, 'go_to_order_basket')]")).getAttribute("innerText").trim()
        );
    }
}
