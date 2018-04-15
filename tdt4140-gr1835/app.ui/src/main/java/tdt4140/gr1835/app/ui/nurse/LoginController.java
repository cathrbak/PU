package tdt4140.gr1835.app.ui.nurse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.omg.CORBA.portable.IndirectionException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;
import tdt4140.gr1835.app.database.ConnectionSQL;
import tdt4140.gr1835.app.database.MockingDatabase;
import tdt4140.gr1835.app.database.UserDatabaseHandler;
import tdt4140.gr1835.app.webclient.RESTClient;
import tdt4140.gr1835.app.webclient.RestClientImp;

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
	
	private RESTClient database;
	
	public LoginController() {
		this.database= new RestClientImp();
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
	public void handleLoginButton() throws Exception {
		if(loginOk()) {
			
            System.out.println("logincheck passed, taking user to loading screen");
            Stage stage; 
            Parent root;
            //get reference to the button's stage        
            stage=(Stage) button_login.getScene().getWindow();
       
            System.out.println("henter helsesøster fra databsen");
            Nurse nurse=database.getNurse(brukernavn.getText());
            
            System.out.println("henter studenter");
            List<Student> students = database.getStudents(nurse.getUsername());
            students.stream().forEach(student->System.out.println(student.getUsername()));
            nurse.setStudents(students);
            
            
            
            System.out.println("henter undersøkelser og student id");
            for(Student student : nurse.getStudents()) {
        			System.out.println("Svar for "+student.getUsername());
            		List<Table> answers = database.getAnswers(student.getUsername());
            		answers.stream().forEach(a->System.out.println(a));
            		student.setAnswers(answers);
            }
            
            MainPageController controller= new MainPageController(nurse);//Lager en kontroller instans

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                
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
		responsLabel.setText("");
	}
	
	private boolean loginOk() throws Exception {
		System.out.println("Prøver å hente Nurseobjekt fra databasen");
		Nurse nyNurse= database.getNurse(brukernavn.getText());
		if(nyNurse==null) {
			responsLabel.setText("Brukeren eksisterer ikke i våre systemer");
			return false;
		}
		if(!nyNurse.getPassword().equals(passord.getText())) {
			responsLabel.setText("Brukeren finnes, men passordet er feil");
			return false;
		}
		if(passord.getText().equals("")) {
			responsLabel.setText("Du må skrive inn et passord");
			return false;
		}
		return true;
	}
	
}