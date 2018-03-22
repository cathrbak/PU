package tdt3140.gr1835.app.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tdt4140.gr1835.app.core.Student;

public class ListOfStudentConverter implements JsonConverterService<List<Student>> {
	
	Gson gson=new Gson();

	@Override
	public List<Student> convertToObject(String jasonFile) {
		Type listType=new TypeToken<ArrayList<Student>>() {}.getType();
		return gson.fromJson(jasonFile, listType);
	}

	@Override
	public String convertToJason(List<Student> objectToConvert) {
		return gson.toJson(objectToConvert);
	}
}
