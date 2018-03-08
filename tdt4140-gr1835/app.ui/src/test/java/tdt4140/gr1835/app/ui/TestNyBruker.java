package tdt4140.gr1835.app.ui;

import org.junit.BeforeClass;
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


public class TestNyBruker extends ApplicationTest {
	
	Button btn;
	
	Label infotext;
	
	@BeforeClass
	public static void headless() {
		GitLab_CI_Setup.init();
	}

	@Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@Test
	public void testFeilBrukerInnlogging() {
		
	// Klikker meg over til Opprett ny bruker vinduet
	// Man bruker fx:id identifikatoren fra FXML filen inne i clickOn metoden
		clickOn("#button_nybruker");
	
	// Trykker videre på first name tekstboksen, fyller inn, og bruker tab for å komme til neste felt
	// Feil inntasting
	    	clickOn("#firstName");
		write("N0ra");
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
		
		// prøver å lage ny ugyldig bruker
		clickOn("#button_registrer");
		
		try {
			FxAssert.verifyThat("#infotext", hasText("Kan ikke opprette ugyldig bruker"));
		}
		catch (IllegalArgumentException e){
			assertFalse("Fikk IllegalArgumentException etter å sette inn gyldig argument",e.getClass().equals(IllegalArgumentException.class));
		}
		clickOn("#firstName");
		push(KeyCode.LEFT);
		push(KeyCode.LEFT);
		push(KeyCode.BACK_SPACE);
		write("o");
		clickOn("#button_registrer");
		
		FxAssert.verifyThat("#infotext", hasText("Det eksisterer en bruker med dette brukernavnet"));
	}
	

    @Test
    public void testNyBrukerKorrektInnlogging() {
    	
    //Klikker meg over til Opprett ny bruker vinduet
    	//Man bruker fx:id identitikatoren fra FXML filen inne i clickOn metoden. 
    		clickOn("#button_nybruker");
    		
    	//Trykker videre på first name tekstboksen, fyller inn, og bruker tab for å komme til neste felt.
    	// Riktig inntasting 
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
    		
    	//Prøver å lage ny gyldig bruker
    		clickOn("#button_registrer");
    	
    		try {
    			FxAssert.verifyThat("#infotext", hasText("Det eksisterer en bruker med dette brukernavnet"));
    		}
    		catch (IllegalArgumentException e){
    			assertFalse("Fikk IllegalArgumentException etter å sette inn gyldig argument",e.getClass().equals(IllegalArgumentException.class));
    		}
    }
 
    
    

}