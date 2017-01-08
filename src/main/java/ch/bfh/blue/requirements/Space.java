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


	/**
	 * returns the name of this space
	 *
	 * @return
	 */
	public abstract String getName();

	/**
	 * returns the number of this space
	 *
	 * @return
	 */
	public abstract int getSpaceNumber();

	/**
	 * returns a list of all reservations of this space
	 *
	 * @return
	 */
	public abstract List<Reservation> getReservations();

	/**
	 * sets the number for this space
	 *
	 * @param spaceNumber
	 */
	public abstract void setSpaceNumber(int spaceNumber);

	/**
	 * sets the name for this space
	 *
	 * @param name
	 */
	public abstract void setName(String name);

	/**
	 * adds a list of reservations for this space
	 *
	 * @param res
	 */
	public abstract void setReservations(List<Reservation> res);

	/**
	 * adds a single reservation for this space
	 *
	 * @param res
	 */
	public abstract void addReservation(Reservation res);

	/**
	 * removes the given reservation from this space
	 *
	 * @param res
	 */
	public abstract void removeReservation(Reservation res);

}
