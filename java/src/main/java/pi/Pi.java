package pi;


import pi.factorial.DummyFactorialSupplier;
import pi.factorial.FactorialCache;
import pi.factorial.FastFactorialSupplier;
import pi.strategy.ComputeStrategy;
import pi.strategy.ForkJoinStrategy;
import pi.strategy.ParallelStrategy;
import pi.strategy.SingleThreadStrategy;

public class Pi {

    public static ComputeStrategy parallelCachedFactorial(int numThreads, FactorialCache factorialCache) {
        return new ParallelStrategy(numThreads, factorialCache);
    }

    public static ComputeStrategy parallel(int numThreads) {
        return new ParallelStrategy(numThreads, new FastFactorialSupplier());
    }

    public static ComputeStrategy single() {
        return new SingleThreadStrategy(new DummyFactorialSupplier());
    }

    public static ComputeStrategy singleCached(FactorialCache factorialCache) {
        return new SingleThreadStrategy(factorialCache);
    }

    public static ComputeStrategy commonForkJoin() {
        return new ForkJoinStrategy(new FastFactorialSupplier());
    }

}
