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

import java.sql.Time;
import java.util.Date;

import org.junit.Test;

import ch.bfh.blue.srs.Reservation;
import ch.bfh.blue.srs.Space;

public class ReservationSpaceTest {

	@Test
	public void testReservationHasSpace() {
		Space s1 = new Space("space1");
		Reservation r1 = new Reservation(new Date(), new Time(5), false);
		r1.addSpace(s1);
		assertNotNull(r1.getSpaces());
	}

	@Test
	public void testSpaceIsBooked() {
		Space s1 = new Space("space1");
		Reservation r1 = new Reservation(new Date(), new Time(5), false);
		r1.addSpace(s1);
		assertTrue(s1.isBooked());
	}

}
