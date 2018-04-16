package tdt4140.gr1835.app.ui.student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;
import tdt4140.gr1835.app.database.ConnectionSQL;
import tdt4140.gr1835.app.database.UserDatabaseHandler;
import tdt4140.gr1835.app.ui.nurse.FxApp;
import tdt4140.gr1835.app.webclient.RESTClient;
import tdt4140.gr1835.app.webclient.RestClientImp;

public class ServeyForStudentController {

	RESTClient database;
	private Student student;
		public ServeyForStudentController(Student student) {
			this.student = student;
			this.database=new RestClientImp();
		}
	
		//Infotext
		@FXML
		Label infoText;
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
	
	//oppretter alle mulige svarknapper
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
	@FXML
	private RadioButton AB61;
	@FXML
	private RadioButton AB62;
	@FXML
	private RadioButton AB63;
	@FXML
	private RadioButton AB64;
	@FXML
	private RadioButton AB65;
	@FXML
	private RadioButton AB71;
	@FXML
	private RadioButton AB72;
	@FXML
	private RadioButton AB73;
	@FXML
	private RadioButton AB74;
	@FXML
	private RadioButton AB75;
	@FXML
	private RadioButton AB81;
	@FXML
	private RadioButton AB82;
	@FXML
	private RadioButton AB83;
	@FXML
	private RadioButton AB84;
	@FXML
	private RadioButton AB85;
	@FXML
	private RadioButton AB91;
	@FXML
	private RadioButton AB92;
	@FXML
	private RadioButton AB93;
	@FXML
	private RadioButton AB94;
	@FXML
	private RadioButton AB95;
	@FXML
	private RadioButton AB101;
	@FXML
	private RadioButton AB102;
	@FXML
	private RadioButton AB103;
	@FXML
	private RadioButton AB104;
	@FXML
	private RadioButton AB105;
	
	
	
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
		buttons.add(AB61);
		buttons.add(AB62);
		buttons.add(AB63);
		buttons.add(AB64);
		buttons.add(AB65);
		buttons.add(AB71);
		buttons.add(AB72);
		buttons.add(AB73);
		buttons.add(AB74);
		buttons.add(AB75);
		buttons.add(AB81);
		buttons.add(AB82);
		buttons.add(AB83);
		buttons.add(AB84);
		buttons.add(AB85);
		buttons.add(AB91);
		buttons.add(AB92);
		buttons.add(AB93);
		buttons.add(AB94);
		buttons.add(AB95);
		buttons.add(AB101);
		buttons.add(AB102);
		buttons.add(AB103);
		buttons.add(AB104);
		buttons.add(AB105);
	}
	
	//oppretter en ny spørreundersøkelse som sendes til database
	//for deretter å sende brukeren til hovedsiden igjen.
	public void handleSendAnswerButton() throws SQLException, Exception {
		addToList();
		IntAns.clear();
	
		for (RadioButton b : buttons) {
			if (b.isSelected()) {
				
				String a = b.getId();
				char c = a.charAt(a.length()-1);
				int i = Character.getNumericValue(c);
				IntAns.add(i);
				
			}
		}
			if (IntAns.size() != 10) {
				System.out.println("feil antall");
				infoText.setText("Vennligst svar på alle spm");
				infoText.setVisible(true);
		} else {
			int sum = 0;
			for (int i = 0; i<10;i++) {
				sum+= IntAns.get(i);
				
		}
			IntAns.add(sum);
			IntAns.add(0, student.getStudentID());
			System.out.println(this.student.toString());
			Table answer = new Table(IntAns.get(0), IntAns.get(1), IntAns.get(2), 
					IntAns.get(3), IntAns.get(4), IntAns.get(5), IntAns.get(6),IntAns.get(7), 
					IntAns.get(8), IntAns.get(9), IntAns.get(10), IntAns.get(11));
			
			System.out.println(answer);
			boolean ok = database.addNewSurvey(student.getUsername(),answer);
			if(!ok) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Feilmelding");
				alert.setHeaderText(null);
				alert.setContentText("Kunne ikke legge til undersøkelse. Prøv igjen!");
				alert.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Opprettet");
				alert.setHeaderText(null);
				alert.setContentText("Fullført! Takk for at du svarte på spørreundersøkelsen!");
				alert.showAndWait();
			}
			handleBackButton();
		}
			
		}
			
	}


