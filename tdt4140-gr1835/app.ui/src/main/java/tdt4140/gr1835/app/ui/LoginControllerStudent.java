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
import tdt4140.gr1835.app.core.ConnectionSQL;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.UserDatabaseHandler;

public class LoginControllerStudent {
	@FXML
	TextField studentbrukernavn;
	@FXML
	PasswordField studentpassord;
	
	@FXML
	Button studentbutton_login;
	@FXML
	Button studentbutton_nybruker;
	
	@FXML
	Label studentresponsLabel;
	
	private UserDatabaseHandler database;
	
	public LoginControllerStudent() {
		System.out.println("Oppretter database");
		this.database= new ConnectionSQL();
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
        stage=(Stage) studentbutton_nybruker.getScene().getWindow();
        
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Ny_BrukerStudent.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	public void handleLoginButton() throws Exception {
		if(loginOk()) {
			//Ta meg til mainPage
            System.out.println("Login ok, sender bruker til mainPage");
            
            Stage stage; 
            Parent root;
            //get reference to the button's stage        
            stage=(Stage) studentbutton_login.getScene().getWindow();
            
            Student student=database.getStudent(studentbrukernavn.getText());//Henter relevant Studentobjekt
            
            MainPageControllerStudent controller= new MainPageControllerStudent(student);//Lager en kontroller instans

                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPageStudent.fxml"));
                
                loader.setController(controller); //Smeller den kontrolleren inn i fxmlfilen

                root = (Parent) loader.load();
              //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            //Legger på css stylesheetet
            scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
	       
		}
	}
	
	@FXML
	public void handleTextChange() {
		studentresponsLabel.setText("");
	}
	
	
	
	
	private boolean loginOk() throws Exception {
		try {
			System.out.println("Prøver å hente Studentobjekt fra databasen");
			Student nyStudent= database.getStudent(studentbrukernavn.getText());
			if(!nyStudent.getPassword().equals(studentpassord.getText())) {
				studentresponsLabel.setText("Brukeren finnes, men passordet er feil");
				return false;
			}
		}catch (IllegalStateException e) {
			System.out.println("getStudent ga en IllegalStateException da brukeren ikke eksisterer");
			studentresponsLabel.setText(e.getMessage());
			return false;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}


