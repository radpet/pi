import lombok.extern.log4j.Log4j2;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Consumer;

@Log4j2
public class Application {
    private String[] args;

    private Application(String[] args) {
        this.args = args;
    }

    private void run() {
        parseCommandLine().ifPresent(commandLine -> {
            String mode = getOptionValue(commandLine, OptionCode.MODE);
            int numThreads = "single".equals(mode) ? 1 : Integer.parseInt(getOptionValue(commandLine, OptionCode.NUM_THREADS));
            int precision = Integer.parseInt(getOptionValue(commandLine, OptionCode.PRECISION));
            int numReps = Integer.parseInt(getOptionValue(commandLine, OptionCode.NUM_REPS));
            boolean silent = hasOption(commandLine, OptionCode.SILENT);
            String outputPath = getOptionValue(commandLine, OptionCode.OUTPUT);

            Timer timer = createTimer(mode, numThreads, precision, numReps, outputPath);
            double avgTime = timer.time(silent).stream().mapToDouble(d -> d).average().getAsDouble();

            log.info("Computing pi with precision={} took {} ms on avg for {} calls with params mode={}, num_threads={}",
                    () -> precision, () -> avgTime, () -> numReps, () -> mode, () -> numThreads);
        });
    }

    private Timer createTimer(String mode, int numThreads, int precision, int numReps, String outputPath) {
        Timer timer;

        Consumer<ComputeStrategy> compute = (strategy) -> {
            BigDecimal pi = strategy.compute(precision);
            strategy.onComplete();
            PiFileWriter.writeToFile(pi, outputPath);
        };

        if ("single".equals(mode)) {
            timer = new Timer(numReps, () -> {
                ComputeStrategy strategy = Pi.single();
                compute.accept(strategy);
            });
        } else if ("shared".equals(mode)) {
            timer = new Timer(numReps, () -> {
                ComputeStrategy strategy = Pi.commonForkJoin();
                compute.accept(strategy);
            });
        } else {
            timer = new Timer(numReps, () -> {
                ComputeStrategy strategy = Pi.parallel(numThreads);
                compute.accept(strategy);
            });
        }

        return timer;
    }

    private boolean hasOption(CommandLine commandLine, OptionCode optionCode) {
        return commandLine.hasOption(optionCode.key);
    }

    private String getOptionValue(CommandLine commandLine, OptionCode optionCode) {
        return commandLine.getOptionValue(optionCode.key, optionCode.defaultValue);
    }

    private Optional<CommandLine> parseCommandLine() {
        Options options = options();
        CommandLineParser parser = new DefaultParser();
        try {
            return Optional.of(parser.parse(options, args));
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }


    private Options options() {
        Options options = new Options();
        options.addOption(OptionCode.MODE.key, true, "mode");
        options.addOption(OptionCode.NUM_THREADS.key, true, "number of threads");
        options.addOption(OptionCode.PRECISION.key, true, "digits after decimal point");
        options.addOption(OptionCode.SILENT.key, "disable information logging");


        options.addOption(OptionCode.OUTPUT.key, true, "path to output pi file");
        options.addOption(OptionCode.NUM_REPS.key, true, "number of repetions to calculate pi");


        return options;
    }

    private enum OptionCode {
        MODE("mode", "single"),
        NUM_THREADS("num_threads", String.valueOf(Runtime.getRuntime().availableProcessors())),
        PRECISION("precision", "2"),
        SILENT("silent", "false"),
        OUTPUT("ouput", "./pi.tmp"),
        NUM_REPS("num_reps", "1");

        String key;
        String defaultValue;

        OptionCode(String key, String defaultValue) {
            this.key = key;
            this.defaultValue = defaultValue;
        }
    }


    public static void main(String[] args) {
        new Application(args).run();
    }
}
