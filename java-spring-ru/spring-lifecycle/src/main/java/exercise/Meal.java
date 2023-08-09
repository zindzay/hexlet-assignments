package exercise;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

// BEGIN
@Component("meal")
// END
public class Meal {
    public String getMealForDaytime(String daytime) {

        switch (daytime) {
            case "morning":
                return "breakfast";
            case "day":
                return "lunch";
            case "evening":
                return "dinner";
            default:
                return "nothing :)";
        }
    }

    // Для самостоятельной работы
    // BEGIN
    @PostConstruct
    private void init() {
        System.out.println("Init bean meal");
    }
    // END
}
