package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

// BEGIN
public final class App {
    private static final int RATIO = 2;

    public static String[][] enlargeArrayImage(String[][] image) {
        return Arrays.stream(image)
                .map(line -> scale(List.of(scale(List.of(line), RATIO)), RATIO))
                .flatMap(Collection::stream)
                .map(it -> it.toArray(String[]::new))
                .toArray(String[][]::new);
    }

    private static <T> List<T> scale(List<T> elements, int ratio) {
        final List<T> scaled = new ArrayList<>(elements.size() * ratio);

        for (var element : elements) {
            for (var j = 0; j < ratio; j++) {
                scaled.add(element);
            }
        }

        return scaled;
    }
}
// END
