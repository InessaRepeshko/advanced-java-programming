package part2_lab4.task1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MetroStationsFX extends Application {
    public static Logger logger = LogManager.getLogger(MetroStationsFX.class.getSimpleName());

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("MetroStationsForm.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Metro Stations");
            primaryStage.show();
        } catch (Exception e) {
            if (logger != null) {
                logger.error(e.toString());
            }

            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        FileUtils.setLogger(logger);
        logger.info("Application opened");
        launch(args);
    }
}
