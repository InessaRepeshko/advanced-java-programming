package part2_lab4.task_control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StringConcatenatorController {
    @FXML
    private TextField textFieldString1;
    @FXML
    private TextField textFieldString2;
    @FXML
    private TextArea textAreaResult = new TextArea("");

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    @FXML
    private void concatenateStrings(ActionEvent event) {
        textAreaResult.setText("");
        StringBuilder result = new StringBuilder();

        if (textFieldString1.getText().isEmpty()
                || textFieldString2.getText().isEmpty()) {
            showError("String cannot be empty");
        }

        result.append(textFieldString1.getText());
        result.append(textFieldString2.getText());
        textAreaResult.appendText(result.toString());
    }
}
