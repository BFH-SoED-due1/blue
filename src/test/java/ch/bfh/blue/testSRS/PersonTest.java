/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.testSRS;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ch.bfh.blue.jpa.Mieter;
import ch.bfh.blue.jpa.ReservationImpl;
import ch.bfh.blue.jpa.UserDataImpl;
import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;

public class PersonTest {

	@Test
	public void testSetterAndGetters() {
		UserDataImpl data = new UserDataImpl("muster@muster.ch","max","123");
		Person p = new Mieter();
		Reservation r = new ReservationImpl();
		int id = 132;
		List<Reservation> rList = new ArrayList<>();
		rList.add(r);
		p.setReservation(rList);
		p.setId(id);
		p.setData(data);
		assertEquals(rList, p.getReservations());
		assertEquals(id, p.getId());
		assertEquals(data, p.getData());
	}

	@Test
	public void testToString() {
		UserDataImpl data = new UserDataImpl("muster@muster.ch","max","123");
		Person p = new Mieter();
		p.setData(data);
		assertEquals(data.getUserName(), p.toString());
	}

}
