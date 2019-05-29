package pi.factorial;

import pi.MathUtils;

import java.math.BigInteger;

public class DummyFactorialSupplier implements FactorialSupplier {
    @Override
    public BigInteger get(long k) {
        return MathUtils.factorial(k, false);
    }
}
