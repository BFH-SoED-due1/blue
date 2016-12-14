/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.requirements;

import java.sql.Timestamp;
import java.util.List;

public abstract class AbstractPersistencManager {

	private static AbstractPersistencManager instance;

	private static String sClass = "ch.bfh.blue.jpa.PersistenceManger";

	public abstract Person makePerson(UserData data);

	public abstract Space makeSpace(String name, int spaceNumber);

	public abstract Reservation makeReservation(Person p, Timestamp stStamp, Timestamp enStamp, Space space);

	public abstract List<Person> getPerson();

	public abstract void close();

	public abstract Person makeLoginQuery(String string, String string2);

	public static AbstractPersistencManager getInstance() throws InstantiationException, IllegalAccessException {
		if (instance == null) {
			try {
				instance = (AbstractPersistencManager) Class.forName(sClass).newInstance();
			} catch (ClassNotFoundException e) {
				System.err.println("Could not load class: " + sClass);
				throw new RuntimeException("Could not load class: " + sClass);
			}

		}

		return instance;
	}

	public static void cleanInstance(){
		instance = null;
	}


}
