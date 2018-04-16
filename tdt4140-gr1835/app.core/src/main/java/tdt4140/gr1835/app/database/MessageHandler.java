package tdt4140.gr1835.app.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;

public class MessageHandler extends AbstractSQLHandler {

	//Metode for � slette meldinger i testene. Ikke bruk i noe annet enn test, da den sletter alle meldingene sendt til en student.
		public void deleteMessages(Message message) throws SQLException {
			try {
				Statement stmt = getStatement();
				
				String query = "DELETE from meldinger WHERE DatagiverID=" + getStudentID(message.getReciver())
				+ ";";
				
				stmt.executeUpdate(query);
				
			}
			catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			closeConnection();
			
		}

		
		//Metode for � legge til en melding i databasen
		public void createNewMessage(Message message) throws SQLException {
			try {
				Statement stmt = getStatement();
				
				String query = "INSERT INTO meldinger(DatagiverID, HelsesosterID, tekst) VALUES (" + getStudentID(message.getReciver()) + ", " +
						getNurseID(message.getSender()) + ", '" + message.getText()+ "');";
				
				stmt.executeUpdate(query);
				
			}
			catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			
			closeConnection();
			
		}
		//Metode for � hente ut 1 melding
		public Message getMessage(Student student, Nurse nurse) throws SQLException {
			ResultSet rs = null;
			Message message = new Message(student, nurse);
			try {
				String query = "SELECT * FROM meldinger WHERE DatagiverID=" + getStudentID(student) +" AND HelsesosterID=" + getNurseID(nurse) + ";";
				Statement stmt = getStatement();
				
				if(stmt.execute(query)) {
					rs = stmt.getResultSet();
					
					if (!rs.isBeforeFirst() ) {    
					    System.out.println("Meldingen ligger ikke i databasen."); 
					    throw new IllegalStateException("Denne meldingen eksisterer ikke i databasen"); 
					} 
				}
			}
			catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			
			while(rs.next()) {
				
				Integer messageID = rs.getInt("MeldingID");
				if(!(messageID==0)) {
					message.setMessageID(messageID);
				}
				Integer studentID = rs.getInt("DatagiverID");
				if(!(studentID==0)) {
					message.setReciver(getStudentFromID(studentID));
				}
				Integer nurseID = rs.getInt("HelsesosterID");
				if(!(nurseID==0)) {
					message.setNurse(getNurseFromID(nurseID));
				}
				Timestamp tstamp = rs.getTimestamp("datoTid");
				message.setTime(tstamp);
				//System.out.println(message.getTime());
				
				String text = rs.getString("tekst");
				if(!(text.equals("null"))){
					message.setText(text);
				}
				
								
			}
			closeConnection();
			return message; 
			
			
		}
		
		//Metode for � hente ut alle meldingene som er sendt til en student.
		public List<Message> getMessages(Student student) throws SQLException {
			List<Message> messages = new ArrayList<Message>();
	  
	        ResultSet rs = null;
	        
	        try {
	            String query = "SELECT * FROM meldinger WHERE DatagiverID=" + student.getStudentID() +";";
	            Statement stmt = getStatement();
	            
	            if(stmt.execute(query)) {
	                rs = stmt.getResultSet();
	                 
	            }
	            
	        }
	        catch (SQLException e) {
	            System.out.println("SQLException: " + e.getMessage());
	        }
	        
	        while(rs.next()) {
	            Integer messageID = rs.getInt("MeldingID");         
	            
	            Message message = getMessageFromID(messageID); 
	            
	            
	            
	            messages.add(message);
	            
	            
	        }
	        closeConnection();
	        return messages;
		}
		
	
		
}
	

