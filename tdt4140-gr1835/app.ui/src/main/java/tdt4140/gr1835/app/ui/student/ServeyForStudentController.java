package tdt4140.gr1835.app.ui.student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;
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
	private RadioButton AB11;
	@FXML
	private RadioButton AB12;
	@FXML
	private RadioButton AB13;
	@FXML
	private RadioButton AB14;
	@FXML
	private RadioButton AB15;
	@FXML
	private RadioButton AB21;
	@FXML
	private RadioButton AB22;
	@FXML
	private RadioButton AB23;
	@FXML
	private RadioButton AB24;
	@FXML
	private RadioButton AB25;
	@FXML
	private RadioButton AB31;
	@FXML
	private RadioButton AB32;
	@FXML
	private RadioButton AB33;
	@FXML
	private RadioButton AB34;
	@FXML
	private RadioButton AB35;
	@FXML
	private RadioButton AB41;
	@FXML
	private RadioButton AB42;
	@FXML
	private RadioButton AB43;
	@FXML
	private RadioButton AB44;
	@FXML
	private RadioButton AB45;
	@FXML
	private RadioButton AB51;
	@FXML
	private RadioButton AB52;
	@FXML
	private RadioButton AB53;
	@FXML
	private RadioButton AB54;
	@FXML
	private RadioButton AB55;
	
	
	
	ArrayList<RadioButton> buttons = new ArrayList<>();
	ArrayList<Integer> IntAns = new ArrayList<>();
	
	public void addToList() {
		buttons.clear();
		buttons.add(AB11);
		buttons.add(AB12);
		buttons.add(AB13);
		buttons.add(AB14);
		buttons.add(AB15);
		buttons.add(AB21);
		buttons.add(AB22);
		buttons.add(AB23);
		buttons.add(AB24);
		buttons.add(AB25);
		buttons.add(AB31);
		buttons.add(AB32);
		buttons.add(AB33);
		buttons.add(AB34);
		buttons.add(AB35);
		buttons.add(AB41);
		buttons.add(AB42);
		buttons.add(AB43);
		buttons.add(AB44);
		buttons.add(AB45);
		buttons.add(AB51);
		buttons.add(AB52);
		buttons.add(AB53);
		buttons.add(AB54);
		buttons.add(AB55);
		
		
	}
	
	
	
	public void handleSendAnswerButton() throws SQLException, Exception {
		addToList();
		IntAns.clear();
		for (RadioButton b : buttons) {
			if (b.isSelected()) {
				System.out.println(b.getId());
				String a = b.getId();
				char c = a.charAt(a.length()-1);
				int i = Character.getNumericValue(c);
				IntAns.add(i);
				
			}
			if (IntAns.size() != 1) {
				System.out.println("feil antall");
		} else {
			int sum = 0;
			for (int i = 0; i<10;i++) {
				sum += IntAns.get(i);
			}
			IntAns.add(sum);
			IntAns.add(0, this.student.getStudentID());
			Table answer = new Table(IntAns.get(0), IntAns.get(1), IntAns.get(2), 
					IntAns.get(3), IntAns.get(4), IntAns.get(5), IntAns.get(6),IntAns.get(7), IntAns.get(8), IntAns.get(9), IntAns.get(10), IntAns.get(11));
			student.addAnswer(answer);
			System.out.println(answer);
			handleBackButton();
		}
			
		}
			
			
		
	}

	
	
}
