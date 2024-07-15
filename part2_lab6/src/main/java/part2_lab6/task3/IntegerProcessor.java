package part2_lab6.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class IntegerProcessor implements Runnable {
    private final BlockingQueue<Integer> blockingQueue;

    private final List<Integer> numbers;

    public IntegerProcessor(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
        numbers = new ArrayList<>();
    }

    private void addToList(Integer number) {
        numbers.add(number);
    }

    private int getCount() {
        return numbers.size();
    }

    private int getSum() {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    private double getAverage() {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(Double.MIN_VALUE);
    }

    private String getNumbersString() {
        return numbers.toString();
    }

    public void run() {
        System.out.println("LOGGING:");

        try {
            while (!Thread.currentThread().isInterrupted()) {
                addToList(blockingQueue.take());
                System.out.printf("The average value \t%.2f\t  for sum \t\t" + getSum() +
                        "\t of \t" + getCount() + "\t numbers is calculated by "
                        + Thread.currentThread().getName() + ".\n", getAverage());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("The " + Thread.currentThread().getName() + " is interrupted.");
            System.out.println("\nSUMMARY:\n" +
                    "The list of numbers:\t" + getNumbersString() + ".\n" +
                    "The sum of numbers:\t\t" + getSum() + ".\n" +
                    "The count of numbers:\t" + getCount() + ".\n" +
                    "The average value:\t\t" + getAverage() + ".");
        }
    }
}
