package tdt3140.gr1835.app.json;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tdt4140.gr1835.app.core.Table;

public class TableJsonConverter implements JsonConverterService<Table> {
	
	Gson helper= new Gson();
	

	@Override
	public Table convertToObject(String jasonFile) {
		EasyTable eTable=helper.fromJson(jasonFile, EasyTable.class);
		return new Table(eTable.getStudentID(),
				eTable.getAnsList().get(0),
				eTable.getAnsList().get(1),
				eTable.getAnsList().get(2),
				eTable.getAnsList().get(3),
				eTable.getAnsList().get(4),
				eTable.getAnsList().get(5),
				eTable.getAnsList().get(6),
				eTable.getAnsList().get(7),
				eTable.getAnsList().get(8),
				eTable.getAnsList().get(9),
				eTable.getTotal());
	}

	@Override
	public String convertToJason(Table objectToConvert) {
		EasyTable table=new EasyTable();
		List<Integer> list=new ArrayList<>();
		list.add(objectToConvert.getSpm1());
		list.add(objectToConvert.getSpm2());
		list.add(objectToConvert.getSpm3());
		list.add(objectToConvert.getSpm4());
		list.add(objectToConvert.getSpm5());
		list.add(objectToConvert.getSpm6());
		list.add(objectToConvert.getSpm7());
		list.add(objectToConvert.getSpm8());
		list.add(objectToConvert.getSpm9());
		list.add(objectToConvert.getSpm10());
		table.setAnsList(list);
		table.setStudentID(objectToConvert.getPersonID());
		table.setTotal(objectToConvert.getTotal());
		return helper.toJson(table);
	}
}