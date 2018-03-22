package tdt4140.gr1835.app.core;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface UserDatabaseHandler {
	
	public void closeConnection() throws SQLException;
	
	//Metoder for Nurses
	public void createNewNurse(Nurse nurse) throws SQLException;
	public void updateNurse(Nurse nurse) throws SQLException;
	public Nurse getNurse(String username) throws SQLException;
	public void deleteNurse(Nurse nurse) throws SQLException;

	//Metoder for Studenter
	public void createNewStudent(Student student) throws SQLException;
	public Student getStudent(String username) throws SQLException;
	public void updateStudent(Student student) throws SQLException;
	public void deleteStudent(Student student) throws SQLException;
	
	//Metoder for Undersøkelser
	public void createSurvey(Table survey) throws SQLException;
	public void deleteSurvey(Student student) throws SQLException;
	
	//Gir meg datagivere til denne helses�steren
	//Denne burde byttes om til List
	public List<Student> getStudents(Nurse nurse) throws SQLException, Exception;
	
	//Gir meg svarene til en student
	public List<Table> getAnswers(Student student) throws SQLException;

	

}
