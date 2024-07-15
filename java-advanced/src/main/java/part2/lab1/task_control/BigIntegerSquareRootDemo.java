package part2.lab1.task_control;

import java.math.BigInteger;
import java.util.stream.IntStream;

import static part2.lab1.task_control.BigIntegerSquareRoot.*;

/**
 * The {@code BigIntegerSquareRootDemo} class represents showing of the functionality of the {@link BigIntegerSquareRoot}.
 * It generates array with random BigInteger numbers, finds the integer square root of BigInteger
 * using two different methods and compares the results and the time taken by each method.
 */
public class BigIntegerSquareRootDemo {
    /**
     * Carries out showing of the functionality of the {@link BigIntegerSquareRoot}. The {@code args} are not used.
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        BigInteger[] array = generateRandomBigIntegerArray();
        IntStream.range(0, array.length).forEach(i -> {
            System.out.println("\n***The number with randomly generated digits to find the integer square roots:***");
            System.out.println("Number of digits = " + NUM_DIGITS[i]
                    + ",\tBigInteger number = " + array[i]);

            try {
                ResultAndDurationPair resultUsingSqrt =
                        calculateAndMeasureTime(array[i], BigIntegerSquareRoot::findSqrtUsingSqrtFunction);
                System.out.println("Result using sqrt function: " + formatBigInteger(resultUsingSqrt.getResult())
                        + "\nTime taken using sqrt function: " + resultUsingSqrt.getDuration() + " nanoseconds");

                ResultAndDurationPair resultUsingMultiplication =
                        calculateAndMeasureTime(array[i], BigIntegerSquareRoot::findSqrtUsingMultiplication);
                System.out.println("Result using multiplication function: "
                        + formatBigInteger(resultUsingMultiplication.getResult())
                        + "\nTime taken using multiplication function: "
                        + resultUsingMultiplication.getDuration() + " nanoseconds");

                System.out.println("Conclusions:");
                printComparingResults(resultUsingSqrt.getResult(), resultUsingMultiplication.getResult());
                printComparingDurations(resultUsingSqrt.getDuration(), resultUsingMultiplication.getDuration());
            } catch (ArithmeticException e){
                System.err.println(e.getMessage());
            }
        });
    }
}
