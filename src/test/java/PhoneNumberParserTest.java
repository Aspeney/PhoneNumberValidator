import org.example.PhoneNumberParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberParserTest {

    private final PhoneNumberParser parser = new PhoneNumberParser();

    @Test
    void shouldReturnSingleSpaceSeparatedTokens() {
        String input = "123 456";
        String[] expected = {"123", "456"};

        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void shouldTreatMultipleSpacesBetweenTokensAsOne() {
        String input = "123    456   789";
        String[] expected = {"123", "456", "789"};

        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void shouldHandleTabsAndNewlinesAsWhitespace() {
        String input = "123\t456\n789";
        String[] expected = {"123", "456", "789"};

        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void shouldIgnoreLeadingAndTrailingSpaces() {
        String input = "   123 456  ";
        String[] expected = {"123", "456"};

        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void shouldReturnEmptyArrayForEmptyInput() {
        String input = "   ";
        String[] expected = {};

        assertArrayEquals(expected, parser.tokenize(input));
    }

    @Test
    void shouldTestSingleTokenInput() {
        String input = "123";
        String[] expected = {"123"};

        assertArrayEquals(expected, parser.tokenize(input));
    }
}
