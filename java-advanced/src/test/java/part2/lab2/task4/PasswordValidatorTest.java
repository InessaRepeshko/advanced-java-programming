package part2.lab2.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code PasswordValidatorTest} class provides unit tests for the {@link PasswordValidator} class.
 * It tests the validating of password strings by certain rules.
 */
class PasswordValidatorTest {
    /**
     * The constant array representing the indexes of the rules of the {@link PasswordValidator#RULES} array that
     * the password does not match in {@link PasswordValidatorDemo#PASSWORD_STRINGS} test data.
     */
    public static final int[][] RULES_INDEXES = {
            new int[] {0, 1, 2, 3, 4},
            new int[] {0},
            new int[] {1},
            new int[] {2},
            new int[] {3},
            new int[] {4},
            new int[] {0, 3},
            new int[] {1, 2},
            new int[] {0, 1, 3},
            new int[] {1, 2, 3},
            new int[] {1, 2, 3, 4}
    };

    /**
     * Generates indexed descriptions based on the provided {@link PasswordValidator#RULES} rules
     * and their corresponding indexes at {@link PasswordValidatorTest#RULES_INDEXES}.
     * @param rules an array of rule descriptions;
     * @param rulesIndexes a two-dimensional array where each inner array contains indexes of rules for a specific category.
     * @return an array of lists of rule descriptions indexed according to the provided indexes.
     */
    public static List<String>[] getIndexedDescriptions(String[] rules, int[][] rulesIndexes) {
        List<String>[] descriptions = new List[rulesIndexes.length];

        IntStream.range(0, rulesIndexes.length).forEach(i -> {
            descriptions[i] = IntStream.of(rulesIndexes[i])
                    .filter(ruleIndex -> ruleIndex >= 0 && ruleIndex < rules.length)
                    .mapToObj(ruleIndex -> rules[ruleIndex])
                    .collect(Collectors.toList());
        });

        return descriptions;
    }


    /** Tests the validating of password strings by all certain rules.
     * by {@link PasswordValidator#verifyPasswordMatchingAllRules(String)} method
     */
    @Test
    @DisplayName("Should verify password by all rules")
    public void verifyPasswordMatchingAllRules() {
        IntStream.range(0, 4)
                .forEach(i -> {
                    assertTrue(
                            PasswordValidator.verifyPasswordMatchingAllRules(
                                    PasswordValidatorDemo.PASSWORD_STRINGS[i]
                            )
                    );
                });
    }

    /** Tests getting the indexes of the rules of the {@link PasswordValidator#RULES} array that
     * the password does not match by {@link PasswordValidator#getPasswordMismatchRulesIndexes(String)} method.
     */
    @Test
    @DisplayName("Should verify getting indexes of the rules that the password does not match")
    public void getPasswordMismatchRulesIndexes() {
        IntStream.range(4, PasswordValidatorDemo.PASSWORD_STRINGS.length)
                .forEach(i -> {
                    assertArrayEquals(
                            RULES_INDEXES[i - 4],
                            PasswordValidator.getPasswordMismatchRulesIndexes(
                                    PasswordValidatorDemo.PASSWORD_STRINGS[i]
                            )
                    );
                });
    }

    /** Tests getting the descriptions of the rules of the {@link PasswordValidator#RULES} array that
     * the password does not match by {@link PasswordValidator#getPasswordMismatchRulesDescriptions(String)} method.
     */
    @Test
    @DisplayName("Should verify getting indexes of the rules that the password does not match")
    public void getPasswordMismatchRulesDescriptions() {
        IntStream.range(4, PasswordValidatorDemo.PASSWORD_STRINGS.length)
                .forEach(i -> {
                    assertEquals(
                            getIndexedDescriptions(PasswordValidator.RULES, RULES_INDEXES)[i - 4],
                            PasswordValidator.getPasswordMismatchRulesDescriptions(PasswordValidatorDemo.PASSWORD_STRINGS[i])
                    );
                });
    }
}
