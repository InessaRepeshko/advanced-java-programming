package part2.lab2.task5;

import java.util.Arrays;
import java.util.List;

/**
 * The {@code SubstringsParserDemo} class demonstrates the usage of the {@link SubstringsParser} class
 * by generating valid and invalid input strings and displaying the resulting substrings.
 */
public class SubstringsParserDemo {
    /**
     * Generates a list of valid input strings containing letters enclosed between digits.
     * @return a list of valid input strings.
     */
    public static List<String> createValidStrings() {
        return Arrays.asList(
                "123abc456def789",
                "1a2b3c4d5e6f7g8",
                "12a34b56c78d",
                "1abc2",
                "a1b2c3d4e5f6g7",
                "ab123cd456ef"
        );
    }

    /**
     * Generates a list of invalid input strings without letters enclosed between digits.
     * @return a list of invalid input strings.
     */
    public static List<String> createInvalidStrings() {
        return Arrays.asList(
                "123456789",
                "abcdef",
                "1234abcdef",
                "abcdef1234",
                "  123 kjf 987 ",
                "!@#$%^&*()!@#$%^&*(",
                ""
        );
    }

    /**
     * The main method generates input strings, extracts substrings, and displays the results.
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.print("The resulting substrings of strings:");
        try {
            createValidStrings().forEach(input -> {
                System.out.print("\n\nThe string:\t\"" + input + "\"\n"
                        + "The substrings:\t");
                SubstringsParser.printSubstringsList(
                        SubstringsParser.getSubstrings(input)
                );
            });

            createInvalidStrings().forEach(input -> {
                System.out.print("\n\nThe string:\t\"" + input + "\"\n"
                        + "The substrings:\t");
                SubstringsParser.printSubstringsList(
                        SubstringsParser.getSubstrings(input)
                );
            });

            System.out.print("\n\nThe string:\t\"" + null + "\"\n"
                    + "The substrings:\t");
            SubstringsParser.printSubstringsList(
                    SubstringsParser.getSubstrings(null)
            );
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }
}
