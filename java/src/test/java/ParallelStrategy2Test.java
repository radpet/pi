public class ParallelStrategy2Test extends BaseStrategyTests {
    ParallelStrategy2Test() {
        super(() -> Pi.parallel(2));
    }
}
