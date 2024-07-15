module part2_lab4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;
    requires org.apache.logging.log4j;

    exports part2_lab4.task1;
    opens part2_lab4.task1 to javafx.fxml, xstream;
    exports part2_lab4.task2;
    opens part2_lab4.task2 to javafx.fxml, xstream;
    exports part2_lab4.task3;
    opens part2_lab4.task3 to javafx.fxml, xstream;
    exports part2_lab4.task4;
    opens part2_lab4.task4 to javafx.fxml, xstream;
    exports part2_lab4.task_control;
    opens part2_lab4.task_control to javafx.fxml, xstream;
}