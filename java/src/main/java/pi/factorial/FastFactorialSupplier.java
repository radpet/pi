package pi.factorial;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.LongStream;

public class FastFactorialSupplier implements FactorialSupplier {
    private final ExecutorService executorService;

    public FastFactorialSupplier(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public BigInteger get(long k) {
        try {
            return executorService
                    .submit(
                            () -> LongStream.range(2, k + 1)
                                    .parallel()
                                    .mapToObj(BigInteger::valueOf)
                                    .reduce(BigInteger.ONE, (current, factSoFar) -> factSoFar.multiply(current))
                    )
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return BigInteger.ZERO;
    }
}
