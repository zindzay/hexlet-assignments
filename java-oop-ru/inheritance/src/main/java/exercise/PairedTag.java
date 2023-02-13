package exercise;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public final class PairedTag extends Tag {
    private final List<Tag> children;
    private final String body;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.children = children;
        this.body = body;
    }

    @Override
    public String toString() {
        String value = children.stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));

        return String.format("<%s%s>%s%s</%s>", getName(), stringifyAttributes(), body, value, getName());
    }
}
// END
