package pi;

import pi.factorial.FactorialCache;

public class ParallelStrategy12CachedTest extends BaseStrategyTests {
    ParallelStrategy12CachedTest() {
        super(() -> Pi.parallelCachedFactorial(12, getFactorialSupplier()));
    }

    private static FactorialCache getFactorialSupplier() {
        return FactorialCache.of(10000);
    }
}
