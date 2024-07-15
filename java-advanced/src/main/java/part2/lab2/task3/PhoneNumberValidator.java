package part2.lab2.task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code PhoneNumberParser} class provides utility methods for validating of phone number strings
 * is a number of the Kyivstar telecommunications company.
 */
public class PhoneNumberValidator {
    /** Checks if the phone number is a number of the Kyivstar telecommunications company. */
    public static boolean verifyIsKyivstarNumber(String phoneNumber) {
        String regex = "^\\+380(67|96|97|98)\\d{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }
}
