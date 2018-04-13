package tdt4140.gr1835.app.core;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tdt4140.gr1835.app.database.MockingDatabase;
import tdt4140.gr1835.app.database.UserDatabaseHandler;

import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class UserDatabaseHandlerTest {

	UserDatabaseHandler udh;
	Nurse testNurse;
	Student testStudent;
	@Before
	public void setUp() throws Exception {
		udh = new MockingDatabase();
		testNurse= new Nurse("carl");
		testStudent= new Student("hanskristian");
		testNurse.setFaculty("AD");
	}

	@After
	public void tearDown() throws Exception {
		udh =null;
		testNurse= null;
		testStudent= null;
	}

	@Test
	public void testInstance() {
		if (udh==null && testNurse==null && testStudent==null) {
			fail("Testobjektet ble ikke opprettet");
		}
	}
	
	//Testmetoder for studenter
	/*
	@Test
	public void testCreateNewStudent() throws SQLException {
		udh.createNewStudent(testStudent);
		assertEquals("Prøver å lage en ny Nurse",testStudent, udh.getStudent("hanskristian"));
		
		try {
			Student hkdobbeltgjenger = new Student("hanskristian");
			udh.createNewStudent(hkdobbeltgjenger);
			fail("Prøver å legge til bruker som allerede eksisterer med dette brukernavnet. Burde utløse en IllegalStateException");
		}catch (IllegalStateException e) {
			assertTrue(e.getClass().equals(IllegalStateException.class));
		}
	}
	*/
	/*
	 * testUpdateStudent()
	 * Denne testen forutsetter at man har lagt inn en Student i databasen med brukernavn sverress
	 */
	@Test
	public void testUpdateStudent() throws SQLException {
		Student endretStudent=udh.getStudent("sverress");
		endretStudent.setEmail("sverre.s.spetalen@gmail.com");
		udh.updateStudent(endretStudent);
		assertEquals("sverre.s.spetalen@gmail.com",udh.getStudent("sverress").getEmail() );
	}
	
	/*
	 * Tester avhengighet til faktultet også :)
	 */
	/*
	@Test
	public void testgetStudents() throws Exception {
		List<Student> expected= new ArrayList<>();
		
		//Oppretter studenter som tilhører AD fakultetet
		Student s=new Student("haraldmu");
		s.setPassword("marin");
		s.setFirstName("Harald");
		s.setFaculty("AD");
		expected.add(s);
		Student n=new Student("alexoh");
		n.setPassword("ngk");
		n.setFaculty("AD");
		expected.add(n);
		Student j=new Student("petter");
		j.setPassword("jh");
		j.setFaculty("AD");
		expected.add(j);
		
		//Legger de til i databasen
		udh.createNewStudent(s);
		udh.createNewStudent(n);
		udh.createNewStudent(j);
		
		//Sjekker om jeg kun får ut disse studentene og ikke noen andre
		assertThat(udh.getStudents(testNurse),is(expected));
		
		
	}
	*/
	//Testmetoder for Helsesøstere
	
	/*
	 * testUpdateNurse()
	 * Denne testen forutsetter at man har lagt inn en Nurse i databasen med brukernavn cathrine
	 */
	@Test
	public void testUpdateNurse() throws SQLException {
		Nurse endretNurse=udh.getNurse("cathrine");
		endretNurse.setEmail("cathrine@gmail.com");
		udh.updateNurse(endretNurse);
		assertEquals("cathrine@gmail.com",udh.getNurse("cathrine").getEmail() );
	}
	/*
	@Test
	public void testCreateNewNurse() throws SQLException {
		udh.createNewNurse(testNurse);
		assertEquals("Prøver å lage en ny Nurse",testNurse, udh.getNurse("carl"));
		
		try {
			Nurse carldobbeltgjenger = new Nurse("carl");
			udh.createNewNurse(carldobbeltgjenger);
			fail("Prøver å legge til bruker som allerede eksisterer med dette brukernavnet. Burde utløse en IllegalStateException");
		}catch (IllegalStateException e) {
			assertTrue(e.getClass().equals(IllegalStateException.class));
		}
	}
	*/
	
	

}
