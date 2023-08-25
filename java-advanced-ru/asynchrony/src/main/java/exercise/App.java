package exercise;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;

class App {

    // BEGIN
    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    public static CompletableFuture<String> unionFiles(String source1, String source2, String dest) {
        CompletableFuture<String> futureString1 = CompletableFuture.supplyAsync(() -> {
            String content = "";
            try {
                content = Files.readString(getFullPath(source1));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        CompletableFuture<String> futureString2 = CompletableFuture.supplyAsync(() -> {
            String content = "";
            try {
                content = Files.readString(getFullPath(source2));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        return futureString1.thenCombine(futureString2, (string1, string2) -> {
            String union = string1 + string2;
            try {
                Files.writeString(getFullPath(dest), union, StandardOpenOption.CREATE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return "ok!";
        }).exceptionally(e -> {
            System.out.println("Oops! We have an exception - " + e.getMessage());
            return "Unknown!";
        });
    }

    public static CompletableFuture<Long> getDirectorySize(String path) {
        return CompletableFuture.supplyAsync(() -> {
            long size;
            try {
                size = Files.walk(getFullPath(path), 1)
                        .filter(Files::isRegularFile)
                        .mapToLong(p -> {
                            try {
                                return Files.size(p);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .sum();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return size;

        }).exceptionally(e -> {
            System.out.println("Oops! We have an exception - " + e.getMessage());
            return null;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = unionFiles(
                "src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/dest.txt"
        );
        result.get();
        System.out.println("done!");

        CompletableFuture<Long> size = getDirectorySize("src/main/resources");
        System.out.println(size.get());
        // END
    }

}

