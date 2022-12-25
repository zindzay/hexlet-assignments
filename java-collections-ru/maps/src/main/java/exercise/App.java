package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public final class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        if (sentence.isEmpty()) {
            return Map.of();
        }

        Map<String, Integer> wordsByNumberOfWords = new HashMap<>();

        for (var word : sentence.split(" ")) {
            var number = wordsByNumberOfWords.getOrDefault(word, 0);

            wordsByNumberOfWords.put(word, number + 1);
        }

        return wordsByNumberOfWords;
    }

    public static String toString(Map<String, Integer> wordsByNumberOfWords) {
        if (wordsByNumberOfWords.isEmpty()) {
            return "{}";
        }

        var sb = new StringBuilder();

        sb.append("{\n");

        for (var entry : wordsByNumberOfWords.entrySet()) {
            sb
                    .append("  ")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }

        sb.append("}");

        return sb.toString();
    }
}
//END
