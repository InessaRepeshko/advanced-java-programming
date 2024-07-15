package part2_lab6.task3;

import java.util.concurrent.*;

public class BlockingQueueProcessor {
    private static final int QUEUE_CAPACITY = 10;
    private static final int THREADS_NUM = 2;
    private static final long DELAY = 12L;
    private static final int RANGE_FROM = 1;
    private static final int RANGE_TO = 10;

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

        IntegerProducer producer = new IntegerProducer(blockingQueue, RANGE_FROM, RANGE_TO);
        IntegerProcessor processor = new IntegerProcessor(blockingQueue);

        ExecutorService executor = Executors.newFixedThreadPool(THREADS_NUM);
        executor.execute(producer);
        executor.execute(processor);

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            executor.shutdownNow();
            scheduler.shutdown();
        }, DELAY, TimeUnit.SECONDS);
    }
}
