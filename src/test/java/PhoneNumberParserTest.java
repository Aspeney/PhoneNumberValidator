import org.example.PhoneNumberParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberParserTest {

    private final PhoneNumberParser parser = new PhoneNumberParser();

    @Test
    void testSingleSpaceSeparatedTokens() {
        String input = "123 456";
        String[] expected = {"123", "456"};

        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void testMultipleSpacesBetweenTokens() {
        String input = "123    456   789";
        String[] expected = {"123", "456", "789"};

        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void testTabsAndNewlinesHandledAsWhitespace() {
        String input = "123\t456\n789";
        String[] expected = {"123", "456", "789"};

        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void testLeadingAndTrailingSpacesIgnored() {
        String input = "   123 456  ";
        String[] expected = {"123", "456"};

        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void testEmptyInputReturnsEmptyArray() {
        String input = "   ";
        String[] expected = {};

        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void testSingleTokenInput() {
        String input = "123";
        String[] expected = {"123"};

        assertArrayEquals(expected, parser.tokenize(input));
    }
}
