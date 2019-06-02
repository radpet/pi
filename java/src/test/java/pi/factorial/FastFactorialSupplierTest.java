package pi.factorial;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FastFactorialSupplierTest {

    @Test
    public void testWithoutParallel() {
        runTest(new DummyFactorialSupplier());
    } //2s 403

    @Test
    public void testParallelismWorkStealing1() {
        runTest(new FastFactorialSupplier(Executors.newWorkStealingPool(1)));
    } //1s 43

    @Test
    public void testParallelismForkJoin1() {
        runTest(new FastFactorialSupplier(new ForkJoinPool(1)));
    } // 711ms

    @Test
    public void testExecutorForkJoin() {
        runTest(new FastFactorialSupplier(new ForkJoinPool()));
    } //85ms

    private void runTest(FactorialSupplier factorialSupplier) {
        BigInteger result = factorialSupplier.get(100000);
        assertNotNull(result);
    }
}
