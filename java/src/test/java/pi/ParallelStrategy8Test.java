package pi;

public class ParallelStrategy8Test extends BaseStrategyTests {
    ParallelStrategy8Test() {
        super(()-> Pi.parallel(8));
    }
}