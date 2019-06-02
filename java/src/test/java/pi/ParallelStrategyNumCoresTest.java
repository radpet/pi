package pi;

import pi.factorial.FastFactorialSupplier;
import pi.strategy.ParallelStrategy;

import java.util.concurrent.ForkJoinPool;

public class ParallelStrategyNumCoresTest extends BaseStrategyTests {
    ParallelStrategyNumCoresTest() {
        super(()-> new ParallelStrategy(Runtime.getRuntime().availableProcessors(), new FastFactorialSupplier(ForkJoinPool.commonPool())));
    }
}