package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// BEGIN
public final class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String, String>> result = new ArrayList<>();

        for (var book : books) {
            if (in(where, book)) {
                result.add(book);
            }
        }

        return result;
    }

    public static boolean in(Map<String, String> where, Map<String, String> book) {
        for (var entre : where.entrySet()) {
            var key = entre.getKey();
            var value = entre.getValue();

            if (!book.containsKey(key) || !value.equals(book.get(key))) {
                return false;
            }
        }

        return true;
    }
}
//END
