/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.testSRS;

import static org.junit.Assert.assertNotNull;

import java.sql.Time;
import java.util.Date;

import org.junit.Test;

import ch.bfh.blue.srs.Mieter;
import ch.bfh.blue.srs.Person;
import ch.bfh.blue.srs.Reservation;
import ch.bfh.blue.srs.Teilnehmer;
import ch.bfh.blue.srs.UserData;

public class ReservationPersonTest {

	@Test
	public void testPersonHasReservation() {
		Mieter m1 = new Mieter(new UserData("max.muster@musterprovider.ch", "Max", "123"));
		Reservation r1 = new Reservation(new Date(), new Time(5), false);
		m1.addReservation(r1);
		assertNotNull(m1.getReservations());
	}

	@Test
	public void testReservationHasRenter(){
		Person m1 = new Mieter(new UserData("max.muster@musterprovider.ch", "Max", "123"));
		Reservation r1 = new Reservation(new Date(), new Time(5), false);
		r1.setRenter((Mieter) m1);
		assertNotNull(r1.getRenter());
	}

	@Test
	public void testReservationHasParticipants(){
		Teilnehmer t1 = new Teilnehmer(new UserData("max.muster@musterprovider.ch", "Max", "123"));
		Reservation r1 = new Reservation(new Date(), new Time(5), false);
		Mieter m = new Mieter(new UserData("moritz.muster@provider.ch", "moriz", "456"));
		m.addParticipants(r1, t1);
		assertNotNull(r1.getParticipants());
	}

	@Test
	public void testMieterHasParticipants(){
		Teilnehmer t1 = new Teilnehmer(new UserData("max.muster@musterprovider.ch", "Max", "123"));
		Reservation r1 = new Reservation(new Date(), new Time(5), false);
		Mieter m = new Mieter(new UserData("moritz.muster@provider.ch", "moriz", "456"));
		m.addParticipants(r1, t1);
		assertNotNull(m.getParticipants(r1));
	}

}