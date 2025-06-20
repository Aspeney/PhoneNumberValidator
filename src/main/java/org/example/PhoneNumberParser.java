package org.example;

public class PhoneNumberParser {

    public String[] tokenize(String rawString) {
        String trimmed = rawString.trim();
        if (trimmed.isEmpty()) {
            return new String[0];
        }
        return trimmed.split("\\s+");
    }
}


