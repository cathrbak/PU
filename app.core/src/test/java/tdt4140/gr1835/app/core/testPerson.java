package tdt4140.gr1835.app.core;

public class testPerson extends junit.framework.TestCase{
	
	private Person person;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		person= new Person("Sverre",22);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		person=null;
	}
	
	public void testSetName() {
		person.setName("Jonas");
		assertEquals("Jonas", person.getName());
	}
}
