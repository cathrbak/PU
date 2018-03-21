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

public class MainPageControllerStudent implements Initializable {
UserDatabaseHandler database;
	
	public MainPageControllerStudent(Student student) throws SQLException, Exception {
		this.database=new ConnectionSQL();
		this.student = student;
		addInfo();	
	}
				
	private Student student;
	
	@FXML
	Button Profile;
	@FXML
	Button Logout;

	@FXML
	Button Anonymitet;
	
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
	
	
	public int idNumber = 1;
	public int total = 0;
	
	//lager listen som skal inneholde dataen
	final ObservableList<Table> data = FXCollections.observableArrayList();

	
	  //Må kunne fylle tabellen med studentens svar
	 
	  public void addInfo() throws SQLException, Exception{

			try {
				List<Table> listOfAnswers = database.getAnswers(student);
				for(Table answer: listOfAnswers) {
					data.add(answer);	
				}
					
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		
		
	
	@FXML
	public void handleProfileButton() throws IOException {
		//Ta meg til profil
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) Profile.getScene().getWindow();
        ProfileControllerStudent controller= new ProfileControllerStudent(this.student);//Lager en kontroller instans

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileStudent.fxml"));
            
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
        root = FXMLLoader.load(getClass().getResource("LoginStudent.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	public void handleAnonymitetButton() throws IOException {
		//Ta meg til mainPage
        System.out.println("Sender studentbruker til questionPage");
        
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) Anonymitet.getScene().getWindow();
        
       // QuestionsController controller= new QuestionsController(this.student);//Lager en kontroller instans

            FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionsStudent.fxml"));
            
           // loader.setController(controller); //Smeller den kontrolleren inn i fxmlfilen

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
	    tableID.setItems(data);
	    }	

}
