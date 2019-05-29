package pi.factorial;

import pi.MathUtils;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FactorialCache implements FactorialSupplier {

    private Map<Long, BigInteger> cache;

    private FactorialCache(int k) {// slow constructor
        cache = new HashMap<>(); // threads will only read from it
        calculate(k);
    }

    private void calculate(int k) {
        BigInteger result = BigInteger.ONE;
        cache.put(0l, result);
        cache.put(1l, result);
        for (long i = 2; i <= k; i += 1) {
            result = result.multiply(BigInteger.valueOf(i));
            cache.put(i, result);
        }
    }

    @Override
    public BigInteger get(long k) {
        return cache.get(k);
    }

    public static FactorialCache of(int precision) {
        return new FactorialCache(4 * MathUtils.calculateIterations(precision) + 2); // max factorial arg is 4*n, where n is upper limit
    }
}
