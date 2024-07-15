package part2_lab4.task2;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import static part2_lab4.task2.DoubleListSorter.*;

public class DoubleListSorterDemo {
    public static void main(String[] args) {
        ObservableList<Double> list = FXCollections.observableArrayList();
        list.addListener((ListChangeListener<? super Double>) change -> System.out.println(list));

        System.out.println("Initial list of Doubles:");
        list.addAll(-2.1, -3.7, 1.3, 0.6, -3.2, 4.1, -0.2, 9.0, -5.1);

        System.out.println("\nSorted list of Doubles iteratively:");
        sortDoubleList(list);
    }
}
