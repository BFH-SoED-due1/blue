package ch.bfh.blue.testSRS;

import java.sql.Time;
import java.util.Date;

import org.junit.Test;

import ch.bfh.blue.srs.Reservation;
import ch.bfh.blue.srs.Space;

public class ReservationSpaceTest {

	@Test
	public void testReservationHasSpace(){
		Space s1 = new Space("space1");
		Reservation r1 = new Reservation(new Date(), new Time(5), false);
		r1.addSpace(s1);
		// TODO Implement class Reservation such that following assertion holds:
		// assertNotNull(r1.getSpace());
	}

	@Test
	public void testSpaceIsBooked(){
		Space s1 = new Space("space1");
		Reservation r1 = new Reservation(new Date(), new Time(5), false);
		r1.addSpace(s1);
		// TODO Implement class Reservation such that following assertion holds:
		// assertTrue(s1.isBooked());
	}


}
