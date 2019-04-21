import java.math.BigDecimal;
import java.util.stream.IntStream;

public class SingleThreadStrategy extends ComputeStrategy {

    @Override
    protected BigDecimal strategy(int precision) {
        return IntStream.range(0, precision)
                .mapToObj(i -> computeMember(i, precision + 2))
                .reduce(BigDecimal.ZERO, (current, sumSoFar) -> sumSoFar.add(current));
    }

    @Override
    public String toString() {
        return "Single thread strategy";
    }
}
