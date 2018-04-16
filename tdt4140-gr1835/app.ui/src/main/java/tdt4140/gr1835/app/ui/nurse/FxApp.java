package tdt4140.gr1835.app.ui.nurse;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FxApp extends Application {
	
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    		Parent root;
    		
    		LoginController controller = new LoginController();
    		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        
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