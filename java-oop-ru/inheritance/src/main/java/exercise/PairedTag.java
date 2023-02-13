package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
public final class PairedTag extends Tag {
    private final List<Tag> tags;
    private final String body;
    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> tags) {
        super(name, attributes);
        this.tags = tags;
        this.body = body;
    }

    // "<p id="abc">Text paragraph</p>"
    // "<div class="y-5"><br id="s"><hr class="a-5"></div>"
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(String.format("<%s", getName()));

        for (var attribute : getAttributes().entrySet()) {
            sb.append(String.format(" %s=\"%s\"", attribute.getKey(), attribute.getValue()));
        }

        sb.append(">");
        sb.append(body);

        for (var tag : tags) {
            sb.append(tag.toString());
        }

        sb.append(String.format("</%s>", getName()));

        return sb.toString();
    }
}
// END
