package tdt4140.gr1835.app.ui.student;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;
import tdt4140.gr1835.app.ui.nurse.FxApp;
import tdt4140.gr1835.app.webclient.RESTClient;
import tdt4140.gr1835.app.webclient.RestClientImp;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class MainPageControllerStudent implements Initializable {
	
	RESTClient database;
	
	public MainPageControllerStudent(Student student){
		this.database=new RestClientImp();
		this.student = student;
		addInfo();	
	}
	@FXML
	Label InnloggetStudent;
	
	@FXML
	public void setInnloggetStudentLabel() {
		InnloggetStudent.setText("Logget inn som: " + student.getUsername());
	}

	@FXML
	RadioButton radioButton;
	
	public void handleAnonymousRadioButton() {
		if (radioButton.isSelected()) {
		student.setAnonymous(true);
		boolean ok = database.updateStudent(student);
		if(!ok) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Feilmelding");
			alert.setHeaderText(null);
			alert.setContentText("Kunne ikke endre anonymitet. Prøv igjen!");
			alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Oppdatert");
			alert.setHeaderText(null);
			alert.setContentText("Du er nå anonym for din helsesøster,"+ student.getNurse().getFirstName()+" "+student.getNurse().getSecondName()+".\n "
					+ "Helsesøsteren vil fortsatt kunne kontakte deg, men ser ikke ditt navn på sin profil");
			alert.showAndWait();
		}
		} else if (!radioButton.isSelected()) {
			student.setAnonymous(false);
			boolean ok = database.updateStudent(student);
			if(!ok) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Feilmelding");
				alert.setHeaderText(null);
				alert.setContentText("Kunne ikke endre anonymitet. Prøv igjen!");
				alert.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Oppdatert");
				alert.setHeaderText(null);
				alert.setContentText("Du er ikke lenger anonym for din helsesøster, "+ student.getNurse().getFirstName()+" "+student.getNurse().getSecondName());
				alert.showAndWait();
			}
			
		}
	}
	
				
	private Student student;
	
	@FXML
	Button Profile;
	@FXML
	Button Logout;

	@FXML
	Button Spørsmål;
	
	@FXML 
	TableView<Table> tableID;
	@FXML 
	TableColumn<Table, String> Dato;
	@FXML 
	TableColumn<Table, Integer> Spm1, Spm2, Spm3, Spm4, Spm5, Spm6, Spm7, Spm8, Spm9, Spm10;
	@FXML 
	TableColumn<Table, Integer> Total;
	
	
	//lager listen som skal inneholde dataen
	final ObservableList<Table> data = FXCollections.observableArrayList();

	
	  //Må kunne fylle tabellen med studentens svar
	 
	  public void addInfo() {
			for(Table answer: student.getAnswers()) {
				data.add(answer);	
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
            
        loader.setController(controller); //Legger til kontrolleren i fxmlfilen

        root = (Parent) loader.load();
          //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
	
	
	@FXML
	public void handleLogoutButton() throws IOException {
		Stage stage; 
	    Parent root;
        //get reference to the button's stage         
	    stage=(Stage) Spørsmål.getScene().getWindow();
	    	LoginControllerStudent controller = new LoginControllerStudent();
		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginStudent.fxml"));
    
	    loader.setController(controller);
	    
	    root = (Parent) loader.load();
	    Scene scene_login = new Scene(root);
	    scene_login.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene_login);
        stage.show();
	}
	
	
	// Denne metoden fungerer
	@FXML
	public void handleQuestionButton() throws IOException {
		//Ta meg til mainPage
        System.out.println("Sender studentbruker til questionPage");
        
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) Spørsmål.getScene().getWindow();
        
        QuestionsControllerStudent controller= new QuestionsControllerStudent(this.student);//Lager en kontroller instans

        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionsStudent.fxml"));
            
        loader.setController(controller); //Legger kontrolleren inn i fxmlfilen

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
		Dato.setCellValueFactory(new PropertyValueFactory<Table, String>("Dato"));
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
	    
	    if (student.isAnonymous()==true) {
	    	radioButton.setSelected(true);
	    }
	    
	    setInnloggetStudentLabel();
	    
	    }	
	
	//Ny spørreundersøkelse
	@FXML
	Button newServey;
	@FXML 
	public void handleNewServeyButton() throws SQLException, Exception {
		//Ta meg til side for spørreundersøkelse
       
        
        Stage stage; 
        Parent root;
             
        stage=(Stage) newServey.getScene().getWindow();
       
        ServeyForStudentController controller= new ServeyForStudentController(this.student);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ServeyForStudent.fxml"));
        loader.setController(controller);
        root = (Parent) loader.load();
          
        Scene scene = new Scene(root);
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
	
}

