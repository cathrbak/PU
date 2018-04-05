package tdt4140.gr1835.app.ui.nurse;



import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;

public class SendToStudentProfile implements EventHandler<ActionEvent>{
	
	private Nurse nurse;
	private Student student;
	private Hyperlink link;
	
	
	
	public SendToStudentProfile(Nurse nurse, Student student, Hyperlink link) {
		this.nurse = nurse;
		this.student = student;
		this.link=link;
	}



	@Override
	public void handle(ActionEvent event) {
		System.out.println("Sender helsesøster til studentprofil");
		Parent root;
		Stage stage;
		stage=(Stage) link.getScene().getWindow();
		StudentProfileController controller;
			controller = new StudentProfileController(this.nurse, this.student);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentProfil.fxml"));
			loader.setController(controller);
			try {
				root = (Parent) loader.load();
				Scene scene = new Scene(root);
				
				scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}




}
