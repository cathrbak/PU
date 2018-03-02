package tdt4140.gr1835.app.ui;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.MockingDatabase;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.UserDatabaseHandler;

public class Ny_BrukerController {
	
	UserDatabaseHandler udh;
	
	public Ny_BrukerController() {
		this.udh=new MockingDatabase();
	}
	
	List<String> inputs= new ArrayList<>();

	//Knapper
	@FXML
	Button button_registrer;
	
	//Tekstbokser
	@FXML
	TextField firstName;
	@FXML
	TextField familyName;
	@FXML
	TextField username;
	@FXML
	TextField password;
	@FXML
	TextField repeatpassword;
	@FXML
	TextField repeatemail;
	@FXML
	TextField email;
	@FXML
	TextField faculty;
	@FXML
	TextField phoneNumber;
	
	//Infotext
	@FXML
	Label infotext;
	
	//Tilbakeknapp
	@FXML
	Button backButton;
	
	@FXML
	public void handleButtonRegistrer() {
		Nurse nyNurse=new Nurse(username.getText());
		//Lager Nurseobjekt med inputs
		
		try {
			nyNurse.setFirstName(firstName.getText());
			}catch (Exception e) {
				nyNurse=null;
				System.err.println("Noe gikk galt i opprettelsen av Nurseobjektet");
				infotext.setText("Feil fornavn:"+nyNurse);
				infotext.setVisible(true);
				return;
				
			}
		try {
			nyNurse.setSecondName(familyName.getText());
			}catch (Exception e) {
				nyNurse=null;
				System.err.println("Noe gikk galt i opprettelsen av Nurseobjektet");
				infotext.setText("Feil etternavn:"+nyNurse);
				infotext.setVisible(true);
				return;
			}
		try {
			nyNurse.setPassword(password.getText());
			}catch (Exception e) {
				nyNurse=null;
				System.err.println("Noe gikk galt i opprettelsen av Nurseobjektet");
				infotext.setText("Feil passord:"+nyNurse);
				infotext.setVisible(true);
				return;
			}
		try {
			nyNurse.setEmail(email.getText());
		}catch (Exception e) {
			nyNurse=null;
			System.err.println("Noe gikk galt i opprettelsen av Nurseobjektet");
			infotext.setText("Feil email:"+nyNurse);
			infotext.setVisible(true);
			return;
		}
		
			
		try {
			nyNurse.setPhoneNumber(phoneNumber.getText());
			}catch (Exception e) {
				nyNurse=null;
				System.err.println("Noe gikk galt i opprettelsen av Nurseobjektet");
				infotext.setText("Feil telefonnummer:"+nyNurse);
				infotext.setVisible(true);
				return;
			}
		try {
				nyNurse.setFaculty(faculty.getText());
				}catch (Exception e) {
					nyNurse=null;
					System.err.println("Noe gikk galt i opprettelsen av Nurseobjektet");
					infotext.setText("Feil fakultetsnavn:"+nyNurse);
					infotext.setVisible(true);
					return;
				}
			
		
		infotext.setText("Nytt nurseobjekt laget :"+nyNurse);
		infotext.setVisible(true);
	}

	@FXML 
	public void handleBackButton() throws IOException {
		Stage stage; 
	    Parent root;
        //get reference to the button's stage         
        stage=(Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}

}
