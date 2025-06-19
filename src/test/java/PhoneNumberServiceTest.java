import org.example.AmbiguityResolver;
import org.example.PhoneNumberParser;
import org.example.PhoneNumberService;
import org.example.PhoneNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberServiceTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUpStreams() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testProcessPrintsValidAndInvalidInterpretations() {

        PhoneNumberParser parser = new PhoneNumberParser() {
            @Override
            public String[] tokenize(String rawString) {
                return new String[]{"123", "456"};
            }
        };

        AmbiguityResolver resolver = new AmbiguityResolver() {
            @Override
            public List<String> generateInterpretations(String[] tokens) {
                return Arrays.asList("123456", "123-456");
            }
        };

        PhoneNumberValidator validator = new PhoneNumberValidator() {
            @Override
            public boolean isValid(String s) {
                return "123456".equals(s);
            }
        };

        PhoneNumberService service = new PhoneNumberService(validator, resolver, parser);
        service.process("123 456");

        String output = outContent.toString();

        assertTrue(output.contains("Interpretation 1: 123456 [phone number: VALID]"));
        assertTrue(output.contains("Interpretation 2: 123-456 [phone number: INVALID]"));
    }

    @BeforeEach
    void restoreStreams() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.setOut(originalOut)));
    }
}
