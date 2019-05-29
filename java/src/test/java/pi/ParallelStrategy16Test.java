package pi;

public class ParallelStrategy16Test extends BaseStrategyTests {
    ParallelStrategy16Test() {
        super(()-> Pi.parallel(16));
    }
}