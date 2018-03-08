package tdt4140.gr1835.app.ui;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.MockingDatabase;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.UserDatabaseHandler;

public class LoginController{
	
	@FXML
	TextField brukernavn;
	@FXML
	PasswordField passord;
	
	@FXML
	Button button_login;
	@FXML
	Button button_nybruker;
	
	@FXML
	Label responsLabel;
	
	private UserDatabaseHandler database;
	
	public LoginController() {
		System.out.println("Oppretter mockingdatabase");
		this.database= new MockingDatabase();
//		Scene scene=(Scene) button_nybruker.getScene();
//		
//		if(scene.getUserData() instanceof String) {
//    			responsLabel.setText("Velkommen " + scene.getUserData() + "\n"+ "Skriv inn ditt nye brukernavn og passord");
//		}
	}


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
			//Ta meg til mainPage
			System.out.println("Login ok, sender bruker til mainPage");
			
			Stage stage; 
		    Parent root;
	        //get reference to the button's stage         
	        stage=(Stage) button_login.getScene().getWindow();
	        //load up OTHER FXML document
	        root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
	      	//create a new scene with root and set the stage
	        Scene scene = new Scene(root);
	        try {
				scene.setUserData(database.getNurse(brukernavn.getText()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //Legger på css stylesheetet
	        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
	        stage.setScene(scene);
	        stage.show();
		}
	}
	
	@FXML
	public void handleTextChange() {
		responsLabel.setText("");
	}
	
	
	private boolean loginOk() {
		try {
			System.out.println("Prøver å hente Nurseobjekt fra databasen");
			Nurse nyNurse= database.getNurse(brukernavn.getText());
			if(!nyNurse.getPassword().equals(passord.getText())) {
				responsLabel.setText("Brukeren finnes, men passordet er feil");
				return false;
			}
		}catch (IllegalStateException e) {
			System.out.println("getNurse ga en IllegalStateException da brukeren ikke eksisterer");
			responsLabel.setText(e.getMessage());
			return false;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
}