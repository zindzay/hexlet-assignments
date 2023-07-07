package exercise.controller;

import exercise.model.User;
import exercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserRepository userRepository;

    // BEGIN
    public List<User> getUsersByNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
//        CriteriaBuilder criteriaBuilder = this
        return List.of();
    }
    // END
}

