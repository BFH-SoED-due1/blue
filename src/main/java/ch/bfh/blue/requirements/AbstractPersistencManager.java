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

	public abstract Reservation makeReservation(String title, Person p, Timestamp stStamp, Timestamp enStamp, Space space);

	public abstract List<Space> getAllSpaces();

	public abstract List<Person> getAllPersons();

	public abstract void close();

	public abstract Person makeLoginQuery(String string, String string2);

	public abstract List<Space> getFreeSpaces(Timestamp stStamp,Timestamp endStamp);

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

	// only for JUnit Test
	public static void setSclass(String Sclass){
		sClass = Sclass;
	}

}
