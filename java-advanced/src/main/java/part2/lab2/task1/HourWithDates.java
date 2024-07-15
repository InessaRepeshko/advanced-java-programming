package part2.lab2.task1;

import part1.lab4.task1.Hour;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * The {@code HourWithDates} class represents an hour with additional date information.
 * It extends the {@link Hour} class and includes methods for managing {@link ZonedDateTime} operating hour field,
 * calculating {@link Duration} intervals and displaying results.
 */
public class HourWithDates extends Hour {
    /** The path to the resource bundle files for localization. */
    private final String resourceBundle = "part2/lab2/task1/hours";

    /** Represents the operating hour date and time for this instance. */
    private ZonedDateTime operatingHour;

    /** Constructs a new default {@link HourWithDates} object. */
    public HourWithDates() {}

    /**
     * Constructs a new {@link HourWithDates} object with the specified parameters.
     * @param ridership The number of ridership.
     * @param comment The comment associated with this hour.
     * @param operatingHour The operating hour.
     */
    public HourWithDates(int ridership, String comment, ZonedDateTime operatingHour) {
        super(ridership, comment);
        this.operatingHour = operatingHour;
    }

    /**
     * Gets the {@link HourWithDates#operatingHour}.
     * @return The operating hour date and time.
     */
    public ZonedDateTime getOperatingHour() {
        return operatingHour;
    }

    /**
     * Overrides the {@link Hour#getComment()} method to retrieve localized comment.
     * @return Localized comment.
     */
    @Override
    public String getComment() {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);
        return bundle.getString(super.getComment());
    }

    /**
     * Sets the {@link HourWithDates#operatingHour}.
     * @param operatingHour The operating hour to set.
     */
    public void setOperatingHour(ZonedDateTime operatingHour) {
        this.operatingHour = operatingHour;
    }

    /**
     * Provides the string representing the {@link HourWithDates} object.
     * @return the string representing the {@link HourWithDates} object.
     */
    @Override
    public String toString() {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(Locale.getDefault());
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());

        return String.format(
                Locale.getDefault(),
                "\t" + bundle.getString("hour") + "\t{ "
                        + bundle.getString("operationHour") + ": " + getOperatingHour().format(dateTimeFormatter) + ";\t"
                        + bundle.getString("ridership") + " = " + numberFormat.format(getRidership()) + ";\t"
                        + bundle.getString("comment") + " = \'" + getComment() + "\' }");
    }

    /**
     * Calculates the word count of the comment using regular expressions.
     * @return The word count of the comment.
     */
    @Override
    public int calculateWordCountOfComment() {
        String comment = getComment();

        if (comment == null || comment.isEmpty()) {
            return 0;
        }

        String regex = "\\s+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(comment);
        String[] wordArray = pattern.split(comment);

        return wordArray.length;
    }

    /**
     * Calculates the time interval in {@link Duration} between two operating hours.
     * @param hour1 The first HourWithDates object.
     * @param hour2 The second HourWithDates object.
     * @return The duration between the two hours.
     */
    public Duration getInterval(HourWithDates hour1, HourWithDates hour2) {
        return Duration.between(hour1.getOperatingHour(), hour2.getOperatingHour()).abs();
    }

    /**
     * Finds the smallest interval in {@link Duration} between hours in the given array.
     * @param hours An array of HourWithDates objects.
     * @return The smallest interval in {@link Duration}.
     */
    public Duration findSmallestIntervalBetweenHours(HourWithDates[] hours) {
        return Stream.of(hours)
                .flatMap(hour -> Stream.of(hours)
                        .filter(other -> hour != other)
                        .map(other -> getInterval(hour, other))
                )
                .min(Duration::compareTo)
                .orElse(Duration.ZERO);
    }

    /**
     * Finds the largest interval in {@link Duration} between hours in the given array.
     * @param hours An array of HourWithDates objects.
     * @return The largest interval in {@link Duration}.
     */
    public Duration findLargestIntervalBetweenHours(HourWithDates[] hours) {
        return Stream.of(hours)
                .flatMap(hour -> Stream.of(hours)
                        .filter(other -> hour != other)
                        .map(other -> getInterval(hour, other))
                )
                .max(Duration::compareTo)
                .orElse(Duration.ZERO);
    }


    /**
     * Generates a new {@link HourWithDates} object with predefined values.
     * @return The created {@link HourWithDates} object.
     */
    public HourWithDates createHour() {
        setOperatingHour(
                ZonedDateTime.of(
                        2024,
                        Month.MAY.getValue(),
                        3,
                        17, 0, 0, 0,
                        ZoneId.of("Europe/Kiev")
                )
        );
        setRidership(1234);
        setComment("veryHighRidership");

        return this;
    }

    /**
     * Generates an array of {@link HourWithDates} objects with predefined values.
     * @return An array of {@link HourWithDates} objects.
     */
    public HourWithDates[] createHours() {
        return new HourWithDates[] {
            new HourWithDates(1100, "veryHighRidership",
                    ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 8, 0, 0, 0, ZoneId.of("Europe/Kiev"))),
            new HourWithDates(110, "lowRidership",
                    ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 12, 0, 0, 0, ZoneId.of("Europe/Kiev"))),
            new HourWithDates(650, "highRidership",
                    ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 14, 0, 0, 0, ZoneId.of("Europe/Kiev"))),
            new HourWithDates(532, "highRidership",
                    ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 15, 0, 0, 0, ZoneId.of("Europe/Kiev"))),
            new HourWithDates(60, "veryLowRidership",
                    ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 22, 0, 0, 0, ZoneId.of("Europe/Kiev"))),
            new HourWithDates(188, "lowRidership",
                    ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 10, 0, 0, 0, ZoneId.of("Europe/Kiev"))),
            new HourWithDates(200, "mediumRidership",
                    ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 5, 0, 0, 0, ZoneId.of("Europe/Kiev"))),
            new HourWithDates(200, "mediumRidership",
                    ZonedDateTime.of(2024, Month.MAY.getValue(), 18, 17, 8, 32, 0, ZoneId.of("Europe/Kiev"))),
        };
    }

    /**
     * Prints the interval in {@link Duration} in a human-readable format.
     * @param interval The duration to print.
     */
    public void printInterval(Duration interval) {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        System.out.println(
                numberFormat.format(interval.toDays()) + " " + bundle.getString("timeDays") + "    "
                + numberFormat.format(interval.toHours() % 24) + " " + bundle.getString("timeHours") + "    "
                + numberFormat.format(interval.toMinutes() % 60) + " " + bundle.getString("timeMinutes") + "    "
                + numberFormat.format(interval.toSeconds() % 60 ) + " " + bundle.getString("timeSeconds") + "."
        );
    }

    /**
     * Shows the interval results between predefined hours.
     */
    public void showIntervalsResults() {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);

        HourWithDates hour1 = new HourWithDates(1234, "veryHighRidership",
                ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 7, 0, 0, 0, ZoneId.of("Europe/Kiev")));
        HourWithDates hour2 = new HourWithDates(123, "lowRidership",
                        ZonedDateTime.of(2024, Month.JANUARY.getValue(), 1, 7, 0, 0, 0, ZoneId.of("Europe/Kiev")));
        HourWithDates hour3 = new HourWithDates(654, "highRidership",
                        ZonedDateTime.of(2024, Month.JUNE.getValue(), 15, 18, 15, 34, 0, ZoneId.of("Europe/Kiev")));
        System.out.println(bundle.getString("createdHours") + ":");
        System.out.println(hour1 + "\n" + hour2 + "\n" + hour3);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(Locale.getDefault());
        System.out.print(bundle.getString("timeIntervalBetweenHours") + " [ "
                + hour1.getOperatingHour().format(dateTimeFormatter) + "; "
                + hour2.getOperatingHour().format(dateTimeFormatter) + " ]:\t");
        printInterval(getInterval(hour1, hour2));
        System.out.print(bundle.getString("timeIntervalBetweenHours") + " [ "
                + hour1.getOperatingHour().format(dateTimeFormatter) + "; "
                + hour3.getOperatingHour().format(dateTimeFormatter) + " ]:\t");
        printInterval(getInterval(hour1, hour3));
        System.out.print(bundle.getString("timeIntervalBetweenHours") + " [ "
                + hour2.getOperatingHour().format(dateTimeFormatter) + "; "
                + hour3.getOperatingHour().format(dateTimeFormatter) + " ]:\t");
        printInterval(getInterval(hour2, hour3));


        HourWithDates[] hours = createHours();
        System.out.println("\n" + bundle.getString("createdHourArray") + ":");
        Arrays.stream(createHours()).forEach(System.out::println);

        Duration smallestInterval = findSmallestIntervalBetweenHours(hours);
        Duration largestInterval = findLargestIntervalBetweenHours(hours);

        System.out.print(bundle.getString("smallestInterval") + ":\t");
        printInterval(smallestInterval);
        System.out.print(bundle.getString("largestInterval") + ":\t");
        printInterval(largestInterval);
    }

    /**
     * Performs testing the functionality of the {@link HourWithDates} class:
     * <p>- the extended {@link Hour} class methods;
     * <p>- methods for managing {@link ZonedDateTime} {@link HourWithDates#operatingHour} field,
     * <p>- methods for managing localized {@link HourWithDates} fields,
     * <p>- methods for calculating {@link Duration} time intervals and displaying results.
     */
    public void testHour() {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);
        System.out.println( "\n***********************************************************************************\n\t\t\t\t\t\t"
                + bundle.getString("localizationText")
                + "\n***********************************************************************************");

        System.out.println(bundle.getString("createdHour") + ":");
        System.out.println(this + "\n");

        showIntervalsResults();
    }

    /**
     * Performs testing of the {@link HourWithDates} class functionality. The {@code args} are not used.
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new HourWithDates().createHour().testHour();

        Locale.setDefault(new Locale("uk"));
        new HourWithDates().createHour().testHour();
    }
}
