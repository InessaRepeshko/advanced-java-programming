package part2.lab2.task_control;

import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

/**
 * TimeZonesPrinter class provides functionality to retrieve and print the list of available time zones.
 */
public class TimeZonesPrinter {
    /**
     * Retrieves the list of available time zones.
     * @return A list of available time zone IDs.
     */
    public static List<String> getTimeZones() {
        return Arrays.stream(TimeZone.getAvailableIDs()).toList();
    }

    /**
     * Main method to print the list of available time zones.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("The list of available time zones:");
        getTimeZones().forEach(System.out::println);
    }
}
