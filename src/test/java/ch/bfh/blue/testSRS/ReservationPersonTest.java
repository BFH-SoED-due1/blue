/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.testSRS;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.bfh.blue.jpa.Mieter;
import ch.bfh.blue.jpa.ReservationImpl;
import ch.bfh.blue.jpa.SpaceImpl;
import ch.bfh.blue.jpa.UserDataImpl;
import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;
public class ReservationPersonTest {


	@Test
	public void testPersonHasReservation() {
		Mieter m1 = new Mieter(new UserDataImpl("max.muster@musterprovider.ch", "Max", "123"));
		Reservation r1 = new ReservationImpl(null, m1,null,null, new SpaceImpl("s1",1));
		m1.addReservation(r1);
		assertNotNull(m1.getReservations());
	}

	@Test
	public void testReservationHasRenter(){
		Person m1 = new Mieter(new UserDataImpl("max.muster@musterprovider.ch", "Max", "123"));
		Reservation r1 = new ReservationImpl(null, m1,null,null, new SpaceImpl("s1",1));
		r1.setOwner(m1);
		assertNotNull(r1.getOwner());
	}

	@Test
	public void testRemoveReservation(){
		Person p = new Mieter(new UserDataImpl("max.muster@musterprovider.ch", "Max", "123"));
		Reservation r1 = new ReservationImpl(null, p,null,null, new SpaceImpl("s1",1));
		p.addReservation(r1);
		p.removeReservation(r1);
		assertTrue(p.getReservations().isEmpty());
	}



}