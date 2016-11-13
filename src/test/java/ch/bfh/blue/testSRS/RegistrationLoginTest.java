package ch.bfh.blue.testSRS;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.bfh.blue.srs.Mieter;
import ch.bfh.blue.srs.Registration;
import ch.bfh.blue.srs.Teilnehmer;
import ch.bfh.blue.srs.UserData;

public class RegistrationLoginTest {


	@Test
	public void testRegistration() {
		Registration r = new Registration();
		r.register(new UserData("max.muster@provider.ch", "max", "123"), true);
		assertNotNull(r.getRegisteredUsers());
	}

	@Test
	public void testLoginMieter() {
		Registration r = new Registration();
		r.register(new UserData("max.muster@provider.ch", "max", "123"), true);
		Mieter m = (Mieter) r.getPerson("max");
		assertTrue(r.login("max", "123",m));
	}

	@Test
	public void testLoginTeilnehmer() {
		Registration r = new Registration();
		r.register(new UserData("max.muster@provider.ch", "max", "123"), false);
		Teilnehmer t = (Teilnehmer) r.getPerson("max");
		assertTrue(r.login("max", "123",t));
	}

	@Test
	public void testWrongPassword() {
		Registration r = new Registration();
		r.register(new UserData("max.muster@provider.ch", "max", "123"), false);
		Teilnehmer t = (Teilnehmer) r.getPerson("max");
		assertFalse(r.login("max", "234",t));
	}

	@Test
	public void testWrongUserName() {
		Registration r = new Registration();
		r.register(new UserData("max.muster@provider.ch", "max", "123"), false);
		Teilnehmer t = (Teilnehmer) r.getPerson("max");
		assertFalse(r.login("moriz", "123",t));
	}


}
