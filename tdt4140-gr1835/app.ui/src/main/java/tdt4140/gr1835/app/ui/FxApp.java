package tdt4140.gr1835.app.ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxApp extends Application {
	
	

    @Override
    public void start(Stage stage) throws Exception {
    	
        Parent root_login = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Parent root_nybruker = FXMLLoader.load(getClass().getResource("Ny_Bruker.fxml"));
        Parent root_profile = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Scene scene_login = new Scene(root_login);
        Scene scene_nybruker = new Scene(root_nybruker);
        Scene scene_profile = new Scene(root_profile);
        scene_login.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        scene_nybruker.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        scene_profile.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene_login);
        stage.setScene(scene_profile);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}