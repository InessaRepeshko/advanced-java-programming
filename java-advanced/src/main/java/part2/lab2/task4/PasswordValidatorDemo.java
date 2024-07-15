package part2.lab2.task4;

import java.util.stream.IntStream;

/**
 * The {@code PasswordValidatorDemo} class is a demonstration of functionality of the {@link PasswordValidator} class.
 */
public class PasswordValidatorDemo {
    /** The constant array representing the password strings with valid and invalid data used in the tests. */
    public static final String[] PASSWORD_STRINGS = {
            "Password123*",
            "qweRTY123_",
            "LongPassword123_-*",
            "Passw123* ",
            "",
            "PASSWORD123*",
            "password123*",
            "Password*",
            "Password123",
            "Pass1*",
            "PASSWORD123",
            "password_",
            "12345678",
            "password",
            "short",

    };

    /**
     * Performs testing of the functionality of the {@link PasswordValidator}. The {@code args} are not used.
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        IntStream.range(0, PASSWORD_STRINGS.length)
                .forEach(i -> {
                    System.out.println("\nThe password is:\t\"" + PASSWORD_STRINGS[i] + "\"");
                    PasswordValidator.printPasswordVerificationResult(PASSWORD_STRINGS[i]);
                });
    }
}
