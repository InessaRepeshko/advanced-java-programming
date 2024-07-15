package part2_lab4.task2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;

public class DoubleListSorter {
    public static void sortDoubleList(ObservableList<Double> list) {
        ObservableList<Double> negatives = FXCollections.observableArrayList();
        int positiveIndex = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < 0) {
                negatives.add(list.get(i));
            }

            if (list.get(i) >= 0) {
                Collections.swap(list, i, positiveIndex);
                positiveIndex++;
            }
        }

        for (int i = positiveIndex, j = negatives.size() - 1; i < list.size(); i++, j--) {
            list.set(i, negatives.get(j));
        }
    }
}
