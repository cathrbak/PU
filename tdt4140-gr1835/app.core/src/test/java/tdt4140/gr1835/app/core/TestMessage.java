package tdt4140.gr1835.app.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMessage {

	//må teste at meldingen ikke er tom
	//må teste at meldingen har en tilhørende Nurse
	//må teste at meldingen har en tilhørende mottaker (Student)
	//må teste at tidspunkt er i riktig format
	
	Message testMessage;
	Student testStud;
	Nurse testNurse;

	
	@Before
	public void setUp() throws Exception {
		testStud = new Student("Sverre");
		testNurse = new Nurse("Nora");
		this.testMessage=new Message(testStud, testNurse);
	}

	@After
	public void tearDown() throws Exception {
		this.testMessage=null;
		this.testNurse = null;
		this.testStud = null;
	}
	
	@Test
	public void testMessageText() {
		System.out.println("testMessageText");
		String expected="HeiPåDeg!";
		testMessage.setText(expected);
		assertEquals(expected, testMessage.getText());
		try {
			testMessage.setText("");
			fail("Prøver å sende en tom melding. Skal ikke være mulig.");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
		try {
			testMessage.setText("QwertyuiopåasdfghjkløæzxcQwertyuiopåasdfghjkløæzxc"
					+ "QwertyuiopåasdfghjkløæzxcQwertyuiopåasdfghjkløæzxcQwertyuiopåasdfghjkløæzxc"
					+"QwertyuiopåasdfghjkløæzxcQwertyuiopåasdfghjkløæzxcQwertyuiopåasdfghjkløæzxc"
					+ "QwertyuiopåasdfghjkløæzxcQwertyuiopåasdfghjkløæzxchei");
			fail("Prøver å sende en alt for lang melding. Skal ikke være mulig.");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testReciver() {
		System.out.println("testReciver");
		assertFalse(testStud==null);
		assertEquals(testStud, testMessage.getReciver());
	}
	
	@Test
	public void testSender() {
		System.out.println("testSender");
		assertFalse(testNurse==null);
		assertEquals(testNurse, testMessage.getSender());
	}
	
}
