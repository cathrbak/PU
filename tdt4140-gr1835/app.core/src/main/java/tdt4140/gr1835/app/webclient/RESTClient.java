package tdt4140.gr1835.app.webclient;
import java.util.List;

import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;

public interface RESTClient {
	
	
	
	/*
	 * Dette grensesnittet skal ha alle metodene til URLene som definert i Design av REST API fra wiki:
	 * https://gitlab.stud.iie.ntnu.no/tdt4140-2018/35/wikis/Dokumentasjon/Design-av-REST-API
	 */
	
	
//	GET-varianter
//-----------------------------------------------------------------------------------------------------------
	
//	Alle metoder returnerer null eller tom liste hvis det ikke eksisteter noe i data i databasen
	
//	/nurses/{username/id}  : gir meg Nurse-objektet med dette brukernavnet
	public Nurse getNurse(String username);
	
//	/nurses/{username/id}/students  : gir meg en liste med alle studentene tilknyttet denne helsesøsteren
	public List<Student> getStudents(String username);
	
//	/nurses/{username}/messages  : gir en liste med alle Message-objekter til denne helsesøsteren
	public List<Message> getMessagesNurse(String username);
	
//	/students/{username/id}  : gir Student-objekt med dette brukernavnet
	public Student getStudent(String username);
	
//	/students/{username/id}/answers  : gir en liste med alle Table-objekter til denne studenten
	public List<Table> getAnswers(String username);
	
//	/students/{username/id}/messages  : gir en liste med alle Message-objekter til denne studenten
	public List<Message> getMessagesStudent(String username);
	
	

//	POST-varianter
//-----------------------------------------------------------------------------------------------------------
	
//  Alle metoder Rrturnerer true hvis opprettelsen var vellykket, false ellers
	
//	/nurses  : Oppretter et nytt Nurseobjekt
	public boolean createNurse(Nurse nurse);
	
//	/students  : Oppretter et nytt Studentobjekt
	public boolean createStudent(Student student);
	
//	/students/{username}/answers  : Oppretter et nytt svar på en undersøkelse
	public boolean addNewSurvey(String studentUsername, Table table);
	
//	/nurses/{username}/messages  : Oppretter en ny melding
	public boolean newMessage(Message message);

//	PUT-varianter
//-----------------------------------------------------------------------------------------------------------

//  Alle metoder Rrturnerer true hvis oppdateringen var vellykket, false ellers
	
//	/nurses/{username}  : oppdaterer helsesøsteren
	public boolean updateNurse(Nurse nurse);
	
//	/students/{username}  : oppdaterer student
	public boolean updateStudent(Student student);

	
//	DELETE-varianter
//-----------------------------------------------------------------------------------------------------------

//  Alle metoder Rrturnerer true hvis slettingen var vellykket, false ellers
	
//	/nurses/{username/id}  : sletter helsesøsteren
	public boolean deleteNurse(String username);
//	/students/{username/id}  : ikke tillatt
	public boolean deleteStudent(String username);
}
