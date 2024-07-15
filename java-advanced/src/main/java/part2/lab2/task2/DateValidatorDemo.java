package part2.lab2.task2;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.IntStream;

/**
 * The {@code DateParserTester} class is a demonstration of functionality of the {@link DateValidator}.
 */
public class DateValidatorDemo {
    /** The constant array representing the date strings with valid and invalid data and date formats used in the tests. */
    public static final String[] DATE_STRINGS = {
            "25.12.2023",
            " 01.01.2024 ",
            "29.02.2024",
            "31.03.2025",
            "25/12/2023",
            "5.3.2023",
            "32.12.2023",
            "15.13.2023",
            "31.02.202",
            "opened",
            "12.02/2023",
            "2023.12.31",
            "32.12.2023",
            "12.2023",
            "abc.12.2024",
            "20022024"
    };

    /**
     * Performs testing of the functionality of the {@link DateValidator}. The {@code args} are not used.
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        IntStream.range(0, DATE_STRINGS.length).forEach(i -> {
            System.out.println("\nThe date string is:\t\"" + DATE_STRINGS[i] + "\"");

            System.out.print("The results:");

            if (DateValidator.isValidDateFormat(DATE_STRINGS[i])) {
                try {
                    Date date = DateValidator.parseStringToDate(DATE_STRINGS[i]);
                    System.out.println("\n\tParsed String to Date object:\t" + date);

                    GregorianCalendar calendar = DateValidator.parseStringToGregorianCalendar(DATE_STRINGS[i]);
                    System.out.println("\tParsed String to GregorianCalendar Object:\t" + calendar.getTime());

                    LocalDate localDate = DateValidator.parseStringToLocalDate(DATE_STRINGS[i]);
                    System.out.println("\tParsed String to LocalDate Object:\t" + localDate);
                } catch (ParseException e) {
                    System.out.println("Unable to identify the date");
                    System.err.println(e.getMessage());
                }
            } else {
                System.out.println("\tInvalid date format entered");
            }
        });
    }
}
