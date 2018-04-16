package tdt4140.gr1835.app.ui.student;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1835.app.ui.nurse.FxApp;
import tdt4140.gr1835.app.ui.nurse.LoginController;

public class FxAppStudent extends Application {
	
	

    @Override
    public void start(Stage primaryStage) throws Exception {
    		
    		
    	Parent root;
		
    	LoginControllerStudent controller = new LoginControllerStudent();
		
    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginStudent.fxml"));
    
    loader.setController(controller);
    
    root = (Parent) loader.load();
    Scene scene_login = new Scene(root);
    scene_login.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
    primaryStage.setScene(scene_login);
    primaryStage.show();
        
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}