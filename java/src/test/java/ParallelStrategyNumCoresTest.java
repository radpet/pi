public class ParallelStrategyNumCoresTest extends BaseStrategyTests {
    ParallelStrategyNumCoresTest() {
        super(()-> new ParallelStrategy(Runtime.getRuntime().availableProcessors()));
    }
}