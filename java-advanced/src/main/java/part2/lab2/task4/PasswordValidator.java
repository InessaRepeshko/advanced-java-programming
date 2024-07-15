package part2.lab2.task4;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The {@code PasswordParser} class provides utility methods for validating of password strings by certain rules
 * and printing results of validation.
 */
public class PasswordValidator {
    /** The array of descriptions of the rules of password validation. */
    public static final String[] RULES = {
            "\t- there must be at least one lowercase letter",
            "\t- there must be at least one capital letter",
            "\t- there must be at least one digit",
            "\t- there must be at least one special character: _ - *",
            "\t- there must be at least 8 characters long"
    };

    /** The array of regular expressions corresponding to the rules to be checked. */
    public static final String[] REGEXES = {
            ".*[a-z].*",
            ".*[A-Z].*",
            ".*\\d.*",
            ".*[_\\-*].*",
            ".{8,}"
    };

    /**
     * Verifies whether the password matches all the {@link PasswordValidator#RULES} using a regular expression.
     * @param password the password string to be validated;
     * @return {@code true} if the password matches all the rules, {@code false} otherwise.
     */
    public static boolean verifyPasswordMatchingAllRules(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[_\\-*]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    /**
     * Retrieves descriptions of rules that the password does not match.
     * @param password the password string to be validated;
     * @return a list of descriptions of rules that the password does not match.
     */
    public static List<String> getPasswordMismatchRulesDescriptions(String password) {
        return IntStream.range(0, REGEXES.length)
                .filter(i -> !password.matches(REGEXES[i]))
                .mapToObj(i -> RULES[i])
                .collect(Collectors.toList());
    }

    /**
     * Gets the indexes of the rules of the {@link PasswordValidator#RULES} array that the password does not match.
     * @param password the password string to be validated;
     * @return an array of indexes of rules that the password does not match.
     */
    public static int[] getPasswordMismatchRulesIndexes(String password) {
        return IntStream.range(0, PasswordValidator.REGEXES.length)
                .filter(i -> !password.matches(PasswordValidator.REGEXES[i]))
                .toArray();
    }

    /**
     * Prints the result of password verification by certain rules.
     * @param password The password string to be validated.
     */
    public static void printPasswordVerificationResult(String password) {
        if (verifyPasswordMatchingAllRules(password)) {
            System.out.println("The password matches all the rules.");
        } else {
            System.out.println("The password does not match the following rules:");
            getPasswordMismatchRulesDescriptions(password).forEach(System.out::println);
        }
    }
}
