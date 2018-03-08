package tdt4140.gr1835.app.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TableTest extends TestCase{
	
	private Table testTable;

	@Before
	public void setUp() throws Exception {
		System.out.println("Setup");
		testTable=new Table(1,1,2,3,4,5,1,2,3,4,5,30);
	}

	@Test
	public void testNewTable() {
		System.out.println("testTable");
		assertFalse(testTable==null);
		assertEquals(1, testTable.getPersonID());
	}
	
	@Test
	public void testRightValue() {
		System.out.println("testAllInt");
		Table testTable2= new Table(1,1,2,3,4,5,1,2,3,4,5,30);
		int expected = testTable.getSpm5();
		assertEquals(expected, testTable2.getSpm5());
		/*try {
			testTable2.setSpm5();
			fail("Testen skulle ha feilet fordi ett av svarene ikke er en Integer");
		}catch(IllegalArgumentException e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}*/
		try {
			//Table testTable2= new Table(1,1,2,3,4,50,1,2,3,4,5,30);
			testTable2.setSpm5(50);
			fail("Testen skulle ha feilet pga at et av tallene er større enn 5");
		}catch(IllegalArgumentException e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
		
	}
	
	@After
	public void tearDown() {
		System.out.println("tearDown");
		testTable=null;
	}

}
