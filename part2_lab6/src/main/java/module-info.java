module part2_lab6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.graalvm.sdk;
    requires java.scripting;
    requires org.apache.logging.log4j;
    requires java.compiler;

    opens part2_lab6.task1 to javafx.fxml;
    exports part2_lab6.task1;
    opens part2_lab6.task2 to javafx.fxml;
    exports part2_lab6.task2;
    opens part2_lab6.task3 to javafx.fxml;
    exports part2_lab6.task3;
    opens part2_lab6.task4 to javafx.fxml;
    exports part2_lab6.task4;
    opens part2_lab6.task5 to javafx.fxml;
    exports part2_lab6.task5;
}