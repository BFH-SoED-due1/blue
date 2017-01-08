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


	public abstract String getName();

	public abstract int getSpaceNumber();

	public abstract List<Reservation> getReservations();

	public abstract void setSpaceNumber(int spaceNumber);

	public abstract void setName(String name);

	public abstract void setReservations(List<Reservation> res);

	public abstract void addReservation(Reservation res);

	public abstract void removeReservation(Reservation res);

}
