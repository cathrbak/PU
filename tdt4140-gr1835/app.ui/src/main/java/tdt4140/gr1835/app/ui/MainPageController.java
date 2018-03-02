package tdt4140.gr1835.app.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainPageController implements Initializable {
	
	@FXML
	Menu meny;
	@FXML
	MenuItem Profile;
	
	@FXML
	MenuItem Settings;
	
	@FXML
	MenuItem logOut;
	
	
	@FXML
	public void handleProfileMenuItem(ActionEvent event) throws IOException {
		Stage stage; 
		Parent root;
	    
        //get reference to the button's stage         
        stage=(Stage) Profile.getOnAction();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Profil.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
	
	 @Override
	    public void initialize(URL url, ResourceBundle rb) {
	        // TODO
	    }
	
	

}
