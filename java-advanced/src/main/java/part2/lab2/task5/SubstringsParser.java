package part2.lab2.task5;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The {@code SubstringsParser} class provides methods for extracting substrings from a given input string.
 */
public class SubstringsParser {
    /**
     * Extracts substrings from the input string that are enclosed between digits.
     * @param input the input string from which substrings are to be extracted;
     * @return a list of substrings extracted from the input string;
     * @throws NullPointerException if the input string is {@code null}.
     */
    public static List<String> getSubstrings(String input) throws NullPointerException {
        String regex = "(?<=\\d)([a-zA-Z]+)(?=\\d)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.results()
                .map(matchResult -> matchResult.group())
                .collect(Collectors.toList());
    }


    /**
     * Prints the list of substrings.
     * @param substringsList the list of substrings to be printed.
     */
    public static void printSubstringsList(List<String> substringsList) {
        if (substringsList.isEmpty()) {
            System.out.print("Missing substrings");
        } else {
            substringsList.forEach(substring -> System.out.print("\"" + substring + "\"\t"));
        }
    }
}
