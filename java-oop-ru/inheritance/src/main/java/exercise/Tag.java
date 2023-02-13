package exercise;

import java.util.Map;

// BEGIN
public class Tag {
    private final String name;
    private final Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    protected String stringifyAttributes() {
        final StringBuilder sb = new StringBuilder();

        for (var attribute : getAttributes().entrySet()) {
            sb.append(String.format(" %s=\"%s\"", attribute.getKey(), attribute.getValue()));
        }

        return sb.toString();
    }
}
// END
