package tdt4140.gr1835.app.json;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;

import junit.framework.Assert;
import tdt3140.gr1835.app.json.JsonConverterService;
import tdt3140.gr1835.app.json.ListOfMessagesConverter;
import tdt3140.gr1835.app.json.ListOfStudentConverter;
import tdt3140.gr1835.app.json.ListOfTableConverter;
import tdt3140.gr1835.app.json.MessageJsonConverter;
import tdt3140.gr1835.app.json.NurseJsonConverter;
import tdt3140.gr1835.app.json.StudentJsonConverter;
import tdt3140.gr1835.app.json.TableJsonConverter;
import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;
import tdt4140.gr1835.app.core.TableTest;

public class JsonConverterServiceTest {
	
	private Student testStudent;
	private Nurse testNurse;
	private Message testMessage;
	private Table testTable;
	private List<Student> studentList=new ArrayList<>();
	private List<Table> tableList=new ArrayList<>();
	
	private List<Message> messageList=new ArrayList<>();
	private long currentTimeMillis;
	private Table simpletable;
	
	
	@SuppressWarnings("deprecation")
	public JsonConverterServiceTest() {
		testStudent=new Student("sverress");
		testStudent.setPassword("sss");
		testStudent.setFirstName("Sverre");
		testStudent.setSecondName("Spetalen");
		testStudent.setFaculty("IE");
		studentList.add(testStudent);
		Student n=new Student("norak");
		n.setPassword("ngk");
		n.setFirstName("Nora");
		n.setSecondName("Kallager");
		n.setFaculty("IE");
		studentList.add(n);
		Student j=new Student("jonash");
		j.setPassword("jh");
		j.setFirstName("Jonas");
		j.setSecondName("Haga");
		j.setFaculty("IE");
		studentList.add(j);
		
		testNurse= new Nurse("cathrine");
		testNurse.setPassword("c");
		testNurse.setFirstName("Cathrine");
		testNurse.setSecondName("Akre");
		testNurse.setFaculty("IE");
		testStudent.setNurse(testNurse);
		n.setNurse(testNurse);
		
		List<Integer> svarliste0=Arrays.asList(1,2,3,4,2,4,3,3,4,4);
		List<Integer> svarliste1=Arrays.asList(1,1,3,4,2,3,1,4,2,4);
		List<Integer> svarliste2=Arrays.asList(1,4,3,4,3,3,3,2,1,2);
		
		testTable=new Table(12,1,2,3,4,2,4,3,3,4,4,svarliste0.stream().collect(Collectors.summingInt(i->i)));
		currentTimeMillis = System.currentTimeMillis();
		testTable.setTstamp(new Timestamp(currentTimeMillis));
		this.simpletable = new Table(11,1,1,3,4,2,3,1,4,2,4,svarliste1.stream().collect(Collectors.summingInt(i->i)));
		Table table2=new Table(1,1,4,3,4,3,3,3,2,1,2,svarliste2.stream().collect(Collectors.summingInt(i->i)));
		Table table3=new Table(2,1,4,3,4,3,3,3,2,1,2,svarliste2.stream().collect(Collectors.summingInt(i->i)));
		
		tableList.add(table3);
		tableList.add(table2);
		tableList.add(simpletable);
		tableList.add(testTable);
		

		testMessage=new Message(testStudent, testNurse);
		testMessage.setText("Hello mr!");
		testMessage.setMessageID(1);
		testMessage.setTime(new Timestamp(System.currentTimeMillis()));
		testMessage.setReciver(testStudent);
		testMessage.setNurse(testNurse);
		Message message1=new Message(n, testNurse);
		message1.setText("Heihei");
		Message message2=new Message(n, testNurse);
		Message message3=new Message(n, testNurse);
		Message message4=new Message(n, testNurse);
		message2.setText("Heihei");
		message3.setText("Heihei");
		message4.setText("Heihei");
		messageList.add(message4);
		messageList.add(message3);
		messageList.add(message2);
		messageList.add(message1);
		messageList.add(testMessage);
	}
	
	@Test
	public void testNurse() {
		JsonConverterService<Nurse> service = new NurseJsonConverter();
		String json=service.convertToJason(testNurse);
		assertEquals(testNurse.getUsername(), service.convertToObject(json).getUsername());
	}

	@Test
	public void testStudent() {
		JsonConverterService<Student> service = new StudentJsonConverter();
		String json=service.convertToJason(testStudent);
		assertEquals(testStudent.getPassword(), service.convertToObject(json).getPassword());
		assertEquals(testStudent.getUsername(), service.convertToObject(json).getUsername());
	}
	
	@Test
	public void teststudentList() {
		JsonConverterService<List<Student>> service = new ListOfStudentConverter();
		String json=service.convertToJason(studentList);
		assertEquals(studentList.get(0).getUsername(), service.convertToObject(json).get(0).getUsername());
		assertEquals(studentList.get(1).getFaculty(), service.convertToObject(json).get(1).getFaculty());
	}
	
	@Test
	public void testTable() {
		JsonConverterService<Table> service = new TableJsonConverter();
		String json=service.convertToJason(testTable);
		assertEquals(testTable.getPersonID(), service.convertToObject(json).getPersonID());
		assertEquals(testTable.getSpm1(), service.convertToObject(json).getSpm1());
		assertEquals(testTable.getSpm10(), service.convertToObject(json).getSpm10());
		assertEquals(testTable.getTotal(), service.convertToObject(json).getTotal());
		assertEquals(testTable.getTstamp(), service.convertToObject(json).getTstamp());
	}
	@Test
	public void testSimpleTable() {
		JsonConverterService<Table> service = new TableJsonConverter();
		String json=service.convertToJason(simpletable);
		assertEquals(simpletable.getPersonID(), service.convertToObject(json).getPersonID());
		assertEquals(simpletable.getSpm1(), service.convertToObject(json).getSpm1());
		assertEquals(simpletable.getSpm10(), service.convertToObject(json).getSpm10());
		assertEquals(simpletable.getTotal(), service.convertToObject(json).getTotal());
		assertEquals(simpletable.getTstamp(), service.convertToObject(json).getTstamp());
	}
	
	@Test
	public void testListTable() {
		JsonConverterService<List<Table>> service = new ListOfTableConverter();
		String json=service.convertToJason(tableList);
		assertEquals(tableList.get(3).getPersonID(), service.convertToObject(json).get(3).getPersonID());
		assertEquals(tableList.get(2).getSpm2(), service.convertToObject(json).get(2).getSpm2());
		assertEquals(tableList.get(0).getTotal(), service.convertToObject(json).get(1).getTotal());
	}
	
	@Test
	public void testFullMessage() {
		JsonConverterService<Message> converter= new MessageJsonConverter();
		String json=converter.convertToJason(testMessage);
		System.out.println();
		assertEquals(testMessage.getText(),converter.convertToObject(json).getText());
		assertEquals(testMessage.getTime(),converter.convertToObject(json).getTime());
		assertEquals(testMessage.getReciver().getUsername(),converter.convertToObject(json).getReciver().getUsername());
		assertEquals(testMessage.getSender().getUsername(),converter.convertToObject(json).getSender().getUsername());
	}
	
	@Test
	public void testSimpleMessage() {
		JsonConverterService<Message> converter= new MessageJsonConverter();
		Message message = new Message(testStudent,testNurse);
		System.out.println(message.getTime());
		String json=converter.convertToJason(message);
		System.out.println(json);
		assertEquals(message.getText(),converter.convertToObject(json).getText());
		assertEquals(message.getTime(),converter.convertToObject(json).getTime());
		assertEquals(message.getReciver().getUsername(),converter.convertToObject(json).getReciver().getUsername());
		assertEquals(message.getSender().getUsername(),converter.convertToObject(json).getSender().getUsername());
	}
	
	@Test
	public void testListMessage() {
		JsonConverterService<List<Message>> converter= new ListOfMessagesConverter();
		String json=converter.convertToJason(messageList);
		assertEquals(messageList.get(2).getText(),converter.convertToObject(json).get(2).getText());
		assertEquals(messageList.get(messageList.size()-1).getTime(),converter.convertToObject(json).get(messageList.size()-1).getTime());
	}

}
