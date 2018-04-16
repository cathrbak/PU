package org.tdt4140.gr1835.app.webserver.resources;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
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
		messagesStudent.stream().forEach(m->System.out.println(m.getText()));
		assertEquals("sverress", messagesStudent.get(0).getReciver().getUsername());
	}
	
	@Test
	public void testGetStudent() {
		Student student = client.getStudent("sverress");
		assertEquals("sverress", student.getUsername());
	}
	
	

}
