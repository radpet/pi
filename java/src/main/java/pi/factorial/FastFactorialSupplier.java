package pi.factorial;

import pi.SharedForkJoinPool;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.stream.LongStream;

public class FastFactorialSupplier implements FactorialSupplier {
    @Override
    public BigInteger get(long k) {
        try {
            return SharedForkJoinPool.get(0).submit(() -> LongStream.range(2, k + 1)
                    .parallel()
                    .mapToObj(BigInteger::valueOf)
                    .reduce(BigInteger.ONE, (current, factSoFar) -> factSoFar.multiply(current))).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return BigInteger.ZERO;
    }
}
