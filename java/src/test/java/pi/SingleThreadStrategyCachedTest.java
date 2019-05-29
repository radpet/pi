package pi;

import pi.factorial.FactorialCache;

public class SingleThreadStrategyCachedTest extends BaseStrategyTests {
    SingleThreadStrategyCachedTest() {
        super(() -> Pi.singleCached(getFactorialSupplier()));
    }

    private static FactorialCache getFactorialSupplier() {
        return FactorialCache.of(10000);
    }
}
