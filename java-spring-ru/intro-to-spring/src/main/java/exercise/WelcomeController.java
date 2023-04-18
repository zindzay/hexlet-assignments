package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public final class WelcomeController {

    @GetMapping(path = "/")
    public String getWelcomePage() {
        return "Welcome to Spring";
    }

    @GetMapping(path = "/hello")
    public String getHelloPage(@RequestParam(name = "name", defaultValue = "World", required = false) String name) {
        return "Hello, " + name;
    }
}
// END
