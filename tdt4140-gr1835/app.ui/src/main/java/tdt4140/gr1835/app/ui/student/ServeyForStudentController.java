package tdt4140.gr1835.app.ui.student;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.ui.nurse.FxApp;

public class ServeyForStudentController {

	private Student student;
		
		public ServeyForStudentController(Student student) {
			this.student = student;
		}
	
	//Tilbakeknapp
			@FXML
			Button BackButton;
			@FXML 
			public void handleBackButton() throws SQLException, Exception {
				//Ta meg til mainPage
	           
	            
	            Stage stage; 
	            Parent root;
	            //get reference to the button's stage        
	            stage=(Stage) BackButton.getScene().getWindow();
	           
	            MainPageControllerStudent controller= new MainPageControllerStudent(this.student);
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPageStudent.fxml"));
	            loader.setController(controller);
	            root = (Parent) loader.load();
	              
	            Scene scene = new Scene(root);
	            scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
	            stage.setScene(scene);
	            stage.show();
			}
	
	
	@FXML
	public void ansewerServey() {
		// Group
		ToggleGroup group1 = new ToggleGroup();
		 
		// Radio 1
		RadioButton button1 = new RadioButton("1");
		button1.setToggleGroup(group1);
		 
		// Radio 2
		RadioButton button2 = new RadioButton("2");
		button2.setToggleGroup(group1);
		
		// Radio 1
		RadioButton button3 = new RadioButton("3");
		button3.setToggleGroup(group1);
		
		// Radio 1
		RadioButton button4 = new RadioButton("4");
		button4.setToggleGroup(group1);
		
		// Radio 1
		RadioButton button5 = new RadioButton("5");
		button5.setToggleGroup(group1);
		
	}
	
	
}
