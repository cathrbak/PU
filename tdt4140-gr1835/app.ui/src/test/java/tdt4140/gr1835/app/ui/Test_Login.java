package tdt4140.gr1835.app.ui;

import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

//import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.*;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class Test_Login extends ApplicationTest {
	
	@BeforeClass
	public static void headless() {
		GitLab_CI_Setup.init();
	}
	
	Stage stage;

	@Override
    public void start(Stage stage) throws Exception {
		this.stage=stage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@Test
	public void testGyldigInlogging() {
		clickOn("#brukernavn");
		write("testsoster");
		push(KeyCode.TAB);
		write("testpass");
		clickOn("#button_login");
		
		FxAssert.verifyThat("#Profile", hasText("Profil")); //Finner profilknappen
	}
	@Test
	public void testUgyldigInlogging() {
		
		//Prøver ugyldig bruker
		clickOn("#brukernavn");
		write("sverre");
		push(KeyCode.TAB);
		write("sss");
		clickOn("#button_login");
		
		FxAssert.verifyThat("#responsLabel", hasText("Denne brukeren eksisterer ikke i databasen")); //Sjekker om vi får riktig respons
		
		//Prover gyldig bruker med feil passord
		clickOn("#brukernavn");
		write("ss");
		push(KeyCode.TAB);
		write("ss");
		clickOn("#button_login");
		
		FxAssert.verifyThat("#responsLabel", hasText("Brukeren finnes, men passordet er feil")); //Sjekker om vi får riktig respons
		
		
	}
}
