package exercise.controller;

import exercise.model.QUser;
import exercise.model.User;
import exercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserRepository userRepository;

    public Iterable<User> getUsersByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findAll(QUser.user.firstName.goe(firstName).and(QUser.user.lastName.loe(lastName)));
    }

    @GetMapping("/")
    public Iterable<User> getUser(@RequestParam String firstName, @RequestParam String lastName) {
        return getUsersByFirstNameAndLastName(firstName, lastName);
    }
    // END
}

