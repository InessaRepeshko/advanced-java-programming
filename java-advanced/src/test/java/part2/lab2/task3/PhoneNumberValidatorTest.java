package part2.lab2.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static part2.lab2.task3.PhoneNumberValidatorDemo.PHONE_NUMBER_STRINGS;

/**
 * The {@code PhoneNumberValidatorTest} class provides unit tests for the {@link PhoneNumberValidator} class.
 * It tests the validating of phone number strings is a number of the Kyivstar telecommunications company.
 */
class PhoneNumberValidatorTest {
    /** Tests the validating of phone number strings is a number of the Kyivstar telecommunications company
     * by {@link PhoneNumberValidator#verifyIsKyivstarNumber(String)} method.
     */
    @Test
    @DisplayName("Should verify phone number strings is Kyivstar number")
    public void testVerifyIsKyivstarNumber() {
        IntStream.range(0, PHONE_NUMBER_STRINGS.length)
                .forEach(i -> {
                    if (i < 4) {
                        assertTrue(PhoneNumberValidator.verifyIsKyivstarNumber(PHONE_NUMBER_STRINGS[i]));
                    } else {
                        assertFalse(PhoneNumberValidator.verifyIsKyivstarNumber(PHONE_NUMBER_STRINGS[i]));
                    }
                });
    }
}
