package tdt3140.gr1835.app.json;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tdt4140.gr1835.app.core.Table;

public class ListOfTableConverter implements JsonConverterService<List<Table>> {
	
	Gson gson= new Gson();

	@Override
	public List<Table> convertToObject(String jasonFile) {
		Type listType=new TypeToken<ArrayList<EasyTable>>() {}.getType();
		List<EasyTable> list= gson.fromJson(jasonFile, listType);
		List<Table> res=new ArrayList<>();
		for(EasyTable table:list) {
			Table resTable = new Table(table.getStudentID(),
					table.getAnsList().get(0),
					table.getAnsList().get(1),
					table.getAnsList().get(2),
					table.getAnsList().get(3),
					table.getAnsList().get(4),
					table.getAnsList().get(5),
					table.getAnsList().get(6),
					table.getAnsList().get(7),
					table.getAnsList().get(8),
					table.getAnsList().get(9),
					table.getTotal());
			resTable.setTstamp(new Timestamp(table.getMillis()));
			res.add(resTable);
		}
		return res;
	}

	@Override
	public String convertToJason(List<Table> objectToConvert) {
		JsonConverterService<Table> tableConverter= new TableJsonConverter();
		StringBuilder builder= new StringBuilder();
		builder.append("[");
		for(Table table:objectToConvert) {
			String convertToJason = tableConverter.convertToJason(table);
			builder.append(convertToJason+", ");
		}
		
		builder.replace(builder.lastIndexOf(","),builder.length(), "]");
		return builder.toString();
	}

}
