package tdt4140.gr1835.app.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NurseTest {
	
	Nurse testnurse;

	@Before
	public void setUp() throws Exception {
		testnurse = new Nurse("sverre");
	}

	@After
	public void tearDown() throws Exception {
		testnurse=null;
	}

	@Test
	public void test() {
		testnurse.setNurseID(1);
		assertEquals(1, testnurse.getNurseID());
		try {
			testnurse.setNurseID(-12);
			fail("Burde kastet exception etter at vi ");
		}catch (IllegalArgumentException e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
	}

}
