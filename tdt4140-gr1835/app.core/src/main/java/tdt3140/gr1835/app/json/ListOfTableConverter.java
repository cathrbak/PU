package tdt3140.gr1835.app.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tdt4140.gr1835.app.core.Table;

public class ListOfTableConverter implements JsonConverterService<List<Table>> {
	
	Gson gson= new Gson();

	@Override
	public List<Table> convertToObject(String jasonFile) {
		Type listType=new TypeToken<ArrayList<Table>>() {}.getType();
		return gson.fromJson(jasonFile, listType);
	}

	@Override
	public String convertToJason(List<Table> objectToConvert) {
		return gson.toJson(objectToConvert);
	}

}
