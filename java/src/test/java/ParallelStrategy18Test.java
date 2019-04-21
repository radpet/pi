public class ParallelStrategy18Test extends BaseStrategyTests {
    ParallelStrategy18Test() {
        super(()-> Pi.parallel(18));
    }
}