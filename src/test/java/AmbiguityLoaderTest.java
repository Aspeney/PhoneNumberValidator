import org.example.AmbiguityLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AmbiguityLoaderTest {

    private AmbiguityLoader loader;

    @BeforeEach
    void setUp() {
        loader = new AmbiguityLoader();
    }

    @Test
    void testLoadRegularRules_containsExpectedCompositeEntries() {
        Map<String, List<String>> rules = loader.loadRegularRules();

        // Example check: "20 1" should map to ["21", "201"]
        assertTrue(rules.containsKey("20 1"));
        assertEquals(List.of("21", "201"), rules.get("20 1"));

        // Check another
        assertEquals(List.of("29", "209"), rules.get("20 9"));

        // Spot-check for the last pair: "90 9" -> ["99", "909"]
        assertEquals(List.of("99", "909"), rules.get("90 9"));
    }

    @Test
    void testLoadRegularRules_containsSingleTokenAmbiguities() {
        Map<String, List<String>> rules = loader.loadRegularRules();

        // "21" should map to ["21", "201"]
        assertTrue(rules.containsKey("21"));
        assertEquals(List.of("21", "201"), rules.get("21"));

        // "32" -> ["32", "302"]
        assertEquals(List.of("32", "302"), rules.get("32"));

        // Should not contain tokens like "10", "20", ..., "90" as those are excluded (i % 10 == 0)
        assertFalse(rules.containsKey("10"));
        assertFalse(rules.containsKey("20"));
        assertFalse(rules.containsKey("90"));
    }

    @Test
    void testLoadStartOnlyRules_containsExpectedEntry() {
        Map<String, List<String>> rules = loader.loadStartOnlyRules();

        assertEquals(1, rules.size());
        assertTrue(rules.containsKey("69 30"));
        assertEquals(List.of("6930", "693"), rules.get("69 30"));
    }
}
