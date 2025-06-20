import org.example.AmbiguityResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AmbiguityResolverTest {

    private AmbiguityResolver resolver;

    @BeforeEach
    void setUp() {
        resolver = new AmbiguityResolver();
    }

    @Test
    void testSingleTokenInterpretationWithoutAmbiguity() {
        String[] tokens = {"10"};
        List<String> interpretations = resolver.generateInterpretations(tokens);

        assertEquals(1, interpretations.size());
        assertTrue(interpretations.contains("10"));
    }

    @Test
    void testSingleTokenInterpretationWithAmbiguity() {
        String[] tokens = {"21"};
        List<String> interpretations = resolver.generateInterpretations(tokens);

        assertEquals(2, interpretations.size());
        assertTrue(interpretations.contains("21"));
        assertTrue(interpretations.contains("201"));
    }

    @Test
    void testTwoTokensWithRegularAmbiguity() {
        String[] tokens = {"20", "1"};
        List<String> interpretations = resolver.generateInterpretations(tokens);

        assertTrue(interpretations.contains("201"));
        assertTrue(interpretations.contains("21"));
        assertTrue(interpretations.contains("201"));
        assertTrue(interpretations.contains("201"));
    }

    @Test
    void testTwoTokensWithoutAmbiguity() {
        String[] tokens = {"11", "12"};
        List<String> interpretations = resolver.generateInterpretations(tokens);

        assertEquals(4, interpretations.size());
        assertTrue(interpretations.contains("1112"));
        assertTrue(interpretations.contains("11102"));
        assertTrue(interpretations.contains("10112"));
        assertTrue(interpretations.contains("101102"));
    }


    @Test
    void testStartOnlyAmbiguity_appliesOnlyAtStart() {
        String[] tokens = {"69", "30"};
        List<String> interpretations = resolver.generateInterpretations(tokens);

        assertTrue(interpretations.contains("6930"));
        assertTrue(interpretations.contains("693"));
    }

    @Test
    void testStartOnlyAmbiguity_notAppliedInMiddle() {
        String[] tokens = {"1", "69", "30"};
        List<String> interpretations = resolver.generateInterpretations(tokens);

        assertFalse(interpretations.contains("1693"));
        assertTrue(interpretations.contains("16930"));
    }

    @Test
    void testMultipleAmbiguitiesCombined() {
        String[] tokens = {"20", "1", "21"};
        List<String> interpretations = resolver.generateInterpretations(tokens);

        assertTrue(interpretations.contains("20121"));
        assertTrue(interpretations.contains("2121"));
        assertTrue(interpretations.contains("201201"));
        assertTrue(interpretations.contains("21201"));
    }
}
