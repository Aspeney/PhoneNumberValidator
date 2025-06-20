package org.example;

import java.util.*;

public class AmbiguityLoader {


    public Map<String, List<String>> loadRegularRules() {
        Map<String, List<String>> map = new HashMap<>();

        for (int tens = 20; tens <= 90; tens += 10) {
            for (int ones = 1; ones <= 9; ones++) {
                String key = tens + " " + ones;
                String combined = String.valueOf(tens + ones);
                String joined = tens + String.valueOf(ones);
                map.put(key, List.of(combined, joined));
            }
        }

        map.putAll(generateZeroTrailingCases());
        map.putAll(generateSingleTokenAmbiguities());
        return map;
    }

    private Map<String, List<String>> generateZeroTrailingCases() {
        Map<String, List<String>> result = new HashMap<>();

        for (int hundred = 100; hundred <= 900; hundred += 100) {
            String hundredStr = String.valueOf(hundred);

            char firstNonZero = '0';
            for (char c : hundredStr.toCharArray()) {
                if (c != '0') {
                    firstNonZero = c;
                    break;
                }
            }

            for (int ones = 1; ones <= 9; ones++) {
                String key = hundredStr + " " + ones;
                String combined = "" + firstNonZero + ones;
                String joined = hundredStr + ones;
                result.put(key, List.of(combined, joined));
            }


            for (int twoDigit = 10; twoDigit <= 99; twoDigit++) {
                String key = hundredStr + " " + twoDigit;
                String combined = "" + firstNonZero + twoDigit;
                String joined = hundredStr + twoDigit;
                result.put(key, List.of(combined, joined));
            }
        }

        return result;
    }


    public Map<String, List<String>> loadStartOnlyRules() {
        Map<String, List<String>> map = new HashMap<>();
        return map;
    }

    private Map<String, List<String>> generateSingleTokenAmbiguities() {
        Map<String, List<String>> result = new HashMap<>();

        for (int i = 10; i <= 99; i++) {
            if (i % 10 == 0) continue;

            String token = String.valueOf(i);
            char first = token.charAt(0);
            char second = token.charAt(1);
            String alt = "" + first + "0" + second;

            result.put(token, List.of(token, alt));
        }

        return result;
    }

}
