package pi.strategy;

import lombok.extern.log4j.Log4j2;
import pi.MathUtils;
import pi.SharedForkJoinPool;
import pi.factorial.FactorialSupplier;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public class ParallelStrategy extends ComputeStrategy {
    private final int numThreads;
    private FactorialSupplier factorialSupplier;

    public ParallelStrategy(int numThreads, FactorialSupplier factorialSupplier) {
        this.numThreads = numThreads;
        this.factorialSupplier = factorialSupplier;
    }

    @Override
    protected BigDecimal strategy(int precision) {
        try {
            return SharedForkJoinPool.get(numThreads).submit(computePi(precision)).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    private Callable<BigDecimal> computePi(int precision) {
        return () ->
                IntStream.range(0, MathUtils.calculateIterations(precision))
                        .parallel()
                        .mapToObj(i -> computeMember(i, precision + 2, factorialSupplier))
                        .reduce(BigDecimal.ZERO, (current, sumSoFar) -> sumSoFar.add(current));

    }

    @Override
    public String toString() {
        return "Parallel pi.factorial.strategy using threads=" + numThreads;
    }
}
