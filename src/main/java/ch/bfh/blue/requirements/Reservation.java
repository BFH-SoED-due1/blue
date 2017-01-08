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

	public abstract Space getSpace();

	public abstract Person getOwner();

	public abstract Timestamp getStStamp();

	public abstract Timestamp getEnStamp();

	public abstract String getTitle();

	public abstract int getId();

	public abstract void setId(int id);

	public abstract void setOwner(Person p);

	public abstract void setSpace(Space s1);

	public abstract void setStStamp(Timestamp stStamp);

	public abstract void setEnStamp(Timestamp enStamp);

	public abstract void setTitle(String title) ;
}
