package part2.lab2.task2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code DateParser} class provides utility methods for validating and parsing date strings.
 */
public class DateValidator {
    /**
     * Validates if the provided input string adheres to the expected date format (dd.MM.yyyy) with optional spaces.
     * @param input the date string to be validated;
     * @return {@code true} if the input matches the expected format, {@code true} otherwise.
     */
    public static boolean isValidDateFormat(String input) {
        String regex = "\\s*(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.](\\d{4})\\s*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    /**
     * Parses the provided input string into the {@link Date} object in the format dd.MM.yyyy
     * by removing spaces from both ends of the string before parsing.
     * @param input The date string to be parsed;
     * @return a Date object representing the parsed date;
     * @throws ParseException if the input string does not conform to the expected format.
     */
    public static Date parseStringToDate(String input) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = dateFormat.parse(input.trim());

        return date;
    }

    /**
     * Parses the provided input string into the {@link GregorianCalendar} object in the format dd.MM.yyyy
     * by splitting the entered string into parts using dots,
     * extracts the day, month (Months in the Calendar class start from 0) and year,
     * and then creates an object of type GregorianCalendar with these values of day, month and year.
     * @param input The date string to be parsed.
     * @return A GregorianCalendar object representing the parsed date.
     */
    public static GregorianCalendar parseStringToGregorianCalendar(String input) {
        String[] parts = input.trim().split("\\.");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]) - 1;
        int year = Integer.parseInt(parts[2]);
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);

        return calendar;
    }

    /**
     * Parses the provided input string into the {@link LocalDate} object in the format dd.MM.yyyy
     * by removing spaces from both ends of the string before parsing.
     * @param input the date string to be parsed;
     * @return a LocalDate object representing the parsed date.
     */
    public static LocalDate parseStringToLocalDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(input.trim(), formatter);

        return localDate;
    }
}
