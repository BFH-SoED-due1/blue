package ch.bfh.blue.testSRS;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.bfh.blue.jpa.Mieter;
import ch.bfh.blue.jpa.UserDataImpl;
import ch.bfh.blue.service.Registration;

public class RegistrationLoginTest {


	@Test
	public void testRegistration() {
		Registration r = new Registration();
		r.register(new UserDataImpl("max.muster@provider.ch", "max", "123"));
		assertNotNull(r.getRegisteredUsers());
	}

	@Test
	public void testLoginMieter() {
		Registration r = new Registration();
		r.register(new UserDataImpl("max.muster@provider.ch", "max", "123"));
		Mieter m = r.getMieter("max");
		assertTrue(r.login("max", "123",m));
	}

	@Test
	public void testWrongPassword() {
		Registration r = new Registration();
		r.register(new UserDataImpl("max.muster@provider.ch", "max", "123"));
		Mieter m = r.getMieter("max");
		assertFalse(r.login("max", "234",m));
	}

	@Test
	public void testWrongUserName() {
		Registration r = new Registration();
		r.register(new UserDataImpl("max.muster@provider.ch", "max", "123"));
		Mieter m = r.getMieter("max");
		assertFalse(r.login("moriz", "123",m));
	}


}
