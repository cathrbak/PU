package tdt4140.gr1835.app.ui.nurse;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

//import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.*;
import static org.hamcrest.CoreMatchers.*;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import tdt4140.gr1835.app.database.ConnectionSQL;
import tdt4140.gr1835.app.database.UserDatabaseHandler;
import tdt4140.gr1835.app.ui.GitLab_CI_Setup;


public class TestNyBruker extends ApplicationTest {
	
	Button btn;
	
	Label infotext;
	
	@BeforeClass
	public static void headless() {
		System.out.println("Starter Test Ny Bruker");
		GitLab_CI_Setup.init();
	}


	Stage stage;
	LoginController controller;

	@Override
    public void start(Stage stage) throws Exception {
		this.stage=stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        LoginController controller=new LoginController(true); //kjører i offlinemodus
        loader.setController(controller);
        this.controller=controller;
        Parent root=loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@Test
	public void testErrorMessages() {
		clickOn("#button_nybruker");
		
		clickOn("#firstName");
		write("Nora1");
		FxAssert.verifyThat("#firstNameRespons", hasText("Fornavn skal kun inneholde bokstaver"));
		
		push(KeyCode.TAB);
		write("Kallager1");
		FxAssert.verifyThat("#familyNameRespons", hasText("Etternavn skal kun inneholde bokstaver"));

		push(KeyCode.TAB);
		write("norak1");
		
		push(KeyCode.TAB);
		write("hallo");
		push(KeyCode.TAB);
		write("hallo");
		
		push(KeyCode.TAB);
		write("norak@");
		FxAssert.verifyThat("#emailRespons", hasText("Ugyldig email"));
		write("stud");
		push(KeyCode.TAB);
		write("norak@");
		FxAssert.verifyThat("#repeatEmailRespons", hasText("Emailene er ikke like"));
		write("stud");
		
		push(KeyCode.TAB);
//		write("12345678");

		push(KeyCode.TAB);
		write("NN");
		FxAssert.verifyThat("#facultyRespons", hasText("Dette fakultetet eksisterer ikke"));
		
		clickOn("#backButton");
	}
	
//	@Test
//	public void testValidUser() {
//		clickOn("#button_nybruker");
//		
//		clickOn("#firstName");
//		write("Janine");
//		push(KeyCode.TAB);
//		write("Stang");
//		push(KeyCode.TAB);
//		write("janines");
//		
//		push(KeyCode.TAB);
//		write("hallo");
//		push(KeyCode.TAB);
//		write("hallo");
//		
//		push(KeyCode.TAB);
//		write("janine@stud");
//
//		push(KeyCode.TAB);
//		write("janine@stud");
//		
//		push(KeyCode.TAB);
//		write("98765432");
//
//		push(KeyCode.TAB);
//		write("VM");
//
//		clickOn("#button_register");
//	}
}