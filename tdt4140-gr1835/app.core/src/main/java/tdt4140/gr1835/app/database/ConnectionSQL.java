package tdt4140.gr1835.app.database;

import java.util.List;

import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


//Man kan ikke opprette en student uten � fylle inn b�de fakultet og helses�ster.

public class ConnectionSQL implements UserDatabaseHandler{

	
	//ConnectionSQL må vite om AbstractSQLHandler slik at disse metodene kan ligge i AbstractHandler.
	private final String dbURL="jdbc:mysql://mysql.stud.ntnu.no/jonahag_prosjektdb?user=jonahag_pu35&password=gruppe35&useSSL=false";
 
	
	private Connection getConnection() throws SQLException{
		return DriverManager.getConnection(dbURL);
	}
	
	public void closeConnection() throws SQLException {
		getConnection().close();
	}
	
	public Statement getStatement() throws SQLException{
		Connection conn = getConnection();
		return conn.createStatement();
	}
	
	// gjør slik at studentHandler og nurseHandler vet om hverandre.
	private StudentHandler studentHandler = new StudentHandler();
	private NurseHandler nurseHandler = new NurseHandler();
	{
		nurseHandler.setStudentHandler(studentHandler);
		studentHandler.setNurseHandler(nurseHandler);
	}
	
	//METODER FOR NURSE
	private NurseHandler createNurseHandler = new NurseHandler();
	
	@Override
	public void createNewNurse(Nurse nurse) throws SQLException {
		createNurseHandler.createNewNurse(nurse);
	}
	

	private NurseHandler getNurseHandler = new NurseHandler();
	
	public Nurse getNurse(String username) throws SQLException {
		return getNurseHandler.getNurse(username);
	}
	
	private NurseHandler updateNurseHandler = new NurseHandler();
	
	@Override
	public void updateNurse(Nurse nurse) throws SQLException {
		updateNurseHandler.updateNurse(nurse);
	}
		
	
	private NurseHandler deleteNurseHandler = new NurseHandler();
	
	@Override
	public void deleteNurse(Nurse nurse) throws SQLException {
		deleteNurseHandler.deleteNurse(nurse);
	}
	
	private NurseHandler getNurseID = new NurseHandler();
	
	public int getNurseID(Nurse nurse) throws SQLException {
		return getNurseID.getNurseID(nurse);
	}
	
	//METODER FOR STUDENT
	private StudentHandler newstudentHandler = new StudentHandler();
	@Override
	public void createNewStudent(Student student) throws SQLException {
		newstudentHandler.createNewStudent(student);
	}

	private StudentHandler updateHandler = new StudentHandler();
	@Override
	public void updateStudent(Student student) throws SQLException {
		updateHandler.updateStudent(student);
	}

	private StudentHandler deleteStudentHandler = new StudentHandler();
	
	@Override
	public void deleteStudent(Student student) throws SQLException {
		deleteStudentHandler.deleteStudent(student);
	}
	
	private StudentHandler getStudentsHandler = new StudentHandler();
	
	public List<Student> getStudents(Nurse nurse) throws SQLException {
		return getStudentsHandler.getStudents(nurse);
	}
	
	private StudentHandler getStudentHandler = new StudentHandler();
	
	public Student getStudent(String username) throws SQLException {
		return getStudentHandler.getStudent(username);
	}
	
	private StudentHandler getStudentIDHandler = new StudentHandler();

	public int getStudentID(Student student) throws SQLException {
		return getStudentIDHandler.getStudentID(student);
	}
	
	//METODER FOR SURVEY
	private SurveyHandler createSurveyHandler = new SurveyHandler();
	
	@Override
	public void createSurvey(Table survey) throws SQLException {
		createSurveyHandler.createSurvey(survey);
	}
	
	private SurveyHandler deleteSurveyHandler = new SurveyHandler();
	
	@Override
	public void deleteSurvey(Student student) throws SQLException {
		deleteSurveyHandler.deleteSurvey(student);
	}
	
	private StudentHandler answerHandler = new StudentHandler();

	public List<Table> getAnswers (Student student) throws SQLException {
		return answerHandler.getAnswers(student);
	}
					
	//METODER FOR MESSAGE
	private MessageHandler createMessageHandler = new MessageHandler();
	
	@Override
	public void createNewMessage(Message message) throws SQLException {
		createMessageHandler.createNewMessage(message);
	}
	
private MessageHandler deleteMessageHandler = new MessageHandler();
	
	@Override
	public void deleteMessages(Message message) throws SQLException {
		deleteMessageHandler.deleteMessages(message);
	}
	
	private MessageHandler getMessagesHandler = new MessageHandler();

	
	@Override
	public List<Message> getMessages (Student student) throws SQLException {
		return getMessagesHandler.getMessages(student);
	}

	private MessageHandler getMessageHandler = new MessageHandler();
	
	public Message getMessage (Student student, Nurse nurse) throws SQLException {
		return getMessageHandler.getMessage(student, nurse);
	}
	
}


