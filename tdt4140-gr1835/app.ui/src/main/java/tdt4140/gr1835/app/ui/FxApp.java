package tdt4140.gr1835.app.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FxApp.fxml"));
        Scene scene = new Scene(root);
 //Neste linje er jeg litt usikker på hvorfor funker. Virker som et kall på getResource henter filer fra resourcemappen
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}