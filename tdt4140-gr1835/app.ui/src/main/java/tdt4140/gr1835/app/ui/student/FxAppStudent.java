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
    
    /*public void mainpage(Stage secondStage) throws Exception {
    		Parent root_MainPage = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Parent root_Profile = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Scene scene_MainPage = new Scene(root_MainPage);
        Scene scene_Profile = new Scene(root_Profile);
        scene_MainPage.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        scene_Profile.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        secondStage.setScene(scene_MainPage);
        secondStage.show();
        
    }*/
    

    public static void main(String[] args) {
        launch(args);
    }
}