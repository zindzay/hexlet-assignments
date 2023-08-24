package exercise;

import java.util.Arrays;

// BEGIN
public final class MinThread extends Thread {

    private final int[] numbers;

    private int min;

    public MinThread(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        min = Arrays.stream(numbers).min().orElseThrow(RuntimeException::new);
    }

    public int getMin() {
        return min;
    }

}
// END
