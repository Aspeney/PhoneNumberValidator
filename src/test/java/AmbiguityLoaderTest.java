import org.example.AmbiguityLoader;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class AmbiguityLoaderTest {

    @Test
    void testLoadRegularRulesGeneratesExpectedKeys() {
        AmbiguityLoader loader = new AmbiguityLoader();
        Map<String, List<String>> rules = loader.loadRegularRules();

        assertNotNull(rules);
        assertFalse(rules.isEmpty());

        // Two-token ambiguity
        assertTrue(rules.containsKey("20 1"));
        assertIterableEquals(List.of("21", "201"), rules.get("20 1"));

        assertTrue(rules.containsKey("30 6"));
        assertIterableEquals(List.of("36", "306"), rules.get("30 6"));

        // One-token ambiguity
        assertTrue(rules.containsKey("69"));
        assertIterableEquals(List.of("69", "609"), rules.get("69"));
    }

    @Test
    void testStartOnlyRulesAreLoadedCorrectly() {
        AmbiguityLoader loader = new AmbiguityLoader();
        Map<String, List<String>> startOnly = loader.loadStartOnlyRules();

        assertNotNull(startOnly);
        assertTrue(startOnly.containsKey("69 30"));
        assertIterableEquals(List.of("6930", "693"), startOnly.get("69 30"));
    }
}
