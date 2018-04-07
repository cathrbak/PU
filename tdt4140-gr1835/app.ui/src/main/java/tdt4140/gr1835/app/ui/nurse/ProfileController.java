package tdt4140.gr1835.app.ui.nurse;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.Nurse;

public class ProfileController implements Initializable{
	private Nurse nurse;
	public ProfileController(Nurse nurse) {
		this.nurse = nurse;
		
	}

	//Tilbakeknapp
		@FXML
		Button BackToMainButton;
		@FXML 
		public void handleBackToMainButton() throws SQLException, Exception {
			//Ta meg til mainPage
           
            
            Stage stage; 
            Parent root;
            //get reference to the button's stage        
            stage=(Stage) BackToMainButton.getScene().getWindow();
            
            
            
            MainPageController controller= new MainPageController(this.nurse);//Lager en kontroller instans

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
	
		// Labels for fullt navn for innlogget helsesøster
		@FXML
		Label fulltnavn;

		// Metode for å sette fullt navn
		@FXML
		public void setFulltnavnLabel() {
			fulltnavn.setText(nurse.getFirstName() + " " + nurse.getSecondName());
		}
		

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// kaller på Label-metodene
			setFulltnavnLabel();
			
		}
	

		
	
}
