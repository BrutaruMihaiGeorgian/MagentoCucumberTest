package pages;

import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ElementHelper;

/**
 * Clasa reprezintă pagina de înregistrare a unui cont nou.
 * Conține metode pentru completarea formularului și trimiterea acestuia.
 */
public class RegisterPage {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterPage.class);

    private final ElementHelper helper;

    // Selectori pentru câmpurile formularului de înregistrare
    private final By firstName = By.id("firstname");
    private final By lastName = By.id("lastname");
    private final By email = By.id("email_address");
    private final By password = By.id("password");
    private final By confirmPassword = By.id("password-confirmation");
    private final By submitBtn = By.cssSelector("#form-validate button[type='submit']");
    private final By successMsg = By.cssSelector("div[data-bind*='prepareMessageForHtml']");

    /**
     * Constructorul primește WebDriver-ul și creează obiectul helper.
     * @param driver instanța WebDriver folosită în test
     */
    public RegisterPage(WebDriver driver){
        this.helper = new ElementHelper(driver);
        LOG.info("Pagina RegisterPage inițializată");
    }

    /**
     * Completează câmpurile cu prenume și nume.
     */
    public void fillName(String first, String last){
        LOG.info("Completez prenumele: {} și numele: {}", first, last);
        helper.insertText(firstName, first);
        helper.insertText(lastName, last);
        LOG.debug("Prenume și nume introduse cu succes");
    }

    /**
     * Completează câmpurile pentru email și parolă.
     */
    public void fillCredentials(String emailStr, String pass){
        LOG.info("Completez datele de autentificare: email={} și parola=****", emailStr);
        helper.insertText(email, emailStr);
        helper.insertText(password, pass);
        helper.insertText(confirmPassword, pass);
        LOG.debug("Email și parolă completate cu succes");
    }

    /**
     * Apasă butonul „Create an Account”.
     */
    public void submit(){
        LOG.info("Apăs butonul 'Create an Account'");
        helper.click(submitBtn);
        LOG.debug("Butonul de submit a fost apăsat");
    }

    /**
     * Returnează mesajul de succes după crearea contului.
     */
    public String getSuccessMessage(){
        LOG.info("Citesc mesajul de succes de pe pagină");
        String msg = helper.waitVisible(successMsg).getText();
        LOG.info("Mesaj obținut: {}", msg);
        return msg;
    }
}