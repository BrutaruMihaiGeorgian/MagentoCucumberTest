package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ElementHelper;

/**
 * Clasa reprezintă pagina de checkout din magazinul online.
 * Aici sunt localizate elementele și sunt definite metodele
 * pentru a finaliza procesul de comandă.
 */
public class CheckoutPage {

    private static final Logger LOG = LoggerFactory.getLogger(CheckoutPage.class);

    // Helper pentru acțiuni comune pe elemente (click, scriere text, așteptare)
    private final ElementHelper helper;

    // Selectori pentru câmpurile și butoanele din pagina de checkout
    private final By email = By.id("customer-email");
    private final By password = By.id("customer-password");
    private final By signInBtn = By.cssSelector("#customer-email-fieldset button");

    private final By street = By.cssSelector("input[name='street[0]']");
    private final By city = By.cssSelector("input[name='city']");
    private final By region = By.cssSelector("select[name='region_id']");
    private final By postcode = By.cssSelector("input[name='postcode']");
    private final By country = By.cssSelector("select[name='country_id']");
    private final By phone = By.cssSelector("input[name='telephone']");

    private final By shippingMethod15 = By.xpath("//span[@class='price' and contains(text(),'$15.00')]");
    private final By continueBtn = By.cssSelector("button[data-role='opc-continue']");

    private final By orderTotal = By.cssSelector("td[data-th='Order Total'] .price");
    private final By placeOrder = By.cssSelector("button[title='Place Order']");
    private final By thankYou = By.cssSelector("span.base");

    /**
     * Constructorul primește WebDriver-ul și creează obiectul helper.
     * @param driver instanța WebDriver folosită în test
     */
    public CheckoutPage(WebDriver driver){
        helper = new ElementHelper(driver);
        LOG.info("CheckoutPage inițializat");
    }

    /**
     * Autentificare în timpul procesului de checkout.
     */
    public void signInDuringCheckout(String emailStr, String pwd){
        LOG.info("Autentificare în checkout cu email: {}", emailStr);
        helper.insertText(email, emailStr);
        helper.insertText(password, pwd);
        helper.click(signInBtn);
        LOG.info("Butonul 'Sign In' a fost apăsat");
    }

    /**
     * Completează formularul de adresă pentru livrare.
     */
    public void fillAddress(){
        LOG.info("Completare adresă de livrare...");
        new Select(helper.waitVisible(region)).selectByValue("43");
        helper.insertText(street, "Calea Martirilor");
        helper.insertText(city, "Oradea");
        helper.insertText(postcode, "12345-6789");
        new Select(helper.waitVisible(country)).selectByValue("US");
        helper.insertText(phone, "0700000000");
        LOG.info("Adresă completată cu succes.");
    }

    /**
     * Alege metoda de livrare de $15 și trece la pasul următor.
     */
    public void chooseShippingAndContinue(){
        LOG.info("Selectare metodă de livrare ($15.00) și continuare...");
        helper.click(shippingMethod15);
        helper.click(continueBtn);
        LOG.info("Metoda de livrare selectată și s-a apăsat 'Continue'");
    }

    /**
     * Returnează valoarea totală a comenzii afișată în pagina de checkout.
     */
    public String getTotal(){
        String total = helper.waitVisible(orderTotal).getText();
        LOG.info("Totalul comenzii afișat: {}", total);
        return total;
    }

    /**
     * Plasează comanda apăsând butonul „Place Order”.
     */
    public void placeOrder(){
        LOG.info("Apăs pe butonul 'Place Order'");
        helper.click(placeOrder);
    }

    /**
     * Returnează mesajul de confirmare după plasarea comenzii.
     */
    public String getConfirmation(){
        String msg = helper.waitVisible(thankYou).getText();
        LOG.info("Mesaj de confirmare primit: {}", msg);
        return msg;
    }
}