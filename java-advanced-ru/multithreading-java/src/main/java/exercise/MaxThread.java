package exercise;

import java.util.Arrays;

// BEGIN
public final class MaxThread extends Thread {

    private final int[] numbers;

    private int max;

    public MaxThread(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        max = Arrays.stream(numbers).max().orElseThrow(RuntimeException::new);
    }

    public int getMax() {
        return max;
    }

}
// END
