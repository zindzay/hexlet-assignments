package exercise;

import java.util.List;

// BEGIN
public final class App {
    private static final List<String> FREE_DOMAINS = List.of("gmail.com", "yandex.ru", "hotmail.com");

    public static Long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                .filter(App::isFree)
                .count();
    }

    public static Boolean isFree(String email) {
        return FREE_DOMAINS.contains(getDomain(email));
    }

    public static String getDomain(String email) {
        return email.split("@")[1];
    }
}
// END
