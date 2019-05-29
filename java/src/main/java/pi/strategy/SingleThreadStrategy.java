package pi.strategy;

import pi.MathUtils;
import pi.factorial.FactorialSupplier;

import java.math.BigDecimal;
import java.util.stream.IntStream;

public class SingleThreadStrategy extends ComputeStrategy {

    private final FactorialSupplier factorialSupplier;

    public SingleThreadStrategy(FactorialSupplier factorialSupplier) {
        this.factorialSupplier = factorialSupplier;
    }

    @Override
    protected BigDecimal strategy(int precision) {
        return IntStream.range(0, MathUtils.calculateIterations(precision))
                .mapToObj(i -> computeMember(i, precision + 2, factorialSupplier))
                .reduce(BigDecimal.ZERO, (current, sumSoFar) -> sumSoFar.add(current));
    }

    @Override
    public String toString() {
        return "Single thread pi.factorial.strategy";
    }
}
