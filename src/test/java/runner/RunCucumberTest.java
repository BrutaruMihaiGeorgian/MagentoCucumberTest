package runner;

// Adnotările pentru JUnit Platform Suite (ca să ruleze Cucumber pe motorul JUnit Platform)
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

// Constante folosite de Cucumber pentru configurare (plugin-uri, glue etc.)
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * Runner-ul principal pentru Cucumber + JUnit 5.
 *
 * Explicații:
 * - @Suite → marchează clasa ca o suită de teste pentru JUnit Platform.
 * - @IncludeEngines("cucumber") → spune framework-ului să ruleze testele cu motorul Cucumber.
 * - @SelectClasspathResource("features") → indică directorul unde sunt fișierele .feature
 *   (de obicei în src/test/resources/features).
 * - @ConfigurationParameter → setăm configurări pentru rularea testelor Cucumber:
 *     - GLUE_PROPERTY_NAME → pachetele unde se află step definitions și hooks.
 *     - PLUGIN_PROPERTY_NAME → ce rapoarte/loguri să genereze (pretty, html, junit).
 */
@Suite
@IncludeEngines("cucumber") // Folosim motorul Cucumber
@SelectClasspathResource("features") // Căutăm feature files în src/test/resources/features
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps,hooks") // Pachetele unde avem step definitions
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/cucumber-report.html, junit:target/cucumber-junit.xml" // Formatele de raport generate
)
public class RunCucumberTest {
    // Clasa rămâne goală → rolul ei e doar de "punct de intrare" pentru JUnit + Cucumber.
}