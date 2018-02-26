package tdt4140.gr1835.app.core;

import java.util.Collection;

public interface UserDatabaseHandler {
	
	public boolean createNewNurse(String username);
	public Student getStudent(String username);
	public void updateStudent(Student student);
	
	//Gir meg datagivere til denne helsesøsteren
	public Collection<Student> getStudents(Nurse nurse);
	

}
