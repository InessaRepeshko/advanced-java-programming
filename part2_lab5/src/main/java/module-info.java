module part2_lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;
    requires org.apache.logging.log4j;
    requires java.sql;
    requires lombok;

    opens part2_lab5.task1 to xstream, org.apache.logging.log4j;
    exports part2_lab5.task1;
    opens part2_lab5.task1.lab3 to xstream;
    exports part2_lab5.task1.lab3;
    opens part2_lab5.task2;
    exports part2_lab5.task2;
    opens part2_lab5.task3 to javafx.fxml, xstream, java.sql, org.apache.logging.log4j;
    exports part2_lab5.task3;
    opens part2_lab5.task4;
    exports part2_lab5.task4;
    opens part2_lab5.task_control to lombok;
    exports part2_lab5.task_control;
}