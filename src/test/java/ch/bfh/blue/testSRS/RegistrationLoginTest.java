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
		Registration.register(new UserData("max.muster@provider.ch", "max", "123"), true);
		assertNotNull(Registration.getRegisteredUsers());
	}

	@Test
	public void testLoginMieter() {
		Registration.register(new UserData("max.muster@provider.ch", "max", "123"), true);
		Mieter m = (Mieter) Registration.getPerson("max");
		assertTrue(m.login("max", "123"));
	}

	@Test
	public void testLoginTeilnehmer() {
		Registration.register(new UserData("max.muster@provider.ch", "max", "123"), false);
		Teilnehmer t = (Teilnehmer) Registration.getPerson("max");
		assertTrue(t.login("max", "123"));
	}

	@Test
	public void testWrongPassword() {
		Registration.register(new UserData("max.muster@provider.ch", "max", "123"), false);
		Teilnehmer t = (Teilnehmer) Registration.getPerson("max");
		assertFalse(t.login("max", "234"));
	}

	@Test
	public void testWrongUserName() {
		Registration.register(new UserData("max.muster@provider.ch", "max", "123"), false);
		Teilnehmer t = (Teilnehmer) Registration.getPerson("max");
		assertFalse(t.login("moriz", "123"));
	}


}
