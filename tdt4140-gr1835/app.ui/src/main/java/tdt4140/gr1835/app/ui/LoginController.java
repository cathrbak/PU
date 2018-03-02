package tdt4140.gr1835.app.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	TextField brukernavn;
	@FXML
	TextField passord;
	
	@FXML
	Button button_login;
	@FXML
	Button button_nybruker;
	
	@FXML
	Label responsLabel;
	
	@FXML
	ImageView butterflyImage;


	@FXML
	public void handleNyBrukerButton() throws IOException {
		Stage stage; 
	    Parent root;
        //get reference to the button's stage         
        stage=(Stage) button_nybruker.getScene().getWindow();
        
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Ny_Bruker.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	public void handleLoginButton() throws IOException {
		if(loginOk()) {
			Stage stage; 
		    Parent root;
	        //get reference to the button's stage         
	        stage=(Stage) button_login.getScene().getWindow();
	        //load up OTHER FXML document
	        root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
	      	//create a new scene with root and set the stage
	        Scene scene = new Scene(root);
	        //Legger på css stylesheetet
	        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
	        stage.setScene(scene);
	        stage.show();
		}

	}
	private boolean loginOk() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

	
}