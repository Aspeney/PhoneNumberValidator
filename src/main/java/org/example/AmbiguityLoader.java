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

        map.putAll(generateSingleTokenAmbiguities());

        return map;
    }

    public Map<String, List<String>> loadStartOnlyRules() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("69 30", List.of("6930", "693"));
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
