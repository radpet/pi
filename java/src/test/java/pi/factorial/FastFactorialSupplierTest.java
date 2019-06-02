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
    }

    @Test
    public void testParallelismWorkStealing1() {
        runTest(new FastFactorialSupplier(Executors.newWorkStealingPool(1)));
    }

    @Test
    public void testParallelismForkJoin1() {
        runTest(new FastFactorialSupplier(new ForkJoinPool(1)));
    }

    @Test
    public void testExecutorForkJoin() {
        runTest(new FastFactorialSupplier(new ForkJoinPool()));
    }

    private void runTest(FactorialSupplier factorialSupplier) {
        BigInteger result = factorialSupplier.get(100000);
        assertNotNull(result);
    }
}
