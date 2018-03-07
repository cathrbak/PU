package tdt4140.gr1835.app.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainPageController implements Initializable {
				
	
	@FXML
	Button Profile;
	@FXML
	Button Logout;

	@FXML
	Button Question;
	
	@FXML
	public void handleProfileButton() throws IOException {
		Stage stage; 
	    Parent root;
        //get reference to the button's stage         
        stage=(Stage) Profile.getScene().getWindow();
        
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
	
	
	@FXML
	public void handleLogoutButton() throws IOException {
		Stage stage; 
	    Parent root;
        //get reference to the button's stage         
        stage=(Stage) Logout.getScene().getWindow();
        
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	public void handleQuestionButton() throws IOException {
		Stage stage;
		Parent root;
		//get reference to the button's stage
		stage=(Stage) Question.getScene().getWindow();
		
		// load up OTHER FXML document
		root = FXMLLoader.load(getClass().getResource("Questions.fxml"));
		// create a new scene with root and set the stage
		Scene scene = new Scene(root);
		//Legger på css stylesheetet
		scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	        
	    }
	

	

}
