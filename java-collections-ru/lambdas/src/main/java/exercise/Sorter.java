package exercise;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

// BEGIN
public final class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(IS_MALE)
                .map(GET_NAME)
                .toList();
    }

    private static final Predicate<Map<String, String>> IS_MALE =
            (Map<String, String> user) -> "male".equals(user.get("gender"));
    private static final Function<Map<String, String>, String> GET_NAME =
            (Map<String, String> user) -> user.get("name");
}
// END
