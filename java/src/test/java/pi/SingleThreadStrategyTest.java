package pi;

public class SingleThreadStrategyTest extends BaseStrategyTests {
    SingleThreadStrategyTest() {
        super(Pi::single);
    }
}
