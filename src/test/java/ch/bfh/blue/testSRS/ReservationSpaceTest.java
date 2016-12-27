/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.testSRS;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ch.bfh.blue.jpa.ReservationImpl;
import ch.bfh.blue.jpa.SpaceImpl;
import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;


public class ReservationSpaceTest {

	@Test
	public void testReservationHasSpace() {
		Space s1 = new SpaceImpl("space1",205);
		Reservation r1 = new ReservationImpl(null,null, null, s1);
		r1.setSpace(s1);
		assertNotNull(r1.getSpace());
	}



}
