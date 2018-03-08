package tdt4140.gr1835.app.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestStudent {
	
	Student testStudent;

	@Before
	public void setUp() throws Exception {
		this.testStudent=new Student("testStud");
	}

	@After
	public void tearDown() throws Exception {
		this.testStudent=null;
	}
	
	@Test
	public void testInstace() {
		assertFalse(testStudent==null);
	}
	
	@Test
	public void testSetNurse() {
		Nurse nora=new Nurse("nora");
		nora.setFaculty("IE");
		testStudent.setFaculty("IE");
		testStudent.setNurse(nora);
		assertEquals(nora, testStudent.getNurse());
		try {
			testStudent.setFaculty("AD");
			testStudent.setNurse(nora);
			fail("forventer feil da Nurse og Student må være fra samme fakultet");
		}catch (IllegalStateException e) {
			assertTrue(e.getClass().equals(IllegalStateException.class));
		}
	}
	
	@Test
	public void testsetAnonymus() {
		testStudent.setAnonymous(true);
		assertTrue(testStudent.isAnonymous());
	}
	
	@Test
	public void testSex() { 
		String expected="mann";
		testStudent.setSex(expected);
		assertEquals(expected, testStudent.getSex()); //getSex() -> Personlig favorittmetode
		testStudent.setSex("Mann");
		assertEquals(expected, testStudent.getSex());
		try {
			testStudent.setSex("homse");
			fail("Burde feile etter at vi legger til ugyldig kjønn");
		}catch (IllegalArgumentException e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
	}

}
