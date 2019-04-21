public class Pi {

    public static ComputeStrategy parallel(int numThreads) {
        return new ParallelStrategy(numThreads);
    }

    public static ComputeStrategy single() {
        return new SingleThreadStrategy();
    }

    public static ComputeStrategy commonForkJoin() {
        return new ForkJoinStrategy();
    }

}
