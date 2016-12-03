/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.requirements;

import java.util.Set;

public interface Person {

	public abstract void addReservation(Reservation r);

	public abstract void removeReservation(Reservation r);

	public abstract Set<Reservation> getReservations();

	public abstract void setReservation(Set<Reservation> reservations);

}
