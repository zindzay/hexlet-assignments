package exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static String getForwardedVariables(String config) {
        final var environmentPrefix = "environment=";
        final var forwardedPPrefix = "X_FORWARDED_";
        final var paramsDelimiter = ",";

        return Arrays.stream(config.split("\n"))
                .filter(it -> it.startsWith(environmentPrefix))
                .map(it -> it.replace(environmentPrefix, "").replace("\"", ""))
                .flatMap(it -> Arrays.stream(it.split(paramsDelimiter))
                        .filter(param -> param.startsWith(forwardedPPrefix)))
                .map(it -> it.replace(forwardedPPrefix, ""))
                .collect(Collectors.joining(paramsDelimiter));
    }

    public static void main(String[] args) throws IOException {
        final var path = Paths.get("src", "test", "resources", "fixtures", "s2.conf")
                .toAbsolutePath()
                .normalize();
        final var content = Files.readString(path).trim();

        System.out.println(getForwardedVariables(content));
    }
}
//END
