package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public final class FileKV implements KeyValueStorage {
    private final String filepath;

    public FileKV(String filepath, Map<String, String> initial) {
        this.filepath = filepath;
        initial.forEach(this::set);
    }


    @Override
    public void set(String key, String value) {
        Map<String, String> map = Utils.unserialize(Utils.readFile(filepath));
        map.put(key, value);
        Utils.writeFile(filepath, Utils.serialize(map));
    }

    @Override
    public void unset(String key) {
        Map<String, String> map = Utils.unserialize(Utils.readFile(filepath));
        map.remove(key);
        Utils.writeFile(filepath, Utils.serialize(map));
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> map = Utils.unserialize(Utils.readFile(filepath));

        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = Utils.unserialize(Utils.readFile(filepath));

        return new HashMap<>(map);
    }
}
// END
