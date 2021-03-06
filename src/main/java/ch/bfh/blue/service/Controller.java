/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import ch.bfh.blue.jpa.UserDataImpl;
import ch.bfh.blue.requirements.AbstractPersistencManager;
import ch.bfh.blue.requirements.Person;
import ch.bfh.blue.requirements.Reservation;
import ch.bfh.blue.requirements.Space;
import ch.bfh.blue.requirements.UserData;



public class Controller {
	private AbstractPersistencManager pm;

	public Controller() throws InstantiationException, IllegalAccessException {
		pm = AbstractPersistencManager.getInstance();
	}

	/**
	 * checks the login data of the user
	 *
	 * @param user
	 * @param pw
	 * @return
	 */
	public Person authentication(String user, String pw) {
		return pm.makeLoginQuery(user, pw);
	}

	/**
	 * returns a list of all spaces that are free in the selected timeframe
	 *
	 * @param stDate
	 * @param enDate
	 * @return
	 */
	public List<Space> getSpaceOnTime(Date stDate, Date enDate) {
		Timestamp startStamp = new Timestamp(stDate.getTime());
		Timestamp endStamp = new Timestamp(enDate.getTime());
		return pm.getFreeSpaces(startStamp, endStamp);
	}

	/**
	 * checks for a space if it is free for a certain timeframe
	 *
	 * @param stDate
	 * @param enDate
	 * @param s
	 * @return
	 */
	public boolean isFree(Date stDate, Date enDate, Space s) {
		List<Space> spaces = pm.getFreeSpaces(new Timestamp(stDate.getTime()), new Timestamp(enDate.getTime()));
		return spaces.contains(s);
	}

	/**
	 * creates a reservation in the database
	 *
	 * @param titles
	 * @param p
	 * @param stDate
	 * @param enDate
	 * @param space
	 * @return
	 */
	public Reservation createReservation(String titles, Person p, Date stDate, Date enDate, Space space) {
		return pm.makeReservation(titles, p, new Timestamp(stDate.getTime()), new Timestamp(enDate.getTime()), space);
	}

	/**
	 * creates a space in the database
	 *
	 * @param name
	 * @param spaceNumber
	 * @return
	 */
	public Space createSpace(String name, int spaceNumber) {
		return pm.makeSpace(name, spaceNumber);
	}

	/**
	 * creates a person in the database
	 *
	 * @param email
	 * @param user
	 * @param pw
	 * @return
	 */
	public Person createPerson(String email, String user, String pw) {
		UserData data = new UserDataImpl(email, user, pw);
		return pm.makePerson(data);
	}

	/**
	 * returns a list of all spaces
	 *
	 * @return
	 */
	public List<Space> getAllspaces() {
		return pm.getAllSpaces();
	}

	/**
	 * calls the close function of the persistence manager
	 */
	public void close() {
		pm.close();
	}

}
