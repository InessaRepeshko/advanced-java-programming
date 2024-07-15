package part2_lab4.task4;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.Map;

public class DictionaryController {
    @FXML
    private TableView<Map.Entry<String, String>> DictionaryTable;

    @FXML
    public TextField UAField;

    @FXML
    public TextField ENField;

    @FXML
    public TextField searchField;

    private Map<String, String> dictionary = new HashMap<>();
    private ObservableList<Map.Entry<String, String>> tableData;

    @FXML
    public void initialize() {
        TableColumn<Map.Entry<String, String>, String> ENColumn = new TableColumn<>("English");
        ENColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));

        TableColumn<Map.Entry<String, String>, String> UAColumn = new TableColumn<>("Ukrainian");
        UAColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));

        DictionaryTable.getColumns().addAll(ENColumn, UAColumn);

        DictionaryTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableData = FXCollections.observableArrayList(dictionary.entrySet());
        DictionaryTable.setItems(tableData);
    }

    @FXML
    public void addWord(javafx.event.ActionEvent actionEvent) {
        if (ENField.getText().isEmpty() || UAField.getText().isEmpty() ) {
            showError("Field can not be empty");
            return;
        } else if (!ENField.getText().matches("[a-zA-Z]+")){
            showError("\"" + ENField.getText() + "\" is not all English characters");
        } else if (!UAField.getText().matches("[а-яА-ЯІіЇїЄєҐґ']+")) {
            showError("\"" + UAField.getText() + "\" is not all Ukrainian characters");
        } else {
            DictionaryTable.getSelectionModel().clearSelection();
            dictionary.put(ENField.getText(), UAField.getText());
            tableData.setAll(dictionary.entrySet());
            showResult("Word added", "");
        }
    }

    @FXML
    public void searchWord(ActionEvent actionEvent) {
        DictionaryTable.getSelectionModel().clearSelection();
        String word = searchField.getText();
        DictionaryTable.getSelectionModel().clearSelection();
        boolean exist = false;

        for (int i = 0; i < tableData.size(); i++) {
            if (tableData.get(i).getKey().equals(word)
                    || tableData.get(i).getValue().equals(word)) {
                DictionaryTable.getSelectionModel().select(i);
                DictionaryTable.scrollTo(i);
                exist = true;
                showResult("Word found", "EN:\t\t" + tableData.get(i).getKey() + "\nUA:\t\t" + tableData.get(i).getValue());
            }
        }

        if (!exist) {
            showError("Word \"" + word + "\" not found");
        }
    }

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static void showResult(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
