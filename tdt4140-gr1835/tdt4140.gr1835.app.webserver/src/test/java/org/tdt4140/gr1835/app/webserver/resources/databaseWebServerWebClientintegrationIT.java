package org.tdt4140.gr1835.app.webserver.resources;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;
import tdt4140.gr1835.app.webclient.RESTClient;
import tdt4140.gr1835.app.webclient.RestClientImp;

public class databaseWebServerWebClientintegrationIT {
	
	RESTClient client;
	
	public databaseWebServerWebClientintegrationIT() {
		client=new RestClientImp();
	}
	
	//GET

	@Test
	public void testGetNurse(){
		Nurse nurse = client.getNurse("testsoster");
		assertEquals("testsoster", nurse.getUsername());
	}
	
	@Test
	public void testGetMessages() {
		List<Message> messagesStudent = client.getMessagesStudent("sverress");
		//messagesStudent.stream().forEach(m->System.out.println(m.getText()));
		assertEquals("sverress", messagesStudent.get(0).getReciver().getUsername());
		assertEquals("Heisann2", messagesStudent.get(3).getText());
	}
	
	@Test
	public void testGetStudent() {
		Student student = client.getStudent("sverress");
		assertEquals("sverress", student.getUsername());
		assertEquals(1, student.getStudentID());
	}
	
	@Test
	public void testGetStudents() {
		List<Student> students = client.getStudents("testsoster");
		students.stream().forEach(s->System.out.println(s.getUsername()));
		assertEquals("Tar kontakt neste uke.", students.get(0).getNotat());
		assertEquals("norak", students.get(1).getUsername());
	}
	
	@Test
	public void testGetAnswers() {
		List<Table> answers = client.getAnswers("sverress");
		answers.stream().forEach(a->System.out.println(a.getSpm3()));
		assertEquals(34, answers.get(0).getTotal());
		assertEquals(5, answers.get(0).getSpm3());
	}
}
