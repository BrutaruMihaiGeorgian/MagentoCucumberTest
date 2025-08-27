package steps;

import io.cucumber.java.en.*; // Adnotări Gherkin: @Given, @When, @Then
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Clasa MagentoSteps conține definițiile pentru pașii din scenariile .feature.
 * - Legătura se face prin textul dintre ghilimele (ex: "I go to {string}").
 * - Fiecare step apelează metode din Page Object Model (HomePage, RegisterPage, etc.).
 * - Se folosesc loguri SLF4J pentru a urmări execuția testului.
 */
public class MagentoSteps {

    // Logger pentru pașii executați
    private static final Logger LOG = LoggerFactory.getLogger(MagentoSteps.class);

    // Helper pentru acces rapid la driver-ul salvat în TestContext
    private WebDriver driver() {
        return TestContext.DRIVER;
    }

    // ========================
    // 🔹 Background steps
    // ========================

    @Given("the browser is open")
    public void theBrowserIsOpen() {
        LOG.info("Browser started in Hooks (inițializat în @Before)");
    }

    @Given("I go to {string}")
    public void iGoTo(String url) {
        // HomePage.open() știe deja URL-ul → parametrul "url" e doar pentru lizibilitate în .feature
        new HomePage(driver()).open();
        LOG.info("Navigated to {}", url);
    }

    @Given("I accept cookies if present")
    public void iAcceptCookiesIfPresent() {
        new HomePage(driver()).acceptCookiesIfPresent();
        LOG.info("Accepted cookies if present");
    }

    // ========================
    // 🔹 Create Account flow
    // ========================

    @When("I open the Create Account page")
    public void iOpenTheCreateAccountPage() {
        new HomePage(driver()).goToCreateAccount();
        LOG.info("Opened Create Account page");
    }

    @When("I register with first name {string} last name {string} random email and password {string}")
    public void iRegister(String first, String last, String password) {
        LOG.info("Registering with random email={}", TestContext.EMAIL);
        RegisterPage register = new RegisterPage(driver());
        register.fillName(first, last);
        register.fillCredentials(TestContext.EMAIL, password); // email random din Hooks
        register.submit();
    }

    @Then("I should see the text {string}")
    public void iShouldSeeTheText(String expected) {
        String actual = new RegisterPage(driver()).getSuccessMessage();
        LOG.info("Assert register: expected='{}', actual='{}'", expected, actual);
        assertEquals(expected, actual);
    }

    // ========================
    // 🔹 Order flow
    // ========================

    @When("I open the Breathe-Easy Tank product page")
    public void iOpenProductPage() {
        new HomePage(driver()).openBreatheEasyTank();
        LOG.info("Opened Breathe-Easy Tank page");
    }

    @When("I choose size M and color Blue and add to cart")
    public void iConfigureAndAddToCart() {
        ProductPage product = new ProductPage(driver());
        product.selectSize();
        product.selectColor();
        product.addToCart();
        LOG.info("Selected size M, color Blue, added to cart");
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        HomePage home = new HomePage(driver());
        home.openMiniCart();
        new MiniCartPage(driver()).proceedToCheckout();
        LOG.info("Proceeded to checkout");
    }

    @When("I sign in during checkout with the same email and password")
    public void iSignInDuringCheckout() {
        CheckoutPage checkout = new CheckoutPage(driver());
        checkout.signInDuringCheckout(TestContext.EMAIL, TestContext.PASSWORD);
        LOG.info("Signed in during checkout");
    }

    @When("I fill the address and choose shipping")
    public void iFillAddressAndChooseShipping() {
        CheckoutPage checkout = new CheckoutPage(driver());
        checkout.fillAddress();
        checkout.chooseShippingAndContinue();
        LOG.info("Filled address and selected shipping option");
    }

    @Then("I should see order total {string}")
    public void iShouldSeeOrderTotal(String expectedTotal) {
        String actual = new CheckoutPage(driver()).getTotal();
        LOG.info("Assert total: expected={}, actual={}", expectedTotal, actual);
        assertEquals(expectedTotal, actual);
    }

    @When("I place the order")
    public void iPlaceTheOrder() {
        new CheckoutPage(driver()).placeOrder();
        LOG.info("Order placed");
    }

    @Then("I should see confirmation {string}")
    public void iShouldSeeConfirmation(String expected) {
        String actual = new CheckoutPage(driver()).getConfirmation();
        LOG.info("Assert confirmation: expected='{}', actual='{}'", expected, actual);
        assertEquals(expected, actual);
    }
}