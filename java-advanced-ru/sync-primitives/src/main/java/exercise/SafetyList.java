package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    // BEGIN

    private final List<Integer> numbers = new ArrayList<>();

    public synchronized void add(final Integer num) {
        numbers.add(num);
    }

    public Integer get(final Integer i) {
        return numbers.get(i);
    }

    public int getSize() {
        return numbers.size();
    }

    // END
}
