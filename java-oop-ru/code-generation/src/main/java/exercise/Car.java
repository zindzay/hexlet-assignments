package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.IOException;

// BEGIN
@AllArgsConstructor
@Value
@Getter
@Setter
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public static Car unserialize(String content) throws IOException {
        return new ObjectMapper().readValue(content, Car.class);
    }
    // END
}
