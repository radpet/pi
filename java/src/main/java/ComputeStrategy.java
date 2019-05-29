import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

@Log4j2
abstract class ComputeStrategy {
    protected abstract BigDecimal strategy(int precision);

    public BigDecimal compute(int precision) {
        return computeSum(strategy(precision), precision + 2);
    }

    private static BigDecimal computeSum(BigDecimal membersSum, int scale) {
        // pi = 4*882 * (1/memberSum)
        return new BigDecimal(3528).divide(membersSum, scale, BigDecimal.ROUND_HALF_UP);
    }

    static BigDecimal computeMember(int n, int scale, boolean parallel) {
        // compute (-1)^n*(4*n)! / (4^n*n!)^4
        int sign = n % 2 == 0 ? 1 : -1;
        BigDecimal a = new BigDecimal(
                BigInteger.valueOf(sign)
                        .multiply(MathUtils.factorial(4 * n, parallel))
        ).divide(new BigDecimal(
                        BigInteger.valueOf(4).pow(n)
                                .multiply(MathUtils.factorial(n, parallel)).pow(4)
                )
                , scale, RoundingMode.HALF_UP);

        //compute (1123+21460*n) / 882^(2*n)
        BigDecimal b = new BigDecimal(
                BigInteger.valueOf(1123)
                        .add(BigInteger.valueOf(21460).multiply(BigInteger.valueOf(n)))
        ).divide(new BigDecimal(
                BigInteger.valueOf(882).pow(2 * n)
        ), scale, RoundingMode.HALF_UP);

        return a.multiply(b);
    }

    public void onComplete() {
    }


}
