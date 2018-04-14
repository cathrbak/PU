package tdt4140.gr1835.app.ui.student;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1835.app.ui.nurse.FxApp;

public class FxAppStudent extends Application {
	
	

    @Override
    public void start(Stage primaryStage) throws Exception {
    		
    		
        Parent root_login = FXMLLoader.load(getClass().getResource("LoginStudent.fxml"));
        Scene scene_login = new Scene(root_login);
        scene_login.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        primaryStage.setScene(scene_login);
        primaryStage.show();
        
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}