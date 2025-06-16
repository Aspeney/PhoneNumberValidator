import org.example.GreekNumberValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GreekNumberValidatorTest {

    private final GreekNumberValidator validator = new GreekNumberValidator();

    @Test
    void testValid10DigitLandline() {
        assertTrue(validator.isValid("2101234567"));  // starts with 2
    }

    @Test
    void testValid10DigitMobile() {
        assertTrue(validator.isValid("6901234567"));  // starts with 69
    }

    @Test
    void testValid14DigitLandline() {
        assertTrue(validator.isValid("00302101234567"));  // starts with 00302
    }

    @Test
    void testValid14DigitMobile() {
        assertTrue(validator.isValid("00306901234567"));  // starts with 003069
    }

    @Test
    void testInvalidLength() {
        assertFalse(validator.isValid("210123456"));   // 9 digits
        assertFalse(validator.isValid("21012345678")); // 11 digits
        assertFalse(validator.isValid("0030210123456")); // 13 digits
    }

    @Test
    void testInvalidPrefixes() {
        assertFalse(validator.isValid("3101234567"));     // not 2 or 69
        assertFalse(validator.isValid("00303101234567")); // not 00302 or 003069
    }
}
