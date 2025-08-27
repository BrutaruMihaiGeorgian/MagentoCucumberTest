package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clasă utilitară pentru generarea de adrese de email aleatorii.
 * Este folosită în teste automate atunci când trebuie creat un cont nou
 * și nu vrem să folosim aceeași adresă de email de două ori.
 */
public class EmailUtils {

    private static final Logger LOG = LoggerFactory.getLogger(EmailUtils.class);

    /**
     * Generează o adresă de email aleatorie în format:
     *  - 5 litere mici (a-z)
     *  - 5 cifre (0-9)
     *  - sufixul "@email.com"
     *
     * @return adresa de email generată, de exemplu "abcde12345@email.com"
     */
    public static String randomEmail() {
        StringBuilder letters = new StringBuilder();
        StringBuilder digits = new StringBuilder();

        // Generăm partea cu litere
        for (int i = 0; i < 5; i++) {
            letters.append((char) ('a' + Math.random() * 26));
        }

        // Generăm partea cu cifre
        for (int i = 0; i < 5; i++) {
            digits.append((int) (Math.random() * 10));
        }

        String email = letters + digits.toString() + "@email.com";

        // Logăm email-ul generat
        LOG.info("Generated random email: {}", email);

        return email;
    }
}