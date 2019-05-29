import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.stream.IntStream;

@Log4j2
class ForkJoinStrategy extends ComputeStrategy {
    @Override
    protected BigDecimal strategy(int precision) {
        return IntStream.range(0, MathUtils.calculateIterations(precision))
                .parallel()
                .mapToObj(i -> computeMember(i, precision + 2, true))
                .reduce(BigDecimal.ZERO, (current, sumSoFar) -> sumSoFar.add(current));
    }

    @Override
    public String toString() {
        return "Parallel strategy using common fork join pool";
    }
}
