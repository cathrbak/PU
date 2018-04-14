package tdt4140.gr1835.app.ui.nurse;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.database.ConnectionSQL;
import tdt4140.gr1835.app.database.MockingDatabase;
import tdt4140.gr1835.app.database.UserDatabaseHandler;
import tdt4140.gr1835.app.webclient.RESTClient;
import tdt4140.gr1835.app.webclient.RestClientImp;

public class Ny_BrukerController {
	
	RESTClient database;
	
	public Ny_BrukerController() {
		this.database=new RestClientImp();
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
	PasswordField password;
	@FXML
	PasswordField repeatpassword;
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
	

	
	
	@FXML
	public void handleButtonRegistrer() throws IOException, SQLException{
		infotext.setVisible(false);
		System.out.println("Tester om alle feltene er riktig");
		try {
			Nurse newNurse= new Nurse(username.getText());
			newNurse.setEmail(email.getText());
			newNurse.setFaculty(faculty.getText());
			newNurse.setFirstName(firstName.getText());
			newNurse.setPassword(password.getText());
			newNurse.setPhoneNumber(phoneNumber.getText());
			newNurse.setSecondName(familyName.getText());
		}catch (Exception e) {
			infotext.setText("Kan ikke opprette ugyldig bruker");
			infotext.setVisible(true);
			return;
		}
		
		System.out.println("Legger til den nye brukeren i databasen");
		Nurse nurse = database.getNurse(username.getText());
		if(nurse==null) { //eksisterer da ingen med dette brukernavnet
			Nurse newNurse= new Nurse(username.getText());
			newNurse.setEmail(email.getText());
			newNurse.setFaculty(faculty.getText());
			newNurse.setFirstName(firstName.getText());
			newNurse.setPassword(password.getText());
			newNurse.setPhoneNumber(phoneNumber.getText());
			newNurse.setSecondName(familyName.getText());
			System.out.println("Oppretter objektet: "+newNurse);
			
			boolean OK = database.createNurse(newNurse);
			if(!OK) { //hvis det gikk noe galt med opprettelsen må man si ifra til brukeren
				infotext.setText("Fikk problemer med å opprette brukerer, prøv på nytt");
				infotext.setVisible(true);
				return;
			}
			
			
		}else {
			infotext.setText("Det eksisterer en bruker med dette brukernavnet");
			System.out.println("Det eksisterer en bruker med dette brukernavnet");
			infotext.setVisible(true);
			return;
		}
		infotext.setText("Brukeren ble opprettet");
		infotext.setVisible(true);
		
		Stage stage; 
	    Parent root;
        //get reference to the button's stage         
        stage=(Stage) backButton.getScene().getWindow();
        LoginController controller= new LoginController("Velkommen " + firstName.getText()+ " " + familyName.getText());
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

        loader.setController(controller);
        root = (Parent) loader.load();
        
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
	
	//Tilbakeknapp
	@FXML
	Button backButton;
	@FXML 
	public void handleBackButton() throws IOException {
		Stage stage; 
	    Parent root;
        //get reference to the button's stage         
        stage=(Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        LoginController controller= new LoginController("Velkommen");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

        loader.setController(controller);
        root = (Parent) loader.load();
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
//-----------------------------------------------------------------------------------------------------------	
	//Metoder for å se etter feil i feltene
	
	@FXML
	Label firstNameRespons;
	
	public void handleFirstNameTextChange() {
		firstNameRespons.setVisible(false);
		try {
			Nurse nyNurse= new Nurse("");
			if(firstName.getText().equals("")) {
				return;
			}
			nyNurse.setFirstName(firstName.getText());
		}catch (Exception e) {
			firstNameRespons.setText(e.getMessage());
			firstNameRespons.setVisible(true);
		}
	}
	
	@FXML
	Label familyNameRespons;
	
	@FXML
	public void handleSecondNameTextChange() {
		familyNameRespons.setVisible(false);
		try {
			Nurse nyNurse= new Nurse("");
			if(familyName.getText().equals("")) {
				return;
			}
			nyNurse.setSecondName(familyName.getText());
		}catch (Exception e) {
			familyNameRespons.setText(e.getMessage());
			familyNameRespons.setVisible(true);
		}
	}
	
	@FXML
	Label usernameRespons;
	
	@FXML
	public void handleUsernameTextChange(){
		usernameRespons.setVisible(false);
		try {
			Nurse nyNurse= new Nurse("");
			if(username.getText().equals("")) {
				return;
			}
			nyNurse.setUsername(username.getText());
		}catch (Exception e) {
			usernameRespons.setText(e.getMessage());
			usernameRespons.setVisible(true);
		}
	}
	
	@FXML
	Label passwordRespons;
	
	@FXML
	public void handlePasswordTextChange() {
		passwordRespons.setVisible(false);
		try {
			Nurse nyNurse= new Nurse("");
			nyNurse.setPassword(password.getText());
		}catch (Exception e) {
			passwordRespons.setText(e.getMessage());
			passwordRespons.setVisible(true);
		}
	}
	
	@FXML
	Label repeatPassordRespons;
	
	public void handlerepeatpasswordTextChange() {
		repeatPassordRespons.setVisible(false);
		if(repeatpassword.getText().equals("")) {
			return;
		}
		if(!password.getText().equals(repeatpassword.getText())) {
			repeatPassordRespons.setText("Passordene er ikke like");
			repeatPassordRespons.setVisible(true);
		}
	}
	
	@FXML
	Label emailRespons;
	
	public void handleEmailTextChange() {
		emailRespons.setVisible(false);
		try {
			Nurse nyNurse= new Nurse("");
			if(email.getText().equals("")) {
				return;
			}
			nyNurse.setEmail(email.getText());
		}catch (Exception e) {
			emailRespons.setText(e.getMessage());
			emailRespons.setVisible(true);
		}
	}
	
	@FXML
	Label repeatEmailRespons;
	
	public void handleRepeatEmailTextChange() {
		repeatEmailRespons.setVisible(false);
		if(repeatemail.getText().equals("")) {
			return;
		}
		if(!email.getText().equals(repeatemail.getText())) {
			repeatEmailRespons.setText("Emailene er ikke like");
			repeatEmailRespons.setVisible(true);
		}
	}
	
	@FXML
	Label phoneNumberRespons;
	
	public void handlePhoneNumberTextChange() {
		phoneNumberRespons.setVisible(false);
		try {
			Nurse nyNurse= new Nurse("");
			if(phoneNumber.getText().equals("")) {
				return;
			}
			nyNurse.setPhoneNumber(phoneNumber.getText());
		}catch (Exception e) {
			phoneNumberRespons.setText(e.getMessage()); //Feilmeldingen her er litt lang.
			phoneNumberRespons.setVisible(true);
		}
	}

	@FXML
	Label facultyRespons;
	
	public void handleFacultyTextChange() {
		facultyRespons.setVisible(false);
		try {
			Nurse nyNurse= new Nurse("");
			if(faculty.getText().equals("")) {
				return;
			}
			nyNurse.setFaculty(faculty.getText());
		}catch (Exception e) {
			facultyRespons.setText(e.getMessage());
			facultyRespons.setVisible(true);
		}
	}
}
