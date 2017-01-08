/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.requirements;

import java.util.List;

public interface Person {

	public abstract void addReservation(Reservation r);

	public abstract void removeReservation(Reservation r);

	public abstract List<Reservation> getReservations();

	public abstract UserData getData();

	public abstract int getId();

	public abstract void setData(UserData data);

	public abstract void setId(int id);

	public abstract void setReservation(List<Reservation> reservations);

}
