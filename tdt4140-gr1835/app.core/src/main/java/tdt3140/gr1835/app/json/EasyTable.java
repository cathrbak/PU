package tdt3140.gr1835.app.json;

import java.util.List;

public class EasyTable {
	
	private List<Integer> ansList;
	private int total;
	private int studentID;
	
	public EasyTable(List<Integer> ansList, int total, int studentID) {
		this.ansList = ansList;
		this.total = total;
		this.studentID = studentID;
	}
	
	public EasyTable() {
		
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
