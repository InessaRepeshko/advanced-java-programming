package part2_lab6.task5;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class PiApproximator {
    private static final int TIMEOUT = 100;

    private static final int SCALE = 50;

    private static final Scanner scanner = new Scanner(System.in);

    private static void timeout() {
        try {
            TimeUnit.NANOSECONDS.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static Thread calculationThread(
            AtomicReference<BigDecimal> pi,
            AtomicReference<BigInteger> calculatedFractions,
            BigDecimal precision) {
        Thread calculationThread = new Thread(() -> {
            BigDecimal divisor = BigDecimal.ZERO;
            BigDecimal sign = BigDecimal.ONE;
            BigDecimal fraction;

            while (pi.get().subtract(BigDecimal.valueOf(Math.PI)).abs().compareTo(precision) >= 0) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("The calculation " + Thread.currentThread().getName() + " is interrupted.");
                    break;
                }

                fraction = sign.multiply(BigDecimal.valueOf(4))
                        .divide(
                                BigDecimal.valueOf(2).multiply(divisor).add(BigDecimal.ONE),
                                SCALE,
                                RoundingMode.HALF_UP
                        );
                sign = sign.negate();
                divisor = divisor.add(BigDecimal.ONE);
                BigDecimal finalFraction = fraction;
                pi.updateAndGet(value -> value.add(finalFraction));
                calculatedFractions.updateAndGet(value -> value.add(BigInteger.ONE));
                timeout();
            }
        });

        calculationThread.start();

        return calculationThread;
    }


    private static boolean isEnd(String command) {
        return command.contains("end");
    }

    private static Thread userInputThread(
            AtomicReference<BigInteger> calculatedFractions,
            Thread calculationThread) {
        Thread userInputThread = new Thread(() -> {
            while (calculationThread.isAlive()) {
                System.out.print(
                        "\n\nEnter anything to get the current number of calculated alternating series elements\n " +
                        "or \"end\" to finish the program execution: ");
                String command = scanner.nextLine();

                if (Thread.currentThread().isInterrupted()
                        || isEnd(command)) {
                    calculationThread.interrupt();
                    System.out.println(
                            "The user input " + Thread.currentThread().getName() + " is interrupted.");
                    break;
                } else if (!command.isEmpty()) {
                    System.out.print(
                            "The current number of elements of the alternating series " +
                            "by Leibniz's formula for PI:\t" + calculatedFractions.get() + ".");
                }
            }
        });

        userInputThread.start();

        return userInputThread;
    }

    private static boolean isEpsilon(String input) {
        return input.matches("^0*.0*1$");
    }

    public static BigDecimal readPrecision() {
        BigDecimal epsilon;

        while (true) {
            try {
                System.out.print("Enter the precision value:\t");
                String input = scanner.nextLine();

                if (isEpsilon(input)) {
                    epsilon = new BigDecimal(input);
                    break;
                } else {
                    System.out.println(
                            "Error:\tInvalid precision value.\n" +
                            "\t\tPlease enter a very small positive epsilon (close to zero).\n");
                }
            } catch (NumberFormatException e) {
                System.out.println(
                        "Error:\tInvalid format of the precision value.\n" +
                        "\t\tPlease enter the epsilon separated by a \".\".\n");
            }
        }

        return epsilon;
    }

    public static void main(String[] args) {
        System.out.println("_____________________________________________________________________\n" +
                "Calculating PI in a separate thread\n" +
                "_____________________________________________________________________");
        AtomicReference<BigDecimal> pi = new AtomicReference<>(BigDecimal.ZERO);
        AtomicReference<BigInteger> calculatedFractions = new AtomicReference<>(BigInteger.ZERO);
        BigDecimal precision = readPrecision();

        Thread calculationThread = calculationThread(pi, calculatedFractions, precision);
        Thread userInputThread = userInputThread(calculatedFractions, calculationThread);

        try {
            calculationThread.join();
            userInputThread.interrupt();
            System.out.println(
                    "\n\nThe " + Thread.currentThread().getName() +
                    " and calculation " + calculationThread.getName() +
                    " threads are joined.\n" +
                    "The user input " + userInputThread.getName() + " is interrupted.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("The " + Thread.currentThread().getName() + " thread is interrupted.");
        }

        System.out.printf(
                "\n\nRESULTS:\n" +
                "The number of elements of the alternating series by Leibniz's formula for PI:\t%,d.\n",
                calculatedFractions.get());
        System.out.printf("The calculated value of the PI number with the given precision: \t"
                + pi.get() + ".\n");
        System.out.println("The library value of the PI number:\t\t\t\t\t\t\t\t\t" + Math.PI);

        scanner.close();
    }
}
