package pi;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {

    @Test
    public void testFactorialNormal() {
        assertEquals(BigInteger.ONE, MathUtils.factorial(0,false));
        assertEquals(BigInteger.ONE, MathUtils.factorial(1, false));
        assertEquals(BigInteger.valueOf(120), MathUtils.factorial(5, false));
    }

    @Test
    public void testFactorialParallel() {
        assertEquals(BigInteger.ONE, MathUtils.factorial(0,true));
        assertEquals(BigInteger.ONE, MathUtils.factorial(1, true));
        assertEquals(BigInteger.valueOf(120), MathUtils.factorial(5, true));
    }
}
