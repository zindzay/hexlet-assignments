package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public final class FileKV implements KeyValueStorage {
    private final String path;

    public FileKV(String path) {
        this.path = path;
    }


    @Override
    public void set(String key, String value) {
        Map<String, String> map = Utils.unserialize(Utils.readFile(path));
        map.put(key, value);
        Utils.writeFile(path, Utils.serialize(map));
    }

    @Override
    public void unset(String key) {
        Map<String, String> map = Utils.unserialize(Utils.readFile(path));
        map.remove(key);
        Utils.writeFile(path, Utils.serialize(map));
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> map = Utils.unserialize(Utils.readFile(path));

        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = Utils.unserialize(Utils.readFile(path));

        return new HashMap<>(map);
    }
}
// END
