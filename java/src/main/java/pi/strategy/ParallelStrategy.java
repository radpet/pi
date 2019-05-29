package pi.strategy;

import lombok.extern.log4j.Log4j2;
import pi.MathUtils;
import pi.factorial.FactorialSupplier;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public class ParallelStrategy extends ComputeStrategy {
    private final ExecutorService executor;
    private final int numThreads;
    private FactorialSupplier factorialSupplier;

    public ParallelStrategy(int numThreads, FactorialSupplier factorialSupplier) {
        this.numThreads = numThreads;
        this.factorialSupplier = factorialSupplier;
        executor = new ForkJoinPool(numThreads);
    }

    @Override
    protected BigDecimal strategy(int precision) {
        try {
            return executor.invokeAll(computePi(precision))
                    .stream()
                    .map(f -> {
                        try {
                            return f.get();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                        return BigDecimal.ZERO;
                    })
                    .reduce(BigDecimal.ZERO, (current, sumSoFar) -> sumSoFar.add(current));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return BigDecimal.ZERO;
    }

    private List<Callable<BigDecimal>> computePi(int precision) {
        return IntStream.range(0, MathUtils.calculateIterations(precision))
                .mapToObj(i -> (Callable<BigDecimal>) () -> computeMember(i, precision + 2, factorialSupplier))
                .collect(Collectors.toList());
    }

    @Override
    public void onComplete() {
        executor.shutdown();
    }

    @Override
    public String toString() {
        return "Parallel pi.factorial.strategy using threads=" + numThreads;
    }
}
