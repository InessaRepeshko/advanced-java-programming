package part2.lab2.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static part2.lab2.task2.DateValidatorDemo.DATE_STRINGS;

/**
 * The {@code DateValidatorTest} class provides unit tests for the {@link DateValidator} class.
 * It tests the parsing the provided string into the {@link Date}, {@link GregorianCalendar}
 * and {@link LocalDate} (in the format dd.MM.yyyy) objects.
 */
class DateValidatorTest {
    /** The constant array representing strings with valid results of parsing strings to Date and GregorianCalendar
     *  objects used in the tests.
     */
    private static final String[] DATE_AND_GCALNEDAR_OBJS = {
            "Mon Dec 25 00:00:00 EET 2023",
            "Mon Jan 01 00:00:00 EET 2024",
            "Thu Feb 29 00:00:00 EET 2024",
            "Mon Mar 31 00:00:00 EEST 2025"
    };

    /** The constant array representing strings with valid results of parsing strings to LocalDate objects used in the tests. */
    private static final String[] LOCAL_DATE_OBJS = {
            "2023-12-25",
            "2024-01-01",
            "2024-02-29",
            "2025-03-31"
    };

    /** Tests the parsing of date strings to Date objects
     * by {@link DateValidator#parseStringToDate(String)} method.
     */
    @Test
    @DisplayName("Should verify parsed Date object from String")
    public void testParseStringToDate() {
        IntStream.range(0, DATE_STRINGS.length)
                .forEach(i -> {
                    if (DateValidator.isValidDateFormat(DATE_STRINGS[i])) {
                        try {
                            assertEquals(
                                    DATE_AND_GCALNEDAR_OBJS[i],
                                    DateValidator.parseStringToDate(DATE_STRINGS[i]).toString()
                            );
                        } catch (ParseException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                });
    }

    /** Tests the parsing of date strings to GregorianCalendar objects
     * by {@link DateValidator#parseStringToGregorianCalendar(String)} method.
     */
    @Test
    @DisplayName("Should verify parsed GregorianCalendar object from String")
    public void testParseStringToGregorianCalendar() {
        IntStream.range(0, DATE_STRINGS.length)
                .forEach(i -> {
                    if (DateValidator.isValidDateFormat(DATE_STRINGS[i])) {
                        assertEquals(
                                DATE_AND_GCALNEDAR_OBJS[i],
                                DateValidator.parseStringToGregorianCalendar(DATE_STRINGS[i]).getTime().toString()
                        );
                    }
                });
    }

    /** Tests the parsing of date strings to LocalDate objects in the format dd.MM.yyyy
     * by {@link DateValidator#parseStringToLocalDate(String)} method.
     */
    @Test
    @DisplayName("Should verify parsed LocalDate object from String")
    public void testParseStringToLocalDate() {
        IntStream.range(0, DATE_STRINGS.length)
                .forEach(i -> {
                    if (DateValidator.isValidDateFormat(DATE_STRINGS[i])) {
                        assertEquals(
                                LOCAL_DATE_OBJS[i],
                                DateValidator.parseStringToLocalDate(DATE_STRINGS[i]).toString()
                        );
                    }
                });
    }
}
