package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number sequence (space-separated): ");
        String input = scanner.nextLine();

        PhoneNumberValidator validator = new GreekNumberValidator();
        AmbiguityResolver resolver = new AmbiguityResolver();
        PhoneNumberParser parser = new PhoneNumberParser();

        PhoneNumberService service = new PhoneNumberService(validator, resolver, parser);
        service.process(input);
    }
}
