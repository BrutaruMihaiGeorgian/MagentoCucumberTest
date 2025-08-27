package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Clasă utilitară care conține metode comune pentru interacțiunea cu elementele din pagină.
 * Folosește WebDriverWait pentru așteptări explicite, astfel încât testele să fie mai stabile.
 * Fiecare acțiune este logată pentru debugging și trasabilitate.
 */
public class ElementHelper {

    private static final Logger LOG = LoggerFactory.getLogger(ElementHelper.class);

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ElementHelper(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public WebElement waitVisible(By locator){
        LOG.debug("Waiting for element to be visible: {}", locator);
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        LOG.trace("Element is visible: {}", locator);
        return el;
    }

    public WebElement waitClickable(By locator){
        LOG.debug("Waiting for element to be clickable: {}", locator);
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        LOG.trace("Element is clickable: {}", locator);
        return el;
    }

    public void click(By locator){
        LOG.info("Clicking on element: {}", locator);
        waitClickable(locator).click();
        LOG.trace("Clicked element: {}", locator);
    }

    public void insertText(By locator, String text){
        LOG.info("Inserting text '{}' into element: {}", text, locator);
        WebElement el = waitVisible(locator);
        el.clear();
        el.sendKeys(text);
        LOG.trace("Text inserted: {}", text);
    }

    public boolean isPresent(By locator){
        boolean present = !driver.findElements(locator).isEmpty();
        LOG.debug("Checking presence of element {} -> {}", locator, present);
        return present;
    }
}