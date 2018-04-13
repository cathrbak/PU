package tdt4140.gr1835.app.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

import tdt3140.gr1835.app.json.MessageJsonConverter;
import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;
import tdt4140.gr1835.app.database.ConnectionSQL;
import tdt4140.gr1835.app.database.UserDatabaseHandler;
import tdt4140.gr1835.app.webclient.RestClientImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;


public class ConnectionSQLTest {

	UserDatabaseHandler udh;
	Nurse testNurse;
	Student testStudent;
	Table testSurvey;
	Message testMessage;
	
	@Before
	public void setUp() throws SQLException{
		udh =new ConnectionSQL();
		
		testNurse = new Nurse("testNurseUN");
		testNurse.setFaculty("OK");
		testNurse.setEmail("jajaja@gmail.com");
		testNurse.setFirstName("Sos");
		testNurse.setPassword("bla");
		
		
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
		testStudent.setNotat("Dette er et notat");
		
		
		testSurvey = new Table(2,1,2,3,4,5,4,3,2,1,1,20);
		
		
		
		testMessage = new Message(testStudent, testNurse);
		testMessage.setNurse(testNurse);
		testMessage.setReciver(testStudent);
		testMessage.setText("Heisann");
		//Timestamp blir automatisk satt, s� det blir vanskelig � teste. Vet ikke hva jeg skal assertEquals.
	}
	
	@After
	public void tearDown() throws SQLException{
		udh.deleteNurse(testNurse);
		udh.deleteStudent(testStudent);
		//udh.deleteSurvey(testStudent);
		udh.closeConnection();
		udh = null;
		testMessage= null;
		testNurse = null;
		testStudent = null;
		
	}
	
	@Test
	public void testInstance() {
		if (udh==null && testNurse==null && testStudent==null && testSurvey ==null) {
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
		assertEquals("Notat",testStudent.getNotat(), udh.getStudent("testStudentUN").getNotat());

		
		
		try {
			Student dobbeltgjenger = new Student("testStudentUN");
			dobbeltgjenger.setFaculty("MH");
			dobbeltgjenger.setNurse(udh.getNurse("sostertiltester"));
			udh.createNewStudent(dobbeltgjenger);
			
			fail("Prover a legge til bruker som allerede eksisterer med dette brukernavnet. Burde utlose en IllegalStateException");
		}catch (IllegalStateException e) {
			assertTrue(e.getClass().equals(IllegalStateException.class));
		}
		
		udh.deleteStudent(testStudent);
	}
	
	/*@Test
	public void testCreateSurvey() throws SQLException {
		
		udh.createSurvey(testSurvey);
		
		
		try {
			Table duplikat = new Table(2,1,2,3,4,5,4,3,2,1,1,20);
			udh.createSurvey(duplikat);
			
			fail("Prover a legge til undersøkelse som allerede eksisterer med duplikerende ID. Burde utlose en IllegalStateException");
		}catch (IllegalStateException e) {
			assertTrue(e.getClass().equals(IllegalStateException.class));
		}
				
	}
	*/
	
	
	@Test 
	public void testUpdateStudent() throws SQLException {
		udh.createNewStudent(testStudent);
		Student endretStudent=udh.getStudent("testStudentUN");
		endretStudent.setEmail("hei@gmail.com");
		endretStudent.setFaculty("MH");
		endretStudent.setNurse(udh.getNurse("testsoster"));
		endretStudent.setPassword("frans");
		endretStudent.setNotat("Dette er ogsaa et notat");
		udh.updateStudent(endretStudent);
		
		assertEquals("hei@gmail.com",udh.getStudent(endretStudent.getUsername()).getEmail() );
		assertEquals("frans", udh.getStudent(endretStudent.getUsername()).getPassword());
		assertEquals("Dette er ogsaa et notat", udh.getStudent(endretStudent.getUsername()).getNotat());
		udh.deleteStudent(testStudent);
	}
	
	
	@Test
	public void testgetStudents() throws Exception {
		List<Student> expected= new ArrayList<>();
		List<Student> fromDB= new ArrayList<>();
		
		//Henter sostertiltest
		Nurse sostertiltest=udh.getNurse("sostertiltester");
		
		//Oppretter studenter som tilhorer MH fakultetet
		Student s=new Student("haraldmu");
		s.setFaculty("OK");
		s.setNurse(sostertiltest);
		expected.add(s);
		
		Student n=new Student("alexoh");
		n.setFaculty("OK");
		n.setNurse(sostertiltest);
		expected.add(n);
		
		Student j=new Student("petter");
		j.setFaculty("OK");
		j.setNurse(sostertiltest);
		expected.add(j);
		
		//Legger de til i databasen
		udh.createNewStudent(s);
		udh.createNewStudent(n);
		udh.createNewStudent(j);
		
		//Sjekker om jeg kun får ut disse studentene og ikke noen andre
		//assertThat(udh.getStudents(testNurse),is(expected));
		fromDB = udh.getStudents(sostertiltest);
		Student first = expected.get(0);
		Student firstdb = fromDB.get(0);
		Student second = expected.get(1);
		Student seconddb = fromDB.get(1);
		Student third = expected.get(2);
		Student thirddb = fromDB.get(2);
		assertEquals("Sjekker om forste student i begge listene er like", first.getUsername(),firstdb.getUsername());
		assertEquals("Sjekker om andre student i begge listene er like", second.getUsername(),seconddb.getUsername());
		assertEquals("Sjekker om tredje student i begge listene er like", third.getUsername(),thirddb.getUsername());
		udh.deleteStudent(n);
		udh.deleteStudent(s);
		udh.deleteStudent(j);
	}
	
	
	@Test
	public void testCreateNewNurse() throws SQLException {
		udh.createNewNurse(testNurse);	
		
		assertEquals("Brukernavn",testNurse.getUsername(), udh.getNurse("testNurseUN").getUsername());
		assertEquals("Passord",testNurse.getPassword(), udh.getNurse("testNurseUN").getPassword());
		assertEquals("Fornavn",testNurse.getFirstName(), udh.getNurse("testNurseUN").getFirstName());
		assertEquals("Etternavn",testNurse.getSecondName(), udh.getNurse("testNurseUN").getSecondName());
		assertEquals("Fakultet",testNurse.getFaculty(), udh.getNurse("testNurseUN").getFaculty());
		assertEquals("TelefonNr",testNurse.getPhoneNumber(), udh.getNurse("testNurseUN").getPhoneNumber());
		assertEquals("Email",testNurse.getEmail(), udh.getNurse("testNurseUN").getEmail());
		udh.deleteNurse(testNurse);
		
	}
	
	@Test 
	public void testUpdateNurse() throws SQLException {
		udh.createNewNurse(testNurse);
		Nurse endretNurse=udh.getNurse("testNurseUN");
		endretNurse.setPassword("blabla");
		
		udh.updateNurse(endretNurse);
		
		assertEquals("blabla",udh.getNurse(endretNurse.getUsername()).getPassword());
	
		udh.deleteNurse(testNurse);
	}
	
	@Ignore
	@Test
	public void testCreateNewMessage() throws SQLException{
		udh.createNewStudent(testStudent);
		udh.createNewNurse(testNurse);
		udh.createNewMessage(testMessage);
		
		assertEquals("Helsesoster", testMessage.getSender().getUsername(), udh.getMessage(testStudent,testNurse).getSender().getUsername());
		assertEquals("Student", testMessage.getReciver().getUsername(), udh.getMessage(testStudent,testNurse).getReciver().getUsername());
		assertEquals("Tekst", testMessage.getText(), udh.getMessage(testStudent,testNurse).getText());
		
		udh.deleteMessages(testMessage);
		udh.deleteStudent(testStudent);
		udh.deleteNurse(testNurse);
	}
	
	@Ignore
	@Test
	public void testCreateMessageWebServer() throws SQLException {
		Nurse nurse = udh.getNurse("testsoster");
		Student student = udh.getStudent("sverress");
		Message testMessage = new Message(student,nurse);
		testMessage.setText("Heisann");
		udh.createNewMessage(testMessage);
		List<Message> messages = udh.getMessages(student);
		Message dhmessage = messages.stream().filter(m->m.getText().equals("Heisann")).collect(Collectors.toList()).get(0);
		assertEquals(testMessage.getTime(),dhmessage.getTime());
		assertEquals(testMessage.getReciver().getUsername(), dhmessage.getReciver().getUsername());
	}
	
	
	@Test
	public void testGetMessages() throws SQLException{
	
		Message testMessage2 = new Message(testStudent, testNurse);
		testMessage2.setNurse(testNurse);
		testMessage2.setReciver(testStudent);
		testMessage2.setText("2. melding");
		
		udh.createNewStudent(testStudent);
		udh.createNewNurse(testNurse);
		
		udh.createNewMessage(testMessage);
		udh.createNewMessage(testMessage2);
		
		List<Message> expected = new ArrayList<>();
		List<Message> fromdb= new ArrayList<>();
		
		expected.add(testMessage);
		expected.add(testMessage2);
		
		fromdb = udh.getMessages(testStudent);
		
		assertEquals("Sjekker om f�rste melding er samme", expected.get(0).getText(), fromdb.get(0).getText());
		assertEquals("Sjekker om andre melding er samme", expected.get(1).getText(), fromdb.get(1).getText());
		
		udh.deleteMessages(testMessage);
		udh.deleteMessages(testMessage2);
		udh.deleteNurse(testNurse);
		udh.deleteStudent(testStudent);
		
	}
	
	
	

	
}
