package pi.factorial;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IterativeFactorialTest {
    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime, Mode.Throughput, Mode.All})
    @Fork(value = 1, warmups = 1)
    public void measure() {
        runTest(new IterativeFact());
    }

    private void runTest(FactorialSupplier factorialSupplier) {
        BigInteger result = factorialSupplier.get(100000);
        assertNotNull(result);
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    class IterativeFact implements FactorialSupplier {

        @Override
        public BigInteger get(long k) {
            BigInteger result = BigInteger.ONE;

            while (k-- != 0) {
                result = result.multiply(BigInteger.valueOf(k));
            }

            return result;
        }
    }
}
