package part2.lab1.task1;

import part1.lab4.task1.Hour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * The {@code MetroStationWithStreamsTest} class provides unit tests for the {@link MetroStationWithStreams} class.
 * It includes tests for setting Operating Hours, sorting and finding Operating Hours based on certain conditions.
 * Each test method in this class corresponds to a method in the {@link MetroStationWithStreams} class.
 */
class MetroStationWithStreamsTest {
    private MetroStationWithStreams metroStationWithStreams;

    /**
     * Assistive method to get riderships from a list of Operating Hours.
     * @param hours the list of Operating Hours;
     * @return an array of riderships.
     */
    private static int[] getRiderships(List<Hour> hours) {
        return hours.stream()
                .mapToInt(Hour::getRidership)
                .toArray();
    }

    /**
     * Assistive method to get riderships from an array of Operating Hours.
     * @param hours the array of Operating Hours;
     * @return an array of riderships.
     */
    private static int[] getRiderships(Hour[] hours) {
        return Arrays.stream(hours)
                .mapToInt(Hour::getRidership)
                .toArray();
    }

    @BeforeEach
    public void setup() {
        metroStationWithStreams = new MetroStationWithStreams("Politekhnichna", 1984,
                Arrays.asList(
                        new Hour(110, "Low ridership"),
                        new Hour(650, "High ridership"),
                        new Hour(532, "High ridership"),
                        new Hour(60, "Very low ridership"),
                        new Hour(188, "Low ridership"),
                        new Hour(200, "Medium ridership")
                ));
    }

    @Nested
    class TestSettingHours {
        /**
         * Tests the {@link MetroStationWithStreams#setHoursList(List)} method.
         */
        @Test
        @DisplayName("Should Set Hours")
        public void setHours() {
            List<Hour> hours = new ArrayList<>(metroStationWithStreams.getHoursList());
            hours.add(new Hour(27, "Very low ridership"));
            hours.add(new Hour(250, "Medium ridership"));
            metroStationWithStreams.setHoursList(hours);
            assertEquals(hours.size(), metroStationWithStreams.getHoursList().size());
            hours.forEach(hour -> assertTrue(metroStationWithStreams.getHoursList().contains(hour)));
        }

        /**
         * Tests the {@link MetroStationWithStreams#setHoursList(List)} method.
         * Checks if the method handles duplicate Operating Hours correctly.
         */
        @Test
        @DisplayName("Should Handle Duplicate Operating Hours When Setting")
        public void setDaysWithDuplicate() {
            List<Hour> hours = new ArrayList<>(metroStationWithStreams.getHoursList());
            Hour duplicateHour = new Hour(200, "Medium ridership");
            hours.add(duplicateHour);
            metroStationWithStreams.setHoursList(hours);
            long count = metroStationWithStreams.getHoursList().stream()
                    .filter(hour -> hour.equals(duplicateHour))
                    .count();
            assertEquals(2, count);
        }
    }

    @Nested
    class TestSortingHours {
        /**
         * Tests the {@link MetroStationWithStreams#sortByDecreasingRidership()} method.
         */
        @Test
        @DisplayName("Should Sort By Decreasing Ridership")
        public void sortByDecreasingRidership() {
            metroStationWithStreams.sortByDecreasingRidership();
            int[] expected = new int[]{650, 532, 200, 188, 110, 60};
            int[] actual = getRiderships(metroStationWithStreams.getHoursList());
            assertArrayEquals(expected, actual);
        }

        /**
         * Tests the {@link MetroStationWithStreams#sortByDescendingCommentLength()} method.
         */
        @Test
        @DisplayName("Should Sort By Descending Comment Length")
        public void sortByDescendingCommentLength() {
            metroStationWithStreams.sortByDescendingCommentLength();
            int[] expected = new int[]{60, 200, 650, 532, 110, 188};
            int[] actual = getRiderships(metroStationWithStreams.getHoursList());
            assertArrayEquals(expected, actual);
        }
    }

    @Nested
    class TestTotalRidershipCalculations {
        /**
         * Tests the {@link MetroStationWithStreams#calculateTotalRidership()} method.
         */
        @Test
        @DisplayName("Should Calculate Total Ridership")
        public void calculateTotalRidership() {
            assertEquals(1740, metroStationWithStreams.calculateTotalRidership(), 0);
        }

        /**
         * Tests the {@link MetroStationWithStreams#calculateTotalRidership()} method.
         * Checks if the method returns null when there are no Operating Hours.
         */
        @ParameterizedTest
        @DisplayName("Should Return Null When Calculating Total Ridership Without Operating Hours")
        @EmptySource
        public void calculateTotalRidershipWithoutHours(List<Hour> emptyList) {
            metroStationWithStreams.setHoursList(emptyList);
            assertNull(metroStationWithStreams.calculateTotalRidership());
        }
    }

    @Nested
    class TestSearchHours {
        /**
         * Tests the {@link MetroStationWithStreams#findHoursWithMinRidership()} method.
         * Checks if the method finds the one Operating Hour with the minimal ridership correctly.
         */
        @Test
        @DisplayName("Should Find One Operating Hour With Minimum Ridership")
        public void findHoursWithMinRidershipWithOneMinRidership() {
            int[] expected = new int[]{60};
            int[] actual = getRiderships(metroStationWithStreams.findHoursWithMinRidership());
            assertArrayEquals(expected, actual);
        }

        /**
         * Tests the {@link MetroStationWithStreams#findHoursWithMinRidership()} method.
         * Checks if the method finds multiple Operating Hours with the minimal ridership correctly.
         */
        @Test
        @DisplayName("Should Find Two Operating Hours With Minimum Ridership")
        public void findHoursWithMinRidershipWithMultipleMinRidership() {
            List<Hour> hours = new ArrayList<>(metroStationWithStreams.getHoursList());
            Hour duplicateMinRidership = new Hour(60, "Very low ridership");
            hours.add(duplicateMinRidership);
            metroStationWithStreams.setHoursList(hours);

            int[] expected = new int[]{60, 60};
            int[] actual = getRiderships(metroStationWithStreams.findHoursWithMinRidership());
            assertArrayEquals(expected, actual);
        }

        /**
         * Tests the {@link MetroStationWithStreams#findHoursWithMinRidership()} method.
         * Checks if the method returns null when there are no Operating Hours.
         */
        @ParameterizedTest
        @DisplayName("Should Return Null When Finding Minimum Ridership Operating Hours Without Operating Hours")
        @EmptySource
        public void findHoursWithMinRidershipWithoutHours(List<Hour> emptyList) {
            metroStationWithStreams.setHoursList(emptyList);
            assertNull(metroStationWithStreams.findHoursWithMinRidership());
        }

        /**
         * Tests the {@link MetroStationWithStreams#findHoursWithMaxWordCountOfComment()} method.
         * Checks if the method finds the one Operating Hour with the max word count of comment correctly.
         */
        @Test
        @DisplayName("Should Find One Operating Hour With Maximum Word Count Of Comment")
        public void findHoursWithMaxWordCountOfCommentWithOneMaxWordCountOfComment() {
            int[] expected = new int[]{60};
            int[] actual = getRiderships(metroStationWithStreams.findHoursWithMaxWordCountOfComment());
            assertArrayEquals(expected, actual);
        }

        /**
         * Tests the {@link MetroStationWithStreams#findHoursWithMaxWordCountOfComment()} method.
         * Checks if the method finds multiple Operating Hours with the max word count of comment correctly.
         */
        @Test
        @DisplayName("Should Find Two Operating Hours With Maximum Word Count Of Comment")
        public void findHoursWithMaxWordCountOfCommentWithMultipleMaxWordCountOfComment() {
            List<Hour> hours = new ArrayList<>(metroStationWithStreams.getHoursList());
            Hour duplicateHourWithMaxWordCountOfComment = new Hour(1100, "Very high ridership");
            hours.add(duplicateHourWithMaxWordCountOfComment);
            metroStationWithStreams.setHoursList(hours);

            int[] expected = new int[]{60, 1100};
            int[] actual = getRiderships(metroStationWithStreams.findHoursWithMaxWordCountOfComment());
            assertArrayEquals(expected, actual);
        }

        /**
         * Tests the {@link MetroStationWithStreams#findHoursWithMaxWordCountOfComment()} ()} method.
         * Checks if the method returns null when there are no Operating Hours.
         */
        @ParameterizedTest
        @DisplayName("Should Return Null When Finding Operating Hours With Maximum Word Count Of Comment Without Operating Hours")
        @EmptySource
        public void findHoursWithMaxWordCountOfCommentWithoutHours(List<Hour> emptyList) {
            metroStationWithStreams.setHoursList(emptyList);
            assertNull(metroStationWithStreams.findHoursWithMaxWordCountOfComment());
        }
    }
}
