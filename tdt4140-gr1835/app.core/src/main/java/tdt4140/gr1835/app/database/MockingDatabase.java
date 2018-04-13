package tdt4140.gr1835.app.database;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;

public class MockingDatabase implements UserDatabaseHandler{
	
	private Student student;
	private Nurse nurse;
	private Message message;
	private Table answer;
	private static List<Student> students;
	private static List<Table> answers;
	private static List<Message> messages;
	private static List<Nurse> nurses;
	
	public MockingDatabase() {
		this.students = new ArrayList<>();
		this.answers = new ArrayList<>();
		this.messages = new ArrayList<>();
		this.nurses = new ArrayList<>();
		init();
	}
	
	
	public void init() {
		this.student=new Student("sverress");
		student.setPassword("sss");
		student.setFirstName("Sverre");
		student.setSecondName("Spetalen");
		student.setFaculty("IE");
		students.add(student);
		Student n=new Student("norak");
		n.setPassword("ngk");
		n.setFirstName("Nora");
		n.setSecondName("Kallager");
		n.setFaculty("IE");
		students.add(n);
		Student j=new Student("jonash");
		j.setPassword("jh");
		j.setFirstName("Jonas");
		j.setSecondName("Haga");
		j.setFaculty("IE");
		students.add(j);
		
		nurse= new Nurse("cathrine");
		nurse.setPassword("c");
		nurse.setFirstName("Cathrine");
		nurse.setSecondName("Akre");
		nurse.setFaculty("IE");
		student.setNurse(nurse);
		n.setNurse(nurse);
		
		List<Integer> svarliste0=Arrays.asList(1,2,3,4,2,4,3,3,4,4);
		List<Integer> svarliste1=Arrays.asList(1,1,3,4,2,3,1,4,2,4);
		List<Integer> svarliste2=Arrays.asList(1,4,3,4,3,3,3,2,1,2);
		
		answer=new Table(12,1,2,3,4,2,4,3,3,4,4,svarliste0.stream().collect(Collectors.summingInt(i->i)));
		Table table1=new Table(11,1,1,3,4,2,3,1,4,2,4,svarliste1.stream().collect(Collectors.summingInt(i->i)));
		Table table2=new Table(1,1,4,3,4,3,3,3,2,1,2,svarliste2.stream().collect(Collectors.summingInt(i->i)));
		Table table3=new Table(2,1,4,3,4,3,3,3,2,1,2,svarliste2.stream().collect(Collectors.summingInt(i->i)));
		
		answers.add(table3);
		answers.add(table2);
		answers.add(table1);
		answers.add(answer);
		

		message=new Message(student, nurse);
		message.setText("Hello mr!");
		message.setMessageID(1);
		message.setTime(new Timestamp(System.currentTimeMillis()));
		message.setReciver(student);
		message.setNurse(nurse);
		Message message1=new Message(n, nurse);
		message1.setText("Heihei");
		Message message2=new Message(n, nurse);
		Message message3=new Message(n, nurse);
		Message message4=new Message(n, nurse);
		message2.setText("Heihei");
		message3.setText("Heihei");
		message4.setText("Heihei");
		messages.add(message4);
		messages.add(message3);
		messages.add(message2);
		messages.add(message1);
		messages.add(message);
	}


	@Override
	public void closeConnection() throws SQLException {
		
	}


	@Override
	public void createNewNurse(Nurse nurse) throws SQLException {
		nurses.add(nurse);
	}


	@Override
	public void updateNurse(Nurse nurse) throws SQLException {
		
	}


	@Override
	public Nurse getNurse(String username) throws SQLException {
		return nurse;
	}


	@Override
	public void deleteNurse(Nurse nurse) throws SQLException {
		nurses.remove(nurse);
	}


	@Override
	public void createNewStudent(Student student) throws SQLException {
		students.add(student);
		
	}


	@Override
	public Student getStudent(String username) throws SQLException {
		// TODO Auto-generated method stub
		return student;
	}


	@Override
	public void updateStudent(Student student) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteStudent(Student student) throws SQLException {
		students.remove(student);
		
	}


	@Override
	public void createSurvey(Table survey) throws SQLException {
		answers.add(survey);
		
	}


	@Override
	public void deleteSurvey(Student student) throws SQLException {
		answers.remove(student);
		
	}


	@Override
	public List<Student> getStudents(Nurse nurse) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return students;
	}


	@Override
	public List<Table> getAnswers(Student student) throws SQLException {
		// TODO Auto-generated method stub
		return answers;
	}


	@Override
	public int getStudentID(Student student) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void deleteMessages(Message message) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void createNewMessage(Message message) throws SQLException {
		messages.add(message);
		
	}


	@Override
	public Message getMessage(Student student, Nurse nurse) throws SQLException {
		// TODO Auto-generated method stub
		return message;
	}


	@Override
	public List<Message> getMessages(Student student) throws SQLException {
		// TODO Auto-generated method stub
		return messages;
	}


	@Override
	public Message getMessageFromID(Integer messageid) throws SQLException {
		// TODO Auto-generated method stub
		return message;
	}


	@Override
	public int getNurseID(Nurse nurse) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Student getStudentFromID(int studentID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
