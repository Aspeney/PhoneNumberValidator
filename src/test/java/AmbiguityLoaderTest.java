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
    void shouldLoadRegularRulesIfContainsExpectedCompositeEntries() {
        Map<String, List<String>> rules = loader.loadRegularRules();

        assertTrue(rules.containsKey("20 1"));
        assertEquals(List.of("21", "201"), rules.get("20 1"));
        assertEquals(List.of("29", "209"), rules.get("20 9"));
        assertEquals(List.of("99", "909"), rules.get("90 9"));
    }

    @Test
    void shouldLoadRegularRulesIfContainsSingleTokenAmbiguities() {
        Map<String, List<String>> rules = loader.loadRegularRules();

        assertTrue(rules.containsKey("21"));
        assertEquals(List.of("21", "201"), rules.get("21"));
        assertEquals(List.of("32", "302"), rules.get("32"));
        assertFalse(rules.containsKey("10"));
        assertFalse(rules.containsKey("20"));
        assertFalse(rules.containsKey("90"));
    }

    @Test
    void shouldLoadStartOnlyRulesIfContainsExpectedEntry() {
        Map<String, List<String>> rules = loader.loadStartOnlyRules();

        assertEquals(1, rules.size());
        assertTrue(rules.containsKey("69 30"));
        assertEquals(List.of("6930", "693"), rules.get("69 30"));
    }
}
