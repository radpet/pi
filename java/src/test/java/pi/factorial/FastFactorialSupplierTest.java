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


    @Test
    public void testSO(){
        assertNotNull(multiplyAll(1, 100000, 20));//333ms
    }

    public static BigInteger multiplyAll(long from, long to, int split) {
        if(split < 1 || to - from < 2) return serial(from, to);
        split--;
        long middle = (from + to) >>> 1;
        return multiplyAll(from, middle, split).multiply(multiplyAll(middle, to, split));
    }

    private static BigInteger serial(long l1, long l2) {
        BigInteger bi = BigInteger.valueOf(l1++);
        for(; l1 < l2; l1++) {
            bi = bi.multiply(BigInteger.valueOf(l1));
        }
        return bi;
    }
}
