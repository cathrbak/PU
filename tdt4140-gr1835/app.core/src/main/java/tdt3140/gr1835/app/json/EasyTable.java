package tdt3140.gr1835.app.json;

import java.util.List;

public class EasyTable {
	
	private List<Integer> ansList;
	private int total;
	private int studentID;
	private long millis;
	
	public EasyTable(List<Integer> ansList, int total, int studentID,long millis) {
		this.ansList = ansList;
		this.total = total;
		this.studentID = studentID;
		this.millis = millis;
	}
	
	public EasyTable() {
		
	}
	
	public long getMillis() {
		return millis;
	}
	
	public void setMillis(long millis) {
		this.millis = millis;
	}
	
	
	public List<Integer> getAnsList() {
		return ansList;
	}

	public void setAnsList(List<Integer> ansList) {
		this.ansList = ansList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}


	
	

}
