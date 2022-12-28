package exercise;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

// BEGIN
public final class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(IS_MALE)
                .sorted(SORT_BY_DATE)
                .map(GET_NAME)
                .toList();
    }

    private static final Comparator<Map<String, String>> SORT_BY_DATE =
            Comparator.comparing((Map<String, String> user) -> LocalDate.parse(user.get("birthday")));
    private static final Predicate<Map<String, String>> IS_MALE =
            (Map<String, String> user) -> "male".equals(user.get("gender"));
    private static final Function<Map<String, String>, String> GET_NAME =
            (Map<String, String> user) -> user.get("name");
}
// END
