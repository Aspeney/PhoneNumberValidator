import org.example.AmbiguityResolver;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AmbiguityResolverTest {

    private static final String FILE_NAME = "ambiguities.txt";

    @BeforeEach
    void setup() throws IOException {
        // Sample ambiguity rules for testing
        String content = String.join("\n",
                "69=609",
                "60 9=609",
                "20 1=21,201"
        );
        Files.write(Paths.get(FILE_NAME), content.getBytes());
    }

    @AfterEach
    void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get(FILE_NAME));
    }

    @Test
    void testSingleTokenReplacement() {
        AmbiguityResolver resolver = new AmbiguityResolver();
        List<String> result = resolver.generateInterpretations(new String[]{"69"});
        assertEquals(Set.of("69", "609"), new HashSet<>(result));
    }

    @Test
    void testTwoTokenReplacement() {
        AmbiguityResolver resolver = new AmbiguityResolver();
        List<String> result = resolver.generateInterpretations(new String[]{"60", "9"});
        assertTrue(result.contains("609"));
        assertTrue(result.contains("609"));
        assertTrue(result.contains("609"));
    }

    @Test
    void testMixedInterpretations() {
        AmbiguityResolver resolver = new AmbiguityResolver();
        List<String> result = resolver.generateInterpretations(new String[]{"20", "1"});

        Set<String> expected = Set.of("201", "21");
        assertTrue(result.containsAll(expected));
    }

    @Test
    void testNoAmbiguities() {
        AmbiguityResolver resolver = new AmbiguityResolver();
        List<String> result = resolver.generateInterpretations(new String[]{"7", "8"});
        assertEquals(List.of("78"), result);
    }

    @Test
    void testEmptyInput() {
        AmbiguityResolver resolver = new AmbiguityResolver();
        List<String> result = resolver.generateInterpretations(new String[]{});
        assertEquals(List.of(""), result);
    }
}
