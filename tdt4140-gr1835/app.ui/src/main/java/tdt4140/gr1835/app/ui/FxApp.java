package tdt4140.gr1835.app.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.MockingDatabase;
import tdt4140.gr1835.app.core.UserDatabaseHandler;

public class FxApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root_login = FXMLLoader.load(getClass().getResource("FxApp.fxml"));
        Parent root_nybruker = FXMLLoader.load(getClass().getResource("FxApp_nybruker.fxml"));

        Scene scene_login = new Scene(root_login);
        Scene scene_nybruker = new Scene(root_nybruker);
        scene_login.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene_login);
        stage.show();
        

        //scene_nybruker.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        //stage.setScene(scene_nybruker);
       // stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}