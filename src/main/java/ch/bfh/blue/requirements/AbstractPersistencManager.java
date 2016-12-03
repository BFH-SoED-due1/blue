/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.requirements;

import java.sql.Timestamp;

import ch.bfh.blue.jpa.UserData;

public abstract class AbstractPersistencManager  {

	public abstract Person makePerson(UserData data);

	public abstract Space makeSpace(String name,int spaceNumber);

	public abstract Reservation makeReservation(Person p,Timestamp stStamp,Timestamp enStamp,Space space);









}
