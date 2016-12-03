/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.requirements;

import java.util.Set;

public interface Space {
	public String getName();

	public Set<Reservation> getReservations();

	public void addReservation(Reservation res);

	public void removeReservation(Reservation res);

	public void booking();

	boolean isBooked();


}
