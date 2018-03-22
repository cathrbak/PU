package tdt3140.gr1835.app.json;

import com.google.gson.Gson;

import tdt4140.gr1835.app.core.Student;

public class StudentJsonConverter implements JsonConverterService<Student> {
	
	Gson helper = new Gson();

	@Override
	public Student convertToObject(String jasonFile) {
		return helper.fromJson(jasonFile, Student.class);
	}

	@Override
	public String convertToJason(Student objectToConvert) {
		return helper.toJson(objectToConvert);
	}
}

