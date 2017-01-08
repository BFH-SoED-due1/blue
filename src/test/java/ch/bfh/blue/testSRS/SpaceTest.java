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

import ch.bfh.blue.jpa.ReservationImpl;
import ch.bfh.blue.jpa.SpaceImpl;
import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;

public class SpaceTest {

	@Test
	public void testSetterAndGetter() {
		Space s1 = new SpaceImpl();
		Reservation r = new ReservationImpl();
		int spaceNumber = 253;
		List<Reservation> rList = new ArrayList<>();
		rList.add(r);
		String name = "S1";
		s1.setName(name);
		s1.setSpaceNumber(spaceNumber);
		s1.setReservations(rList);
		assertEquals(name,s1.getName());
		assertEquals(spaceNumber,s1.getSpaceNumber());
		assertEquals(rList,s1.getReservations());
	}

	@Test
	public void testToString() {
		Space s1 = new SpaceImpl();
		String name = "S1";
		s1.setName(name);
		assertEquals(name,s1.toString());

	}

}
