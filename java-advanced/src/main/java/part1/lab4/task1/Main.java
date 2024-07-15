package part1.lab4.task1;

import java.util.*;

/**
 * The {@code Main} class performs testing of the {@link MetroStationWithCollection}, {@link MetroStationWithList}
 * and {@link MetroStationWithSortedSet} classes functionality.
 */
public class Main {
    /**
     * Calls the test methods for testing the functionality of the {@link MetroStationWithCollection} class.
     * @param metroStation the MetroStationWithCollection object with test data.
     */
    private static void testMetroStation(MetroStationWithCollection metroStation) {
        metroStation.testMetroStationWithCollection();
        metroStation.testSortingData();
        System.out.println();
    }

    /**
     * Performs testing of the {@link MetroStationWithList} class functionality.
     * @param hourArray the {@code Hour} array with test data.
     */
    private static void testMetroStationWithList(Hour[] hourArray) {
        System.out.println("***************************************************************************");
        System.out.println("TESTING METRO STATION WITH LIST:");
        System.out.println("***************************************************************************");
        MetroStationWithList metroStation = new MetroStationWithList(
                "Sportyvna",
                1975,
                new ArrayList<>(Arrays.asList(hourArray))
        );

        System.out.println("Get data about Metro Station from the MetroStationWithList class:\n"
                + "Name:\t\'" + metroStation.getName() + "\'\tOpened:\t" + metroStation.getOpened() + "\n");
        System.out.println("Get data from the Operating Hours List:");
        List<Hour> hours = metroStation.getHoursList();
        hours.forEach(System.out::println);
        System.out.println();

        testMetroStation(metroStation);
        System.out.println();
    }

    /**
     * Performs testing of the {@link MetroStationWithSortedSet} class functionality.
     * @param hourArray the {@code Hour} array with test data.
     */
    private static void testMetroStationWithSortedSet(Hour[] hourArray) {
        System.out.println("***************************************************************************");
        System.out.println("TESTING METRO STATION WITH SORTED SET:");
        System.out.println("***************************************************************************");
        SortedSet<Hour> hours = new TreeSet<>(Comparator.comparing(Hour::hashCode));
        hours.addAll(Arrays.asList(hourArray));
        MetroStationWithSortedSet metroStation = new MetroStationWithSortedSet(
                "Sportyvna",
                1975,
                hours
        );

        System.out.println("Get data about Metro Station from the MetroStationWithSortedSet class:\n"
                + "Name:\t\'" + metroStation.getName() + "\'\tOpened:\t" + metroStation.getOpened() + "\n");
        System.out.println("Get data from the Operating Hours SortedSet:");
        SortedSet<Hour> hourSortedSet = metroStation.getHoursSortedSet();
        hourSortedSet.forEach(System.out::println);
        System.out.println();

        testMetroStation(metroStation);
        System.out.println();
    }

    /**
     * Performs testing of the {@link MetroStationWithCollection}, {@link MetroStationWithList} and
     * {@link MetroStationWithSortedSet} classes functionality. The {@code args} are not used.
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        Hour[] hours = {
                new Hour(578, "Medium ridership"),
                new Hour(256, "Medium ridership"),
                new Hour(817, "High ridership"),
                new Hour(1115, "Very high ridership"),
                new Hour(817, "High ridership"),
        };
        testMetroStationWithList(hours);
        testMetroStationWithSortedSet(hours);
    }
}
