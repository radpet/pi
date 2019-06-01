package pi;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class MathUtils {

    public static BigInteger factorial(long k) {
        return LongStream.range(2, k + 1)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, (current, factSoFar) -> factSoFar.multiply(current));
    }

    public static int calculateIterations(int precision) {
        return precision / 5 + 1;
    }
}
