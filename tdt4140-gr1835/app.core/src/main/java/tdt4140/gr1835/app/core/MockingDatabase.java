package tdt4140.gr1835.app.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MockingDatabase {
	/*
	public List<Student> users= new ArrayList<>();

	public MockingDatabase() {
		init();
	}

	private void init() {
		Student s=new Student("sverress");
		s.setPassword("sss");
		s.setFirstName("Sverre");
		s.setSecondName("Spetalen");
		s.setFaculty("IE");
		users.add(s);
		Student n=new Student("norak");
		n.setPassword("ngk");
		n.setFirstName("Nora");
		n.setSecondName("Kallager");
		n.setFaculty("IE");
		users.add(n);
		Student j=new Student("jonash");
		j.setPassword("jh");
		j.setFirstName("Jonas");
		j.setSecondName("Haga");
		j.setFaculty("IE");
		users.add(j);
		
		
	}

	@Override
	public boolean createNewNurse(String username) {
		if(!users.stream().anyMatch(u->u.getUsername().equals(username))) {
			Student newUser=new Student(username);
			users.add(newUser);
			return true;
		}
		return false;
	}

	//@Override
	public Student getStudent(String username) {
		List<Student> result= users.stream()
				.filter(u->u.getUsername().equals(username))
				.collect(Collectors.toList());
		if (result.size()<1) {
			throw new IllegalStateException("Fant ingen treff på dette brukernavnet");
		}
		if (result.size()>1) {
			throw new IllegalStateException("Fant flere treff på dette brukernavnet");
		}
		return result.get(0);
	}
	
	public static void main(String[] args) {
	
		
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Student> getStudents(Nurse nurse) {
		// TODO Auto-generated method stub
		return null;
	}


*/
}
