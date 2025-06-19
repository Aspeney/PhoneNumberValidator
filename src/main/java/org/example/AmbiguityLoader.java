package org.example;

import java.util.*;

public class AmbiguityLoader {

    public Map<String, List<String>> loadRegularRules() {
        Map<String, List<String>> map = new HashMap<>();

        // Generate all 2-digit + 1-digit ambiguities like: 20 1 = 21,201
        for (int tens = 20; tens <= 90; tens += 10) {
            for (int ones = 1; ones <= 9; ones++) {
                String key = tens + " " + ones;
                String a = String.valueOf(tens + ones);       // e.g., 20 + 1 = 21
                String b = tens + String.valueOf(ones);       // e.g., "20" + "1" = "201"
                map.put(key, List.of(a, b));
            }
        }

        // Single-token ambiguities like: 21 = 21,201
        for (int i = 21; i <= 99; i++) {
            if (i % 10 != 0) { // skip round numbers
                map.put(String.valueOf(i), List.of(
                        String.valueOf(i),
                        String.valueOf(i * 10 + 1)  // crude example: 21 → 201
                ));
            }
        }

        // Special two-token ambiguities
        map.put("30 6", List.of("36", "306"));
        map.put("60 4", List.of("64", "604"));
        map.put("40 5", List.of("45", "405"));
        map.put("0 30", List.of("030", "0030"));
        map.put("0030 2", List.of("00302"));
        map.put("0030 69", List.of("003069"));
        map.put("00 30", List.of("0030"));

        return map;
    }

    public Map<String, List<String>> loadStartOnlyRules() {
        Map<String, List<String>> startOnly = new HashMap<>();
        startOnly.put("69 30", List.of("6930", "693"));
        return startOnly;
    }

}
