package tdt3140.gr1835.app.json;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import tdt4140.gr1835.app.core.Message;

public class ListOfMessagesConverter implements JsonConverterService<List<Message>> {
	
	@Override
	public List<Message> convertToObject(String jasonFile) {
		Type listType=new TypeToken<ArrayList<Message>>() {}.getType();
		GsonBuilder builder=new GsonBuilder();
		Gson gson = builder.setExclusionStrategies(new AnnotationExclutionStrategy()).create();
		List<Message> list=gson.fromJson(jasonFile, listType);
		list.stream().forEach(m->m.setTime(new Timestamp(m.getMillitime())));
		return list;
	}

	@Override
	public String convertToJason(List<Message> objectToConvert) {
		JsonConverterService<Message> messConverterService=new MessageJsonConverter();
		StringBuilder builder= new StringBuilder();
		builder.append("[");
		for(Message message:objectToConvert) {
			builder.append(messConverterService.convertToJason(message)+", ");
		}
		builder.replace(builder.lastIndexOf(","),builder.length(), "]");
		return builder.toString();
	}
	
	public static void main(String[] args) {
		
	}




}
