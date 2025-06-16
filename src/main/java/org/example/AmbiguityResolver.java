package org.example;

import java.util.*;

public class AmbiguityResolver {

    private final Map<String, List<String>> ambiguityMap;

    public AmbiguityResolver() {
        AmbiguityLoader loader = new AmbiguityLoader();
        this.ambiguityMap = loader.loadRules("ambiguities.txt");
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

        current.append(tokens[index]);
        backtrack(tokens, index + 1, current, results);
        current.setLength(len);

        String singleKey = tokens[index];
        List<String> singleOptions = ambiguityMap.get(singleKey);
        if (singleOptions != null) {
            for (String replacement : singleOptions) {
                current.append(replacement);
                backtrack(tokens, index + 1, current, results);
                current.setLength(len);
            }
        }

        if (index < tokens.length - 1) {
            String twoKey = tokens[index] + " " + tokens[index + 1];
            List<String> twoTokenOptions = ambiguityMap.get(twoKey);
            if (twoTokenOptions != null) {
                for (String replacement : twoTokenOptions) {
                    current.append(replacement);
                    backtrack(tokens, index + 2, current, results);
                    current.setLength(len);
                }
            }
        }
    }
}
