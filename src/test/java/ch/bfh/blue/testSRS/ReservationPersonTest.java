/*
INFOS
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

public class ReservationPersonTest {

	@Test
	public void testPersonHasReservation() {
		Mieter m1 = new Mieter("Renter1", "Musterstrasse", new Date());
		Reservation r1 = new Reservation(new Date(), new Time(5), false);
		m1.addReservation(r1);
		assertNotNull(m1.getReservations());
	}

	@Test
	public void testReservationHasRenter(){
		Person m1 = new Mieter("Renter1", "Musterstrasse", new Date());
		Reservation r1 = new Reservation(new Date(), new Time(5), false);
		r1.setRenter((Mieter) m1);
		// TODO Implement class Reservation such that following assertion holds:
		// assertNotNull(r1.getRenter());
	}

	@Test
	public void testReservationHasParticipants(){
		Person t1 = new Teilnehmer("Teilnehmer1", "Musterstrasse", new Date());
		Reservation r1 = new Reservation(new Date(), new Time(5), false);
		r1.addParticipants(t1);
		// TODO Implement class Reservation such that following assertion holds:
		// assertNotNull(r1.getParticipants());
	}

}