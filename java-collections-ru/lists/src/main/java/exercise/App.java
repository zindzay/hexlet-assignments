package exercise;

import java.util.LinkedList;
import java.util.List;

// BEGIN
public final class App {
    public static boolean scrabble(String chars, String word) {
        List<String> charList = new LinkedList<>();

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
