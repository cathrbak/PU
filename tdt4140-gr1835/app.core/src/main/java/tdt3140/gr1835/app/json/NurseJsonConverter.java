package tdt3140.gr1835.app.json;

import com.google.gson.Gson;

import tdt4140.gr1835.app.core.Nurse;

public class NurseJsonConverter implements JsonConverterService<Nurse> {
	
	Gson helper=new Gson();

	@Override
	public Nurse convertToObject(String jasonFile) {
		// TODO Auto-generated method stub
		return helper.fromJson(jasonFile, Nurse.class);
	}

	@Override
	public String convertToJason(Nurse objectToConvert) {
		// TODO Auto-generated method stub
		return helper.toJson(objectToConvert);
	}

}
