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

	/**
	 * links a reservation to this person
	 * 
	 * @param r
	 */
	public abstract void addReservation(Reservation r);

	/**
	 * removes the chosen reservations belonging to this person
	 * 
	 * @param r
	 */
	public abstract void removeReservation(Reservation r);

	/**
	 * returns all reservations of this person
	 * 
	 * @return
	 */
	public abstract List<Reservation> getReservations();

	/**
	 * returns all data of this person
	 * 
	 * @return
	 */
	public abstract UserData getData();

	/**
	 * returns the id of this person
	 * 
	 * @return
	 */
	public abstract int getId();

	/**
	 * sets the userData of this person to the given userData
	 * 
	 * @param data
	 */
	public abstract void setData(UserData data);

	/**
	 * sets the id of this person to the given id
	 * 
	 * @param id
	 */
	public abstract void setId(int id);

	/**
	 * links a list of reservations to this person
	 * 
	 * @param reservations
	 */
	public abstract void setReservation(List<Reservation> reservations);

}
