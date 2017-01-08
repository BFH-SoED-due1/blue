/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.requirements;

import java.sql.Timestamp;

public interface Reservation {

	/**
	 * returns the space belonging to this reservation
	 * 
	 * @return
	 */
	public abstract Space getSpace();

	/**
	 * returns the owner belonging to this reservation
	 * 
	 * @return
	 */
	public abstract Person getOwner();

	/**
	 * returns the start time belonging to this reservation
	 * 
	 * @return
	 */
	public abstract Timestamp getStStamp();

	/**
	 * returns the end time belonging to this reservation
	 * 
	 * @return
	 */
	public abstract Timestamp getEnStamp();

	/**
	 * returns the title belonging to this reservation
	 * 
	 * @return
	 */
	public abstract String getTitle();

	/**
	 * returns the id belonging to this reservation
	 * 
	 * @return
	 */
	public abstract int getId();

	/**
	 * sets the id for this reservation
	 * 
	 * @param id
	 */
	public abstract void setId(int id);

	/**
	 * sets the owner for this reservation
	 * 
	 * @param p
	 */
	public abstract void setOwner(Person p);

	/**
	 * sets the space for this reservation
	 * 
	 * @param s1
	 */
	public abstract void setSpace(Space s1);

	/**
	 * sets the start time for this reservation
	 * 
	 * @param stStamp
	 */
	public abstract void setStStamp(Timestamp stStamp);

	/**
	 * sets the end time for this reservation
	 * 
	 * @param enStamp
	 */
	public abstract void setEnStamp(Timestamp enStamp);

	/**
	 * sets the title for this reservation
	 * 
	 * @param title
	 */
	public abstract void setTitle(String title);
}
