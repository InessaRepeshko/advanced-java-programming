package part2.lab2.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code SubstringsParserTest} class contains unit tests for the {@link SubstringsParser} class.
 * It tests the validating of getting a substrings from a strings and outputting results.
 */
class SubstringsParserTest {
    /**
     * Creates an array of valid substrings for testing purposes.
     * @return an array of valid substrings.
     */
    public static String[][] createValidSubstrings() {
        return Stream.of(
                Arrays.asList("abc", "def"),
                Arrays.asList("a", "b", "c", "d", "e", "f", "g"),
                Arrays.asList("a", "b", "c"),
                Arrays.asList("abc"),
                Arrays.asList("b", "c", "d", "e", "f", "g"),
                Arrays.asList("cd"))
                .map(list -> list.toArray(String[]::new))
                .toArray(String[][]::new);
    }

    /**
     * Tests the {@link SubstringsParser#getSubstrings(String)} method for valid input strings.
     */
    @Test
    @DisplayName("Should verify getting substrings from valid strings")
    public void getSubstringsFromValidStrings() {
        IntStream.range(0, SubstringsParserDemo.createValidStrings().size())
                .forEach(i -> {
                    assertArrayEquals(
                            SubstringsParser.getSubstrings(
                                    SubstringsParserDemo.createValidStrings()
                                            .toArray(String[]::new)[i])
                                    .toArray(),
                            Arrays.stream(createValidSubstrings()[i]).toArray()
                    );
                });
    }

    /**
     * Tests the {@link SubstringsParser#getSubstrings(String)} method for invalid input strings.
     */
    @Test
    @DisplayName("Should verify getting substrings from invalid strings")
    public void getSubstringsFromInvalidStrings() {
        IntStream.range(0, SubstringsParserDemo.createInvalidStrings().size())
                .forEach(i -> {
                    assertTrue(
                            SubstringsParser.getSubstrings(
                                    SubstringsParserDemo.createInvalidStrings()
                                            .toArray(String[]::new)[i])
                                    .isEmpty()
                    );
                });
    }

    /**
     * Tests the {@link SubstringsParser#getSubstrings(String)} method with null input (without string).
     */
    @Test
    @DisplayName("Should verify getting substrings from null (without string)")
    public void getSubstringsWithNull() {
        assertThrows(NullPointerException.class, () -> {
            SubstringsParser.getSubstrings(null);
        });
    }

    /**
     * Tests the {@link SubstringsParser#printSubstringsList(List)} method with valid input strings.
     */
    @Test
    @DisplayName("Should verify output of substrings from valid strings")
    public void printSubstringsListWithValidStrings() {
        IntStream.range(0, SubstringsParserDemo.createValidStrings().size())
                .forEach(i -> {
                    assertEquals(
                            SubstringsParser.getSubstrings(
                                            SubstringsParserDemo.createValidStrings()
                                                    .toArray(String[]::new)[i])
                                    .toString(),
                            Arrays.toString(createValidSubstrings()[i])
                    );
                });
    }

    /**
     * Tests the {@link SubstringsParser#printSubstringsList(List)} method with invalid input strings.
     */
    @Test
    @DisplayName("Should verify output of substrings from invalid strings")
    public void printSubstringsListWithInvalidStrings() {
        IntStream.range(0, SubstringsParserDemo.createInvalidStrings().size())
                .forEach(i -> {
                    assertEquals(
                            SubstringsParser.getSubstrings(
                                            SubstringsParserDemo.createInvalidStrings()
                                                    .toArray(String[]::new)[i])
                                    .toString(),
                            new ArrayList<String>().toString()
                    );
                });
    }
}
