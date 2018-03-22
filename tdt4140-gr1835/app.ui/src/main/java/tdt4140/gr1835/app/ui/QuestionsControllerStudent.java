package tdt4140.gr1835.app.ui;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import tdt4140.gr1835.app.core.Student;


public class QuestionsControllerStudent {
	private Student student;
	public QuestionsControllerStudent(Student student) {
		this.student = student;
	}
	
	@FXML
	Button returnButton;
	
	@FXML
	public void handleReturnButton() throws SQLException, Exception{
		//Ta meg til mainPage
        System.out.println("Sender bruker til mainPageStudent");
        
        Stage stage; 
        Parent root;
        //get reference to the button's stage        
        stage=(Stage) returnButton.getScene().getWindow();
        
        MainPageControllerStudent studentcontroller= new MainPageControllerStudent(this.student);//Lager en kontroller instans

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPageStudent.fxml"));
            
            loader.setController(studentcontroller); //Smeller den kontrolleren inn i fxmlfilen

            root = (Parent) loader.load();
          //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}

}
