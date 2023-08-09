package exercise;

import exercise.daytimes.Daytime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
@RequiredArgsConstructor
public class WelcomeController {
    private Meal meal;

    private Daytime daytime;

    @GetMapping("/daytime")
    public String getGreeting() {
        var time = daytime.getName();
        return "It is " + time + " now. " + "Enjoy your " + meal.getMealForDaytime(time);
    }
}
// END
