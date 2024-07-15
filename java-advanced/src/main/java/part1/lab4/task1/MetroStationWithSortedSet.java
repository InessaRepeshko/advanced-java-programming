package part1.lab4.task1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Represents metro station data with an SortedSet of operating hours.
 * This class is inherited from the abstract {@link MetroStationWithCollection}.
 */
public class MetroStationWithSortedSet extends MetroStationWithCollection {
    /**
     * Private field representing a SortedSet of Hour objects.
     * The set is initialized with a TreeSet using a custom comparator based on the hashCode of the Hour objects.
     */
    private SortedSet<Hour> hours = new TreeSet<>(Comparator.comparing(Hour::hashCode));

    /**
     * The constructor initialises the object with the default values.
     */
    public MetroStationWithSortedSet() {
    }

    /**
     * The constructor initialises the metro station object with the specified values with {@code name},
     * {@code opened} year and operating {@code hours}.
     * @param name the name of metro station;
     * @param opened the opened year of metro station;
     * @param hours the operating hours of metro station.
     */
    public MetroStationWithSortedSet(String name, int opened, SortedSet<Hour> hours) {
        super(name, opened);
        this.hours = hours;
    }

    /**
     * The constructor initialises the metro station object with the specified values with {@code name}
     * and {@code opened} year.
     * @param name the name of metro station;
     * @param opened the opened year of metro station.
     */
    public MetroStationWithSortedSet(String name, int opened) {
        super(name, opened);
    }

    /**
     * Gets the array of operating hours for the metro station.
     * @return the array of hours.
     */
    @Override
    public Hour[] getHours() {
        return hours.toArray(new Hour[0]);
    }

    /**
     * Gets the SortedSet of operating hours for the metro station.
     * @return the SortedSet of operating hours.
     */
    public SortedSet<Hour> getHoursSortedSet() {
        return hours;
    }

    /**
     * Sets the SortedSet of operating hours for the metro station.
     * @param hours the array of operating hours.
     */
    @Override
    public void setHours(Hour[] hours) {
        this.hours = new TreeSet<>(Comparator.comparing(Hour::hashCode));
        this.hours.addAll(Arrays.asList(hours));
    }

    /**
     * Gets the operating {@code hour} with index {@code i}.
     * @return the object of class {@code Hour} with index {@code i}.
     */
    @Override
    public Hour getHour(int i) {
        return hours.toArray(new Hour[0])[i];
    }

    /**
     * Sets the operating {@code hour} with index {@code i}.
     * @param i index of {@code hour};
     * @param hour the object of class {@code Hour} with index {@code i} to be set.
     */
    @Override
    public void setHour(int i, Hour hour) {
        hours.remove(getHour(i));
        hours.add(hour);
    }

    /**

     * Adds a link to the new {@code hour}.
     * @param hour the object of class {@code Hour} to be added;
     * @return {@code true}, if the link was added successfully, {@code false} otherwise.
     */
    @Override
    public boolean addHour(Hour hour) {
        return hours.add(hour);
    }

    /**
     * Creates a new {@code hour} and adds a link to it.
     * @param ridership the ridership;
     * @param comment the comment;
     * @return {@code true}, if the link was added successfully, {@code false} otherwise.
     */
    @Override
    public boolean addHour(int ridership, String comment) {
        return addHour(new Hour(ridership, comment));
    }

    /**
     * Counts the number of hours in the sequence.
     * @return the number of hours.
     */
    @Override
    public int countHours() {
        return hours.size();
    }

    /**
     * Removes the sequence of hours.
     */
    @Override
    public void removeHours() {
        hours.clear();
    }

    /**
     * Overridden decreasing ridership sorting method using {@code TreeSet}.
     * Is provided by the implementation of the Comparable interface for the {@code Hour} class.
     */
    @Override
    public void sortByDecreasingRidership() {
        SortedSet<Hour> newSet = new TreeSet<>();
        newSet.addAll(hours);
        hours = newSet;
    }

    /**
     * Overridden descending comment length sorting method using the {@code TreeSet}.
     * Is provided by {@code Comparator}.
     */
    @Override
    public void sortByDescendingCommentLength() {
        SortedSet<Hour> newSet = new TreeSet<>(Comparator.comparing(Hour::getCommentLength).reversed());
        newSet.addAll(hours);
        hours = newSet;
    }
}
