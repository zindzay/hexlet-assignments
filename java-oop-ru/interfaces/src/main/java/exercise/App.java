package exercise;

import java.util.List;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int count) {
        return homes.stream()
                .sorted(Home::compareTo)
                .limit(count)
                .map(Home::toString)
                .toList();
    }
}
// END
