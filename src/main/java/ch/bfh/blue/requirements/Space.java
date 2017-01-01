/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.requirements;

import java.util.List;


public interface Space {
	public String getName();

	public List<Reservation> getReservations();

	public void addReservation(Reservation res);

	public void removeReservation(Reservation res);

}
