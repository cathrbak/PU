package tdt4140.gr1835.app.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TableTest extends TestCase{
	
	private Table testTable;
	private Table testTableTom;

	@Before
	public void setUp() throws Exception {
		System.out.println("Setup");
		testTable=new Table(1,1,2,3,4,5,1,2,3,4,5,30);
		testTableTom = new Table(1);
	}

	@Test
	public void testNewTable() {
		System.out.println("testTable");
		assertFalse(testTable==null);
		assertEquals(1, testTable.getPersonID());
	}
	
	@Test
	public void testNewTomTable() {
		System.out.println("testTomTable");
		assertFalse(testTableTom==null);
		assertEquals(1, testTableTom.getStudentID());
	}
	
	@Test
	public void testRightValueEmptyConstructor() {
		System.out.println("TestRightValueEmptyConstructor");
		int expected = 1;
		assertEquals(expected, testTableTom.getStudentID());
		try {
			Table testTable2= new Table(0);
			fail("Testen skulle ha feilet pga at personID er mindre enn 1");
		}catch(IllegalArgumentException e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
		
	}
	@Test
	public void testRightValue() {
		System.out.println("testAllInt");
		Table testTable2= new Table(1,1,2,3,4,5,1,2,3,4,5,30);
		int expected = testTable.getSpm5();
		assertEquals(expected, testTable2.getSpm5());
		try {
			testTable2.setSpm5(50);
			fail("Testen skulle ha feilet pga at et av tallene er større enn 5");
		}catch(IllegalArgumentException e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
		
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
public void testSimpleIntegerProperty() {
	
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
		testTableTom = null;
	}

}
