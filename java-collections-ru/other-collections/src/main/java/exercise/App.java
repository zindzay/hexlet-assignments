package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

// BEGIN
public class App {
    public static Map<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, String> result = new LinkedHashMap<>();

        for (var e : map1.entrySet()) {
            var key1 = e.getKey();
            var value1 = e.getValue();

            if (map2.containsKey(key1)) {
                if (Objects.equals(value1, map2.get(key1))) {
                    result.put(key1, "unchanged");
                } else {
                    result.put(key1, "changed");
                }
            } else {
                result.put(key1, "deleted");
            }
        }

        for (var key2 : map2.keySet()) {
            if (!map1.containsKey(key2)) {
                result.put(key2, "added");
            }
        }

        return result;
    }
}
//END
