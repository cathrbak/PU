package tdt4140.gr1835.app.ui.nurse;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
//import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tdt4140.gr1835.app.ui.GitLab_CI_Setup;


public class Test_Login extends ApplicationTest {
	
	@BeforeClass
	public static void headless() {
		System.out.println("Starter Test Login");
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
	public void testOfflineInlogging() {
		System.out.println("testTestsosterGyldigInlogging");
		clickOn("#brukernavn");
		write("offline");
		push(KeyCode.TAB);
		write(" ");
		clickOn("#button_login");
		
		FxAssert.verifyThat("#Profile", hasText("Profil")); //Finner profilknappen
	}
	@Ignore
	@Test
	public void testUgyldigBrukernavnInlogging() {
		
		clickOn("#brukernavn");
		write("sverre");
		push(KeyCode.TAB);
		write("sss");
		clickOn("#button_login");
		
		assertEquals(controller.responsLabel.getText(),"Brukeren eksisterer ikke i våre systemer");
	}
	@Test
	public void testUgyldigpassordInlogging() {
		clickOn("#brukernavn");
		write("offline");
		push(KeyCode.TAB);
		write("asd");
		clickOn("#button_login");
		
		assertEquals(controller.responsLabel.getText(),"Brukeren finnes, men passordet er feil");
	}
	@Ignore
	@Test
	public void testTomtPassordfelt() {
		clickOn("#brukernavn");
		write("offline");
		clickOn("#button_login");
		
		assertEquals(controller.responsLabel.getText(),"Du må skrive inn et passord");
	}
}
