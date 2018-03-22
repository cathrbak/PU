package tdt3140.gr1835.app.json;

public interface JsonConverterService <T> {
	
	/*
	 * Klasser som implementerer dette grensesnittet skal kunne konvertere til og fra jasonobjekter
	 */
	
	public T convertToObject(String jasonFile);
	public String convertToJason(T objectToConvert);

}
