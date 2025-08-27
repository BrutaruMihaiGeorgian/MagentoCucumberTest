package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ElementHelper;

/**
 * Clasa reprezintă mini-coșul de cumpărături care apare
 * atunci când utilizatorul apasă pe pictograma coșului.
 * De aici se poate merge mai departe la pagina de checkout.
 */
public class MiniCartPage {

    private static final Logger LOG = LoggerFactory.getLogger(MiniCartPage.class);

    // Helper pentru acțiuni comune pe elemente
    private final ElementHelper helper;

    // Selector pentru butonul „Proceed to Checkout” din mini-cart
    private final By checkoutBtn = By.id("top-cart-btn-checkout");

    /**
     * Constructorul primește WebDriver-ul și creează obiectul helper.
     * @param driver instanța WebDriver folosită în test
     */
    public MiniCartPage(WebDriver driver){
        this.helper = new ElementHelper(driver);
        LOG.info("MiniCartPage inițializat");
    }

    /**
     * Apasă butonul „Proceed to Checkout” din mini-cart
     * pentru a merge la pagina de checkout.
     */
    public void proceedToCheckout(){
        LOG.info("Apăs pe butonul 'Proceed to Checkout' din mini-cart");
        helper.click(checkoutBtn);
        LOG.info("Am apăsat pe 'Proceed to Checkout'");
    }
}