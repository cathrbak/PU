package tdt4140.gr1835.app.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	//Første forsøk med TDD
	
	private User testUser;

	@Before
	public void setUp() throws Exception {
		System.out.println("Setup");
		testUser=new User("testUser");
	}
	
	@Test
	public void testNewSimpleUser() {
		System.out.println("testNewSimpleUser");
		assertFalse(testUser==null);
		assertEquals("testUser", testUser.getUsername());
	}
	
	@Test
	public void testSetPassword() {
		System.out.println("testSetPassword");
		String expected="jonaserhomo";
		testUser.setPassword(expected);
		assertEquals(expected, testUser.getPassword());
		try {
			testUser.setPassword("");
			fail("prøver å sette tomt passord. Skal ikke være mulig");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testSetFirstName() {
		System.out.println("testSetFirstName");
		String expected="Sverre";
		try {
			testUser.setFirstName(expected);
			assertEquals(expected, testUser.getFirstName());
			testUser.setFirstName("sverre");
			assertEquals(expected, testUser.getFirstName());
			testUser.setFirstName("sVerre");
			assertEquals(expected, testUser.getFirstName());
		}catch (IllegalArgumentException e) {
			assertFalse("Fikk IllegalArgumentException etter å sette inn gyldig argument",e.getClass().equals(IllegalArgumentException.class));
		}
		try {
			testUser.setFirstName("Sv12asd");
			fail("Prøver å legge til fornavn med tall i, skal ikke gå");
		}catch (IllegalArgumentException e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testSetSecondName() {
		System.out.println("testSetSecondName");
		String expected="Sverre";
		try {
			testUser.setSecondName(expected);
			assertEquals(expected, testUser.getSecondName());
			testUser.setSecondName("sverre");
			assertEquals(expected, testUser.getSecondName());
			testUser.setSecondName("sVerre");
			assertEquals(expected, testUser.getSecondName());
		}catch (IllegalArgumentException e) {
			assertFalse("Fikk IllegalArgumentException etter å sette inn gyldig argument",e.getClass().equals(IllegalArgumentException.class));
		}
		try {
			testUser.setSecondName("Sv12asd");
			fail("Prøver å legge til fornavn med tall i, skal ikke gå");
		}catch (IllegalArgumentException e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testSetFaculty() {
		System.out.println("testSetFaculty");
		try {
			String expected="OK";
			testUser.setFaculty(expected);
			assertEquals("Feilet etter setFaculty(OK)",expected, testUser.getFaculty());
			testUser.setFaculty("ok");
			assertEquals("Feilet etter at man prøvde å sette inn med små bokstaver",expected, testUser.getFaculty());
			testUser.setFaculty("ik");
			fail("Test burde feilet etter at vi prøver å sette et fakultet som ikke eksisterer");
			
		}catch (IllegalArgumentException e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
		
	}
	
	@After
	public void tearDown() {
		System.out.println("tearDown");
		testUser=null;
	}


	

}
