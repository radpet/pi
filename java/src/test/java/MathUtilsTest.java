import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {

    @Test
    public void testFactorial() {
        assertEquals(BigInteger.ONE, MathUtils.factorial(0));
        assertEquals(BigInteger.ONE, MathUtils.factorial(1));
        assertEquals(BigInteger.valueOf(120), MathUtils.factorial(5));
    }
}
