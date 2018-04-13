package tdt4140.gr1835.app.ui.nurse;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1835.app.ui.GitLab_CI_Setup;

public class BigIntegrationTest extends ApplicationTest {
	
	/*
	 *  Denne testen lager ny bruker, logger inn med ny bruker, går inn på meldinger, 
	 */
	
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
	
	@Ignore
	@Test
	public void test() {
		assertTrue(true);
	}

}
