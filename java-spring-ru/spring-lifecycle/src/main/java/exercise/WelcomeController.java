package exercise;

import exercise.daytimes.Daytime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
@RequiredArgsConstructor
public class WelcomeController {
    private final Daytime daytime;

    private final Meal meal;

    @GetMapping("/daytime")
    public String getGreeting() {
        String time = daytime.getName();
        return "It is " + time + " now. " + "Enjoy your " + meal.getMealForDaytime(time);
    }
}
// END
