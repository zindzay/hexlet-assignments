package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public final class App {
    public static boolean scrabble(String chars, String word) {
        List<String> charList = new ArrayList<>();

        for (var c : chars.split("")) {
            charList.add(c.toLowerCase());
        }

        for (var c : word.split("")) {
            var i = charList.indexOf(c.toLowerCase());

            if (i == -1) {
                return false;
            }

            charList.remove(i);
        }

        return true;
    }
}
//END
