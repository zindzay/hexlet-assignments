package exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class AppTest {
    private List<Integer> numbers;

    @BeforeEach
    void before() {
        this.numbers = List.of(1, 2, 3, 4);
    }

    @Test
    void testTake() {
        // BEGIN
        Assertions.assertEquals(List.of(1, 2), App.take(numbers, 2));
        Assertions.assertEquals(numbers, App.take(numbers, 8));
        Assertions.assertEquals(List.of(), App.take(numbers, 0));
        Assertions.assertEquals(List.of(), App.take(List.of(), 0));
        // END
    }
}
