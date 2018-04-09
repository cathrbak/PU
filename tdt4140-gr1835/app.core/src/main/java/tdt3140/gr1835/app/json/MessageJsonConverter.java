package tdt3140.gr1835.app.json;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tdt4140.gr1835.app.core.Message;

public class MessageJsonConverter implements JsonConverterService<Message>{
	
	Gson gson;

	@Override
	public Message convertToObject(String jasonFile) {
		GsonBuilder builder= new GsonBuilder();
		//https://stackoverflow.com/questions/4802887/gson-how-to-exclude-specific-fields-from-serialization-without-annotations
		//Oppretter en egen annotasjon som ekskludere gitte felter med @Exclude
		gson= builder.setExclusionStrategies(new AnnotationExclutionStrategy()).create();
		Message mess=gson.fromJson(jasonFile, Message.class);
		mess.setTime(new Timestamp(mess.getMillitime()));
		return mess;
	}

	@Override
	public String convertToJason(Message objectToConvert) {
		GsonBuilder builder= new GsonBuilder();
		//https://stackoverflow.com/questions/4802887/gson-how-to-exclude-specific-fields-from-serialization-without-annotations
		//Oppretter en egen annotasjon som ekskludere gitte felter med @Exclude
		gson= builder.setExclusionStrategies(new AnnotationExclutionStrategy()).create();
		return gson.toJson(objectToConvert);
	}

}
