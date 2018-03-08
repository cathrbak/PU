package tdt4140.gr1835.app.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.Table;

public class MainPageController implements Initializable {
				
	
	@FXML
	Button Profile;
	@FXML
	Button Logout;

	@FXML
	Button Question;
	
	@FXML 
	TableView<Table> tableID;
	@FXML 
	TableColumn<Table, Integer> PersonID;
	@FXML 
	TableColumn<Table, Integer> Spm1;
	@FXML 
	TableColumn<Table, Integer> Spm2;
	@FXML 
	TableColumn<Table, Integer> Spm3;
	@FXML 
	TableColumn<Table, Integer> Spm4;
	@FXML 
	TableColumn<Table, Integer> Spm5;
	@FXML 
	TableColumn<Table, Integer> Spm6;
	@FXML 
	TableColumn<Table, Integer> Spm7;
	@FXML 
	TableColumn<Table, Integer> Spm8;
	@FXML 
	TableColumn<Table, Integer> Spm9;
	@FXML 
	TableColumn<Table, Integer> Spm10;
	@FXML 
	TableColumn<Table, Integer> Total;
	
	
	public int idNumber = 1;
	public int total = 0;
	
	
	//@FXML 
	//private int answer1; 
	
	//lager tabell-data
	final ObservableList<Table> data = FXCollections.observableArrayList(
			new Table(idNumber++, 5, 2,9,7,5,8,3,8,9,10,total), 
			new Table(idNumber++, 5, 2,9,7,5,8,3,8,9,10,total), 
			new Table(idNumber++, 5, 2,9,7,5,8,3,8,9,10,total), 
			new Table(idNumber++, 5, 2,9,7,5,8,3,8,9,10,total),
			new Table(idNumber++, 5, 2,9,7,5,8,3,8,9,10,total),
			new Table(idNumber++, 5, 2,9,7,5,8,3,8,9,10,total),
			new Table(idNumber++, 5, 2,9,7,5,8,3,8,9,10,total));
	

	
	@FXML
	public void handleProfileButton() throws IOException {
		Stage stage; 
	    Parent root;
        //get reference to the button's stage         
        stage=(Stage) Profile.getScene().getWindow();
        
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
	
	
	@FXML
	public void handleLogoutButton() throws IOException {
		Stage stage; 
	    Parent root;
        //get reference to the button's stage         
        stage=(Stage) Logout.getScene().getWindow();
        
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
      	//create a new scene with root and set the stage
        Scene scene = new Scene(root);
        //Legger på css stylesheetet
        scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	public void handleQuestionButton() throws IOException {
		Stage stage;
		Parent root;
		//get reference to the button's stage
		stage=(Stage) Question.getScene().getWindow();
		
		// load up OTHER FXML document
		root = FXMLLoader.load(getClass().getResource("Questions.fxml"));
		// create a new scene with root and set the stage
		Scene scene = new Scene(root);
		//Legger på css stylesheetet
		scene.getStylesheets().add(FxApp.class.getResource("stylesheet.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		PersonID.setCellValueFactory(new PropertyValueFactory<Table, Integer>("PersonID"));
		Spm1.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm1"));
		Spm2.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm2"));
		Spm3.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm3"));
		Spm4.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm4"));
		Spm5.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm5"));
		Spm6.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm6"));
		Spm7.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm7"));
		Spm8.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm8"));
		Spm9.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm9"));
		Spm10.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Spm10"));
		Total.setCellValueFactory(new PropertyValueFactory<Table, Integer>("Total"));
	    tableID.setItems(data);
	    }
	
//legg til data og tabellen vil bli oppdatert
	/*public void onAddItem(ActionEvent event) {
		Table entry = new Table(idNumber, 4);
		idNumber ++;
		//legg til data i tabellen
		data.add(entry);
		
	}*/
	
	

	

}
