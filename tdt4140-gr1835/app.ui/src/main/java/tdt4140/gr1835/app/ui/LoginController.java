package tdt4140.gr1835.app.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tdt4140.gr1835.app.core.MockingDatabase;
import tdt4140.gr1835.app.core.UserDatabaseHandler;

public class FxAppController {
	
	private UserDatabaseHandler dh;
	
	public FxAppController() {
		//dh= new MockingDatabase();
	}
	
	Button button_login;
	Button button_nybruker;
	Button button_registrer;
	
	
	
	
//	public void handleButton() {
//		//System.out.println("Knapp Trykket");
//		System.out.println("Brukernavn: "+brukernavn.getText());
//		System.out.println("Passord: "+passord.getText());
//		if(!dh.getUser(brukernavn.getText()).equals(null)) {
//			responsLabel.setText("Fant bruker");
//		}else {
//			responsLabel.setText("Knapp Trykket");
//		}
//		responsLabel.setVisible(true);;
//	}
	@FXML
	TextField brukernavn;
	@FXML
	TextField passord;
	@FXML
	Label responsLabel;
	
}