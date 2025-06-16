import org.example.PhoneNumberParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberParserTest {

    private final PhoneNumberParser parser = new PhoneNumberParser();

    @Test
    void testTokenizeWithSingleSpace() {
        String input = "123 456";
        String[] expected = {"123", "456"};
        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void testTokenizeWithMultipleSpaces() {
        String input = "123   456   7890";
        String[] expected = {"123", "456", "7890"};
        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void testTokenizeWithLeadingAndTrailingSpaces() {
        String input = "   123 456  ";
        String[] expected = {"123", "456"};
        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void testTokenizeWithTabsAndSpaces() {
        String input = "123\t456\t   789";
        String[] expected = {"123", "456", "789"};
        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void testTokenizeWithSingleToken() {
        String input = "1234567890";
        String[] expected = {"1234567890"};
        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void testTokenizeWithEmptyString() {
        String input = "";
        String[] expected = {};
        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void testTokenizeWithOnlyWhitespace() {
        String input = "   \t   ";
        String[] expected = {};
        assertArrayEquals(expected, parser.tokenize(input));
    }
}
