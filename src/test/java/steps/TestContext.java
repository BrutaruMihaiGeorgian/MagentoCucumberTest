package steps;

import org.openqa.selenium.WebDriver;

/**
 * Clasă statică pentru a stoca și partaja obiecte/comune între pașii Cucumber.
 *
 * ➡ Aceasta funcționează ca un "context de test" (Test Context):
 *    - Permite accesarea driver-ului WebDriver din orice clasă de tip Step.
 *    - Stochează date temporare (ex: email și parolă) care trebuie reutilizate
 *      pe parcursul aceluiași scenariu.
 *
 * ❗ Atenție: fiind câmpuri statice, valorile sunt comune pentru toate testele..
 */
public class TestContext {

    /**
     * Instanța curentă de WebDriver (ChromeDriver, FirefoxDriver etc.).
     * Este setată în Hooks @Before și resetată în @After.
     */
    public static WebDriver DRIVER;

    /**
     * Adresa de email generată aleator pentru testul curent.
     * Folosită la înregistrarea unui utilizator nou și apoi la logare.
     */
    public static String EMAIL;

    /**
     * Parola asociată contului de test.
     * Setată în Hooks și folosită atât la înregistrare cât și la logare.
     */
    public static String PASSWORD;
}