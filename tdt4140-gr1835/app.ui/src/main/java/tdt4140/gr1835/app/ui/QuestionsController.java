package tdt4140.gr1835.app.ui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class QuestionsController {
	
	@FXML
	Button returnButton;
	
	@FXML
	public void handleReturnButton() throws IOException{
		Stage stage;
		Parent root;
		//get reference to the button's stage         
        stage=(Stage) returnButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

	}

}
