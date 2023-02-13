package exercise;

import java.util.Map;

// BEGIN
public final class SingleTag extends Tag {
    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(String.format("<%s", getName()));

        for (var attribute : getAttributes().entrySet()) {
            sb.append(String.format(" %s=\"%s\"", attribute.getKey(), attribute.getValue()));
        }

        sb.append(">");

        return sb.toString();
    }
}
// END
