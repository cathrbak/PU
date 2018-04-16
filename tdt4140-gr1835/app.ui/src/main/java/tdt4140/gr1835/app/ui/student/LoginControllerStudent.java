package tdt4140.gr1835.app.ui.student;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

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
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;
import tdt4140.gr1835.app.database.ConnectionSQL;
import tdt4140.gr1835.app.database.UserDatabaseHandler;
import tdt4140.gr1835.app.ui.nurse.FxApp;
import tdt4140.gr1835.app.webclient.RESTClient;
import tdt4140.gr1835.app.webclient.RestClientImp;

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
	Label responsLabel;
	
	private RESTClient database;
	private boolean offline=false;
	
	public LoginControllerStudent() {
		System.out.println("Oppretter database");
		this.database= new RestClientImp();
	}
	
	public LoginControllerStudent(boolean offline) {
		this.offline=offline;
	}


	@FXML
	public void handleNyBrukerButton(){
		Stage stage; 
	    Parent root;
        //get reference to the button's stage         
        stage=(Stage) studentbutton_nybruker.getScene().getWindow();
        //load up OTHER FXML document
        try {
			root = FXMLLoader.load(getClass().getResource("Ny_BrukerStudent.fxml"));
			//create a new scene with root and set the stage
	        Scene scene = new Scene(root);
	        //Legger på css stylesheetet
	        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			responsLabel.setText("Kunne ikke sende til nyBrukerSide. Prøv igjen!");
		}
      	
	}
	
	@FXML
	public void handleLoginButton() {
		if(loginOk()) {
			Student student;
			if(offline) {
				Nurse nurse=new Nurse("offline");
				nurse.setFaculty("MH");
				nurse.setEmail("jajaja@gmail.com");
				nurse.setFirstName("Sos");
				nurse.setPassword(" ");
				
				student = new Student("testStudentUN");
				student.setStudentID(12);
				student.setPassword("testPass");
				student.setFaculty("MH");
				student.setAnonymous(false);
				student.setFirstName("Jonas");
				student.setSecondName("Haga");
				student.setSex("mann");
				student.setEmail("jonas@gmail.com");
				student.setPhoneNumber("46952270");
				student.setNotat("Dette er et notat");
				
				nurse.setStudents(Arrays.asList(student));
		        
		        Table table=new Table(12,2,3,4,4,2,3,2,3,4,3,30);
		        table.setDato(new Timestamp(System.currentTimeMillis()));
		        student.addAnswer(table);
			}else {
				student=database.getStudent(studentbrukernavn.getText());//Henter relevant Studentobjekt
		        List<Table> listOfAnswers = database.getAnswers(student.getUsername()); //Henter studentens spørreundersøkelse
		        student.setAnswers(listOfAnswers);
			}
			
			//Ta meg til mainPage
            System.out.println("Login ok, sender bruker til mainPageStudent");
            
            Stage stage; 
            Parent root;
            //get reference to the button's stage        
            stage=(Stage) studentbutton_login.getScene().getWindow();
            
            MainPageControllerStudent controller= new MainPageControllerStudent(student);//Lager en kontroller instans

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPageStudent.fxml"));
                
            loader.setController(controller); //Legger kontrolleren inn i fxmlfilen

            try {
				root = (Parent) loader.load();
				//create a new scene with root and set the stage
	            Scene scene = new Scene(root);
	            //Legger på css stylesheetet
	            scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
	            stage.setScene(scene);
	            stage.show();
			} catch (IOException e) {
				e.printStackTrace();
				responsLabel.setText("Kunne ikke sende bruker til hovedside. Prøv igjen");
			}
            
	       
		}
	}
	
	@FXML
	public void handleTextChange() {
		responsLabel.setText("");
	}
	
	private boolean loginOk() {
		Student student;
		if(offline) {
			student = new Student("offline");
			student.setStudentID(12);
			student.setPassword(" ");
			student.setFaculty("MH");
			student.setAnonymous(false);
			student.setFirstName("Jonas");
			student.setSecondName("Haga");
			student.setSex("mann");
			student.setEmail("jonas@gmail.com");
			student.setPhoneNumber("46952270");
			student.setNotat("Dette er et notat");
			if(!studentbrukernavn.getText().equals("offline")) {
				student=null;
			}
		}else {
			student=database.getStudent(studentbrukernavn.getText());
		}
		
		if(student==null) {
			responsLabel.setText("Denne studenten finnes ikke i våre systemer");
			return false;
		}
		if(studentpassord.getText().equals("")) {
			responsLabel.setText("Du må skrive inn et passord");
			return false;
		}
		if(!student.getPassword().equals(studentpassord.getText())) {
			responsLabel.setText("Brukeren finnes, men passordet er feil");
			return false;
		}
		return true;
	}
}


