package tdt4140.gr1835.app.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.statement.StatementContext;

public interface ResultSetMapper {

	User map(int index, ResultSet rs, StatementContext ctx) throws SQLException;

}
