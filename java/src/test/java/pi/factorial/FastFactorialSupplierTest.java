package pi.factorial;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FastFactorialSupplierTest {

    @Test
    public void testWithoutParallel() {
        //2s 403
        runTest(new DummyFactorialSupplier());
    }

    @Test
    public void testParallelismWorkStealing1() {
        //1s 43
        runTest(new FastFactorialSupplier(Executors.newWorkStealingPool(1)));
    }

    @Test
    public void testParallelismForkJoin1() {
        // 711ms
        runTest(new FastFactorialSupplier(new ForkJoinPool(1)));
    }

    @Test
    public void testExecutorForkJoin() {
        //85ms
        runTest(new FastFactorialSupplier(new ForkJoinPool()));
    }

    private void runTest(FactorialSupplier factorialSupplier) {
        BigInteger result = factorialSupplier.get(100000);
        assertNotNull(result);
//        assertEquals(456574, result.toString().length());
    }
}
