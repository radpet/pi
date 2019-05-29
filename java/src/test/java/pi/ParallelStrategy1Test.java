package pi;

public class ParallelStrategy1Test extends BaseStrategyTests {
    ParallelStrategy1Test() {
        super(() -> Pi.parallel(1));
    }
}
