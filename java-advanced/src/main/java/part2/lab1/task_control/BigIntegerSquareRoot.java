package part2.lab1.task_control;

import java.math.BigInteger;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * This class provides methods for working with BigInteger numbers,
 * including generating a random BigInteger, finding the integer square root of BigInteger,
 * and comparing the results and durations of different methods of finding the integer square root of BigInteger.
 */
public class BigIntegerSquareRoot {
    /** The number of digits of the random BigInteger number. */
    public static final int[] NUM_DIGITS = new int[] {10, 42, 90};

    /** The maximum number of digits to be displayed in the console. */
    public static final int MAX_DISPLAY_DIGITS = 100;

    /**
     * Generates a random BigInteger array with the number of digits equal to the length of
     * the '{@code NUM_DIGITS}' array of digits.
     * @return an array of random BigInteger numbers.
     */
    public static BigInteger[] generateRandomBigIntegerArray() {
        Random random = new Random();

        return IntStream.range(0, NUM_DIGITS.length)
                .mapToObj(j -> {
                    StringBuilder sb = new StringBuilder(NUM_DIGITS[j]);
                    sb.append(random.nextInt(9) + 1);
                    IntStream.range(0, NUM_DIGITS[j])
                            .forEach(i -> {
                                sb.append(random.nextInt(10));
                            });

                    return new BigInteger(sb.toString());
                })
                .toArray(BigInteger[]::new);
    }

    /**
     * Finds the integer square root of BigInteger using the sqrt function {@link BigInteger#sqrt()}.
     * @param number the BigInteger number;
     * @return the integer square root of BigInteger number.
     */
    public static BigInteger findSqrtUsingSqrtFunction(BigInteger number) {
        return number.sqrt();
    }

    /**
     * Finds the integer square root of BigInteger using binary search to efficiently find the root
     * using method {@link BigInteger#multiply(BigInteger)}.
     * @param number the BigInteger number;
     * @return the integer square root of BigInteger number;
     * @throws ArithmeticException {@code number} is negative
     * (this would cause the operation to yield a non-real value "i").
     */
    public static BigInteger findSqrtUsingMultiplication(BigInteger number) {
        if (number.compareTo(BigInteger.ZERO) < 0) {
            throw new ArithmeticException("Negative number");
        }

        BigInteger left = BigInteger.ZERO;
        BigInteger right = number;

        while (left.compareTo(right) <= 0) {
            BigInteger mid = left.add(right).shiftRight(1);
            BigInteger midSquared = mid.multiply(mid);

            if (midSquared.compareTo(number) == 0) {
                return mid;
            } else if (midSquared.compareTo(number) < 0) {
                left = mid.add(BigInteger.ONE);
            } else {
                right = mid.subtract(BigInteger.ONE);
            }
        }

        return right;
    }

    /** A class to hold a result and the duration it took to compute that result. */
    public static class ResultAndDurationPair {
        private final BigInteger result;
        private final long duration;

        public ResultAndDurationPair(BigInteger result, long duration) {
            this.result = result;
            this.duration = duration;
        }

        public BigInteger getResult() {
            return result;
        }

        public long getDuration() {
            return duration;
        }
    }

    /**
     * Calculates the result of a function and measures the time it took to compute the result.
     * @param number the BigInteger number;
     * @param function {@code Function} the function to apply to find the integer square root;
     * @return a CalculationResultAndDuration containing the result and the duration it took to compute the result.
     */
    public static ResultAndDurationPair calculateAndMeasureTime(BigInteger number,
                                                                Function<BigInteger, BigInteger> function) {
        long startTime = System.nanoTime();
        BigInteger result = function.apply(number);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        return new ResultAndDurationPair(result, duration);
    }

    /**
     * Formats a BigInteger for display, truncating it if it has more than '{@value MAX_DISPLAY_DIGITS}' digits.
     * @param number the BigInteger to format;
     * @return a string representation of the BigInteger, truncated if necessary.
     */
    public static String formatBigInteger(BigInteger number) {
        String str = number.toString();

        if (str.length() > MAX_DISPLAY_DIGITS) {
            str = str.substring(0, MAX_DISPLAY_DIGITS) + "...";
        }

        return str;
    }

    /**
     * Compares two BigIntegers and prints a message indicating whether they are equal.
     * @param resultUsingSqrt the first BigInteger to compare;
     * @param resultUsingMultiplication the second BigInteger to compare.
     */
    public static void printComparingResults(BigInteger resultUsingSqrt,
                                             BigInteger resultUsingMultiplication) {
        if (resultUsingSqrt.equals(resultUsingMultiplication)) {
            System.out.println("The results are equal.");
        } else {
            System.out.println("The results are not equal.");
        }
    }

    /**
     * Compares two durations and prints a message indicating which is shorter.
     * @param durationUsingSqrt the first duration to compare;
     * @param durationUsingMultiplication the second duration to compare.
     */
    public static void printComparingDurations(long durationUsingSqrt,
                                               long durationUsingMultiplication) {
        if (durationUsingSqrt < durationUsingMultiplication) {
            System.out.println("Sqrt function is faster.");
        } else if (durationUsingSqrt > durationUsingMultiplication) {
            System.out.println("Multiplication is faster.");
        } else {
            System.out.println("Both methods took the same amount of time.");
        }
    }
}
