package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.TestContext;

import java.time.Duration;
import java.util.UUID;

/**
 * Clasa Hooks definește acțiuni care rulează înainte și după fiecare scenariu Cucumber.
 * - @Before: rulează la începutul fiecărui scenariu.
 * - @After: rulează la finalul fiecărui scenariu.
 *
 * În acest caz:
 * - inițializăm WebDriver și datele de test (email, parolă).
 * - închidem browserul după fiecare scenariu.
 */
public class Hooks {
    // Logger pentru a scrie mesaje în consolă și fișier
    private static final Logger LOG = LoggerFactory.getLogger(Hooks.class);

    /**
     * Rulează primul (@Before order=0).
     * Se ocupă de configurarea binarului ChromeDriver prin WebDriverManager.
     * Astfel nu trebuie să instalezi manual driverul, îl descarcă automat.
     */
    @Before(order = 0)
    public void setupBinary() {
        WebDriverManager.chromedriver().setup();
    }

    /**
     * Rulează după setupBinary (@Before order=1).
     * - Deschide un browser Chrome nou.
     * - Setează implicit wait la 5 secunde.
     * - Maximizează fereastra.
     * - Generează un email random și setează parola.
     * - Salvează driverul și datele în TestContext (accesibile în step definitions).
     */
    @Before(order = 1)
    public void openBrowser() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        TestContext.DRIVER = driver;
        TestContext.EMAIL = "user" + UUID.randomUUID().toString().substring(0, 6) + "@test.com";
        TestContext.PASSWORD = "Pa$$w0rd";

        LOG.info("=== Browser started | EMAIL={} ===", TestContext.EMAIL);
    }

    /**
     * Rulează după fiecare scenariu.
     * - Încearcă să închidă browserul cu driver.quit().
     * - Scrie în log dacă apare vreo eroare.
     * - Resetează TestContext.DRIVER la null.
     */
    @After
    public void closeBrowser() {
        if (TestContext.DRIVER != null) {
            try {
                TestContext.DRIVER.quit();
                LOG.info("=== Browser closed ===");
            } catch (Exception e) {
                LOG.warn("Error on driver.quit(): {}", e.getMessage());
            } finally {
                TestContext.DRIVER = null;
            }
        }
    }
}