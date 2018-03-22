package tdt4140.gr1835.app.ui.nurse;

import java.sql.SQLException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.ConnectionSQL;
import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.UserDatabaseHandler;

public class MessageController {

	private Nurse nurse;
	private Student student;
	private Message message;

	
	public MessageController(Nurse nurse, Student student) {
		this.nurse = nurse;
		this.student = student;
	}


	@FXML
	Button Button_send;
	
	@FXML
	TextArea textbox;
	
	@FXML
	public void handleButton_Send() throws SQLException, Exception{
		message = new Message(student, nurse);
		message.setText(textbox.getText());
		//legger inn meldingen i databasen
		UserDatabaseHandler database = new ConnectionSQL();
		database.createNewMessage(message);
		String melding = database.getMessage(student, nurse).getText();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Melding sendt!");
		alert.setHeaderText(null);
		alert.setContentText("Følgende melding ble sendt til " + student.getFirstName() + " " + student.getSecondName()+ ":\n" + melding);
		alert.showAndWait();
		textbox.setText("");
		
//		//Ta meg til profilside
//        System.out.println("Sender bruker til Profilside");
//        
//        Stage stage; 
//        Parent root;
//        //get reference to the button's stage        
//        stage=(Stage) Button_send.getScene().getWindow();
//        
//        MessageController controller= new MessageController(this.nurse, this.student); //Lager en kontroller instans
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentProfil.fxml"));
//            
//        loader.setController(controller); //Smeller den kontrolleren inn i fxmlfilen
//        root = (Parent) loader.load();
//        
//        //create a new scene with root and set the stage 
//        Scene scene = new Scene(root);
//       
//        //Legger på css stylesheetet
//        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
//        stage.setScene(scene);
//        stage.show();
	}
	
	@FXML
	Button returnButton;
	
	@FXML
	public void handleReturnButton() throws SQLException, Exception{
		//Ta meg til Studentprofil
        System.out.println("Sender bruker til Profil");
        
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) returnButton.getScene().getWindow();
        
        StudentProfileController controller = new StudentProfileController(this.nurse, this.student); //Lager en kontroller instans

        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentProfil.fxml"));
            
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
	Label messageRespons;
	
	public void handleTextboxChange() {   			//metode for å vise felt til bruker om at melding er for lang,
		messageRespons.setVisible(false);			//samt sette meldingsteksten til å være det brukeren skriver inn.
		if(textbox.getText().length() > 250) {
			textbox.setText("Meldingen er for lang. Kan ikke overgå 255 tegn.");
			textbox.setVisible(true);
		}
	}

	
}
