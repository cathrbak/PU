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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;

public class ConnectionSQLTest {

	ConnectionSQL udh;
	Nurse testNurse;
	Student testStudent;
	Student testSurveyStudent;
	Table testSurvey;
	Message testMessage;
	
	@Before
	public void setUp() throws SQLException{
		udh =new ConnectionSQL();
		
		testNurse = new Nurse("testNurseUN");
		testNurse.setFaculty("IV");
		testNurse.setEmail("jajaja@gmail.com");
		testNurse.setFirstName("Sos");
		testNurse.setPassword("bla");
		
		
		
		testStudent = new Student("testStudentUN");
		testStudent.setPassword("testPass");
		testStudent.setFaculty("IV");
		testStudent.setAnonymous(false);
		testStudent.setFirstName("Jonas");
		testStudent.setSecondName("Haga");
		testStudent.setSex("mann");
		testStudent.setEmail("jonas@gmail.com");
		testStudent.setPhoneNumber("46952270");
		testStudent.setNurse(testNurse);
		testStudent.setNotat("Dette er et notat");
		
		testSurveyStudent = new Student("testStudentSUR");
		testSurveyStudent.setPassword("surveypass");
		testSurveyStudent.setFaculty("IV");
		testSurveyStudent.setAnonymous(false);
		testSurveyStudent.setFirstName("Hans");
		testSurveyStudent.setSecondName("Ovanger");
		testSurveyStudent.setSex("mann");
		testSurveyStudent.setEmail("hans@gmail.com");
		testSurveyStudent.setPhoneNumber("46952270");
		testSurveyStudent.setNurse(testNurse);
		testSurveyStudent.setNotat("Dette er et notat");
		

		

        
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
		udh.deleteStudent(testSurveyStudent);
		udh.closeConnection();
		
		
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
		
		
		udh.createNewNurse(testNurse);
		testStudent.setNurse(udh.getNurse("testNurseUN"));
		udh.createNewStudent(testStudent);
		
		testStudent.setStudentID(udh.getStudentID(testStudent));
		assertEquals("ID",testStudent.getStudentID(), udh.getStudent("testStudentUN").getStudentID());
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
			dobbeltgjenger.setFaculty("IV");
			dobbeltgjenger.setNurse(udh.getNurse("sostertiltester"));
			udh.createNewStudent(dobbeltgjenger);
			
			fail("Prover a legge til bruker som allerede eksisterer med dette brukernavnet. Burde utlose en IllegalStateException");
		}catch (IllegalStateException e) {
			assertTrue(e.getClass().equals(IllegalStateException.class));
		}
		
		
		udh.deleteStudent(testStudent);
		udh.deleteNurse(testNurse);
	}
	
	
	@Test
    public void testCreateSurvey() throws SQLException {
        
        List<Table> expected = new ArrayList<>();
        List<Table> fromDB = new ArrayList<>();
        
        udh.createNewNurse(testNurse);
		testStudent.setNurse(udh.getNurse("testNurseUN"));
		udh.createNewStudent(testStudent);
        int id = udh.getStudentID(testStudent);
        testStudent.setStudentID(id);
        Table testSurvey = new Table(id,1,2,3,4,5,4,3,2,1,1,26);
        expected.add(testSurvey);
        
//        //Legger inn en ny unders�kelse for personID lik min til testSurveyStudent
        udh.createSurvey(testSurvey);
        
//        //Sjekker om unders�kelsen er den eneste for denne studenten.
        fromDB = udh.getAnswers(testStudent);
        
        
        assertEquals("Sjekker om f�rste spm. er like i begge", fromDB.get(0).getSpm1(), expected.get(0).getSpm1());
        assertEquals("Sjekker om andre spm. er like i begge", fromDB.get(0).getSpm1(), expected.get(0).getSpm1());
        assertEquals("Sjekker om femte spm. er like i begge", fromDB.get(0).getSpm1(), expected.get(0).getSpm1());
        assertEquals("Sjekker om totalen er lik i begge", fromDB.get(0).getTotal(), expected.get(0).getTotal());
        
        udh.deleteSurvey(testStudent);
        udh.deleteNurse(testNurse);
        udh.deleteStudent(testStudent);
    }
	
	@Test
	public void testgetStudents() throws Exception {
		List<Student> expected= new ArrayList<>();
		List<Student> fromDB= new ArrayList<>();
		
		//Henter sostertiltest
		Nurse sostertiltest=udh.getNurse("sostertiltester");
		System.out.println(sostertiltest.getNurseID());
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
	public void testUpdateStudent() throws SQLException {
		
		
		udh.createNewNurse(testNurse);
		testStudent.setNurse(udh.getNurse("testNurseUN"));
		
		udh.createNewStudent(testStudent);
		
		Student endretStudent=udh.getStudent("testStudentUN");
		endretStudent.setEmail("hei@gmail.com");
		endretStudent.setFaculty("IV");
		
		endretStudent.setPassword("frans");
		endretStudent.setNotat("Dette er ogsaa et notat");
		udh.updateStudent(endretStudent);
		
		assertEquals("hei@gmail.com",udh.getStudent(endretStudent.getUsername()).getEmail() );
		assertEquals("frans", udh.getStudent(endretStudent.getUsername()).getPassword());
		assertEquals("Dette er ogsaa et notat", udh.getStudent(endretStudent.getUsername()).getNotat());
		udh.deleteStudent(testStudent);
		udh.deleteNurse(testNurse);
	}
	
	
		
	@Test
	public void testCreateNewNurse() throws SQLException {
		udh.createNewNurse(testNurse);	
		
		testNurse.setNurseID(udh.getNurseID(testNurse));
		assertEquals("ID",testNurse.getNurseID(), udh.getNurse("testNurseUN").getNurseID());
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
	
	/*
	 * ikke tenk på disse to testene, de blir ikke kjørt. Men sikkert mulig å ta litt inspirasjon av dem
	 */
	
	
	@Test
	public void testCreateNewMessage() throws SQLException{
		udh.createNewNurse(testNurse);
		testStudent.setNurse(udh.getNurse("testNurseUN"));
		udh.createNewStudent(testStudent);
		udh.createNewMessage(testMessage);
		
		int studentid = udh.getStudentID(testStudent);
        testStudent.setStudentID(studentid);
        int nurseid = udh.getNurseID(testNurse);
        testNurse.setNurseID(nurseid);
		
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
		
		udh.createNewNurse(testNurse);
		testStudent.setNurse(udh.getNurse("testNurseUN"));
		udh.createNewStudent(testStudent);
		
		udh.createNewMessage(testMessage);
		udh.createNewMessage(testMessage2);
		
		int studentid = udh.getStudentID(testStudent);
        testStudent.setStudentID(studentid);
        int nurseid = udh.getNurseID(testNurse);
        testNurse.setNurseID(nurseid);
        
		
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
