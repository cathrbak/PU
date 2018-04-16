package tdt4140.gr1835.app.ui.nurse;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.omg.CORBA.portable.IndirectionException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class LoginController implements Initializable{
	
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
	private String melding;
	private boolean offline=false;
	
	public LoginController(String welcomeMessage) {
		this.database= new RestClientImp();
		this.melding=welcomeMessage;
	}
	
	public LoginController() {
		this.database= new RestClientImp();
	}
	
	public LoginController(boolean offline) {
		this.offline=true;
	}

	@FXML
	public void handleNyBrukerButton() throws IOException {
		Stage stage; 
	    Parent root;
        //get reference to the button's stage         
        stage=(Stage) button_nybruker.getScene().getWindow();
        Ny_BrukerController controller = new Ny_BrukerController();
                   
        //load up OTHER FXML document
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("Ny_Bruker.fxml"));
      	
        loader.setController(controller);
        root = (Parent) loader.load();
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
			Nurse nurse;
			if(offline) {
				nurse=new Nurse("offline");
				nurse.setFaculty("MH");
				nurse.setEmail("jajaja@gmail.com");
				nurse.setFirstName("Sos");
				nurse.setPassword(" ");
				
				Student testStudent = new Student("testStudentUN");
				testStudent.setStudentID(12);
				testStudent.setPassword("testPass");
				testStudent.setFaculty("MH");
				testStudent.setAnonymous(false);
				testStudent.setFirstName("Jonas");
				testStudent.setSecondName("Haga");
				testStudent.setSex("mann");
				testStudent.setEmail("jonas@gmail.com");
				testStudent.setPhoneNumber("46952270");
				testStudent.setNotat("Dette er et notat");
				
				
				Student testSurveyStudent = new Student("testStudentSUR");
				testSurveyStudent.setStudentID(11);
				testSurveyStudent.setPassword("surveypass");
				testSurveyStudent.setFaculty("MH");
				testSurveyStudent.setAnonymous(false);
				testSurveyStudent.setFirstName("Hans");
				testSurveyStudent.setSecondName("Ovanger");
				testSurveyStudent.setSex("mann");
				testSurveyStudent.setEmail("hans@gmail.com");
				testSurveyStudent.setPhoneNumber("46952270");
				testSurveyStudent.setNotat("Dette er et annet notat");
				
				nurse.setStudents(Arrays.asList(testStudent,testSurveyStudent));
		        
		        Table table=new Table(12,2,3,4,4,2,3,2,3,4,3,30);
		        table.setDato(new Timestamp(System.currentTimeMillis()));
		        testStudent.addAnswer(table);
		        Table table1=new Table(11,2,3,4,4,2,3,2,3,4,3,30);
		        table1.setDato(new Timestamp(System.currentTimeMillis()));
		        testSurveyStudent.addAnswer(table1);
		        
			}else {
				System.out.println("henter helsesøster fra databsen");
	            nurse=database.getNurse(brukernavn.getText());
	            
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
			}
			
            System.out.println("logincheck passed, taking user to loading screen");
            Stage stage; 
            Parent root;
            //get reference to the button's stage        
            stage=(Stage) button_login.getScene().getWindow();
            
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
	
	private boolean loginOk() {
		System.out.println("Prøver å hente Nurseobjekt fra databasen");
		Nurse nyNurse;
		if(offline) {
			nyNurse=new Nurse("offline");
			nyNurse.setFaculty("OK");
			nyNurse.setEmail("jajaja@gmail.com");
			nyNurse.setFirstName("Sos");
			nyNurse.setPassword(" ");
		}else {
			nyNurse= database.getNurse(brukernavn.getText());
		}
		
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
	
	@FXML
	Label velkomst;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		velkomst.setText(melding);
		
	}
	
}