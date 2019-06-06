package pi;


import pi.factorial.DivideAndConquerFactorialSupplier;
import pi.factorial.DummyFactorialSupplier;
import pi.factorial.FactorialSupplier;
import pi.factorial.FastFactorialSupplier;
import pi.strategy.ComputeStrategy;
import pi.strategy.ForkJoinStrategy;
import pi.strategy.ParallelStrategy;
import pi.strategy.SingleThreadStrategy;

import java.util.concurrent.ForkJoinPool;

public class Pi {

    public static ComputeStrategy parallelCachedFactorial(int numThreads, FactorialSupplier factorialCache) {
        return new ParallelStrategy(numThreads, factorialCache);
    }

    public static ComputeStrategy parallel(int numThreads) {
        return new ParallelStrategy(numThreads, new FastFactorialSupplier(SharedForkJoinPool.get(numThreads)));
    }

    public static ComputeStrategy parallelDC(int numThreads) {
        return new ParallelStrategy(numThreads, new DivideAndConquerFactorialSupplier());
    }

    public static ComputeStrategy single() {
        return new SingleThreadStrategy(new DummyFactorialSupplier());
    }

    public static ComputeStrategy singleCached(FactorialSupplier factorialCache) {
        return new SingleThreadStrategy(factorialCache);
    }

    public static ComputeStrategy singleDC() {
        return new SingleThreadStrategy(new DivideAndConquerFactorialSupplier());
    }

    public static ComputeStrategy commonForkJoin() {
        return new ForkJoinStrategy(new FastFactorialSupplier(ForkJoinPool.commonPool()));
    }

}
