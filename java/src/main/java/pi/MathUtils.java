package pi;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class MathUtils {
    private static FactorialUtil factorialUtil;

    public static BigInteger factorial(long k, boolean parallel) {
        if (factorialUtil == null) {
            factorialUtil = new FactorialUtil(1); //todo expose num_threads as env variable or config class
        }

        try {
            return factorialUtil.factorial(k);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return BigInteger.ONE; //maybe a bad value, better throw exceptions
    }

    static class FactorialUtil {
        ExecutorService executorService;

        FactorialUtil(int numThreads) {
            executorService = new ForkJoinPool(numThreads);
        }

        BigInteger factorial(long k) throws ExecutionException, InterruptedException {
            return executorService.submit(() -> LongStream.range(2, k + 1)
                    .parallel()
                    .mapToObj(BigInteger::valueOf)
                    .reduce(BigInteger.ONE, (current, factSoFar) -> factSoFar.multiply(current))).get();
        }
    }

    public static int calculateIterations(int precision) {
        return precision / 5 + 1;
    }
}
