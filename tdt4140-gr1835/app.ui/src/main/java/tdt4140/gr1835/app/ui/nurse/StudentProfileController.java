package tdt4140.gr1835.app.ui.nurse;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;
import tdt4140.gr1835.app.database.ConnectionSQL;
import tdt4140.gr1835.app.database.UserDatabaseHandler;


public class StudentProfileController implements Initializable{
	
	private Nurse nurse;
	private Student student;
	
	public StudentProfileController(Nurse nurse, Student student) {
		this.nurse = nurse;
		this.student = student;
		System.out.println(student.getUsername()+" er logget på med søster: "+nurse.getUsername());
		addInfo();
	}
	
	//Setter label om innlogget helsesøster
	@FXML
	Label InnloggetNurseLabel;
	
	@FXML
	public void setInnloggetNurseLabel() {
		InnloggetNurseLabel.setText("Logget inn som: " + nurse.getUsername() );
	}
	
	//lager listen som skal inneholde dataen
	final ObservableList<Table> data = FXCollections.observableArrayList();
	
	@FXML 
	TableView<Table> tableID;
	@FXML 
	TableColumn<Table, String> Dato;
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
	
	//Legger til data i tabellen:
	public void addInfo() {
		List<Table> listOfAnswers = student.getAnswers();
			for(Table answer: listOfAnswers) {
				data.add(answer);	
				}
		}
		
	@FXML
	Button returnButton;
	
	@FXML
	public void handleReturnButton() throws IOException{
		//Ta meg til mainPage
        System.out.println("Sender bruker til mainPage");
        
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) returnButton.getScene().getWindow();
        
        MainPageController controller= new MainPageController(this.nurse); //Lager en kontroller instans

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
	
	@FXML
	Button sendMessageButton;
	
	@FXML
	public void handleSendMessageButton(){
		
		// Ta meg til meldingssiden (Message)
        System.out.println("Sender bruker til meldingsvindu");
        
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) returnButton.getScene().getWindow();
        
        MessageController controller= new MessageController(this.nurse, this.student); //Lager en kontroller instans

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
            
        loader.setController(controller); //Smeller den kontrolleren inn i fxmlfilen
        try {
			root = (Parent) loader.load();
	        //create a new scene with root and set the stage 
	        Scene scene = new Scene(root);
	       
	        //Legger på css stylesheetet
	        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
	        stage.setScene(scene);
	        stage.show();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//setter infoen til studenten
	@FXML
	Label EpostLabel, TelefonLabel, FakultetLabel;
	@FXML
	Text NavnText;	
				
	public void setNavnText(){
		if (student.isAnonymous()) {
			NavnText.setText("Denne studenten er anonym");
		}
		else {
			NavnText.setText(student.getFirstName() + " " + student.getSecondName());
		}
	}	
		
	//henter epost
	public void setEpostLabel() {
		if (student.isAnonymous()) {
			EpostLabel.setText("anonym@stud.ntnu.no");
		}
		else {
			EpostLabel.setText(student.getEmail());			
			}
	}	
		
	public void setTelefonLabel() {
		if (student.isAnonymous()) {
			TelefonLabel.setText("Ikke oppgitt");
		}
		TelefonLabel.setText(student.getPhoneNumber());
	}	
				
	public void setFakultetLabel() {
		if (student.isAnonymous()) {
			FakultetLabel.setText("Ukjent");
		}
		FakultetLabel.setText(student.getFaculty());
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
	    
	    
	    //setter teksten i alle labelene
	    setInnloggetNurseLabel();
	    setNavnText();
		setTelefonLabel();
		setEpostLabel();
		setFakultetLabel();
		
		//henter frem notatet som skal vises på studentprofilen
		notat.setText(student.getNotat());
	    }	
	
	@FXML
	TextArea notat;
	
	
	@FXML
	public void handleEditButton() {
		//gjør det mulig å skrive i tekstfeltet
		notat.setEditable(true);
	}
	
	@FXML
	public void handleSaveButton() {
		student.setNotat(notat.getText());
		//legger inn notatet i databasen
		UserDatabaseHandler database = new ConnectionSQL();
		database.updateNote(student);
		
		//hindrer at det er mulig å skrive i teksfeltet uten å trykke rediger
		notat.setEditable(false);
	}
	
}
