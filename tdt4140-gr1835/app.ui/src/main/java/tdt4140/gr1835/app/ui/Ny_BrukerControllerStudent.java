package tdt4140.gr1835.app.ui;


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

import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.UserDatabaseHandler;

public class Ny_BrukerControllerStudent {
	
	UserDatabaseHandler database;
	
	public Ny_BrukerControllerStudent() {
		this.database=new ConnectionSQL();
	}
	
	List<String> inputs= new ArrayList<>();

	//Knapper
	@FXML
	Button button_registrer;
	
	//Tekstbokser
	@FXML
	TextField studentfirstName;
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
		System.out.println("Tester om alle feltene er riktig, student");
		try {
			Student newStudent= new Student(username.getText());
			newStudent.setEmail(email.getText());
			newStudent.setFaculty(faculty.getText());
			newStudent.setFirstName(studentfirstName.getText());
			newStudent.setPassword(password.getText());
			newStudent.setPhoneNumber(phoneNumber.getText());
			newStudent.setSecondName(familyName.getText());
		}catch (Exception e) {
			infotext.setText("Kan ikke opprette ugyldig studentbruker");
			infotext.setVisible(true);
			return;
		}
		
		System.out.println("Legger til den nye studentbrukeren i databasen");
		
		try {
			database.getStudent(username.getText());
			infotext.setText("Det eksisterer en studentbruker med dette brukernavnet");
			System.out.println("Det eksisterer en studentbruker med dette brukernavnet");
			infotext.setVisible(true);
			return;
		}catch (IllegalStateException e) {
			if (e.getMessage().equals("Denne studentbrukeren eksisterer ikke i databasen")) { //Dette er burde vi endre på slik at den kanskje returnerer null isteden
				Student newStudent= new Student(username.getText());
				newStudent.setEmail(email.getText());
				newStudent.setFaculty(faculty.getText());
				newStudent.setFirstName(studentfirstName.getText());
				newStudent.setPassword(password.getText());
				newStudent.setPhoneNumber(phoneNumber.getText());
				newStudent.setSecondName(familyName.getText());
				System.out.println("Oppretter objektet: "+newStudent);
				
				try {
					database.createNewStudent(newStudent);
				} catch (SQLException ex) {
					System.out.println("Fikk problemer med å legge til ny Student i databasen");
					System.out.println(ex.getStackTrace());
					return;
				}
			}
		}

		try {
			System.out.println("Dette ligger nå i databasen: "+database.getStudent(username.getText()));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		infotext.setText("Studentbrukeren ble opprettet");
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
        root = FXMLLoader.load(getClass().getResource("LoginStudent.fxml"));
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
			Student nyStudent= new Student("");
			if(studentfirstName.getText().equals("")) {
				return;
			}
			nyStudent.setFirstName(studentfirstName.getText());
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
			Student nyStudent= new Student("");
			if(familyName.getText().equals("")) {
				return;
			}
			nyStudent.setSecondName(familyName.getText());
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
			Student nyStudent= new Student("");
			if(username.getText().equals("")) {
				return;
			}
			nyStudent.setUsername(username.getText());
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
			Student nyStudent= new Student("");
			nyStudent.setPassword(password.getText());
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
			Student nyStudent= new Student("");
			if(email.getText().equals("")) {
				return;
			}
			nyStudent.setEmail(email.getText());
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
			Student nyStudent= new Student("");
			if(phoneNumber.getText().equals("")) {
				return;
			}
			nyStudent.setPhoneNumber(phoneNumber.getText());
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
			Student nyStudent= new Student("");
			if(faculty.getText().equals("")) {
				return;
			}
			nyStudent.setFaculty(faculty.getText());
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
        root = FXMLLoader.load(getClass().getResource("LoginStudent.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}

}