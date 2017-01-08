/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.testSRS;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Test;

import ch.bfh.blue.jpa.Mieter;
import ch.bfh.blue.jpa.ReservationImpl;
import ch.bfh.blue.jpa.SpaceImpl;
import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;

public class ReservationTest {

	@Test
	public void testSetterAndGetter() {
		Reservation r = new ReservationImpl();
		int id = 123;
		Space s1 = new SpaceImpl();
		Person p = new Mieter();
		Timestamp stStamp = new Timestamp(1000);
		Timestamp enStamp = new Timestamp(2000);
		String title = "title";
		r.setStStamp(stStamp);
		r.setEnStamp(enStamp);
		r.setOwner(p);
		r.setSpace(s1);
		r.setTitle(title);
		r.setId(id);
		assertEquals(stStamp,r.getStStamp());
		assertEquals(enStamp,r.getEnStamp());
		assertEquals(p,r.getOwner());
		assertEquals(s1,r.getSpace());
		assertEquals(title,r.getTitle());
		assertEquals(id,r.getId());
	}

}
