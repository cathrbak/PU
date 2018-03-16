package tdt4140.gr1835.app.ui;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.Nurse;

public class MessageController {

	private Nurse nurse;

	public MessageController(Nurse nurse) {
		this.nurse = nurse;
	}
	
	@FXML
	Button Button_send;
	
	@FXML
	public void handleButton_send() throws SQLException, Exception{
		//Ta meg til mainPage
        System.out.println("Sender bruker til mainPage");
        
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) Button_send.getScene().getWindow();
        
        MessageController controller= new MessageController(this.nurse); //Lager en kontroller instans

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
	Button returnButton;
	
	@FXML
	public void handleReturnButton() throws SQLException, Exception{
		//Ta meg til mainPage
        System.out.println("Sender bruker til mainPage");
        
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) returnButton.getScene().getWindow();
        
        MainPageController controller= new MainPageController(this.nurse); //Lager en kontroller instans

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
}
