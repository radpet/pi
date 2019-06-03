package pi.factorial;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class FastFactorialSupplierP1Test {

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime, Mode.Throughput, Mode.All})
    @Fork(value = 1, warmups = 1)
    public void measureName() {
        runTest(new FastFactorialSupplier(new ForkJoinPool(1)));
    }

    private void runTest(FactorialSupplier factorialSupplier) {
        BigInteger result = factorialSupplier.get(100000);
        assertNotNull(result);
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
