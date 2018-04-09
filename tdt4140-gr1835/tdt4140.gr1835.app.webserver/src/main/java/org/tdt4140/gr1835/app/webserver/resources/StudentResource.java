package org.tdt4140.gr1835.app.webserver.resources;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tdt3140.gr1835.app.json.JsonConverterService;
import tdt3140.gr1835.app.json.ListOfMessagesConverter;
import tdt3140.gr1835.app.json.ListOfTableConverter;
import tdt3140.gr1835.app.json.NurseJsonConverter;
import tdt3140.gr1835.app.json.StudentJsonConverter;
import tdt3140.gr1835.app.json.TableJsonConverter;
import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;
import tdt4140.gr1835.app.database.ConnectionSQL;
import tdt4140.gr1835.app.database.MockingDatabase;
import tdt4140.gr1835.app.database.UserDatabaseHandler;

@Path("/students")
public class StudentResource {
	
	private JsonConverterService<Student> studconverter = new StudentJsonConverter();
	private JsonConverterService<Nurse> nurseconverter = new NurseJsonConverter();
	private UserDatabaseHandler database = new MockingDatabase();
	
	/*
	 *   /students: ikke tillatt
	 */
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getStudents(){
		return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
	}
	
	/*
	 *   /students/{username/id}: gir Student-objekt med dette brukernavnet
	 */
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getStudent(@PathParam ("username") String username) throws SQLException {
		Student stud=database.getStudent(username);
		if (stud==null) {
			return null;
		}
		return studconverter.convertToJason(stud);
	}
	
	
	/*
	 * /students/{username/id}/answers  : gir en liste med alle Table-objekter til denne studenten
	 */
	
	@GET
	@Path("/{username}/answers")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTables(@PathParam ("username") String username) {
		List<Table> tables= new ArrayList<>();
		try {
			tables=database.getAnswers(database.getStudent(username));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonConverterService<List<Table>> converterService=new ListOfTableConverter();
		return converterService.convertToJason(tables);
	}
	/*
	 * /students/{username/id}/messages  : gir en liste med alle Message-objekter til denne
	 */
	@GET
	@Path("/{username}/messages")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMessages(@PathParam ("username") String username) {
		List<Message> messages= new ArrayList<>();
		try {
			messages=database.getMessages(database.getStudent(username));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonConverterService<List<Message>> converterService=new ListOfMessagesConverter();
		return converterService.convertToJason(messages);
	}
	
	/*
	 * /students  : Oppretter et nytt Studentobjekt
	 */
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createStudent(String input){
		Student stud=studconverter.convertToObject(input);
		try {
			database.createNewStudent(stud);
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return Response.status(201).build();
	}
	
	/*
	 * /students/{username}/answers  : Oppretter et nytt svar på en undersøkelse
	 */
	
	@POST
	@Path("/{username}/answers")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTable(String input) {
		JsonConverterService<Table> converter= new TableJsonConverter();
		try {
			database.createSurvey(converter.convertToObject(input));
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return Response.status(201).build();
	}
}
