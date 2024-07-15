package part2.lab1.task_control;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code BigIntegerSquareRootTest} class provides unit tests for the {@link BigIntegerSquareRoot} class.
 * It tests the generation of random BigIntegers, the calculation of integer square root using two different methods,
 * and the formatting of BigIntegers for display.
 */
class BigIntegerSquareRootTest {
    /** A constant array representing the BigInteger numbers with different count of digits used in the tests. */
    private static final BigInteger[] BIG_INTEGER_NUMBERS = {
            new BigInteger("-1"),
            BigInteger.ZERO,
            BigInteger.ONE,
            new BigInteger("1361686103"),
            new BigInteger("847039021550742412994880486347242064750832"),
            new BigInteger("446932264837755871755665635909140666652359077720853361181845524741542972999861740790364429")
    };

    /** A constant representing the array of the integer square root of BigInteger numbers
     * for the {@code BIG_INTEGER_NUMBERS} array of the BigInteger numbers. */
    private static final BigInteger[] BIG_INTEGER_SQRTS = {
            null,
            new BigInteger("0"),
            new BigInteger("1"),
            new BigInteger("36901"),
            new BigInteger("920347228795057769415"),
            new BigInteger("668529928154122260725410609185195087552565391")
    };

    /**
     * Provides data for the integer square root calculation tests.
     * @return a stream for the integer square root calculation tests.
     */
    private static Stream<Arguments> provideDataForSqrtCalculationTests() {
        Function<BigInteger, BigInteger> sqrtFunction = BigIntegerSquareRoot::findSqrtUsingSqrtFunction;
        Function<BigInteger, BigInteger> multiplicationFunction = BigIntegerSquareRoot::findSqrtUsingMultiplication;

        return Stream.concat(
                Stream.of(BIG_INTEGER_NUMBERS).map(number -> Arguments.of(sqrtFunction, number)),
                Stream.of(BIG_INTEGER_NUMBERS).map(number -> Arguments.of(multiplicationFunction, number))
        );
    }

    /**
     * Tests the {@link BigIntegerSquareRoot#generateRandomBigIntegerArray()} method.
     * Checks if the generated BigInteger has the expected number of digits.
     */
    @Test
    @DisplayName("Should generate BigInteger with the expected number of digits")
    public void generateRandomBigIntegerArray() {
        BigInteger[] result = BigIntegerSquareRoot.generateRandomBigIntegerArray();
        int[] expectedNumDigits = BigIntegerSquareRoot.NUM_DIGITS;
        int[] actualNumDigits = Arrays.stream(result).mapToInt(number -> number.toString().length() - 1).toArray();

        assertArrayEquals(expectedNumDigits, actualNumDigits);
    }

    /** Tests the calculation of integer square roots using two different methods. */
    @ParameterizedTest(name = "Test {index}")
    @DisplayName("Should calculate the integer square root. " +
            "First 6 tests using function findSqrtUsingSqrtFunction, second 6 tests - findSqrtUsingMultiplication")
    @MethodSource("provideDataForSqrtCalculationTests")
    public void testSqrtCalculation(Function<BigInteger, BigInteger> function) {
        IntStream.range(0, BIG_INTEGER_NUMBERS.length)
                .forEach(i -> {
                    if (BIG_INTEGER_NUMBERS[i].compareTo(BigInteger.ZERO) < 0) {
                        assertThrows(ArithmeticException.class, () -> function.apply(BIG_INTEGER_NUMBERS[i]));
                    } else {
                        BigInteger actual = function.apply(BIG_INTEGER_NUMBERS[i]);
                        assertEquals(BIG_INTEGER_SQRTS[i], actual);
                    }
                });
    }

    /** Tests the {@link BigIntegerSquareRoot#formatBigInteger(BigInteger)} method. */
    @Test
    @DisplayName("Should format BigInteger correctly")
    public void formatBigInteger() {
        IntStream.range(4, BIG_INTEGER_NUMBERS.length)
                .forEach(i -> {
                    String actualString = BigIntegerSquareRoot.formatBigInteger(BIG_INTEGER_NUMBERS[i]);
                    int actualLength = actualString.length();
                    int expectedLength = BIG_INTEGER_NUMBERS[i].toString().length();

                    if (BIG_INTEGER_NUMBERS[i].toString().length() > BigIntegerSquareRoot.MAX_DISPLAY_DIGITS) {
                        expectedLength = BigIntegerSquareRoot.MAX_DISPLAY_DIGITS + 3;
                    }

                    assertEquals(expectedLength, actualLength);
                });
    }
}
