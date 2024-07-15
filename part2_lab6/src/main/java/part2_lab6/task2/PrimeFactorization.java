package part2_lab6.task2;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PrimeFactorization implements Runnable {
    private long from;
    private long to;
    private long lastFound;
    private final Map<Long, List<Long>> map;
    private final int TIMEOUT_SEC = 1;
    private final int TIMEOUT_MILISEC = 100;
    private Thread primesThread;
    private final Runnable displayFunction;
    private final Runnable percentageFunction;
    private final Runnable finishFunction;
    private double percentage;
    private boolean paused;
    private boolean finished;

    public PrimeFactorization(Runnable addFunction, Runnable percentageFunction, Runnable finishFunction) {
        this.displayFunction = addFunction;
        this.percentageFunction = percentageFunction;
        this.finishFunction = finishFunction;
        map = new HashMap<>();
    }

    public synchronized long getFrom() {
        return from;
    }

    public synchronized void setFrom(long from) {
        this.from = from;
    }

    public synchronized long getTo() {
        return to;
    }

    public synchronized void setTo(long to) {
        this.to = to;
    }

    public Map<Long, List<Long>> getMap() {
        return map;
    }

    public synchronized long getLastFound() {
        return lastFound;
    }

    private synchronized void setLastFound(long lastFound) {
        this.lastFound = lastFound;
    }

    public synchronized double getPercentage() {
        return percentage;
    }

    private synchronized void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public synchronized boolean isPaused() {
        return paused;
    }

    private synchronized void setPaused(boolean paused) {
        this.paused = paused;
    }

    public synchronized boolean isFinished() {
        return finished;
    }

    private synchronized void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isValidRange() {
        return getFrom() < getTo();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<Long, List<Long>> entry : getMap().entrySet()) {
            result.append("Prime factors of \t").append(entry.getKey()).append(":\t\t");

            if (entry.getValue().isEmpty()) {
                result.append("None.\n");
            } else {
                result.append(entry.getValue()).append("\n");
            }
        }

        return result.toString();
    }

    public String mapEntryToString(Long key) {
        StringBuilder stringBuilder = new StringBuilder();

        if (!getMap().isEmpty()
                && getMap().containsKey(key)) {
            stringBuilder.append("Prime factors of \t").append(key).append(":\t\t");
            List<Long> entryValue = getMap().get(key);

            if (entryValue.isEmpty()) {
                stringBuilder.append("None.\n");
            } else {
                entryValue.forEach(num -> stringBuilder.append(num).append(",\t"));
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append(".\n");
            }
        } else {
            stringBuilder.append("There are no prime factors.\n");
        }

        return stringBuilder.toString();
    }

    public List<Long> findFactorsOf(long number) {
        List<Long> factorsList = new ArrayList<>();

        for (long i = 2; i <= number; i++) {
            while (number % i == 0) {
                factorsList.add(i);
                number /= i;
            }
        }

        return factorsList;
    }

    public double calculatePercentage(long currentNumber) {
        return (currentNumber - getFrom() + 1) * 1.0 / (getTo() - getFrom() + 1);
    }

    public void updatePercentage(double percentage) {
        setPercentage(percentage);

        if (percentageFunction != null) {
            Platform.runLater(percentageFunction);
        }
    }

    @Override
    public void run() {
        for (long number = getFrom(); number <= getTo(); number++) {
            try {
                updatePercentage(calculatePercentage(number));
                map.putIfAbsent(
                        number,
                        findFactorsOf(number));
                setLastFound(number);

                if (displayFunction != null) {
                    Platform.runLater(displayFunction);
                }

                TimeUnit.SECONDS.sleep(TIMEOUT_SEC);
            } catch (InterruptedException e) {
                while (isPaused()) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(TIMEOUT_MILISEC);
                    } catch (InterruptedException e1) {
                        if (isFinished()) {
                            break;
                        }
                    }
                }

                if (isFinished()) {
                    break;
                }
            }
        }

        if (finishFunction != null) {
            Platform.runLater(finishFunction);
        }
    }

    public void start() {
        primesThread = new Thread(this);
        setPaused(false);
        setFinished(false);
        primesThread.start();
    }

    public void pause() {
        setPaused(true);

        if (primesThread != null) {
            primesThread.interrupt();
        }
    }

    public void resume() {
        setPaused(false);

        if (primesThread != null) {
            primesThread.interrupt();
        }
    }

    public void finish() {
        setFinished(true);

        if (primesThread != null) {
            primesThread.interrupt();
        }
    }
}
