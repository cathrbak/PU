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


public class Test_LoginStudent extends ApplicationTest {
	
	@BeforeClass
	public static void headless() {
		GitLab_CI_Setup.init();
	}
	
	Stage stage;

	@Override
    public void start(Stage stage) throws Exception {
		this.stage=stage;
        Parent root = FXMLLoader.load(getClass().getResource("LoginStudent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@Test
	public void testGyldigInlogging() {
		clickOn("#studentbrukernavn");
		write("sverress");
		push(KeyCode.TAB);
		write("testpass");
		clickOn("#studentbutton_login");
		
		FxAssert.verifyThat("#Profile", hasText("Profil")); //Finner profilknappen
	}
	@Test
	public void testUgyldigInlogging() {
		
		//Prøver ugyldig bruker
		clickOn("#studentbrukernavn");
		write("sver");
		push(KeyCode.TAB);
		write("s");
		clickOn("#studentbutton_login");
		
		FxAssert.verifyThat("#responsLabel", hasText("Ugyldig bruker")); //Sjekker om vi får riktig respons
		
		//Prover gyldig bruker med feil passord - funker
		clickOn("#studentbrukernavn");
		write("ress");
		push(KeyCode.TAB);
		write("ss");
		clickOn("#studentbutton_login");
		
		FxAssert.verifyThat("#responsLabel", hasText("Brukeren finnes, men passordet er feil")); //Sjekker om vi får riktig respons
		
		
	}
}

