package part2_lab6.task1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.DoubleConsumer;

public class FunctionGrapherAppController implements Initializable {
    @FXML private TextField textFieldConstantA;
    @FXML private TextField textFieldConstantB;
    @FXML private TextField textFieldFunctionF;
    @FXML private TextField textFieldFunctionG;
    @FXML private TextField textFieldRangeFrom;
    @FXML private TextField textFieldRangeTo;
    @FXML private TextField textFieldRangeStep;
    @FXML private LineChart<Number, Number> graphChart;
    @FXML private NumberAxis numberAxisX;
    @FXML private NumberAxis numberAxisY;
    private XYChart.Series<Number, Number> series;
    private FunctionGrapher functionGrapher;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        functionGrapher = new FunctionGrapher(
                this::displayFunctionOnGraph,
                this::showError);

        graphChart.setTitle("Graph of function h(x) = f(a/x) – g(b/x)");
        graphChart.setCreateSymbols(false);
        graphChart.getXAxis().setLabel("x");
        graphChart.getYAxis().setLabel("y");
    }

    @FXML
    private void constantAUpdated(ActionEvent event) {
        parseNumericField(
                textFieldConstantA,
                textFieldConstantA.getPromptText(),
                value -> functionGrapher.setCostantA(value));
    }

    @FXML
    private void constantBUpdated(ActionEvent event) {
        parseNumericField(
                textFieldConstantB,
                textFieldConstantB.getPromptText(),
                value -> functionGrapher.setConstantB(value));
    }

    @FXML
    private void functionFUpdated(ActionEvent event) {
        functionGrapher.setFunctionF(
                textFieldFunctionF.getText());
    }

    @FXML
    private void functionGUpdated(ActionEvent event) {
        functionGrapher.setFunctionG(
                textFieldFunctionG.getText());
    }

    @FXML
    private void rangeFromUpdated(ActionEvent event) {
        parseNumericField(
                textFieldRangeFrom,
                textFieldRangeFrom.getPromptText(),
                value -> functionGrapher.setRangeFrom(value));
    }

    @FXML
    private void rangeToUpdated(ActionEvent event) {
        parseNumericField(
                textFieldRangeTo,
                textFieldRangeTo.getPromptText(),
                value -> functionGrapher.setRangeTo(value));
    }

    @FXML
    private void rangeStepUpdated(ActionEvent event) {
        parseNumericField(
                textFieldRangeStep,
                textFieldRangeStep.getPromptText(),
                value -> functionGrapher.setRangeStep(value));
    }

    @FXML
    private void buildGraphClicked(ActionEvent event) {
        try {
            constantAUpdated(event);
            constantBUpdated(event);

            functionFUpdated(event);
            functionGUpdated(event);

            rangeFromUpdated(event);
            rangeToUpdated(event);
            rangeStepUpdated(event);
            verifyRange();

            numberAxisX.setLowerBound(functionGrapher.getRangeFrom());
            numberAxisX.setUpperBound(functionGrapher.getRangeTo());
            numberAxisX.setTickUnit(functionGrapher.getRangeStep());
            numberAxisY.setAutoRanging(true);

            series = new XYChart.Series<>();
            series.setName(null);
            series.setName("h(x);\t" +
                    "f(x) = " + functionGrapher.getFunctionF() + ";\t\t" +
                    "g(x) = " + functionGrapher.getFunctionG() + ".");
            graphChart.getData().setAll(series);

            functionGrapher.start();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            showError(e.getMessage());
        }
    }

    @FXML
    private void clearGraphClicked(ActionEvent event) {
        series.getData().clear();
        graphChart.getData().clear();
    }

    @FXML
    private void clearFieldsClicked(ActionEvent event) {
        textFieldConstantA.clear();
        textFieldConstantB.clear();

        textFieldFunctionF.clear();
        textFieldFunctionG.clear();

        textFieldRangeFrom.clear();
        textFieldRangeTo.clear();
        textFieldRangeStep.clear();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(functionGrapher.getErrorMessage());
        alert.showAndWait();
    }

    private void parseNumericField(TextField textField, String fieldName, DoubleConsumer setRangeFunction) {
        try {
            double value = Double.parseDouble(textField.getText());
            setRangeFunction.accept(value);
        } catch (NumberFormatException e) {
            StringBuilder message = new StringBuilder("Invalid input. ");

            if (fieldName.equals(textFieldConstantA.getPromptText())
                    || fieldName.equals(textFieldConstantB.getPromptText())) {
                message.append("Please enter a real number for constant \"").append(fieldName).append("\".\n");
            } else if (fieldName.equals(textFieldRangeFrom.getPromptText())
                    || fieldName.equals(textFieldRangeTo.getPromptText())
                    || fieldName.equals(textFieldRangeStep.getPromptText())) {
                message.append("Please enter a number for range \"").append(fieldName).append("\".\n");
            } else {
                message.append("\"").append(fieldName).append("\".\n");
            }

            throw new IllegalArgumentException(message + "Error: " + e + "\n");
        }
    }

    private void verifyRange() {
        if (!functionGrapher.isValidRangeBoundaries()
                || !functionGrapher.isValidRangeStep()) {
            String message = """
                    Invalid range is set. The "from" must be less than the "to" and
                    the "step" must be greater than the 0.0 and less than or equal to the "to" - "from".
                    """;
            throw new IllegalArgumentException(message);
        }
    }

    private void displayFunctionOnGraph() {
        StringBuilder stringBuilder = new StringBuilder(
                "_______________________________________\n" +
                "h(x) = f(a / x) – g(b / x);\n" +
                "f(x) = " + functionGrapher.getFunctionF() + ";\n" +
                "g(x) = " + functionGrapher.getFunctionG() + ";\n");

        for (Map.Entry<Number, Number> entry : functionGrapher.getPairsXY().entrySet()) {
            series.getData().add(
                    new XYChart.Data<>(
                            entry.getKey(),
                            entry.getValue()));

            stringBuilder.append("x = ").append(entry.getKey())
                    .append(", \ty = ").append(entry.getValue()).append(";\n");
        }

        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), ".");
        System.out.println(stringBuilder);
    }
}
