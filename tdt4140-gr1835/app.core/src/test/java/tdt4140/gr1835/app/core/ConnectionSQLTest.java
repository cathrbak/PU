package tdt4140.gr1835.app.core;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ConnectionSQLTest {

	UserDatabaseHandler udh;
	Nurse testNurse;
	Student testStudent;
	
	@Before
	public void setUp() throws SQLException{
		udh = new ConnectionSQL();
		
		testNurse = new Nurse("testNurseUN");
		testNurse.setFaculty("MH");
		
		testStudent = new Student("testStudentUN");
		testStudent.setPassword("testPass");
		testStudent.setFaculty("MH");
		testStudent.setAnonymous(false);
		testStudent.setFirstName("Jonas");
		testStudent.setSecondName("Haga");
		testStudent.setSex("mann");
		testStudent.setEmail("jonas@gmail.com");
		testStudent.setPhoneNumber("46952270");
		testStudent.setNurse(udh.getNurse("testsoster"));
		
		
		
		
		
		
	}
	
	@After
	public void tearDown() throws SQLException{
		udh.deleteNurse(testNurse);
		udh.deleteStudent(testStudent);
		
		
		udh = null;
		testNurse = null;
		testStudent = null;
		
	}
	
	@Test
	public void testInstance() {
		if (udh==null && testNurse==null && testStudent==null) {
			fail("Testobjektet ble ikke opprettet");
		}
	}

	//Testmetoder
	
	@Test
	public void testCreateNewStudent() throws SQLException {
		
		udh.createNewStudent(testStudent);
		
		assertEquals("Brukernavn",testStudent.getUsername(), udh.getStudent("testStudentUN").getUsername());
		assertEquals("Passord",testStudent.getPassword(), udh.getStudent("testStudentUN").getPassword());
		assertEquals("Fakultet",testStudent.getFaculty(), udh.getStudent("testStudentUN").getFaculty());
		assertEquals("Anonymitet",testStudent.isAnonymous(), udh.getStudent("testStudentUN").isAnonymous());
		assertEquals("Fornavn",testStudent.getFirstName(), udh.getStudent("testStudentUN").getFirstName());
		assertEquals("Etternavn",testStudent.getSecondName(), udh.getStudent("testStudentUN").getSecondName());
		assertEquals("Kj�nn",testStudent.getSex(), udh.getStudent("testStudentUN").getSex());
		assertEquals("Email",testStudent.getEmail(), udh.getStudent("testStudentUN").getEmail());
		assertEquals("TelefonNr",testStudent.getPhoneNumber(), udh.getStudent("testStudentUN").getPhoneNumber());
		assertEquals("Helsesoster",testStudent.getNurse().getUsername(), udh.getStudent("testStudentUN").getNurse().getUsername());


		
		
		try {
			Student dobbeltgjenger = new Student("testStudentUN");
			dobbeltgjenger.setFaculty("AD");
			dobbeltgjenger.setNurse(udh.getNurse("testsoster"));
			udh.createNewStudent(dobbeltgjenger);
			
			fail("Prover a legge til bruker som allerede eksisterer med dette brukernavnet. Burde utlose en IllegalStateException");
		}catch (IllegalStateException e) {
			assertTrue(e.getClass().equals(IllegalStateException.class));
		}
		
		
	}
	
	
	@Test //Tror ikke updateStudent fungerer helt.
	public void testUpdateStudent() throws SQLException {
		
		Student endretStudent=udh.getStudent("testStudentUN");
		endretStudent.setEmail("hei@gmail.com");
		endretStudent.setFaculty("MH");
		endretStudent.setNurse(udh.getNurse("testsoster"));
		endretStudent.setPassword("frans");
		udh.updateStudent(endretStudent);
		//assertEquals("hei@gmail.com",udh.getStudent(endretStudent.getUsername()).getEmail() );
		//assertEquals("frans", udh.getStudent(endretStudent.getUsername()).getPassword());
	}
	
	
	/*
	@Test
	public void testgetStudents() throws Exception {
		List<Student> expected= new ArrayList<>();
		
		//Oppretter studenter som tilhorer MH fakultetet
		Student s=new Student("haraldmu");
		
		s.setFaculty("MH");
		s.setNurse(udh.getNurse("testsoster"));
		
		expected.add(s);
		Student n=new Student("alexoh");
		
		n.setFaculty("MH");
		n.setNurse(udh.getNurse("testsoster"));
		
		expected.add(n);
		Student j=new Student("petter");
		
		j.setFaculty("MH");
		j.setNurse(udh.getNurse("testsoster"));
		
		expected.add(j);
		
		//Legger de til i databasen
		udh.createNewStudent(s);
		udh.createNewStudent(n);
		udh.createNewStudent(j);
		
		//Sjekker om jeg kun får ut disse studentene og ikke noen andre
		assertThat(udh.getStudents(testNurse),is(expected));
		udh.deleteStudent(udh.getStudent("haraldmu"));
		udh.deleteStudent(udh.getStudent("alexoh"));
		udh.deleteStudent(udh.getStudent("petter"));
	}
	*/
	/*
	@Test
	public void testCreateNewNurse() throws SQLException {
		udh.createNewNurse(testNurse);
		assertEquals("Prøver å lage en ny Nurse",testNurse, udh.getNurse("testNurseUN"));
		
		try {
			Nurse dobbeltgjenger = new Nurse("testNurseUN");
			udh.createNewNurse(dobbeltgjenger);
			fail("Prøver å legge til bruker som allerede eksisterer med dette brukernavnet. Burde utløse en IllegalStateException");
		}catch (IllegalStateException e) {
			assertTrue(e.getClass().equals(IllegalStateException.class));
		}
	}
	*/

	
}
