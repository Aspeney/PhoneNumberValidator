package org.example;

import java.util.List;

public class PhoneNumberService {

    private final PhoneNumberValidator validator;
    private final AmbiguityResolver resolver;
    private final PhoneNumberParser parser;

    public PhoneNumberService(PhoneNumberValidator validator, AmbiguityResolver resolver, PhoneNumberParser parser) {
        this.validator = validator;
        this.resolver = resolver;
        this.parser = parser;
    }

    public void process(String rawPhoneNumber) {
        String[] tokens = parser.tokenize(rawPhoneNumber);
        List<String> interpretations = resolver.generateInterpretations(tokens);

        int count = 1;
        for (String interpretation : interpretations) {
            boolean isValid = validator.isValid(interpretation);
            System.out.println("Interpretation " + count++ + ": " + interpretation +
                    " [phone number: " + (isValid ? "VALID" : "INVALID") + "]");
        }
    }
}
