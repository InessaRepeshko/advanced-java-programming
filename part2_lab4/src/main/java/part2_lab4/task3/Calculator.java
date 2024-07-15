package part2_lab4.task3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Calculator extends Application {
    private TextField firstNumberField;
    private TextField secondNumberField;
    private TextField resultField;
    private RadioButton addButton;
    private RadioButton minusButton;
    private RadioButton multiplyButton;
    private RadioButton divideButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Text title = new Text("Calculator");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Text firstNumberLabel = new Text("First number:");
        Text secondNumberLabel = new Text("Second Number:");
        firstNumberField = new TextField();
        secondNumberField = new TextField();

        HBox operationBox = new HBox();
        operationBox.setSpacing(10);
        addButton = new RadioButton("Add");
        minusButton = new RadioButton("Minus");
        multiplyButton = new RadioButton("Multiply");
        divideButton = new RadioButton("Divide");
        ToggleGroup operationGroup = new ToggleGroup();
        addButton.setToggleGroup(operationGroup);
        minusButton.setToggleGroup(operationGroup);
        multiplyButton.setToggleGroup(operationGroup);
        divideButton.setToggleGroup(operationGroup);
        operationBox.getChildren().addAll(addButton, minusButton, multiplyButton, divideButton);

        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(event -> calculate());

        Text resultLabel = new Text("Result:");
        resultField = new TextField();
        resultField.setEditable(false);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(title, 0, 0, 2, 1);
        grid.add(firstNumberLabel, 0, 1);
        grid.add(firstNumberField, 1, 1);
        grid.add(secondNumberLabel, 0, 2);
        grid.add(secondNumberField, 1, 2);
        grid.add(operationBox, 0, 3, 2, 1);
        grid.add(calculateButton, 0, 4);
        grid.add(resultLabel, 0, 5);
        grid.add(resultField, 1, 5);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(title, grid);

        Scene scene = new Scene(vbox, 370, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }

    private void calculate() {
        try {
            double firstNumber = Double.parseDouble(firstNumberField.getText());
            double secondNumber = Double.parseDouble(secondNumberField.getText());
            double result = 0;

            if (addButton.isSelected()) {
                result = firstNumber + secondNumber;
            } else if (minusButton.isSelected()) {
                result = firstNumber - secondNumber;
            } else if (multiplyButton.isSelected()) {
                result = firstNumber * secondNumber;
            } else if (divideButton.isSelected()) {
                if (secondNumber == 0) {
                    showError("Division by zero");
                    return;
                }

                result = firstNumber / secondNumber;
            }

            resultField.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            showError("Invalid input");
        }
    }

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
