package tdt4140.gr1835.app.ui;

import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertTrue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class FxAppTest extends ApplicationTest {
	
	Button btn;
	
	@BeforeClass
	public static void headless() {
		if (Boolean.valueOf(System.getProperty("gitlab-ci", "false"))) {
			System.setProperty("prism.verbose", "true"); // optional
			System.setProperty("java.awt.headless", "true");
			System.setProperty("testfx.robot", "glass");
			System.setProperty("testfx.headless", "true");
			System.setProperty("glass.platform", "Monocle");
			System.setProperty("monocle.platform", "Headless");
			System.setProperty("prism.order", "sw");
			System.setProperty("prism.text", "t2k");
			System.setProperty("testfx.setup.timeout", "2500");
		}
	}

	@Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	/*

    @Test
    public void testNyBrukerInnlogging() {
    	
    //Klikker meg over til Opprett ny bruker vinduet
    	//Man bruker fx:id identitikatoren fra FXML filen inne i clickOn metoden. 
    		clickOn("#button_nybruker");
    		
    	//Trykker videre på first name tekstboksen, fyller inn, og bruker tab for å komme til neste felt.
    		clickOn("#firstName");
    		write("Sverre");
    		push(KeyCode.TAB);
    		write("Spetalen");
    		push(KeyCode.TAB);
    		write("sverress");
    		push(KeyCode.TAB);
    		write("sss");
    		push(KeyCode.TAB);
    		write("sss");
    		push(KeyCode.TAB);
    		write("sverress@stud.ntnu.no");
    		push(KeyCode.TAB);
    		write("sverress@stud.ntnu.no");
    		push(KeyCode.TAB);
    		write("46839737");
    		push(KeyCode.TAB);
    		write("IE");
    		
    	//Prøver å lage ny bruker
    		clickOn("#button_registrer");
    	

    	    assertTrue(true);
    }
    */
    

}