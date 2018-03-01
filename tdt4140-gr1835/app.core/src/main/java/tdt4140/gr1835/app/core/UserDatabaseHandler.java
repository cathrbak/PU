package tdt4140.gr1835.app.core;

import java.sql.SQLException;
import java.util.Collection;

public interface UserDatabaseHandler {
	
	//Metoder for Nurses
	public void createNewNurse(Nurse nurse) throws SQLException;
	public void updateNurse(Nurse nurse) throws SQLException;
	public Nurse getNurse(String username);

	//Metoder for Studenter
	public void createNewStudent(Student student) throws SQLException;
	public Student getStudent(String username);
	public void updateStudent(Student student) throws SQLException;
	
	//Gir meg datagivere til denne helses�steren
	public Collection<Student> getStudents(Nurse nurse);
	

}
