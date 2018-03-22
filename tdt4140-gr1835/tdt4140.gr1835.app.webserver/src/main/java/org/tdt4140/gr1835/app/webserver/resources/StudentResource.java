package org.tdt4140.gr1835.app.webserver.resources;


import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tdt3140.gr1835.app.json.JsonConverterService;
import tdt3140.gr1835.app.json.NurseJsonConverter;
import tdt3140.gr1835.app.json.StudentJsonConverter;
import tdt4140.gr1835.app.core.ConnectionSQL;
import tdt4140.gr1835.app.core.MockingDatabase;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.UserDatabaseHandler;

@Path("/students")
public class StudentResource {
	
	private JsonConverterService<Student> studconverter = new StudentJsonConverter();
	private JsonConverterService<Nurse> nurseconverter = new NurseJsonConverter();
	private UserDatabaseHandler database = new ConnectionSQL();
	
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
	public Response getTables() {
		return Response.status(Response.Status.NOT_IMPLEMENTED).build();
	}
	
	/*
	 * /students  : Oppretter et nytt Studentobjekt
	 */
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createStudent(String input) throws SQLException {
		System.out.println("Konverterer JSON til objekt");
		Student stud=studconverter.convertToObject(input);
		System.out.println(stud);
		System.out.println("Kaller på database for å lage ny student: createNewStudent");
		database.createNewStudent(stud);
		return studconverter.convertToJason(database.getStudent(stud.getUsername()));
	}
	
	/*
	 * /students/{username}/answers  : Oppretter et nytt svar på en undersøkelse
	 */
	
	@POST
	@Path("/{username}/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTable(String input) throws SQLException {
		return Response.status(Response.Status.NOT_IMPLEMENTED).build();
	}
}
