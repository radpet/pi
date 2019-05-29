package pi.strategy;

import lombok.extern.log4j.Log4j2;
import pi.MathUtils;
import pi.factorial.FactorialSupplier;

import java.math.BigDecimal;
import java.util.stream.IntStream;

@Log4j2
public class ForkJoinStrategy extends ComputeStrategy {
    private final FactorialSupplier factorialSupplier;

    public ForkJoinStrategy(FactorialSupplier factorialSupplier) {
        this.factorialSupplier = factorialSupplier;
    }

    @Override
    protected BigDecimal strategy(int precision) {
        return IntStream.range(0, MathUtils.calculateIterations(precision))
                .parallel()
                .mapToObj(i -> computeMember(i, precision + 2, factorialSupplier))
                .reduce(BigDecimal.ZERO, (current, sumSoFar) -> sumSoFar.add(current));
    }

    @Override
    public String toString() {
        return "Parallel pi.factorial.strategy using common fork join pool";
    }
}
