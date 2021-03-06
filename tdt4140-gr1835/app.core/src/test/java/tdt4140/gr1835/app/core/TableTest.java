package tdt4140.gr1835.app.core;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.control.Hyperlink;
import junit.framework.TestCase;

public class TableTest extends TestCase{
	
	private Table testTable;
	private Table testTableHyperLink;
	private Hyperlink studentID;
	@Before
	public void setUp() throws Exception {
		System.out.println("Setup");
		testTable=new Table(1,1,2,3,4,5,1,2,3,4,5,30);
		testTableHyperLink = new Table(studentID);	
	}

	@Test
	public void testNewTable() {
		System.out.println("testTable");
		assertFalse(testTable==null);
		assertEquals(1, testTable.getPersonID());
	}
	@Test 
	public void testNewTableHyperLink() {
		assertFalse(testTableHyperLink == null);
		assertEquals(studentID, testTableHyperLink.getStudentID());
	}
	
@Test
public void testRightValueConstructor() {
	System.out.println("testValue1");
	int expected = testTable.getSpm1();
	assertEquals(expected, 1);
	try {
		Table testTable2 = new Table(1,50,1,1,1,1,1,1,1,1,1,30);
		Table testTable3 = new Table(1,1,50,1,1,1,1,1,1,1,1,30);
		Table testTable4 = new Table(1,1,1,50,1,1,1,1,1,1,1,30);
		Table testTable5 = new Table(1,1,1,1,50,1,1,1,1,1,1,30);
		Table testTable6 = new Table(1,1,1,1,1,50,1,1,1,1,1,30);
		Table testTable7 = new Table(1,1,1,1,1,1,50,1,1,1,1,30);
		Table testTable8 = new Table(1,1,1,1,1,1,1,50,1,1,1,30);
		Table testTable9 = new Table(1,1,1,1,1,1,1,1,50,1,1,30);
		Table testTable10 = new Table(1,1,1,1,1,1,1,1,1,50,1,30);
		Table testTable11 = new Table(1,1,1,1,1,1,1,1,1,1,50,30);
		fail("Testen skulle ha feilet pga at et av tallene er større enn 5");
	}catch(IllegalArgumentException e) {
		assertTrue(e.getClass().equals(IllegalArgumentException.class));
	}	
}

@Test
public void testSum() {
	Table testTable2= new Table(1,1,2,3,4,5,1,2,3,4,5,30);
	int sum;
	int expected = testTable.getTotal();
	assertEquals(expected, testTable2.getTotal());
	try{
		sum = testTable2.getSpm1() + testTable2.getSpm2() + testTable2.getSpm3() + testTable2.getSpm4() + testTable2.getSpm5() + testTable2.getSpm6() + testTable2.getSpm7() + testTable2.getSpm8() + testTable2.getSpm9() + testTable2.getSpm10(); 
		if (testTable2.getTotal() != sum){
			fail("Testen skulle ha feilet fordi totalen er feil");
		}
		
	}
	catch(IllegalArgumentException e) {
		assertTrue(e.getClass().equals(IllegalArgumentException.class));
	}
}

	
	@After
	public void tearDown() {
		System.out.println("tearDown");
		testTable=null;
		testTableHyperLink = null;
	}
	

}
