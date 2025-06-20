import org.example.GreekNumberValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GreekNumberValidatorTest {

    private final GreekNumberValidator validator = new GreekNumberValidator();

    @Test
    void shouldTestValid10DigitLandline() {
        assertTrue(validator.isValid("2101234567"));
    }

    @Test
    void shouldTestValid10DigitMobile() {
        assertTrue(validator.isValid("6901234567"));
    }

    @Test
    void shouldTestValid14DigitPhoneNumber() {
        assertTrue(validator.isValid("00306901234567"));
    }

    @Test
    void shouldTestInvalidLength() {
        assertFalse(validator.isValid("210123456"));
        assertFalse(validator.isValid("21012345678"));
        assertFalse(validator.isValid("0030210123456"));
    }

    @Test
    void shouldTestInvalidPrefixes() {
        assertFalse(validator.isValid("3101234567"));
        assertFalse(validator.isValid("00303101234567"));
    }
}
