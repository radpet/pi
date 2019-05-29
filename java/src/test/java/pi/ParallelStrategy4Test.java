package pi;

public class ParallelStrategy4Test extends BaseStrategyTests {
    ParallelStrategy4Test() {
        super(()-> Pi.parallel(4));
    }
}