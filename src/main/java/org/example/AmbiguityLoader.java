package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AmbiguityLoader {

    public Map<String, List<String>> loadRules(String filename) {
        Map<String, List<String>> result = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty() || line.startsWith("#")) continue;

                if (!line.contains("=")) continue;

                String[] parts = line.split("=", 2);
                String key = parts[0].trim();
                String[] values = parts[1].replaceAll("[\\[\\]]", "").split(",");

                List<String> replacements = new ArrayList<>();
                for (String v : values) {
                    replacements.add(v.trim());
                }

                result.put(key, replacements);
            }
        } catch (FileNotFoundException e) {
            System.err.println("⚠️ Could not find file: " + filename);
        }

        return result;
    }
}
