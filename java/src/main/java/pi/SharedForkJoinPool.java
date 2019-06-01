package pi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedForkJoinPool {

    private static ExecutorService executor;

    private SharedForkJoinPool() {
    }

    public synchronized static ExecutorService get(int numThreads) {
        if (executor == null) {
            executor = Executors.newWorkStealingPool(numThreads);
        }
        return executor;
    }
}
