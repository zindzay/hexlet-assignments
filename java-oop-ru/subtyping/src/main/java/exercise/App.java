package exercise;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage keyValueStorage) {
        for (var entry : keyValueStorage.toMap().entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();

            keyValueStorage.unset(key);
            keyValueStorage.set(value, key);
        }
    }
}
// END
