package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

// BEGIN
@AllArgsConstructor
@Value
@Getter
@Setter
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
