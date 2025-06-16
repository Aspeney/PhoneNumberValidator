package org.example;

public class PhoneNumberParser {

    public String[] tokenize(String rawString) {
        return rawString.trim().split("\\s+");
    }
}

