package part2_lab6.task3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class IntegerProducer implements Runnable {
    private static final int TIMEOUT = 1;
    private final BlockingQueue<Integer> blockingQueue;

    private int from, to;

    private Integer number;

    private final ThreadLocalRandom random;


    public IntegerProducer(BlockingQueue<Integer> blockingQueue, int from, int to) {
        this.blockingQueue = blockingQueue;
        setRange(from, to);
        random = ThreadLocalRandom.current();
    }

    public void setRange(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public void setNumber() {
        number = random.nextInt(from, to);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                setNumber();
                blockingQueue.put(getNumber());
                System.out.println("The integer \t" + getNumber() + "\t is added by " + Thread.currentThread().getName() + ".");
                TimeUnit.SECONDS.sleep(TIMEOUT);
            }
        } catch (InterruptedException e) {
            /* Idiomatic interrupt processing pattern, graceful shutdown */
            Thread.currentThread().interrupt();
            System.out.println("The " + Thread.currentThread().getName() + " is interrupted.");
        }
    }
}
