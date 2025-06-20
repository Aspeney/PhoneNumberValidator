import org.example.AmbiguityResolver;
import org.example.PhoneNumberParser;
import org.example.PhoneNumberService;
import org.example.PhoneNumberValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneNumberServiceTest {

    private PhoneNumberService service;
    private TestPhoneNumberValidator validator;
    private TestAmbiguityResolver resolver;
    private TestPhoneNumberParser parser;

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Redirect System.out to capture console output
        System.setOut(new PrintStream(output));

        validator = new TestPhoneNumberValidator();
        resolver = new TestAmbiguityResolver();
        parser = new TestPhoneNumberParser();

        service = new PhoneNumberService(validator, resolver, parser);
    }

    @Test
    void testProcessPrintsValidAndInvalidInterpretations() {
        // Prepare controlled input
        parser.setTokens(new String[]{"123", "456"});
        resolver.setInterpretations(Arrays.asList("123456", "123-456"));
        validator.setValidNumbers(List.of("123456")); // Only one valid

        service.process("123 456");

        String consoleOutput = output.toString();
        assertTrue(consoleOutput.contains("Interpretation 1: 123456 [phone number: VALID]"));
        assertTrue(consoleOutput.contains("Interpretation 2: 123-456 [phone number: INVALID]"));
    }

    // Reset System.out after all tests
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    // === Stub Implementations ===

    static class TestPhoneNumberValidator implements PhoneNumberValidator {
        private List<String> validNumbers;

        void setValidNumbers(List<String> validNumbers) {
            this.validNumbers = validNumbers;
        }

        @Override
        public boolean isValid(String phoneNumber) {
            return validNumbers.contains(phoneNumber);
        }
    }

    static class TestAmbiguityResolver extends AmbiguityResolver {
        private List<String> interpretations;

        void setInterpretations(List<String> interpretations) {
            this.interpretations = interpretations;
        }

        @Override
        public List<String> generateInterpretations(String[] tokens) {
            return interpretations;
        }
    }

    static class TestPhoneNumberParser extends PhoneNumberParser {
        private String[] tokens;

        void setTokens(String[] tokens) {
            this.tokens = tokens;
        }

        @Override
        public String[] tokenize(String rawPhoneNumber) {
            return tokens;
        }
    }
}
