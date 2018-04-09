package tdt3140.gr1835.app.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class AnnotationExclutionStrategy implements ExclusionStrategy{

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return f.getAnnotation(Exclude.class) != null;
	}

}
