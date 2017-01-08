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

	/**
	 * persists the given person in the database
	 *
	 * @param data
	 * @return
	 */
	public abstract Person makePerson(UserData data);

	/**
	 * persists the given space in the database
	 *
	 * @param name
	 * @param spaceNumber
	 * @return
	 */
	public abstract Space makeSpace(String name, int spaceNumber);

	/**
	 * persists the given reservation in the database
	 *
	 * @param title
	 * @param p
	 * @param stStamp
	 * @param enStamp
	 * @param space
	 * @return
	 */
	public abstract Reservation makeReservation(String title, Person p, Timestamp stStamp, Timestamp enStamp,
			Space space);

	/**
	 * returns a list of all spaces from the database
	 *
	 * @return
	 */
	public abstract List<Space> getAllSpaces();

	/**
	 * returns a list of all persons from the database
	 *
	 * @return
	 */
	public abstract List<Person> getAllPersons();

	/**
	 * closes the persistence manager and the entity factory and cleans the
	 * instance
	 */
	public abstract void close();

	/**
	 * checks if the given data is existent in the database
	 *
	 * @param userName
	 * @param password
	 * @return
	 */
	public abstract Person makeLoginQuery(String userName, String password);

	/**
	 * returns a list of all the spaces that have no reservation for the given
	 * time frame
	 *
	 * @param stStamp
	 * @param endStamp
	 * @return
	 */
	public abstract List<Space> getFreeSpaces(Timestamp stStamp, Timestamp endStamp);

	/**
	 * if the instance is null, creates a new instance of
	 * AbstractPersistenceManager and returns it
	 *
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
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

	/**
	 * sets the instance to null
	 */
	public static void cleanInstance() {
		instance = null;
	}

	// only for JUnit Test
	public static void setSclass(String Sclass) {
		sClass = Sclass;
	}

}
