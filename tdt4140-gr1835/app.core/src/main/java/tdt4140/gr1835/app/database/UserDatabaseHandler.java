package tdt4140.gr1835.app.database;

import java.sql.SQLException;
import java.util.List;

import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;

public interface UserDatabaseHandler {
	
	public void closeConnection() throws SQLException;
	
	//Metoder for Nurses:
	//Legger til et nurse-objekt i db
	public void createNewNurse(Nurse nurse) throws SQLException;
	//Oppdaterer nurse-objekt i db
	public void updateNurse(Nurse nurse) throws SQLException;
	//Returnerer et nurse-objekt fra db, gitt et username
	public Nurse getNurse(String username) throws SQLException;
	//Returnerer alle student-objektene en gitt nurse har tilgang til
	public List<Student> getStudents(Nurse nurse) throws SQLException;
	

	//Metoder for Studenter:
	//Legger til et student-objekt i db
	public void createNewStudent(Student student) throws SQLException;
	//Oppdaterer nurse-objekt i db
	public void updateStudent(Student student) throws SQLException;
	//Returnerer et nurse-objekt fra db, gitt et username
	public Student getStudent(String username) throws SQLException;
	//Gir meg svarene til alle sporreundersokelser en student har svart på som en liste med table-objekter
	public List<Table> getAnswers(Student student) throws SQLException;
	//Gir meg alle meldinger som er sendt til en student
	public List<Message> getMessages(Student student) throws SQLException;
	
	//Metoder for sporreundersokelser:
	//Legger til et table-objekt i db
	public void createSurvey(Table survey) throws SQLException;
	
	//Metoder for meldinger
	//Legger til et meldings-objekt i db
	public void createNewMessage(Message message) throws SQLException;
	
	
	
	
	
	 
	
	

	
	

}
