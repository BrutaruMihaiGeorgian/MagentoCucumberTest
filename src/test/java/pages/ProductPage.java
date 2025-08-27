package pages;

import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ElementHelper;

/**
 * Clasa reprezintă pagina unui produs individual din magazinul online.
 * Aici putem alege mărimea, culoarea și putem adăuga produsul în coș.
 */
public class ProductPage {

    private static final Logger LOG = LoggerFactory.getLogger(ProductPage.class);

    private final WebDriver driver;
    private final ElementHelper helper;

    // Selectori pentru opțiunile și butonul produsului
    private final By sizeM = By.id("option-label-size-143-item-168");
    private final By colorBlue = By.id("option-label-color-93-item-60");
    private final By addToCart = By.id("product-addtocart-button");

    /**
     * Constructorul primește WebDriver-ul și creează obiectul helper.
     * @param driver instanța WebDriver folosită în test
     */
    public ProductPage(WebDriver driver){
        this.driver = driver;
        this.helper = new ElementHelper(driver);
        LOG.info("ProductPage inițializat");
    }

    /**
     * Selectează mărimea M a produsului.
     */
    public void selectSize(){
        LOG.info("Selectez mărimea M pentru produs");
        helper.click(sizeM);
        LOG.debug("Click efectuat pe elementul cu ID-ul {}", sizeM);
    }

    /**
     * Selectează culoarea Albastru a produsului.
     */
    public void selectColor(){
        LOG.info("Selectez culoarea Albastru pentru produs");
        helper.click(colorBlue);
        LOG.debug("Click efectuat pe elementul cu ID-ul {}", colorBlue);
    }

    /**
     * Adaugă produsul în coș folosind JavaScriptExecutor.
     */
    public void addToCart(){
        LOG.info("Încerc să adaug produsul în coș");

        WebElement btn = helper.waitClickable(addToCart);
        LOG.debug("Butonul Add to Cart a devenit clicabil");

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );
        LOG.debug("Am făcut scroll până la butonul Add to Cart");

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        LOG.info("Produsul a fost adăugat în coș");
    }
}