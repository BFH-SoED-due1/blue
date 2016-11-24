/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.blue.requirements;

import ch.bfh.blue.srs.UserData;

public abstract class Person {
	private UserData data;

	public Person(UserData data) {
		this.data = data;
	}

	public UserData getData() {
		return this.data;
	}

}
