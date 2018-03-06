package tdt4140.gr1835.app.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.jdbi.v3.core.statement.StatementContext;

public class UserMapper implements ResultSetMapper {
	@Override
	public User map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
		Integer[] m = (Integer [])rs.getArray("mobile_no").getArray();
		List<Integer>mobileNos = Arrays.asList(m);
			return new User(rs.getString("username"), mobileNos);
	}
	
}
