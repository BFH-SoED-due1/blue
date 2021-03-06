/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.testSRS;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import ch.bfh.blue.jpa.UserDataImpl;
import ch.bfh.blue.requirements.AbstractPersistencManager;
import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;

public class PersistenceTest {

	@Test
	public void testMakePerson() throws InstantiationException, IllegalAccessException {
		AbstractPersistencManager pm = AbstractPersistencManager.getInstance();
		pm.makePerson(new UserDataImpl("max@muster", "max", "123"));
		assertNotNull(pm.getAllPersons());
		AbstractPersistencManager.cleanInstance();
		pm.close();
	}

	@Test
	public void testMakeSpace() throws InstantiationException, IllegalAccessException {
		AbstractPersistencManager pm = AbstractPersistencManager.getInstance();
		pm.makeSpace("S1", 522);
		assertNotNull(pm.getAllSpaces());
		AbstractPersistencManager.cleanInstance();
		pm.close();
	}

	@Test
	public void testGetPersonWithParameters() throws InstantiationException, IllegalAccessException {
		AbstractPersistencManager pm = AbstractPersistencManager.getInstance();
		pm.makePerson(new UserDataImpl("max@muster", "max", "123"));
		Person p = pm.makeLoginQuery("max", "123");
		assertNotNull(p);
		pm.close();
	}

	@Test
	public void testMakeTwoResvations() throws InstantiationException, IllegalAccessException {
	AbstractPersistencManager pm = AbstractPersistencManager.getInstance();
	pm.makePerson(new UserDataImpl("max@muster", "max", "123"));
	Person p = pm.makeLoginQuery("max", "123");
	Space s = pm.makeSpace("s1", 521);
	Reservation r1 = pm.makeReservation(null,p, new Timestamp(1000), new Timestamp(2000), s);
	Reservation r2 = pm.makeReservation(null,p, new Timestamp(3000), new Timestamp(4000), s);
	List<Reservation> rList = p.getReservations();
	assertEquals(r1, rList.get(0));
	assertEquals(r2, rList.get(1));
	pm.close();
	}


	@Test
	public void testGetSpaceWithEqualTimes() throws InstantiationException, IllegalAccessException {
		AbstractPersistencManager pm = AbstractPersistencManager.getInstance();
		String t = "title";
		Person p = pm.makePerson(new UserDataImpl("max@muster", "max", "123"));
		Space s1 = pm.makeSpace("S1", 521);
		Space s2 = pm.makeSpace("S2", 522);
		Space s3 = pm.makeSpace("S3", 523);
		Timestamp st = new Timestamp(new GregorianCalendar(2016, 1, 14, 12, 00).getTimeInMillis());
		Timestamp et = new Timestamp(new GregorianCalendar(2016, 1, 14, 14, 00).getTimeInMillis());
		pm.makeReservation(t, p, st, et, s1);
		List<Space> s = pm.getFreeSpaces(st, et);
		assertEquals(s2, s.get(0));
		assertEquals(s3, s.get(1));
		pm.close();
	}

	@Test
	public void testGetSpaceWithBetweenTimes() throws InstantiationException, IllegalAccessException {
		AbstractPersistencManager pm = AbstractPersistencManager.getInstance();
		String t = "title";
		Person p = pm.makePerson(new UserDataImpl("max@muster", "max", "123"));
		Space s1 = pm.makeSpace("S1", 521);
		Space s2 = pm.makeSpace("S2", 522);
		Space s3 = pm.makeSpace("S3", 523);
		pm.makeReservation(t, p, new Timestamp(1000), new Timestamp(2000), s1);
		List<Space> s = pm.getFreeSpaces(new Timestamp(1100), new Timestamp(1900));
		assertEquals(s2, s.get(0));
		assertEquals(s3, s.get(1));
		pm.close();
	}

	@Test
	public void testGetSpaceWithOverlapsTimes() throws InstantiationException, IllegalAccessException {
		AbstractPersistencManager pm = AbstractPersistencManager.getInstance();
		String t = "title";
		Person p = pm.makePerson(new UserDataImpl("max@muster", "max", "123"));
		Space s1 = pm.makeSpace("S1", 521);
		Space s2 = pm.makeSpace("S2", 522);
		Space s3 = pm.makeSpace("S3", 523);
		pm.makeReservation(t, p, new Timestamp(1000), new Timestamp(2000), s1);
		List<Space> s = pm.getFreeSpaces(new Timestamp(900), new Timestamp(2100));
		assertEquals(s2, s.get(0));
		assertEquals(s3, s.get(1));
		pm.close();
	}

	@Test
	public void testGetSpaceWithOneEqualTime() throws InstantiationException, IllegalAccessException {
		AbstractPersistencManager pm = AbstractPersistencManager.getInstance();
		String t = "title";
		Person p = pm.makePerson(new UserDataImpl("max@muster", "max", "123"));
		Space s1 = pm.makeSpace("S1", 521);
		Space s2 = pm.makeSpace("S2", 522);
		Space s3 = pm.makeSpace("S3", 523);
		pm.makeReservation(t, p, new Timestamp(1000), new Timestamp(2000), s1);
		List<Space> s = pm.getFreeSpaces(new Timestamp(1000), new Timestamp(2100));
		assertEquals(s2, s.get(0));
		assertEquals(s3, s.get(1));
		pm.close();
	}

	@Test
	public void testPersonHasRes() throws InstantiationException, IllegalAccessException {
		AbstractPersistencManager pm = AbstractPersistencManager.getInstance();
		String t = "title";
		Person p = pm.makePerson(new UserDataImpl("max@muster", "max", "123"));
		Space s1 = pm.makeSpace("S1", 521);
		Reservation r = pm.makeReservation(t, p, new Timestamp(1000), new Timestamp(2000), s1);
		List<Person> persons = pm.getAllPersons();
		List<Reservation> res = persons.get(0).getReservations();
		assertEquals(r, res.get(0));
		pm.close();
	}

	@Test
	public void testSpaceHasRes() throws InstantiationException, IllegalAccessException {
		AbstractPersistencManager pm = AbstractPersistencManager.getInstance();
		String t = "title";
		Person p = pm.makePerson(new UserDataImpl("max@muster", "max", "123"));
		Space s1 = pm.makeSpace("S1", 521);
		Reservation r = pm.makeReservation(t, p, new Timestamp(1000), new Timestamp(2000), s1);
		List<Space> spaces = pm.getAllSpaces();
		List<Reservation> res = spaces.get(0).getReservations();
		assertEquals(r, res.get(0));
		pm.close();
	}


}
