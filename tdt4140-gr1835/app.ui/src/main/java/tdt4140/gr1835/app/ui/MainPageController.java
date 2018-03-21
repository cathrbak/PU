package tdt4140.gr1835.app.ui;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.ConnectionSQL;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;
import tdt4140.gr1835.app.core.UserDatabaseHandler;

public class MainPageController implements Initializable {
	UserDatabaseHandler database;
	
	public MainPageController(Nurse nurse) throws SQLException, Exception {
		this.database=new ConnectionSQL();
		this.nurse = nurse;
		addInfoAnswers();	
		addStudents();
	}
				
	private Nurse nurse;
	
	@FXML
	Button Profile;
	@FXML
	Button Logout;
	@FXML
	Button Question;
	
	//tabellen med studenter
	@FXML 
	TableView<Table> tableStudents;
	@FXML
	TableColumn<Table, Integer> StudentID;
	
	//tabellen med spørreundersøkelser
	@FXML 
	TableView<Table> tableID;
	@FXML 
	TableColumn<Table, Integer> PersonID;
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
	@FXML 
	TableColumn<Table, Integer> Dato;

	
	@FXML
	Button seProfilButton1;
	
	@FXML
	Button seProfilButton2;
	
	
	
	@FXML
	public void handleProfilButton1() throws SQLException, Exception {
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) Profile.getScene().getWindow();
        
		List<Student> students;
		students = database.getStudents(nurse); //henter alle studentene til helsesøsteren
		
		
        StudentProfileController controller= new StudentProfileController(this.nurse, students.get(0));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentProfil.fxml"));
            
        loader.setController(controller); 

        root = (Parent) loader.load();
        
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	public void handleProfilButton2() throws SQLException, Exception {
	
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) Profile.getScene().getWindow();
        
		List<Student> students;
		students = database.getStudents(nurse); //henter alle studentene til helsesøsteren
		int studentID = database.getStudentID(students.get(1)); //henter studentens ID fra databasen
		
        StudentProfileController controller= new StudentProfileController(this.nurse, students.get(1));//Lager en kontroller instans

            FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentProfil.fxml"));
            
            loader.setController(controller); //Smeller den kontrolleren inn i fxmlfilen

            root = (Parent) loader.load();
          //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        System.out.println("Sender bruker tilbake til mainPage");
	}
	
	
	public int idNumber = 1;
	public int total = 0;
	
	//lager listen som skal inneholde dataen med spørreundersøkelser
	final ObservableList<Table> dataAnswers = FXCollections.observableArrayList();
	//lager listen som skal inneholde dataen med studenter
	final ObservableList<Table> dataStudents = FXCollections.observableArrayList();

	//I denne metoden legges informasjonen fra databasen til i listen som skal vises i applikasjonen
	public void addInfoAnswers() throws SQLException, Exception{
		List<Student> students;
		
		students = database.getStudents(nurse); //henter alle studentene til helsesøsteren
		for (Student student : students) { //løkker gjennom hver student og henter svarene deres på spørreundersøkelse
			try {
				List<Table> listOfAnswers = database.getAnswers(student);
				for(Table answer: listOfAnswers) {
					dataAnswers.add(answer);	 //legger det til i listen som skal vises i applikasjon
					System.out.println(answer);
				}
					
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		
	}
	//I denne metoden legges informasjonen fra databasen til i listen som skal vises i applikasjonen	
	public void addStudents() throws SQLException, Exception{
		List<Student> students;
		students = database.getStudents(nurse); //henter alle studentene til helsesøsteren
		for (Student student : students) {
			try{
				int studentID = database.getStudentID(student); //henter studentens ID fra databasen
				Table tableStudent = new Table(studentID);  //lager et table-objekt med kun studentID
				
				dataStudents.add(tableStudent); //legger til i listen som skal vises i tabellen
			}
			catch(SQLException e3) {
				e3.printStackTrace();
			}
				
			}
		}
		
	
	
	@FXML
	public void handleProfileButton() throws IOException {
		//Ta meg til profil
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) Profile.getScene().getWindow();
        ProfileController controller= new ProfileController(this.nurse);//Lager en kontroller instans

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
            
            loader.setController(controller); //Smeller den kontrolleren inn i fxmlfilen

            root = (Parent) loader.load();
          //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        System.out.println("Sender bruker tilbake til mainPage");
	}
	
	
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
	
	@FXML
	public void handleQuestionButton() throws IOException {
		//Ta meg til questionpage
        System.out.println("Sender bruker til questionPage");
        
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) Question.getScene().getWindow();
        
        QuestionsController controller= new QuestionsController(this.nurse);//Lager en kontroller instans

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Questions.fxml"));
            
            loader.setController(controller); //Smeller den kontrolleren inn i fxmlfilen

            root = (Parent) loader.load();
          //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		StudentID.setCellValueFactory(new PropertyValueFactory<Table, Integer>("StudentID"));
		PersonID.setCellValueFactory(new PropertyValueFactory<Table, Integer>("PersonID"));
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
		Dato.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Dato"));

		tableStudents.setItems(dataStudents);
	    tableID.setItems(dataAnswers);
	    }	
}
