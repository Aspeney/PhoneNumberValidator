package org.example;

import java.util.*;

public class AmbiguityResolver {

    private final Map<String, List<String>> ambiguityMap;
    private final Map<String, List<String>> startOnlyMap;

    public AmbiguityResolver() {
        AmbiguityLoader loader = new AmbiguityLoader();
        this.ambiguityMap = loader.loadRegularRules();   // 🚫 no file
        this.startOnlyMap = loader.loadStartOnlyRules(); // 🚫 no file
    }

    public List<String> generateInterpretations(String[] tokens) {
        Set<String> results = new LinkedHashSet<>();
        backtrack(tokens, 0, new StringBuilder(), results);
        return new ArrayList<>(results);
    }

    private void backtrack(String[] tokens, int index, StringBuilder current, Set<String> results) {
        if (index >= tokens.length) {
            results.add(current.toString());
            return;
        }

        int len = current.length();

        // 1. Domyślna ścieżka (token bez zmian)
        current.append(tokens[index]);
        backtrack(tokens, index + 1, current, results);
        current.setLength(len);

        // 2. Pojedynczy token z reguły
        appendAmbiguities(ambiguityMap.get(tokens[index]), index + 1, tokens, current, results, len);

        // 3. Dwuelementowa reguła (jeśli dostępna)
        if (index < tokens.length - 1) {
            String twoTokenKey = tokens[index] + " " + tokens[index + 1];

            if (index == 0) {
                appendAmbiguities(startOnlyMap.get(twoTokenKey), index + 2, tokens, current, results, len);
            }

            appendAmbiguities(ambiguityMap.get(twoTokenKey), index + 2, tokens, current, results, len);
        }
    }

    private void appendAmbiguities(List<String> options, int nextIndex, String[] tokens,
                                   StringBuilder current, Set<String> results, int len) {
        if (options == null) return;
        for (String replacement : options) {
            current.append(replacement);
            backtrack(tokens, nextIndex, current, results);
            current.setLength(len);
        }
    }


}

