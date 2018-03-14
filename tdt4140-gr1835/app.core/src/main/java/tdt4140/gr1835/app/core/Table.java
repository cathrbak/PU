package tdt4140.gr1835.app.core;

import javafx.beans.property.SimpleIntegerProperty;

//dette er en klasse som behandler svarene til en person, personen identifiseres ved PersonID

public class Table  {
	private SimpleIntegerProperty PersonID;
	private SimpleIntegerProperty Spm1;
	private SimpleIntegerProperty Spm2;
	private SimpleIntegerProperty Spm3;
	private SimpleIntegerProperty Spm4;
	private SimpleIntegerProperty Spm5;
	private SimpleIntegerProperty Spm6;
	private SimpleIntegerProperty Spm7;
	private SimpleIntegerProperty Spm8;
	private SimpleIntegerProperty Spm9;
	private SimpleIntegerProperty Spm10;
	private final SimpleIntegerProperty Total;
	
	public Table(int PersonID) {
		this.PersonID = new SimpleIntegerProperty(PersonID);
		this.Total = new SimpleIntegerProperty(0);
	}
	
	public Table(int PersonID, int Spm1, int Spm2,int Spm3,int Spm4,int Spm5,int Spm6,int Spm7,int Spm8,int Spm9,int Spm10,int Total) {
		super();
		this.PersonID = new SimpleIntegerProperty(PersonID);
		if (!((0 < Spm1 && Spm1 < 6) && (0 < Spm2 && Spm2 < 6) && (0 < Spm3 && Spm3 < 6) && (0 < Spm4 && Spm4 < 6) && (0 < Spm5 && Spm5 < 6) && (0 < Spm6 && Spm6 < 6) && (0 < Spm7 && Spm7 < 6) && (0 < Spm8 && Spm8 < 6) && (0 < Spm9 && Spm9 < 6) && (0 < Spm10 && Spm10 < 6))) {
			throw new IllegalArgumentException("Svarene skal være et tall mellom 1 og 5");
		}
		else {
			this.Spm1 = new SimpleIntegerProperty(Spm1);
			this.Spm2 = new SimpleIntegerProperty(Spm2);
			this.Spm3 = new SimpleIntegerProperty(Spm3);
			this.Spm4 = new SimpleIntegerProperty(Spm4);
			this.Spm5 = new SimpleIntegerProperty(Spm5);
			this.Spm6 = new SimpleIntegerProperty(Spm6);
			this.Spm7 = new SimpleIntegerProperty(Spm7);
			this.Spm8 = new SimpleIntegerProperty(Spm8);
			this.Spm9 = new SimpleIntegerProperty(Spm9);
			this.Spm10 = new SimpleIntegerProperty(Spm10);
			this.Total = new SimpleIntegerProperty(Total);
		}
	}
//har laget denne metoden for å få testet dette, usikker på om denne variabelen skal være final eller ikke 
	public void setSpm5(int spm5) {
		if( 0 > spm5 || spm5 > 5){
			throw new IllegalArgumentException("Svaret må være mellom 1 og 5");
		}
		this.Spm5 = new SimpleIntegerProperty(spm5);
	}
	public int getSpm2() {
		return Spm2.get();
	}

	public int getSpm3() {
		return Spm3.get();
	}

	public int getSpm4() {
		return Spm4.get();
	}

	public int getSpm5() {
		return Spm5.get();
	}

	public int getSpm6() {
		return Spm6.get();
	}

	public int getSpm7() {
		return Spm7.get();
	}

	public int getSpm8() {
		return Spm8.get();
	}

	public int getSpm9() {
		return Spm9.get();
	}

	public int getSpm10() {
		return Spm10.get();
	}

	public int getTotal() {
		return Total.get();
	}

	public int getPersonID() {
		return PersonID.get();
	}

	public int getSpm1() {
		return Spm1.get();
	}

	@Override
	public String toString() {
		return "Table [PersonID=" + PersonID + ", Spm1=" + Spm1 + ", Spm2=" + Spm2 + ", Spm3=" + Spm3 + ", Spm4=" + Spm4
				+ ", Spm5=" + Spm5 + ", Spm6=" + Spm6 + ", Spm7=" + Spm7 + ", Spm8=" + Spm8 + ", Spm9=" + Spm9
				+ ", Spm10=" + Spm10 + ", Total=" + Total + "]";
	}
	
	
	

}
