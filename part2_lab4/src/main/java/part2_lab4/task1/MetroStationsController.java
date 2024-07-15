package part2_lab4.task1;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MetroStationsController implements Initializable {
    private MetroStationWithFiles metroStation;
    private ObservableList<Hour> observableList;

    public static void showResult(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static FileChooser getFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
        fileChooser.setTitle(title);
        return fileChooser;
    }

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldOpened;
    @FXML
    private TextField textFieldSearchText;
    @FXML
    private TextArea textAreaSearchResults;
    @FXML
    private TableView<Hour> tableViewHours;
    @FXML
    private TableColumn<Hour, Integer> tableColumnRidership;
    @FXML
    private TableColumn<Hour, String> tableColumnComment;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewHours.setPlaceholder(new Label(""));
        MetroStationsFX.logger.info("Initialisation");
    }

    @FXML
    private void fileNew(ActionEvent event) {
        metroStation = new MetroStationWithFiles();
        observableList = null;
        textFieldName.setText("");
        textFieldOpened.setText("");
        textFieldSearchText.setText("");
        textAreaSearchResults.setText("");
        tableViewHours.setItems(null);
        tableViewHours.setPlaceholder(new Label(""));
        MetroStationsFX.logger.info("New file created");
    }

    @FXML
    private void fileOpen(ActionEvent event) {
        FileChooser fileChooser = getFileChooser("Open XML file");
        File file;

        if ((file = fileChooser.showOpenDialog(null)) != null) {
            try {
                metroStation = FileUtils.deserializeFromXML(file.getCanonicalPath());
                textFieldName.setText(metroStation.getName());
                textFieldOpened.setText(String.valueOf(metroStation.getOpened()));
                textAreaSearchResults.setText("");
                tableViewHours.setItems(null);
                updateTable();
                MetroStationsFX.logger.info("XML file opened");
            } catch (IOException e) {
                String message = "XML file not found";
                showError(message);
                MetroStationsFX.logger.error(message + "\n" + e.getMessage());
            } catch (Exception e) {
                String message = "Invalid XML file format";
                showError(message);
                MetroStationsFX.logger.error(message + "\n" + e.getMessage());
            }
        }
    }

    @FXML
    private void fileSave(ActionEvent event) {
        FileChooser fileChooser = getFileChooser("Save XML file");
        File file;

        if ((file = fileChooser.showSaveDialog(null)) != null) {
            try {
                updateSourceData();
                nameUpdated(event);
                openedUpdated(event);
                FileUtils.serializeToXML(file.getCanonicalPath(), metroStation);
                String message = "Saved to XMl file";
                showResult(message);
            } catch (Exception e) {
                String message = "Error writing to XML file";
                showError(message);
                MetroStationsFX.logger.error(message + "\n" + e.getMessage());
            }
        }
    }

    @FXML
    private void fileExit(ActionEvent event) {
        MetroStationsFX.logger.info("Application closed");
        Platform.exit();
    }

    @FXML
    private void editAddRow(ActionEvent event) {
        if (metroStation == null) {
            return;
        }

        metroStation.addHour(new Hour(0, ""));
        updateTable();
        MetroStationsFX.logger.info("New row added to the table");
    }

    @FXML
    private void editRemoveLastRow(ActionEvent event) {
        if (observableList == null) {
            MetroStationsFX.logger.error("Cannot remove last row from the table. Observable list is empty");
            return;
        }

        Hour[] hours = metroStation.getHours();

        if (hours.length <= 0) {
            MetroStationsFX.logger.error("Cannot remove last row from the table. Hours is empty");
            return;
        }

        metroStation.setHours(Arrays.copyOfRange(hours, 0, hours.length - 1));
        updateTable();

        MetroStationsFX.logger.info("Last row removed from the table");
    }

    @FXML
    private void runSortByDecrRidership(ActionEvent event) {
        updateSourceData();
        metroStation.sortByDecreasingRidership();
        updateTable();
        MetroStationsFX.logger.info("Hours sorted by decreasing ridership");
    }

    @FXML
    private void runSortByDescCommentLength(ActionEvent event) {
        updateSourceData();
        metroStation.sortByDescendingCommentLength();
        updateTable();
        MetroStationsFX.logger.info("Hours sorted by descending comment length");
    }

    @FXML
    void helpAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About...");
        alert.setHeaderText("Ridership of Metro Stations data");
        alert.setContentText("Version 1.0");
        alert.showAndWait();
        MetroStationsFX.logger.info("About program shows");
    }

    @FXML
    private void nameUpdated(ActionEvent event) {
        metroStation.setName(textFieldName.getText());
        MetroStationsFX.logger.info("Metro Station name updated");
    }

    @FXML
    private void openedUpdated(ActionEvent event) {
        try {
            metroStation.setOpened(Integer.parseInt(textFieldOpened.getText()));
            MetroStationsFX.logger.info("Metro Station opened year updated");
        } catch (NumberFormatException e) {
            textFieldOpened.setText(metroStation.getOpened() + "");
            MetroStationsFX.logger.info("Metro Station opened year updated to the last value");
        }
    }

    @FXML
    private void searchByWord(ActionEvent event) {
        updateSourceData();
        textAreaSearchResults.setText("");
        String word = textFieldSearchText.getText();

        Arrays.stream(metroStation.getHours())
                .toList().forEach(hour -> {
                    if (Arrays.stream(hour.getComment()
                                    .split("\s"))
                            .anyMatch(s -> s.equalsIgnoreCase(word))) {
                        showResults(hour);
                    }
                });

        MetroStationsFX.logger.info("Search for Hours by word");
    }

    @FXML
    private void searchBySubstring(ActionEvent event) {
        updateSourceData();
        textAreaSearchResults.setText("");
        String substring = textFieldSearchText.getText();

        Arrays.stream(metroStation.getHours())
                .toList().forEach(hour -> {
                    if (hour.getComment().toUpperCase().indexOf(substring.toUpperCase()) >= 0) {
                        showResults(hour);
                    }
                });

        MetroStationsFX.logger.info("Search for Hours by substring");
    }

    @FXML
    private void searchByMinRidership(ActionEvent event) {
        updateSourceData();
        textAreaSearchResults.setText("");
        Arrays.stream(metroStation.findHoursWithMinRidership()).forEach(this::showResults);
        MetroStationsFX.logger.info("Search for Hours by minimal ridership");
    }

    @FXML
    private void searchByMaxWordCountOfComment(ActionEvent event) {
        updateSourceData();
        textAreaSearchResults.setText("");
        Arrays.stream(metroStation.findHoursWithMaxWordCountOfComment()).forEach(this::showResults);
        MetroStationsFX.logger.info("Search for Hours by maximum word count of comment");
    }

    private void showResults(Hour hour) {
        textAreaSearchResults.appendText("Hour: ridership: " + hour.getRidership()
                + "; comment: \"" + hour.getComment() + "\".\n\n");
    }

    private void updateSourceData() {
        metroStation = new MetroStationWithFiles();

        for (Hour hour : observableList) {
            metroStation.addHour(hour);
        }

        MetroStationsFX.logger.info("Source data updated for Hours of Metro Station");
    }

    private void updateRidership(TableColumn.CellEditEvent<Hour, Integer> t) {
        Hour hour = t.getTableView().getItems().get(t.getTablePosition().getRow());
        hour.setRidership(t.getNewValue());
        MetroStationsFX.logger.info("Hour ridership updated to \"" + t.getNewValue() + "\"");
    }

    private void updateComment(CellEditEvent<Hour, String> t) {
        Hour hour = t.getTableView().getItems().get(t.getTablePosition().getRow());
        hour.setComment(t.getNewValue());
        MetroStationsFX.logger.info("Hour comment updated to \"" + t.getNewValue() + "\"");
    }

    private void updateTable() {
        List<Hour> hourList = new ArrayList<>();
        observableList = FXCollections.observableList(hourList);
        hourList.addAll(metroStation.getHoursList());
        tableViewHours.setItems(observableList);

        tableColumnRidership.setCellValueFactory(new PropertyValueFactory<>("Ridership"));
        tableColumnRidership.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tableColumnRidership.setOnEditCommit(t -> updateRidership(t));

        tableColumnComment.setCellValueFactory(new PropertyValueFactory<>("Comment"));
        tableColumnComment.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnComment.setOnEditCommit(t -> updateComment(t));

        MetroStationsFX.logger.info("Table with Hours updated");
    }
}
