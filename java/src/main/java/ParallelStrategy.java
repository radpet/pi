import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Log4j2
public class ParallelStrategy extends ComputeStrategy {
    private final ExecutorService executor;
    private final int numThreads;

    ParallelStrategy(int numThreads) {
        this.numThreads = numThreads;
        executor = Executors.newFixedThreadPool(numThreads);
    }

    @Override
    protected BigDecimal strategy(int precision) {
        try {
            return executor.submit(computePi(precision)).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return BigDecimal.ZERO;
    }

    private Callable<BigDecimal> computePi(int precision) {
        return () ->
                IntStream.range(0, precision / 5 + 1)
                        .parallel()
                        .mapToObj(i -> computeMember(i, precision + 2))
                        .reduce(BigDecimal.ZERO, (current, sumSoFar) -> sumSoFar.add(current));

    }

    @Override
    public void onComplete() {
        executor.shutdown();
    }

    @Override
    public String toString() {
        return "Parallel strategy using threads=" + numThreads;
    }
}
