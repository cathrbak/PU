package tdt4140.gr1835.app.ui.nurse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.ui.GitLab_CI_Setup;

public class TestMessage extends ApplicationTest{
	
	Button button;
	Student student;
	Nurse nurse;
	
	@BeforeClass
	public static void headless() {
		GitLab_CI_Setup.init();
	}
	
	Stage stage;
	MessageController controller;

	@Override
    public void start(Stage stage) throws Exception {
		this.stage=stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
        MessageController controller=new MessageController(nurse, student); //kjører i offlinemodus
        loader.setController(controller);
        this.controller = controller;
        Parent root=loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@Test
	public void testMessage() {
		clickOn("#textbox");
		write("Hei. Kunne vi tatt en prat neste uke?");
		assertEquals(controller.textbox.getText(), "Hei. Kunne vi tatt en prat neste uke?");
	}
	
	
	@Test
	public void testHandleTextboxChange() {
		clickOn("#textbox");
		write("aaaaabbbbbcccccdddddeeeeeQwertyuiopåasdfghjkløæzxcQwertyuiopåasdfghjkløæzxc"
				+ "aaaaabbbbbcccccdddddeeeeeQwertyuiopåasdfghjkløæzxcQwertyuiopåasdfghjkløæzxc"
				+ "aaaaabbbbbcccccdddddeeeeeQwertyuiopåasdfghjkløæzxcQwertyuiopåasdfghjkløæzxc"
				+ "aaaaabbbbbcccccdddddeeeeeefew");	
		assertEquals(controller.MessageResponse.getText(), "Meldingen er for lang. Kan ikke overgå 250 tegn.");
	}

}
