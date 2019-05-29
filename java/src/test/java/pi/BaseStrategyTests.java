package pi;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pi.strategy.ComputeStrategy;
import pi.strategy.ParallelStrategy;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@Log4j2
public class BaseStrategyTests {
    private Supplier<ComputeStrategy> strategy;

    BaseStrategyTests(Supplier<ComputeStrategy> strategy) {
        this.strategy = strategy;
    }

    @Test
    public void testPi2Digits() {
        BigDecimal pi = strategy.get().compute(2);
        assertEquals("3.14", truncatePi(pi, 2));
    }

    @Test
    public void testPi100Digits() {
        BigDecimal pi = strategy.get().compute(100);
        assertEquals(loadAsString("/100.digits"), truncatePi(pi, 100));
    }

    @Test
    public void testPi1000Digits() {
        BigDecimal pi = strategy.get().compute(1000);
        assertEquals(loadAsString("/1000.digits"), truncatePi(pi, 1000));

    }

    @Test
    public void testPi10000Digits() throws IOException, URISyntaxException {
        BigDecimal pi = strategy.get().compute(10000);
        assertEquals(loadAsString("/10000.digits"), truncatePi(pi, 10000));
    }

    private String loadAsString(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(ParallelStrategy.class.getResource(path).toURI())), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return "";
    }

    private String truncatePi(BigDecimal pi, int decimalPlaces) {
        // 3. + decimalPlaces
        final int fixedSize = 2;
        return pi.toString().substring(0, fixedSize + decimalPlaces);
    }
}
