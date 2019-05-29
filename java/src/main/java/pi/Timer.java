package pi;

import com.google.common.base.Stopwatch;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public class Timer {
    private int numRep;
    private Runnable runnable;

    public Timer(int numRep, Runnable runnable) {
        this.numRep = numRep;
        this.runnable = runnable;
    }

    public List<Double> time(boolean silent) {
        return IntStream.range(0, numRep)
                .mapToDouble(i -> {
                    Stopwatch stopwatch = new Stopwatch();
                    stopwatch.start();
                    if (!silent) {
                        log.info("Run={} started", () -> i);
                    }
                    runnable.run();
                    stopwatch.stop();
                    double time = stopwatch.elapsedTime(TimeUnit.NANOSECONDS) * 1e-6;
                    if (!silent) {
                        log.info("Run={} took {} ms", () -> i, () -> time);
                    }
                    return time;
                })
                .boxed()
                .collect(Collectors.toList());
    }
}
