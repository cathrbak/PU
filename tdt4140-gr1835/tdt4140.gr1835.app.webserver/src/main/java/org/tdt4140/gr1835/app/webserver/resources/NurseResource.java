package org.tdt4140.gr1835.app.webserver.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tdt3140.gr1835.app.json.JsonConverterService;
import tdt3140.gr1835.app.json.ListOfStudentConverter;
import tdt3140.gr1835.app.json.MessageJsonConverter;
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
import tdt4140.gr1835.app.webclient.RestClientImp;



@Path("/nurses")
public class NurseResource {
	
	private JsonConverterService<Nurse> objectConverter = new NurseJsonConverter();
	private JsonConverterService<List<Student>> listConverter = new ListOfStudentConverter();
	private UserDatabaseHandler database = new ConnectionSQL();
	//Lager en cache med de nursene jeg allerede har funnet fra getNurse
	private List<Nurse> nurses= new ArrayList<>();
	
	/*
	 *   /nurses: ikke tillatt
	 */
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getStudents(){
		return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
	}
	
	/*
	 *   /nurses/{username/id}: gir meg Nurse-objektet med dette brukernavnet
	 */
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getNurse(@PathParam ("username") String username) throws SQLException {
		Nurse nurse;
		if(nurses.stream().anyMatch(n -> n.getUsername().equals(username))) {
			System.out.println("Fant nurse objektet lokalt, bruker denne");
			nurse = nurses.stream().filter(n -> n.getUsername().equals(username)).collect(Collectors.toList()).get(0);
		}else {
			nurse= database.getNurse(username);
			nurses.add(nurse);
		}
		return objectConverter.convertToJason(database.getNurse(username));
	}
	
	/*
	 *   /nurses/{username/id}/students  : gir meg en liste med alle studentene tilknyttet denne helsesøsteren
	 */
	
	@GET
	@Path("/{username}/students")
	@Produces(MediaType.APPLICATION_JSON)
	public String getStudents(@PathParam ("username") String username) throws SQLException, Exception {
		Nurse nurse;
		if(nurses.stream().anyMatch(n -> n.getUsername().equals(username))) {
			System.out.println("Fant nurse objektet lokalt, bruker denne");
			nurse = nurses.stream().filter(n -> n.getUsername().equals(username)).collect(Collectors.toList()).get(0);
		}else {
			System.out.println("Henter Nurseobjekt fra databasen");
			try {
				nurse = database.getNurse(username);
			}catch (IllegalStateException e) {
				System.err.println("404. Fant ikke ");
				nurse=null;
			}
		}
		return listConverter.convertToJason(database.getStudents(nurse));
	}
	
	
	/*
	 *   /nurses/{username}/messages  : gir en liste med alle Message-objekter til denne helsesøsteren
	 */
	
	@GET
	@Path("/{username}/messages")
	@Produces(MediaType.TEXT_HTML)
	public Response getMessages(@PathParam ("username") String username) {
		return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Har ikke implementert for meldinger ennå. Mangler getStundets(Nurse) i connection SQL").build();
	}
	
	/*
	 * /nurses: Oppretter et nytt Nurseobjekt
	 */
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNurse(String input){
		Nurse nurse=objectConverter.convertToObject(input);
		try {
			database.createNewNurse(nurse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return Response.status(201).build(); //201 Created
	}
	
	/*
	 * /nurses/{username}/messages  : Oppretter en ny melding
	 */
	
	
	@POST
	@Path("/{username}/messages")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMessage(String input){
		JsonConverterService<Message> converter=new MessageJsonConverter();
		System.out.println("create new Message:" + converter.convertToObject(input));
		try {
			Message convertToObject = converter.convertToObject(input);
			System.out.println(convertToObject.getText()+convertToObject.getReciver());
			database.createNewMessage(convertToObject);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return Response.status(201).build(); //201 Created
	}
	
	public static void main(String[] args) throws SQLException {
		NurseResource nurseResource=new NurseResource();
		RestClientImp database= new RestClientImp();
		Nurse nurse = database.getNurse("testsoster");
		Student student = database.getStudent("sverress");
		Message testMessage = new Message(student,nurse);
		testMessage.setText("Heisann");
		Message testMessage1 = new Message(student,nurse);
		testMessage.setText("Heisann2");
		
		String json=new MessageJsonConverter().convertToJason(testMessage);
		System.out.println(json);
		nurseResource.createMessage(json);
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateNurse(String input){
		Nurse nurse=objectConverter.convertToObject(input);
		try {
			database.updateNurse(nurse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return Response.status(204).build(); //204 Updated
	}
	
	@DELETE
	@Path("/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteNurse(@PathParam ("username") String username){
		
		try {
			database.deleteNurse(database.getNurse(username));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return Response.status(204).build(); //201 Created
	}

}
