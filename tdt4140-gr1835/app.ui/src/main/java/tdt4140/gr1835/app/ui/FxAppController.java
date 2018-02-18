package tdt4140.gr1835.app.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FxAppController {
	
	public void handleButton() {
		//System.out.println("Knapp Trykket");
		System.out.println(brukernavn.getText());
	}
	@FXML
	TextField brukernavn;
	
}