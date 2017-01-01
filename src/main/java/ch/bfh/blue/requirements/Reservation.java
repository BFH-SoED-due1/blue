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

	public void setSpace(Space s1);

	public Space getSpace();

	public void setOwner(Person p);

	public Person getOwner();
	
	public Timestamp getStStamp();

	public void setStStamp(Timestamp stStamp);
	
	public Timestamp getEnStamp();

	public void setEnStamp(Timestamp enStamp);
	
	public String getTitle();

	public void setTitle(String title) ;
}
