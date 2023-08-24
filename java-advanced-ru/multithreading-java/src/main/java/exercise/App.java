package exercise;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(final int[] numbers) {
        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);

        LOGGER.log(Level.INFO, "Thread {} started", maxThread.getName());
        maxThread.start();
        LOGGER.log(Level.INFO, "Thread {} started", minThread.getName());
        minThread.start();

        try {
            maxThread.join();
            LOGGER.log(Level.INFO, "Thread {} finished", maxThread.getName());
            minThread.join();
            LOGGER.log(Level.INFO, "Thread {} finished", minThread.getName());
        } catch (InterruptedException e) {
            LOGGER.log(Level.WARNING, "Interrupted!", e);
            Thread.currentThread().interrupt();
        }


        return Map.of("min", minThread.getMin(), "max", maxThread.getMax());
    }
    // END
}
