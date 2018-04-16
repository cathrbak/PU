package tdt4140.gr1835.app.ui.student;

import org.junit.BeforeClass;
import org.junit.Ignore;
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
import tdt4140.gr1835.app.ui.GitLab_CI_Setup;
import tdt4140.gr1835.app.ui.nurse.LoginController;


public class Test_LoginStudent extends ApplicationTest {
	
	@BeforeClass
	public static void headless() {
		GitLab_CI_Setup.init();
	}
	
	Stage stage;
	LoginControllerStudent controller;

	@Override
    public void start(Stage stage) throws Exception {
		this.stage=stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginStudent.fxml"));
        LoginControllerStudent controller=new LoginControllerStudent(true); //kjører i offlinemodus
        loader.setController(controller);
        this.controller=controller;
        Parent root=loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@Test
	public void testGyldigInlogging() {
		clickOn("#studentbrukernavn");
		write("offline");
		push(KeyCode.TAB);
		write(" ");
		clickOn("#studentbutton_login");
		
		FxAssert.verifyThat("#Logout", hasText("Logg ut")); //Finner profilknappen
	}
	
	@Test
	public void testUgyldigBrukernavnInlogging() {
		
		clickOn("#studentbrukernavn");
		write("sverre");
		push(KeyCode.TAB);
		write("");
		clickOn("#studentbutton_login");
		
		assertEquals(controller.responsLabel.getText(),"Denne studenten finnes ikke i våre systemer");
	}
	@Test
	public void testUgyldigpassordInlogging() {
		clickOn("#studentbrukernavn");
		write("offline");
		push(KeyCode.TAB);
		write("asd");
		clickOn("#studentbutton_login");
		
		assertEquals(controller.responsLabel.getText(),"Brukeren finnes, men passordet er feil");
	}
	
	@Test
	public void testTomtPassordfelt() {
		clickOn("#studentbrukernavn");
		write("offline");
		clickOn("#studentbutton_login");
		
		assertEquals(controller.responsLabel.getText(),"Du må skrive inn et passord");
	}
}

