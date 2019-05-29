package pi;

public class ForkJoinStrategyTest extends BaseStrategyTests {
    ForkJoinStrategyTest() {
        super(()-> Pi.commonForkJoin());
    }
}