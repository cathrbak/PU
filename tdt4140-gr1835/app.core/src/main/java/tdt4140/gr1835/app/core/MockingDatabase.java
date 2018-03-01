package tdt4140.gr1835.app.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MockingDatabase implements UserDatabaseHandler{
	public List<Student> students= new ArrayList<>();
	public List<Nurse> nurses= new ArrayList<>();

	public MockingDatabase() {
		init();
	}

	private void init() {
		Student s=new Student("sverress");
		s.setPassword("sss");
		s.setFirstName("Sverre");
		s.setSecondName("Spetalen");
		s.setFaculty("IE");
		students.add(s);
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
		Nurse testNurse = new Nurse("cathrine");
		testNurse.setPassword("c");
		testNurse.setFirstName("Cathrine");
		testNurse.setSecondName("Arke");
		testNurse.setFaculty("IE");
		nurses.add(testNurse);
	}

	/*
	 *Metoder for studenter 
	 */
	@Override
	public void createNewStudent(Student student) throws SQLException {
		if(containsStudent(student.getUsername())) {
			throw new IllegalStateException("Denne brukeren eksisterer allerede i databasen");
		}
		students.add(student);
		
	}

	@Override
	public Student getStudent(String username) {
		if(!containsStudent(username)) {
			throw new IllegalStateException("Denne brukeren eksisterer ikke i databasen");
		}
		List<Student> result= students.stream()
				.filter(u->u.getUsername().equals(username))
				.collect(Collectors.toList());
		return result.get(0);
	}
	
	private boolean containsStudent(String username) {
		List<Student> result= students.stream()
				.filter(u->u.getUsername().equals(username))
				.collect(Collectors.toList());
		return result.size()==1;
	}

	@Override
	public void updateStudent(Student student) {
		//Dette ordner seg selv med pekeren i java.
	}

	@Override
	public Collection<Student> getStudents(Nurse nurse) {
		List<Student> result= students.stream()
				.filter(u->u.getFaculty().equals(nurse.getFaculty()))
				.collect(Collectors.toList());
		return result;
	}

	/*
	 * Metoder for Helsesøstere
	 */
	@Override
	public void createNewNurse(Nurse nurse) throws SQLException {
		if(containsNurse(nurse.getUsername())) {
			throw new IllegalStateException("Denne brukeren eksisterer allerede i databasen");
		}
		nurses.add(nurse);		
	}

	private boolean containsNurse(String username) {
		List<Nurse> result= nurses.stream()
				.filter(u->u.getUsername().equals(username))
				.collect(Collectors.toList());
		return result.size()==1;
	}

	@Override
	public void updateNurse(Nurse nurse) throws SQLException {
		//Dette ordner seg selv med pekeren i java.
		
	}
	
	@Override
	public Nurse getNurse(String username) {
		if(!containsNurse(username)) {
			throw new IllegalStateException("Denne brukeren eksisterer ikke i databasen");
		}
		List<Nurse> result= nurses.stream()
				.filter(u->u.getUsername().equals(username))
				.collect(Collectors.toList());
		return result.get(0);
	}

	
	


}
