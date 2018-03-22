package tdt3140.gr1835.app.json;

import com.google.gson.Gson;

import tdt4140.gr1835.app.core.Table;

public class TableJsonConverter implements JsonConverterService<Table> {
	
	Gson helper=new Gson();
	

	@Override
	public Table convertToObject(String jasonFile) {
		return helper.fromJson(jasonFile, Table.class);
	}

	@Override
	public String convertToJason(Table objectToConvert) {
		return helper.toJson(objectToConvert);
	}
}
