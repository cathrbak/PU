package tdt4140.gr1835.app.core;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

//henter alle studenter som en helsesøster er ansvarlig for
public interface UserDao {
	@SqlQuery("SELECT * FROM datagiver")
	public List<User> getAllUsers();

// setter inn nye helsesøster-brukere
	@SqlBatch("INSERT INTO helsesoster (username, password, firstName, secondName, faculty, phoneNumber, email) values(:username,:password,:firstName,:secondName,:phoneNumber,:email)")
			public void addUsers(@BindBean List<User> users);

}
