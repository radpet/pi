package pi;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

public class SharedForkJoinPool {

    private static Map<Integer, SharedForkJoinPoolInner> pools;

    public synchronized static ExecutorService get(int numThreads) {
        if (pools == null) {
            pools = new HashMap<>();
        }
        pools.putIfAbsent(numThreads, new SharedForkJoinPoolInner(numThreads));
        return pools.get(numThreads).executorService;
    }

    static class SharedForkJoinPoolInner {
        ExecutorService executorService;

        private SharedForkJoinPoolInner(int parallelism) {
            executorService = new ForkJoinPool(parallelism);
        }
    }
}
