import java.math.BigInteger;
import java.util.stream.LongStream;

class MathUtils {

    static BigInteger factorial(long k, boolean parallel) {
        LongStream longStream = LongStream.range(2, k + 1);
        if (parallel) {
            longStream = longStream.parallel();
        }
        return longStream
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, (current, factSoFar) -> factSoFar.multiply(current));
    }

    static int calculateIterations(int precision) {
        return precision / 5 + 1;
    }
}
