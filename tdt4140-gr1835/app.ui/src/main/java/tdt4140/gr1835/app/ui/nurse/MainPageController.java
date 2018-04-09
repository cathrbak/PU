package tdt4140.gr1835.app.ui.nurse;

import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;

public class MainPageController implements Initializable {

	private Nurse nurse;
	
	public MainPageController(Nurse nurse){
		this.nurse = nurse;
		System.out.println("Logget på som: "+nurse);
		addInfoAnswers();	
		addStudents();
	}
				
	public Nurse getNurse() {
		return this.nurse;
	}

	@FXML
	Label brukernavn;
	@FXML
	Label fakultetsID;
	
	@FXML
	public void setBrukernavnLabel() {
		brukernavn.setText("Logget inn som: " + nurse.getUsername() );
	}
	
	@FXML
	public void setFakultetsIDLabel() {
		fakultetsID.setText("Denne tabellen viser alle studenter \n" + 
				"som tilhører " + nurse.getFaculty() + " fakultetet");
	
	}
	
	
	//tabellen med spørreundersøkelsene til studentene en helsesøster har ansvar for 
	@FXML 
	TableView<Table> tableID;
	@FXML 
	TableColumn<Table, String> Dato;
	@FXML 
	TableColumn<Table, Integer> PersonID;
	@FXML
	TableColumn<Table, String> Navn;
	@FXML 
	TableColumn<Table, Integer> Spm1;
	@FXML 
	TableColumn<Table, Integer> Spm2;
	@FXML 
	TableColumn<Table, Integer> Spm3;
	@FXML 
	TableColumn<Table, Integer> Spm4;
	@FXML 
	TableColumn<Table, Integer> Spm5;
	@FXML 
	TableColumn<Table, Integer> Spm6;
	@FXML 
	TableColumn<Table, Integer> Spm7;
	@FXML 
	TableColumn<Table, Integer> Spm8;
	@FXML 
	TableColumn<Table, Integer> Spm9;
	@FXML 
	TableColumn<Table, Integer> Spm10;
	@FXML 
	TableColumn<Table, Integer> Total;
	

	//lager listen som skal inneholde dataen med spørreundersøkelser
	final ObservableList<Table> dataAnswers = FXCollections.observableArrayList();
	
	//I denne metoden legges informasjonen fra databasen til i listen som skal vises i applikasjonen
	public void addInfoAnswers(){
		List<Student> students=nurse.getStudents(); //henter alle studentene til helsesøsteren
		System.out.println(students.toString());
		for (Student student : students) { //løkker gjennom hver student og henter svarene deres på spørreundersøkelse
				//Table studentTable = new Table(student);
				String studentName;
				if (student.isAnonymous()) {
					studentName = "Anonym";
				}
				else {
					studentName = student.getFirstName() + " " + student.getSecondName();
				}
				
				List<Table> listOfAnswers = student.getAnswers();
				for(Table answer: listOfAnswers) {
					answer.setNavn(studentName);
					dataAnswers.add(answer);	 //legger det til i listen som skal vises i applikasjon
					
				}
		}
		
	}
	
	
	//tabellen med hyperlink til studenters profil
	@FXML 
	TableView<Table> tableStudents;
	@FXML
	TableColumn<Table, Hyperlink> StudentID;
		
	//lager listen som skal inneholde dataen med studenter
	final ObservableList<Table> dataStudents = FXCollections.observableArrayList();
		
	//I denne metoden legges informasjonen fra databasen til i listen som skal vises i applikasjonen	
	public void addStudents(){
		List<Student> students= nurse.getStudents(); //henter alle studentene til helsesøsteren
		for (Student student : students) {
			int studentID = student.getStudentID(); //henter studentens ID fra databasen
			String studentid = Integer.toString(studentID);
			Hyperlink link = new Hyperlink();
			link.setText(studentid);
			link.setOnAction(new SendToStudentProfile(this.nurse, student, link));
			dataStudents.add(new Table(link)); //legger til i listen som skal vises i tabellen
		}				
	}
	//Knapper 
	@FXML
	Button Profile;
	@FXML
	Button Logout;
	@FXML
	Button Question;
	
	
	//Ta meg til profil
	@FXML
	public void handleProfileButton() throws IOException{
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) Profile.getScene().getWindow();
        ProfileController controller= new ProfileController(this.nurse);//Lager en kontroller instans

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
            
        loader.setController(controller); //Legger til kontrolleren  i fxmlfilen

        root = (Parent) loader.load();
         //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        System.out.println("Sender bruker til profil");
	}
	
	//logger ut av applikasjonen
	@FXML
	public void handleLogoutButton() throws IOException {
		Stage stage; 
	    Parent root;
        //get reference to the button's stage         
        stage=(Stage) Logout.getScene().getWindow();
        
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
	
	//Ta meg til questionpage
	@FXML
	public void handleQuestionButton() throws IOException {
        System.out.println("Sender bruker til questionPage");
        
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) Question.getScene().getWindow();
        
        QuestionsController controller= new QuestionsController(this.nurse);//Lager en kontroller instans

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Questions.fxml"));
            
        loader.setController(controller); //Legger til controller

        root = (Parent) loader.load();
          //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
		
	}
	
	
	//initialiserer dataene 
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		StudentID.setCellValueFactory(new PropertyValueFactory<Table, Hyperlink>("StudentID"));
		Dato.setCellValueFactory(new PropertyValueFactory<Table, String>("Dato"));
		PersonID.setCellValueFactory(new PropertyValueFactory<Table, Integer>("PersonID"));
		Navn.setCellValueFactory(new PropertyValueFactory<Table, String>("Navn"));
		Spm1.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm1"));
		Spm2.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm2"));
		Spm3.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm3"));
		Spm4.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm4"));
		Spm5.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm5"));
		Spm6.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm6"));
		Spm7.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm7"));
		Spm8.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm8"));
		Spm9.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm9"));
		Spm10.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm10"));
		Total.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Total"));
		
		//putter dataene i cellene 
		tableStudents.setItems(dataStudents);
	    tableID.setItems(dataAnswers);
	    
	    //kaller på label-metoden her
	    setBrukernavnLabel();
	    setFakultetsIDLabel();
	    }

}
