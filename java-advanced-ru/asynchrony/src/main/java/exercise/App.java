package exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(final String file1, final String file2, final String dest) {
        CompletableFuture<String> futureString1 = CompletableFuture.supplyAsync(() -> {
            final Path path = Paths.get(file1).toAbsolutePath().normalize();

            if (!Files.exists(path)) {
                System.out.println("NoSuchFileException");
                return null;
            }

            try {
                return Files.readString(path);
            } catch (IOException e) {
                return null;
            }
        });
        CompletableFuture<String> futureString2 = CompletableFuture.supplyAsync(() -> {
            final Path path = Paths.get(file2).toAbsolutePath().normalize();

            if (!Files.exists(path)) {
                System.out.println("NoSuchFileException");
                return null;
            }

            try {
                return Files.readString(path);
            } catch (IOException e) {
                throw null;
            }
        });

        return futureString1.thenCombine(futureString2, (string1, string2) -> {
            final Path path = Paths.get(dest).toAbsolutePath().normalize();
            final String result = string1 + string2;
            if (!Files.exists(path)) {
                try {
                    Files.createFile(path);
                } catch (IOException e) {
                    return null;
                }
            }
            try {
                Files.writeString(path, result, StandardOpenOption.APPEND);
            } catch (IOException e) {
                return null;
            }
            return null;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles("src/main/resources/file1.txt", "src/main/resources/file2.txt", "src/main/resources/dest.txt")
                .get();
        // END
    }
}

