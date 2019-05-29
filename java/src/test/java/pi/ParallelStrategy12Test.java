package pi;

public class ParallelStrategy12Test extends BaseStrategyTests {
    ParallelStrategy12Test() {
        super(()-> Pi.parallel(12));
    }
}