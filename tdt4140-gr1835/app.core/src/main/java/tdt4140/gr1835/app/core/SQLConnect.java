package tdt4140.gr1835.app.core;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SQLConnect {
	private final static String dbURL="jdbc:mysql://mysql.stud.ntnu.no/jonahag_prosjektdb?user=jonahag_pu35&password=gruppe35&useSSL=false";
	
	public static void main(String[] args) throws SQLException {
		SQLConnect driver = new SQLConnect();
		driver.createConnection();
	}
	
	void createConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbURL);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from datagiver");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue + " " + rsmd.getColumnName(i));
			    }
			    System.out.println("");
			}

			// må få inn lukking av connection her, slik at ikke databasen krasjer. ikke så viktig akkurat nå, men må inn senere.
			
		
		} catch (Exception ex) {
			Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
		}
	
		}
}
