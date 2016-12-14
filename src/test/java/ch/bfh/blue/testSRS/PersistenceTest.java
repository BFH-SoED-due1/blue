package ch.bfh.blue.testSRS;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ch.bfh.blue.jpa.UserDataImpl;
import ch.bfh.blue.requirements.AbstractPersistencManager;
import ch.bfh.blue.requirements.Person;

public class PersistenceTest {

	@Test
	public void testMakePerson() throws InstantiationException, IllegalAccessException {
	AbstractPersistencManager pm = AbstractPersistencManager.getInstance();
	pm.makePerson(new UserDataImpl("max@muster", "max", "123"));
	assertNotNull(pm.getPerson());
	AbstractPersistencManager.cleanInstance();
	pm.close();
	}

	@Test
	public void testGetPersonWithParameters() throws InstantiationException, IllegalAccessException {
	AbstractPersistencManager pm = AbstractPersistencManager.getInstance();
	pm.makePerson(new UserDataImpl("max@muster", "max", "123"));
	Person p = pm.makeLoginQuery("max", "123");
	assertNotNull(p);
	AbstractPersistencManager.cleanInstance();
	pm.close();
	}

}
