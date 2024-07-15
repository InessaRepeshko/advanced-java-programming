package part2.lab2.task1;

import part1.lab4.task1.Hour;
import part2.lab1.task1.MetroStationWithStreams;

import java.text.Collator;
import java.text.NumberFormat;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The {@code MetroStationWithLocalization} class represents a Metro Station with localized information.
 * It extends the {@link MetroStationWithStreams} class and includes methods for managing opening dates,
 * sorting and searching for specific hours based on ridership or comments.
 */
public class MetroStationWithLocalization extends MetroStationWithStreams {
    /** The path to the resource bundle files for localization. */
    private final String resourceBundle = "part2/lab2/task1/hours";

    /**
     * Represents the {@link ZonedDateTime} opening date of the metro station.
     */
    private ZonedDateTime openingDate;

    /**
     * Constructs a new default {@link MetroStationWithLocalization} object.
     */
    MetroStationWithLocalization() {}

    /**
     * Constructs a new {@link MetroStationWithLocalization} object with the specified parameters.
     * @param name The name of the metro station.
     * @param openingDate The opening date of the metro station.
     */
    MetroStationWithLocalization(String name, ZonedDateTime openingDate) {
        setName(name);
        setOpeningDate(openingDate);
    }

    /**
     * Gets the opening date of the metro station.
     * @return The opening date.
     */
    public ZonedDateTime getOpeningDate() {
        return openingDate;
    }

    /**
     * Sets the opening date of the metro station.
     * @param openingDate The opening date to set.
     */
    public void setOpeningDate(ZonedDateTime openingDate) {
        this.openingDate = openingDate;
    }


    /**
     * Overrides the {@link MetroStationWithStreams#toString()} method and provides the string representing
     * the {@link MetroStationWithLocalization} object with included localized information about the metro station.
     * @return A string representation of the {@link MetroStationWithLocalization} object.
     */
    @Override
    public String toString() {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);
        StringBuilder result  = new StringBuilder(
                bundle.getString("name") + ":\t\'" + getName() + "\'.\t"
                + bundle.getString("openingDate") + ":\t"
                        + getOpeningDate().format(
                                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                                        .withLocale(Locale.getDefault()))
        );

        Arrays.stream(getHours()).forEach( hour -> {
            result.append("\n").append(hour);
        });

        return result + "";
    }

    /**
     * Overridden descending length of the comment sorting method using {@link Comparator#compare(Object, Object)} interface.
     */
    @Override
    public void sortByDescendingCommentLength() {
        Hour[] hours = getHours();
        Arrays.sort(hours, new Comparator<Hour>() {
            @Override
            public int compare(Hour hour1, Hour hour2) {
                return Integer.compare(hour2.getCommentLength(), hour1.getCommentLength());
            }
        });
        setHours(hours);
    }

    /**
     * Overridden the alphabetically comment sorting method using {@link Collator#compare(Object, Object)} interface.
     */
    public void sortByCommentAlphabetically() {
        Hour[] hours = getHours();
        Arrays.sort(hours, new Comparator<Hour>() {
            Collator collator = Collator.getInstance(Locale.getDefault());

            @Override
            public int compare(Hour hour1, Hour hour2) {
                return collator.compare(hour1.getComment(), hour2.getComment());
            }
        });
        setHours(hours);
    }

    /**
     * Finds hours with a word fragment at the start or end of the comment.
     * @param text The word fragment to search for.
     * @return An array of HourWithDates objects.
     */
    public HourWithDates[] findHoursWithWordFragmentAtStartOrEndOfComment(String text) {
        return Arrays.stream(getHours())
                .filter((hour -> {
                    for (String word : hour.getComment().split("\\s")) {
                        if (word.startsWith(text) || word.endsWith(text)) {
                            return true;
                        }
                    }

                    return false;
                }))
                .toArray(HourWithDates[]::new);
    }

    /**
     * Creates a new {@link MetroStationWithLocalization} object with default values.
     * @return The created {@link MetroStationWithLocalization} object.
     */
    public MetroStationWithLocalization createMetroStation() {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);

        setName(bundle.getString("metroStationName"));
        setOpeningDate(ZonedDateTime.of(1984, Month.AUGUST.getValue(), 10, 8, 0, 0, 0, ZoneId.of("Europe/Kiev")));

        addHour(new HourWithDates(1100, "veryHighRidership",
                ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 8, 0, 0, 0, ZoneId.of("Europe/Kiev"))));
        addHour(new HourWithDates(110, "lowRidership",
                ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 12, 0, 0, 0, ZoneId.of("Europe/Kiev"))));
        addHour(new HourWithDates(650, "highRidership",
                ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 14, 0, 0, 0, ZoneId.of("Europe/Kiev"))));
        addHour(new HourWithDates(532, "highRidership",
                ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 15, 0, 0, 0, ZoneId.of("Europe/Kiev"))));
        addHour(new HourWithDates(60, "veryLowRidership",
                ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 22, 0, 0, 0, ZoneId.of("Europe/Kiev"))));
        addHour(new HourWithDates(188, "lowRidership",
                ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 10, 0, 0, 0, ZoneId.of("Europe/Kiev"))));
        addHour(new HourWithDates(200, "mediumRidership",
                ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 5, 0, 0, 0, ZoneId.of("Europe/Kiev"))));
        addHour(new HourWithDates(200, "mediumRidership",
                ZonedDateTime.of(2024, Month.MAY.getValue(), 3, 4, 0, 0, 0, ZoneId.of("Europe/Kiev"))));

        return this;
    }

    /**
     * Prints the results of calculation of the total ridership for the metro station.
     */
    @Override
    public void printTotalRidership() {
        Integer totalRidership = calculateTotalRidership();

        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        System.out.print(bundle.getString("totalRidershipForMetroStation") + ":\t");

        if (totalRidership == null) {
            System.out.print(bundle.getString("noHoursWithRidership") + ".");
        } else {
            System.out.println(numberFormat.format(totalRidership));
        }
    }

    /**
     * Prints hours with minimum ridership.
     */
    @Override
    public void printHoursWithMinRidership() {
        Hour[] hours = findHoursWithMinRidership();

        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);
        System.out.print(bundle.getString("findHoursWithMinRidership") + ":\t");

        if (hours == null) {
            System.out.print(bundle.getString("noHoursWithRidership") + ".");
        } else {
            System.out.println();
            printHours(hours);
        }
    }

    /**
     * Prints hours with maximum word count of comment.
     */
    @Override
    public void printHoursWithMaxWordCountOfComment() {
        Hour[] hours = findHoursWithMaxWordCountOfComment();

        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);
        System.out.print(bundle.getString("findHoursWithMaxWordCountInComment") + ":\t");

        if (hours == null) {
            System.out.print(bundle.getString("noHoursWithRidership") + ".");
        } else {
            System.out.println();
            printHours(hours);
        }
    }

    /**
     * Prints hours with a word fragment in the comment.
     */
    public void printHoursWithWordFragmentInComment() {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);
        HourWithDates[] hours = findHoursWithWordFragmentAtStartOrEndOfComment(bundle.getString("findWordAtStart"));

        System.out.print(bundle.getString("findHoursWithWordFragmentInComment") + " [\""
                + bundle.getString("findWordAtStart") + "\"]:\t");

        if (hours == null) {
            System.out.print(bundle.getString("noHoursWithComment") + ".");
        } else {
            System.out.println();
            printHours(hours);
        }

        hours = findHoursWithWordFragmentAtStartOrEndOfComment(bundle.getString("findWordAtEnd"));

        System.out.print(bundle.getString("findHoursWithWordFragmentInComment") + " [\""
                + bundle.getString("findWordAtEnd") + "\"]:\t");

        if (hours == null) {
            System.out.print(bundle.getString("noHoursWithComment") + ".");
        } else {
            System.out.println();
            printHours(hours);
        }
    }

    /**
     * Shows the creating results of the metro station.
     */
    public void showCreatingResults() {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);
        System.out.println(bundle.getString("creatingResults") + ":");
        System.out.println(this);
    }

    /**
     * Shows the search results of the metro station.
     */
    @Override
    public void showSearchResults() {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);
        System.out.println(bundle.getString("searchingResults") + ":");
        printTotalRidership();
        printHoursWithMinRidership();
        printHoursWithMaxWordCountOfComment();
        printHoursWithWordFragmentInComment();
    }

    /**
     * Shows the sorting results of the metro station.
     */
    public void showSortingResults() {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);
        System.out.println(bundle.getString("sortingResults") + ":");

        System.out.println(bundle.getString("sortedHoursByDecrRidership") + ":");
        sortByDecreasingRidership();
        System.out.println(this);

        System.out.println(bundle.getString("sortedHoursByDescCommentLength") + ":");
        sortByDescendingCommentLength();
        System.out.println(this);

        System.out.println(bundle.getString("sortedHoursCommentAlphabetically") + ":");
        sortByCommentAlphabetically();
        System.out.println(this);
    }

    /**
     * Tests the MetroStationWithLocalization class.
     */
    public void testMetroStation() {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);
        System.out.println( "\n***********************************************************************************\n\t\t\t\t\t\t"
                + bundle.getString("localizationText")
                + "\n***********************************************************************************" );
        showCreatingResults();
        System.out.println();
        showSearchResults();
        System.out.println();
        showSortingResults();
        System.out.println();
    }

    /**
     * The main method to run the {@link MetroStationWithLocalization} class.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new MetroStationWithLocalization()
                .createMetroStation()
                .testMetroStation();

        Locale.setDefault(new Locale("uk"));
        new MetroStationWithLocalization()
                .createMetroStation()
                .testMetroStation();
    }
}
