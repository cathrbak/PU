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
import tdt4140.gr1835.app.core.ConnectionSQL;
import tdt4140.gr1835.app.core.MockingDatabase;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.UserDatabaseHandler;

public class Ny_BrukerController {
	
	UserDatabaseHandler database;
	
	public Ny_BrukerController() {
		this.database=new ConnectionSQL();
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
		
		try {
			database.getNurse(username.getText());
			infotext.setText("Det eksisterer en bruker med dette brukernavnet");
			System.out.println("Det eksisterer en bruker med dette brukernavnet");
			infotext.setVisible(true);
			return;
		}catch (IllegalStateException e) {
			if (e.getMessage().equals("Denne brukeren eksisterer ikke i databasen")) { //Dette er burde vi endre på slik at den kanskje returnerer null isteden
				Nurse newNurse= new Nurse(username.getText());
				newNurse.setEmail(email.getText());
				newNurse.setFaculty(faculty.getText());
				newNurse.setFirstName(firstName.getText());
				newNurse.setPassword(password.getText());
				newNurse.setPhoneNumber(phoneNumber.getText());
				newNurse.setSecondName(familyName.getText());
				System.out.println("Oppretter objektet: "+newNurse);
				
				try {
					database.createNewNurse(newNurse);
				} catch (SQLException ex) {
					System.out.println("Fikk problemer med å legge til ny Helsesøster i databasen");
					System.out.println(ex.getStackTrace());
					return;
				}
			}
		}

		try {
			System.out.println("Dette ligger nå i databasen: "+database.getNurse(username.getText()));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		infotext.setText("Brukeren ble opprettet");
		infotext.setVisible(true);
//		try {
//			TimeUnit.SECONDS.sleep(3);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Stage stage; 
	    Parent root;
        //get reference to the button's stage         
        stage=(Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
//        scene.setUserData(firstName.getText());
//        System.out.println(scene.getUserData());
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
		
		//Så må vi sende dette til databasen

	}
	
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
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}

}