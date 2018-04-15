package tdt4140.gr1835.app.webclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tdt3140.gr1835.app.json.JsonConverterService;
import tdt3140.gr1835.app.json.ListOfMessagesConverter;
import tdt3140.gr1835.app.json.ListOfStudentConverter;
import tdt3140.gr1835.app.json.ListOfTableConverter;
import tdt3140.gr1835.app.json.MessageJsonConverter;
import tdt3140.gr1835.app.json.NurseJsonConverter;
import tdt3140.gr1835.app.json.StudentJsonConverter;
import tdt3140.gr1835.app.json.TableJsonConverter;
import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;

public class RestClientImp implements RESTClient {
	
	private HTTPSimpleClient webclient=new HTTPSimpleClient();
	
	private JsonConverterService<Nurse> nurseJsonConverter= new NurseJsonConverter();
	
	@Override
	public Nurse getNurse(String username){
		String nurseString="";
		try {
			nurseString=webclient.doGet("nurses/"+username);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(nurseString==null) {
			return null;
		}
		return nurseJsonConverter.convertToObject(nurseString);
	}
	
	private JsonConverterService<List<Student>> listStudentConverter=new ListOfStudentConverter();
	
	@Override
	public List<Student> getStudents(String username){
		String studentString="";
		try {
			studentString=webclient.doGet("nurses/"+username+"/students");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(studentString==null) {
			return new ArrayList<>(); //Returnerer tom liste hvis vi ikke fant noen Studenter
		}
		return listStudentConverter.convertToObject(studentString);
	}
	
	private JsonConverterService<List<Message>> listmessageJsonConverter=new ListOfMessagesConverter();
	
	@Override
	public List<Message> getMessagesStudent(String username){
		String messagesString="";
		try {
			messagesString=webclient.doGet("students/"+username+"/messages");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(messagesString==null) {
			return new ArrayList<>();
		}
		return listmessageJsonConverter.convertToObject(messagesString);
	}
	
	@Override
	public Student getStudent(String username){
		String studentString="";
		try {
			studentString=webclient.doGet("students/"+username);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(studentString==null) {
			return null;
		}
		return studentJsonConverter.convertToObject(studentString);
	}
	
	private JsonConverterService<List<Table>> listTableJsonConverter=new ListOfTableConverter();
	
	@Override
	public List<Table> getAnswers(String username){
		String answersString="";
		try {
			answersString=webclient.doGet("students/"+username+"/answers");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (answersString==null) {
			return new ArrayList<>(); //returnerer en tom liste hvis den ikke har noen svar
		}
		return listTableJsonConverter.convertToObject(answersString);
	}
	
	@Override
	public List<Message> getMessagesNurse(String username) {
		String messagesString="";
		try {
			messagesString=webclient.doGet("nurses/"+username+"/messages");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(messagesString==null) {
			return new ArrayList<>();
		}
		return listmessageJsonConverter.convertToObject(messagesString);
	}

	@Override
	public boolean createNurse(Nurse nurse) {
		String inputString=nurseJsonConverter.convertToJason(nurse);
		int statuscode=0;
		try {
			statuscode = webclient.doPost("nurses", inputString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(statuscode!=201 && statuscode!=200) { //Hvis vi ikke får ønsket statuskode returnerer vi false
			return false;
		}else {
			return true;
		}
	}
	
	private JsonConverterService<Student> studentJsonConverter=new StudentJsonConverter();

	@Override
	public boolean createStudent(Student student) {
		String inputString=studentJsonConverter.convertToJason(student);
		int statuscode=0;
		try {
			statuscode = webclient.doPost("students", inputString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(statuscode!=201 && statuscode!=200) { //Hvis vi ikke får ønsket statuskode returnerer vi false
			return false;
		}else {
			return true;
		}
	}
	
	private JsonConverterService<Table> tableJsonConverter= new TableJsonConverter();

	@Override
	public boolean addNewSurvey(String studentUsername, Table survey) {
		String inputString=tableJsonConverter.convertToJason(survey);
		int statuscode=0;
		try {
			statuscode = webclient.doPost("students/"+studentUsername+"/answers", inputString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(statuscode!=201 && statuscode!=200) { //Hvis vi ikke får ønsket statuskode returnerer vi false
			return false;
		}else {
			return true;
		}
	}
	
	JsonConverterService<Message> messageJsonConverter= new MessageJsonConverter();
	
	@Override
	public boolean newMessage(Message message) {
		String inputString=messageJsonConverter.convertToJason(message);
		int statuscode=0;
		try {
			statuscode = webclient.doPost("nurses/"+message.getSender().getUsername()+"/messages", inputString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(statuscode!=201 && statuscode!=200) { //Hvis vi ikke får ønsket statuskode returnerer vi false
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean updateNurse(Nurse nurse) {
		String inputString=nurseJsonConverter.convertToJason(nurse);
		int statuscode=0;
		try {
			statuscode = webclient.doPut("nurses", inputString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(statuscode!=204 && statuscode!=200) { //Hvis vi ikke får ønsket statuskode returnerer vi false
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public boolean updateStudent(Student student) {
		String inputString=studentJsonConverter.convertToJason(student);
		int statuscode=0;
		try {
			statuscode = webclient.doPut("students", inputString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(statuscode!=204 && statuscode!=200) { //Hvis vi ikke får ønsket statuskode returnerer vi false
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public boolean deleteNurse(String username) {
		int statuscode=0;
		try {
			statuscode=webclient.doDelete("nurses/"+username);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(statuscode!=204 && statuscode!=200) { //Hvis vi ikke får ønsket statuskode returnerer vi false
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean deleteStudent(String username) {
		int statuscode=0;
		try {
			statuscode=webclient.doDelete("students/"+username);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(statuscode!=204 && statuscode!=200) { //Hvis vi ikke får ønsket statuskode returnerer vi false
			return false;
		}else {
			return true;
		}

	}
}
