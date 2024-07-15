package part2.lab1.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The {@code BigIntegerPowerTest} class provides unit tests for the {@link BigIntegerPower} class.
 * It tests the generation of random BigIntegers, the calculation of powers using two different methods,
 * and the formatting of BigIntegers for display.
 */
class BigIntegerPowerTest {
    /** A constant representing the base BigInteger used in the tests. */
    private static final BigInteger BASE = new BigInteger("982734918273498127349812734981");
    /*private static final BigInteger BASE = new BigInteger("127349812734981723948712398471");*/

    /** A constant representing the fifth power of {@code BASE}. */
    private static final BigInteger FIFTH_POWER_BASE = new BigInteger("916604400289488816388155" +
            "03476828492344299720577607828648822739360218174865096693195415188766805015123698918170" +
            "0104147995858840718432252921844437198901");
    /*private static final BigInteger FIFTH_POWER_BASE = new BigInteger("334958924472837716462997167667004804576800" +
            "18447392286487064683271297783102987041674543350528067597663788156002300806973596610114111931832594381351");*/

    /*private static Stream<Arguments> provideDataForPowerCalculationTests() {
        return Stream.of(
                Arguments.of(-1, null),
                Arguments.of(0, BigInteger.ONE),
                Arguments.of(5, FIFTH_POWER_BASE)
        );
    }*/

    /**
     * Provides data for the power calculation tests.
     * @return a stream of arguments for the power calculation tests.
     */
    private static Stream<Arguments> provideDataForPowerCalculationTests() {
        BiFunction<BigInteger, Integer, BigInteger> powFunction = BigIntegerPower::powerUsingPowFunction;
        BiFunction<BigInteger, Integer, BigInteger> multiplicationFunction = BigIntegerPower::powerUsingMultiplication;

        return Stream.of(
                Arguments.of(powFunction, -1, null),
                Arguments.of(powFunction, 0, BigInteger.ONE),
                Arguments.of(powFunction, 5, FIFTH_POWER_BASE),
                Arguments.of(multiplicationFunction, -1, null),
                Arguments.of(multiplicationFunction, 0, BigInteger.ONE),
                Arguments.of(multiplicationFunction, 5, FIFTH_POWER_BASE)
        );
    }

    /**
     * Tests the {@link BigIntegerPower#generateRandomBigInteger()} method.
     * Checks if the generated BigInteger has the expected number of digits.
     */
    @Test
    @DisplayName("Should generate BigInteger with the expected number of digits")
    public void generateRandomBigInteger() {
        BigInteger result = BigIntegerPower.generateRandomBigInteger();
        int expectedNumDigits = BigIntegerPower.NUM_DIGITS;
        int actualNumDigits = result.toString().length();

        assertEquals(expectedNumDigits, actualNumDigits);
    }

    /** Tests the calculation of powers using two different methods. */
    @ParameterizedTest(name = "Test {index}: exponent = {1}, expected = {2}")
    @DisplayName("Should calculate power. First 3 tests using function calculatePowerByPowFunction, second 3 tests - calculatePowerByMultiplyFunction")
    @MethodSource("provideDataForPowerCalculationTests")
    public void testPowerCalculation(BiFunction<BigInteger, Integer, BigInteger> function,
                                     int exponent,
                                     BigInteger expected) {
        if (exponent < 0) {
            assertThrows(ArithmeticException.class, () -> function.apply(BASE, exponent));
        } else {
            BigInteger actual = function.apply(BASE, exponent);
            assertEquals(expected, actual);
        }
    }

    /** Tests the {@link BigIntegerPower#formatBigInteger(BigInteger)} method. */
    @Test
    @DisplayName("Should format BigInteger correctly")
    public void formatBigInteger() {
        String actualString = BigIntegerPower.formatBigInteger(BASE);
        int actualLength = actualString.length();
        int expectedLength = BASE.toString().length();

        if (BASE.toString().length() > BigIntegerPower.MAX_DISPLAY_DIGITS) {
            expectedLength = BigIntegerPower.MAX_DISPLAY_DIGITS + 3;
        }

        assertEquals(expectedLength, actualLength);
    }
}
