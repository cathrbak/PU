package tdt4140.gr1835.app.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockingDatabase implements UserDatabaseHandler{
	
	public List<User> users= new ArrayList<>();

	public MockingDatabase() {
		init();
	}

	private void init() {
		User s=new User("sverress");
		s.setPassword("sss");
		s.setFirstName("Sverre");
		s.setSecondName("Spetalen");
		s.setFaculty("IE");
		users.add(s);
		User n=new User("norak");
		n.setPassword("ngk");
		n.setFirstName("Nora");
		n.setSecondName("Kallager");
		n.setFaculty("IE");
		users.add(n);
		User j=new User("jonash");
		j.setPassword("jh");
		j.setFirstName("Jonas");
		j.setSecondName("Haga");
		j.setFaculty("IE");
		users.add(j);
		
		
	}

	@Override
	public boolean createNewUser(String username) {
		if(!users.stream().anyMatch(u->u.getUsername().equals(username))) {
			User newUser=new User(username);
			users.add(newUser);
			return true;
		}
		return false;
	}

	@Override
	public User getUser(String username) {
		List<User> result= users.stream()
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
		UserDatabaseHandler dh=new MockingDatabase();
		System.out.println(dh.getUser("sverress").getFirstName());
		System.out.println(dh.createNewUser("eiriko"));
		System.out.println(dh.getUser("eiriko").getUsername());
		
	}

}
