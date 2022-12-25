package exercise;

import java.util.List;

// BEGIN
public final class App {
    private static final List<String> FREE_DOMAINS = List.of("gmail.com", "yandex.ru", "hotmail.com");

    public static Long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                .map(App::toDomain)
                .filter(FREE_DOMAINS::contains)
                .count();
    }

    public static String toDomain(String email) {
        return email.split("@")[1];
    }
}
// END
