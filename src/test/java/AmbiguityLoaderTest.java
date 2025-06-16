import org.example.AmbiguityLoader;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AmbiguityLoaderTest {

    private static final String TEST_FILE = "test_ambiguities.txt";

    @BeforeEach
    void setupTestFile() throws IOException {
        String content = String.join("\n",
                "# This is a comment",
                "",
                "20 1=21,201",
                "30 6=36,306",
                "69=69,609",
                "   82 = 82 , 802 ",
                "invalidLineWithoutEquals"
        );

        Files.write(Paths.get(TEST_FILE), content.getBytes());
    }

    @AfterEach
    void cleanupTestFile() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }

    @Test
    void testLoadRulesParsesCorrectly() {
        AmbiguityLoader loader = new AmbiguityLoader();
        Map<String, List<String>> rules = loader.loadRules(TEST_FILE);

        assertEquals(4, rules.size());

        assertIterableEquals(List.of("21", "201"), rules.get("20 1"));
        assertIterableEquals(List.of("36", "306"), rules.get("30 6"));
        assertIterableEquals(List.of("69", "609"), rules.get("69"));
        assertIterableEquals(List.of("82", "802"), rules.get("82"));
    }

    @Test
    void testMissingFileReturnsEmptyMap() {
        AmbiguityLoader loader = new AmbiguityLoader();
        Map<String, List<String>> result = loader.loadRules("nonexistent_file.txt");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
