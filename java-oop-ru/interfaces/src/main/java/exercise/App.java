package exercise;

import java.util.Comparator;
import java.util.List;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int n) {
        return homes.stream()
                .sorted(Comparator.comparingDouble(Home::getArea))
                .limit(n)
                .map(Home::toString)
                .toList();
    }
}
// END
