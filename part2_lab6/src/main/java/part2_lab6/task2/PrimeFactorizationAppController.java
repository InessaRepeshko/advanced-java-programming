package part2_lab6.task2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.LongConsumer;

public class PrimeFactorizationAppController implements Initializable {
    @FXML private TextField textFieldRangeFrom;
    @FXML private TextField textFieldRangeTo;
    @FXML private Button buttonStart;
    @FXML private Button buttonPause;
    @FXML private Button buttonResume;
    @FXML private Button buttonFinish;
    @FXML private ProgressBar progressBar;
    @FXML private TextArea textAreaResults;

    private PrimeFactorization primeFactorization;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textAreaResults.setText("");
        primeFactorization = new PrimeFactorization(
                this::addToResult,
                this::setProgress,
                this::finish);
    }

    @FXML
    private void startClicked(ActionEvent actionEvent) {
        try {
            rangeFromUpdated(actionEvent);
            rangeToUpdated(actionEvent);
            verifyRange();

            textAreaResults.setText("");
            progressBar.setProgress(0);

            buttonStart.setDisable(true);
            buttonPause.setDisable(false);
            buttonResume.setDisable(true);
            buttonFinish.setDisable(false);

            primeFactorization.start();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void pauseClicked(ActionEvent actionEvent) {
        primeFactorization.pause();

        buttonStart.setDisable(true);
        buttonPause.setDisable(true);
        buttonResume.setDisable(false);
        buttonFinish.setDisable(false);
    }

    @FXML
    private void resumeClicked(ActionEvent actionEvent) {
        primeFactorization.resume();

        buttonStart.setDisable(true);
        buttonPause.setDisable(false);
        buttonResume.setDisable(true);
        buttonFinish.setDisable(false);
    }

    @FXML
    private void finishClicked(ActionEvent actionEvent) {
        primeFactorization.finish();
    }

    @FXML
    private void rangeFromUpdated(ActionEvent event) {
        parseRange(
                textFieldRangeFrom,
                "from",
                value -> primeFactorization.setFrom(value));
    }

    @FXML
    private void rangeToUpdated(ActionEvent event) {
        parseRange(
                textFieldRangeTo,
                "to",
                value -> primeFactorization.setTo(value));
    }

    private void parseRange(TextField textField, String fieldName, LongConsumer setRangeFunction) {
        try {
            long value = Long.parseLong(textField.getText());
            setRangeFunction.accept(value);
        } catch (NumberFormatException e) {
            String message = "Invalid input. " +
                    "Please enter a long number for range \"" + fieldName + "\".\n";
            showError(message);
            throw new IllegalArgumentException(message + "Error: " + e + "\n");
        }
    }

    private void verifyRange() {
        if (!primeFactorization.isValidRange()) {
            String message = "Invalid interval is set. " +
                    "The \"from\" must be less than or equal to the \"to\".\n";
            showError(message);
            throw new IllegalArgumentException(message);
        }
    }

    private static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private void addToResult() {
        textAreaResults.appendText(
                primeFactorization.mapEntryToString(
                        primeFactorization.getLastFound()));
    }

    private void setProgress() {
        progressBar.setProgress(
                primeFactorization.getPercentage());
    }

    private void finish() {
        buttonStart.setDisable(false);
        buttonPause.setDisable(true);
        buttonResume.setDisable(true);
        buttonFinish.setDisable(true);
    }
}
