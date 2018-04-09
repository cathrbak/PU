package tdt3140.gr1835.app.json;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;

public class StudentJsonConverter implements JsonConverterService<Student> {
	
	Gson helper;

	@Override
	public Student convertToObject(String jasonFile) {
		helper = new Gson();
		return helper.fromJson(jasonFile, Student.class);
	}

	@Override
	public String convertToJason(Student objectToConvert) {
		GsonBuilder builder= new GsonBuilder();
//https://stackoverflow.com/questions/4802887/gson-how-to-exclude-specific-fields-from-serialization-without-annotations
//Oppretter en egen annotasjon som ekskludere gitte felter med @Exclude
		helper= builder.setExclusionStrategies(new AnnotationExclutionStrategy()).create();
		return helper.toJson(objectToConvert,Student.class);
		
	}
}

