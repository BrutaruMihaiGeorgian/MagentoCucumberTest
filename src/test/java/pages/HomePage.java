package pages;

import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ElementHelper;

/**
 * Clasa reprezintă pagina principală (Home) a magazinului online.
 * Conține metode pentru a naviga pe site și a interacționa cu elementele vizibile din această pagină.
 */
public class HomePage {

    private static final Logger LOG = LoggerFactory.getLogger(HomePage.class);

    private final WebDriver driver;
    private final ElementHelper helper;

    // Selectori pentru elementele din pagina principală
    private final By consentBtn = By.cssSelector("button[aria-label='Consent']");
    private final By createAccountLink = By.cssSelector("header .panel .authorization-link + li a");
    private final By breatheEasyTank = By.cssSelector("a[title='Breathe-Easy Tank']");
    private final By cartIcon = By.cssSelector("a.action.showcart");

    /**
     * Constructorul primește WebDriver-ul și creează obiectul helper.
     * @param driver instanța WebDriver folosită în test
     */
    public HomePage(WebDriver driver){
        this.driver = driver;
        this.helper = new ElementHelper(driver);
        LOG.info("HomePage inițializat");
    }

    /**
     * Deschide pagina principală a magazinului online.
     */
    public void open(){
        String url = "https://magento.softwaretestingboard.com/";
        LOG.info("Deschid pagina principală: {}", url);
        driver.get(url);
    }

    /**
     * Acceptă cookie-urile dacă este afișat mesajul de consimțământ.
     */
    public void acceptCookiesIfPresent(){
        LOG.info("Verific dacă apare mesajul pentru cookie-uri...");
        if (helper.isPresent(consentBtn)) {
            LOG.info("Mesaj de cookie-uri prezent. Apăs pe Accept.");
            helper.click(consentBtn);
        } else {
            LOG.info("Nu există mesaj de cookie-uri.");
        }
    }

    /**
     * Navighează către pagina de creare a unui cont nou.
     */
    public void goToCreateAccount(){
        LOG.info("Navighez la formularul de creare cont");
        helper.click(createAccountLink);
    }

    /**
     * Deschide pagina produsului „Breathe-Easy Tank”.
     */
    public void openBreatheEasyTank(){
        LOG.info("Deschid produsul 'Breathe-Easy Tank'");
        WebElement el = helper.waitVisible(breatheEasyTank);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        LOG.info("Produsul 'Breathe-Easy Tank' a fost deschis");
    }

    /**
     * Deschide coșul de cumpărături mini (mini-cart).
     */
    public void openMiniCart(){
        LOG.info("Deschid mini-cart-ul");
        helper.click(cartIcon);
    }
}