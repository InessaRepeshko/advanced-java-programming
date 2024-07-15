package part2.lab2.task3;

import java.util.stream.IntStream;

/**
 * The {@code PhoneNumberValidatorDemo} class is a demonstration of functionality of the {@link PhoneNumberValidator}.
 */
public class PhoneNumberValidatorDemo {
    /** The constant array representing the phone number strings with valid and invalid data and phone number formats used in the tests. */
    public static final String[] PHONE_NUMBER_STRINGS = {
            "+380671234567",
            "+380967654321",
            "+380979876543",
            "+380981112233",
            "+380661234567",
            " 0957654321 ",
            "0999876543",
            "1234567890",
            "06712345",
            "opened",
            "+38 (067) 12 34 567",
            "+38-067-12-34-567"
    };

    /**
     * Performs testing of the functionality of the {@link PhoneNumberValidator}. The {@code args} are not used.
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        IntStream.range(0, PHONE_NUMBER_STRINGS.length).forEach(i -> {
            System.out.println("\nThe phone number is:\t\"" + PHONE_NUMBER_STRINGS[i] + "\"");

            if (PhoneNumberValidator.verifyIsKyivstarNumber(PHONE_NUMBER_STRINGS[i])) {
                System.out.println("The phone number belongs to Kyivstar operator.");
            } else {
                System.out.println("The phone number does NOT belong to Kyivstar operator.");
            }
        });
    }
}
