package part2_lab5.task3;

import part2_lab5.task1.DBUtils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * MetroStationsApp is the main class for the metro stations application.
 * It initializes and starts the JavaFX application.
 */
public class MetroStationsApp extends Application {
    /**
     * Logger for logging application events.
     */
    public static Logger logger = LogManager.getLogger(MetroStationsApp.class.getSimpleName());

    /**
     * The main entry point for JavaFX application.
     * Sets up the primary stage with the main application window.
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = FXMLLoader.load(getClass().getResource("MetroStationsAppWindow.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Metro Stations");
            primaryStage.show();
        } catch (Exception e) {
            logger.error("Failed to start the application.\n" + e);
        }
    }

    /**
     * The main method which serves as the entry point for the application.
     * Sets up the logger and launches the JavaFX application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetroStationsAppController.setLogger(logger);
        MetroStationsEditModeController.setLogger(logger);
        HoursEditModeController.setLogger(logger);
        DBUtils.setLogger(logger);
        logger.info("Application opened");
        launch(args);
    }
}
